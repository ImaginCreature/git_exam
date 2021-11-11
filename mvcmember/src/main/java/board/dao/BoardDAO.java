package board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import board.bean.BoardDTO;

public class BoardDAO {
	
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	private DataSource ds;
	
	private static BoardDAO instance;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
	
	public static BoardDAO getInstance() {
		if(instance == null)
		{
			synchronized (BoardDAO.class)
			{
				instance = new BoardDAO();
			}
		}
		
		return instance;
	}
	
	public BoardDAO() {
		
		Context ctx;
		
		try {
			ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/oracle");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void boardWrite(Map<String, String> map) {
		
		String sql = "insert into board(seq, id, name, email, subject, content, ref)"
				+ "values(seq_board.nextval,?,?,?,?,?,seq_board.currval)";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			
//			pstmt.setString(1, boardDTO.getName());
//			pstmt.setString(2, boardDTO.getId());
//			pstmt.setString(3, boardDTO.getEmail());
//			pstmt.setString(4, boardDTO.getSubject());
//			pstmt.setString(5, boardDTO.getContent());
			
			pstmt.setString(1, map.get("id"));
			pstmt.setString(2, map.get("name"));
			pstmt.setString(3, map.get("email"));
			pstmt.setString(4, map.get("subject"));
			pstmt.setString(5, map.get("content"));
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally { unConnection(); }
		
	}
	
	public List<BoardDTO> print(int startNum, int endNum) {
		
		List<BoardDTO> boardList = new ArrayList<BoardDTO>();
		String sql = "select * "
				+ "from(select rownum rn, tt.* "
				+ "     from(select * from board order by ref desc, step asc) tt) "
				+ "where rn >= ? and rn <= ?";
		
		try {
			conn = ds.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startNum);
			pstmt.setInt(2, endNum);
			
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				BoardDTO boardDTO = new BoardDTO();
				boardDTO.setSeq(rs.getInt("seq"));
				boardDTO.setId(rs.getString("id"));
				boardDTO.setName(rs.getString("name"));
				boardDTO.setEmail(rs.getString("email"));
				boardDTO.setSubject(rs.getString("subject"));
				boardDTO.setContent(rs.getString("content"));
				boardDTO.setRef(rs.getInt("ref"));
				boardDTO.setLev(rs.getInt("lev"));
				boardDTO.setStep(rs.getInt("step"));
				boardDTO.setPseq(rs.getInt("pseq"));
				boardDTO.setReply(rs.getInt("reply"));
				boardDTO.setHit(rs.getInt("hit"));
				boardDTO.setLogtime(sdf.format(rs.getDate("logtime")));
				
				boardList.add(boardDTO); // list 에 boardDTO 를 담아라.
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			boardList = null;
		}finally { unConnection(); }
		
		return boardList;
	}
	
	public int totalA() {
		
		int total = 0;
		
		String sql = "select count(*) as total from board";
		
		try {
			conn = ds.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) total = rs.getInt("total");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally { unConnection(); }
		
		return total;
	}
	
	public BoardDTO content(int seq) {
		
		String sql = "select * from board where seq = ?";
		
		BoardDTO boardDTO = new BoardDTO();
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seq);
			
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				boardDTO.setSeq(rs.getInt("seq"));
				boardDTO.setId(rs.getString("id"));
				boardDTO.setName(rs.getString("name"));
				boardDTO.setEmail(rs.getString("email"));
				boardDTO.setSubject(rs.getString("subject"));
				boardDTO.setContent(rs.getString("content"));
				boardDTO.setRef(rs.getInt("ref"));
				boardDTO.setLev(rs.getInt("lev"));
				boardDTO.setStep(rs.getInt("step"));
				boardDTO.setPseq(rs.getInt("pseq"));
				boardDTO.setReply(rs.getInt("reply"));
				boardDTO.setHit(rs.getInt("hit"));
				boardDTO.setLogtime(sdf.format(rs.getDate("logtime")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally { unConnection(); }
		
		return boardDTO;
	}
	
	public void hit(int seq) {
		String sql = "update board set hit = hit + 1 where seq = ?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seq);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally { unConnection(); }
	}
	
	public void unConnection() {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public int getTotalA() {
		int totalA=0;
		String sql = "select count(*) from board";
	    

	    
	    try {
	      conn =ds.getConnection();
	      pstmt = conn.prepareStatement(sql); // 생성
	      rs = pstmt.executeQuery();// 실행 - 개수 리턴
	      
	      rs.next();
	      totalA = rs.getInt(1);
   
	    } catch (SQLException e) {
	      e.printStackTrace();
	    } finally {
	      try {
	      if (rs != null) rs.close();
	    	  if (pstmt != null) pstmt.close();
	        if (conn != null) conn.close();
	      } catch (SQLException e) {
	        e.printStackTrace();
	      }
	    }
	
		return totalA;
	} 

	public List<BoardDTO> boardList(int startNum, int endNum) {
		// TODO Auto-generated method stub
		return null;
	}
}
