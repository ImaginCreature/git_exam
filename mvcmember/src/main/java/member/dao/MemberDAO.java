package member.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import member.bean.MemberDTO;
import member.bean.ZipcodeDTO;

public class MemberDAO {
	private Connection conn=null;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private DataSource ds;

	private static MemberDAO instance = null; // 싱글톤. static 이란 한 번 만들면 소멸되지 않는다.
	//				쿨래스명		객체명   = null 로 초기화되는 시점은 한 번 뿐이다. 
	
	public static MemberDAO getInstance() {
	// Instance 란 메모리 생성을 의미한다.
		if(instance == null) {
		// instance 는 static 이기 때문에 null로 초기화된 경후는 1번 밖에 없다. 
			synchronized (MemberDAO.class) {
				instance = new MemberDAO(); //생성
			}
		}
		
		return instance;
	}
	
	
	//driver loading
	public MemberDAO() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/oracle"); //Tomcat의 경우 앞에 java:comp/env/라는 접두어를 꼭 붙여줘야한다.
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	//Connection
	public void write(MemberDTO memberDTO) {
		String sql = "insert into member values(?,?,?,?,?,?,?,?,?,?,?,?, sysdate)";
		
	
		try {
			conn = ds.getConnection();
			
			pstmt = conn.prepareStatement(sql); // 생성
			pstmt.setString(1, memberDTO.getName());
			pstmt.setString(2, memberDTO.getId());
			pstmt.setString(3, memberDTO.getPwd());
			pstmt.setString(4, memberDTO.getGender());
			pstmt.setString(5, memberDTO.getEmail1());
			pstmt.setString(6, memberDTO.getEmail2());
			pstmt.setString(7, memberDTO.getTel1());
			pstmt.setString(8, memberDTO.getTel2());
			pstmt.setString(9, memberDTO.getTel3());
			pstmt.setString(10, memberDTO.getZipcode());
			pstmt.setString(11, memberDTO.getAddr1());
			pstmt.setString(12, memberDTO.getAddr2());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null)
				try {
					pstmt.close();
					if(conn != null) conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}


	public MemberDTO login(String id, String pwd) {
		MemberDTO memberDTO = null;
		String sql = "select * from member where id=? and pwd=?";
				
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,id);
			pstmt.setString(2,pwd);
			
			rs = pstmt.executeQuery(); // 실행
			
			if(rs.next()) {
				memberDTO = new MemberDTO();
				memberDTO.setName(rs.getString("name"));
				memberDTO.setId(rs.getString("id"));
				memberDTO.setPwd(rs.getString("pwd"));
				memberDTO.setGender(rs.getString("gender"));
				memberDTO.setEmail1(rs.getString("email1"));
				memberDTO.setEmail2(rs.getString("email2"));
				memberDTO.setTel1(rs.getString("tel1"));
				memberDTO.setTel2(rs.getString("tel2"));
				memberDTO.setTel3(rs.getString("tel3"));
				memberDTO.setZipcode(rs.getString("zipcode"));
				memberDTO.setAddr1(rs.getString("addr1"));
				memberDTO.setAddr2(rs.getString("addr2"));
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
				try {
					if(rs != null) rs.close();
					if(pstmt != null) pstmt.close();
					if(conn != null) conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
		return memberDTO;
	}


	public boolean isCheckId(String id) {
		boolean exist = false;
		String sql = "select * from member where id=?";

		
				
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,id);
			
			rs = pstmt.executeQuery(); // 실행
			
			if(rs.next()) exist = true; // true exist니까 내가 쓰려는 아이디가 이미 존재한다는 것이다. 따라서 중복 아이디라 사용 불가능하다.
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
				try {
					if(rs != null) rs.close();
					if(pstmt != null) pstmt.close();
					if(conn != null) conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
		return exist;
	}


	public List<ZipcodeDTO> getZipcodeList(String sido, String sigungu, String roadname) {
		List<ZipcodeDTO> list = new ArrayList<ZipcodeDTO>();
		
		String sql="select * from newzipcode where sido like ? and sigungu like ? and roadname like ?";
		
		try {
			conn = ds.getConnection(); // 클라이언트는 Connection pool 에 접근권한이 없으므로 ds. 를 통해 연결한다.
			
			pstmt = conn.prepareStatement(sql); // 커넥션 생성
			pstmt.setString(1, "%"+sido+"%"); // 물음표 세 개 중 첫 번째 것이므로 1번.
			pstmt.setString(2, "%"+sigungu+"%");
			pstmt.setString(3, "%"+roadname+"%");
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ZipcodeDTO zipcodeDTO = new ZipcodeDTO();
				zipcodeDTO.setZipcode(rs.getString("zipcode"));
				zipcodeDTO.setSido(rs.getString("sido"));
				zipcodeDTO.setSigungu(rs.getString("sigungu"));
				zipcodeDTO.setYubmyundong(rs.getString("yubmyundong"));
				zipcodeDTO.setRi(rs.getString("ri"));
				zipcodeDTO.setRoadname(rs.getString("roadname"));
				zipcodeDTO.setBuildingname(rs.getString("buildingname"));
								
				list.add(zipcodeDTO);
			} // while문
			
		} catch (SQLException e) {
			e.printStackTrace();
			list = null;
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}

	
}
