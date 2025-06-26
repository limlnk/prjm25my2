package com.tech.prjm09.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.tech.prjm09.dto.BDto;
import com.tech.prjm09.util.DBCon;

public class BDao {
	Connection conn=null;
	public ArrayList<BDto> list() {
		ArrayList<BDto> dtos=new ArrayList<>();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			conn=DBCon.getConnection();
			String sql="select bid,bname,btitle,bcontent,"
					+ "bdate,bhit,bgroup,bstep,bindent "
					+ "from replyboard "
					+ "order by bgroup desc,bstep asc";
			pstmt=conn.prepareStatement(sql);
			
			rs=pstmt.executeQuery();
			while (rs.next()) {
				int bid=rs.getInt("bid");
				String bname=rs.getString("bname");
				String btitle=rs.getString("btitle");
				String bcontent=rs.getString("bcontent");
				Timestamp bdate=rs.getTimestamp("bdate");
				int bhit=rs.getInt("bhit");
				int bgroup=rs.getInt("bgroup");
				int bstep=rs.getInt("bstep");
				int bindent=rs.getInt("bindent");
				BDto dto=new BDto(bid, bname, btitle,
						bcontent, bdate, bhit, bgroup,
						bstep, bindent);
				dtos.add(dto);
			}		
		} catch (Exception e) {
			// TODO: handle exception
		}
		return dtos;
	}
	public void write(String bname, String btitle,
			String bcontent) {
		
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		try {
			conn=DBCon.getConnection();
			String query="insert into replyboard(bid,bname,btitle,"
					+ "bcontent,bdate,bhit,bgroup,bstep,bindent) "
					+ "values(replyboard_seq.nextval,?,?,"
					+ "?,sysdate,0,replyboard_seq.currval,0,0)";
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, bname);
			pstmt.setString(2, btitle);
			pstmt.setString(3, bcontent);
			
			int rn=pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null) {pstmt.close();}
				if(conn!=null) {conn.close();}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
		
	}
	public BDto contentView(String sbid) {
		upHit(sbid);
		
		BDto dto=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=DBCon.getConnection();
			String query="select bid,bname,btitle,bcontent,"
					+ "bdate,bhit,bgroup,bstep,bindent "
					+ "from replyboard where bid=?";
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(sbid));
			rs=pstmt.executeQuery();
			if(rs.next()) {
				int bid=rs.getInt("bid");
				String bname=rs.getString("bname");
				String btitle=rs.getString("btitle");
				String bcontent=rs.getString("bcontent");
				Timestamp bdate=rs.getTimestamp("bdate");
				int bhit=rs.getInt("bhit");
				int bgroup=rs.getInt("bgroup");
				int bstep=rs.getInt("bstep");
				int bindent=rs.getInt("bindent");
				dto=new BDto(bid, bname, btitle,
						bcontent, bdate, bhit, bgroup,
						bstep, bindent);
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) {rs.close();}
				if(pstmt!=null) {pstmt.close();}
				if(conn!=null) {conn.close();}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return dto;
	}
	public BDto modifyView(String sbid) {

		BDto dto=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=DBCon.getConnection();
			String query="select bid,bname,btitle,bcontent,"
					+ "bdate,bhit,bgroup,bstep,bindent "
					+ "from replyboard where bid=?";
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(sbid));
			rs=pstmt.executeQuery();
			if(rs.next()) {
				int bid=rs.getInt("bid");
				String bname=rs.getString("bname");
				String btitle=rs.getString("btitle");
				String bcontent=rs.getString("bcontent");
				Timestamp bdate=rs.getTimestamp("bdate");
				int bhit=rs.getInt("bhit");
				int bgroup=rs.getInt("bgroup");
				int bstep=rs.getInt("bstep");
				int bindent=rs.getInt("bindent");
				dto=new BDto(bid, bname, btitle,
						bcontent, bdate, bhit, bgroup,
						bstep, bindent);
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) {rs.close();}
				if(pstmt!=null) {pstmt.close();}
				if(conn!=null) {conn.close();}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return dto;
	}
	public void upHit(String sbid) {
		PreparedStatement pstmt=null;
		try {
			conn=DBCon.getConnection();
			String query="update replyboard "
					+ "set bhit=bhit+1 "
					+ "where bid=?";
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(sbid));
			
			int rn=pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null) {pstmt.close();}
				if(conn!=null) {conn.close();}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}	
	}
	
	public void modify(String bid,String bname,
			String btitle,String bcontent) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=DBCon.getConnection();
			String query="update replyboard "
					+ "set bname=?,btitle=?,bcontent=? "
					+ "where bid=?";
			
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, bname);
			pstmt.setString(2, btitle);
			pstmt.setString(3, bcontent);
			pstmt.setString(4, bid);
			int rn=pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			try {
				if(pstmt!=null) {pstmt.close();}
				if(conn!=null) {conn.close();}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
	public void reply(String bid,String bname,
			String btitle,String bcontent,
			String bgroup,String bstep, String bindent) {
		
			replyShape(bgroup,bstep);
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=DBCon.getConnection();
			String query="insert into replyboard(bid,bname,btitle,"
					+ "bcontent,bgroup,bstep,bindent) "
					+ "values(replyboard_seq.nextval,?,?,?,?,?,?)";
			
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, bname);
			pstmt.setString(2, btitle);
			pstmt.setString(3, bcontent);
			pstmt.setInt(4, Integer.parseInt(bgroup));
			pstmt.setInt(5, Integer.parseInt(bstep)+1);
			pstmt.setInt(6, Integer.parseInt(bindent)+1);	
			int rn=pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			try {
				if(pstmt!=null) {pstmt.close();}
				if(conn!=null) {conn.close();}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
	public BDto reply_view(String sbid) {
		// TODO Auto-generated method stub
		BDto dto=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=DBCon.getConnection();
			String query="select bid,bname,btitle,bcontent,"
					+ "bdate,bhit,bgroup,bstep,bindent "
					+ "from replyboard where bid=?";
			
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(sbid));
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				int bid=rs.getInt("bid");
				String bname=rs.getString("bname");
				String btitle=rs.getString("btitle");
				String bcontent=rs.getString("bcontent");
				Timestamp bdate=rs.getTimestamp("bdate");
				int bhit=rs.getInt("bhit");
				int bgroup=rs.getInt("bgroup");
				int bstep=rs.getInt("bstep");
				int bindent=rs.getInt("bindent");
				dto=new BDto(bid, bname, btitle,
						bcontent, bdate, bhit, bgroup,
						bstep, bindent);
				

			} 
		}
		catch (Exception e) {
		// TODO: handle exception
		}finally {
			try {
				if(pstmt!=null) {pstmt.close();}
				if(conn!=null) {conn.close();}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return dto;
	}
	
	public void replyShape(String strgroup,String strstep) {
		
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=DBCon.getConnection();
			String query="update replyboard set bstep=bstep+1 "
						+ "where bgroup=? and bstep>?";
			
			
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, strgroup);
			pstmt.setString(2, strstep);
			int rs=pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			try {
				if(pstmt!=null) {pstmt.close();}
				if(conn!=null) {conn.close();}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
		
	}
	
	public void delete(BDto dto) {
		
		
		PreparedStatement pstmt=null;
		try {
			conn=DBCon.getConnection();
			
			String query="delete from replyboard where "
					+ "(select count(bid) from replyboard where bgroup=? "
					+ "and bstep=?+1 and bindent>?)=0 and bid=?";
			
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, dto.getBgroup());
			pstmt.setInt(2, dto.getBstep());
			pstmt.setInt(3, dto.getBindent());
			pstmt.setInt(4, dto.getBid());
			int rn=pstmt.executeUpdate();
			
			if(rn!=0) {
				System.out.println("삭제완료");
				stepinit(dto);
			}
			else {
				System.out.println("삭제실패");
			}
		}catch(Exception e) {
			 e.printStackTrace(); // 꼭 넣으세요!
		}finally {
			try {
				if(pstmt!=null) {pstmt.close();}
				if(conn!=null) {conn.close();}
			} catch (Exception e2) {
				 e2.printStackTrace(); // 꼭 넣으세요!
			}
		}
		
	}
	
	public void stepinit(BDto dto) {
		PreparedStatement pstmt=null;
		try {
			conn=DBCon.getConnection();
			String query="update replyboard set bstep=bstep-1 "
					+ "where bgroup=? and bstep>?";
			
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, dto.getBgroup());
			pstmt.setInt(2, dto.getBstep());
			
			int rn=pstmt.executeUpdate();
			System.out.println("update cnt : "+rn);
			
		}catch(Exception e){
			
		}finally {
			try {
				if(pstmt!=null) {pstmt.close();}
				if(conn!=null) {conn.close();}
			} catch (Exception e2) {
				 e2.printStackTrace(); // 꼭 넣으세요!
			}
		}
		
	}
	
	
	
//	public BDto sel2(String sbid) {
//		// TODO Auto-generated method stub
//		BDto dto=null;
//		PreparedStatement pstmt=null;
//		ResultSet rs=null;
//		try {
//			conn=DBCon.getConnection();
//			String query="select bid,bname,btitle,bcontent,"
//					+ "bdate,bhit,bgroup,bstep,bindent "
//					+ "from replyboard where bid=?";
//			
//			pstmt=conn.prepareStatement(query);
//			pstmt.setInt(1, Integer.parseInt(sbid));
//			rs=pstmt.executeQuery();
//			
//			while(rs.next()) {
//				int bid=rs.getInt("bid");
//				String bname=rs.getString("bname");
//				String btitle=rs.getString("btitle");
//				String bcontent=rs.getString("bcontent");
//				Timestamp bdate=rs.getTimestamp("bdate");
//				int bhit=rs.getInt("bhit");
//				int bgroup=rs.getInt("bgroup");
//				int bstep=rs.getInt("bstep");
//				int bindent=rs.getInt("bindent");
//				dto=new BDto(bid, bname, btitle,
//						bcontent, bdate, bhit, bgroup,
//						bstep, bindent);
//				
//
//			} 
//		}
//		catch (Exception e) {
//		// TODO: handle exception
//		}finally {
//			try {
//				if(pstmt!=null) {pstmt.close();}
//				if(conn!=null) {conn.close();}
//			} catch (Exception e2) {
//				// TODO: handle exception
//			}
//		}
//		return dto;
//	}
//	
//	public boolean nondel2(BDto dto) {
//		conn=null;
//		PreparedStatement pstmt=null;
//		ResultSet rs = null;
//		boolean hasChild = false;
//		try {
//			conn = DBCon.getConnection();
//	        String query = "SELECT COUNT(*) FROM replyboard " +
//	                       "WHERE bid=? and bgroup = ? AND bindent = ? AND bstep > ?";
//
//	        pstmt = conn.prepareStatement(query);
//	        pstmt.setInt(1, dto.getBid());
//	        pstmt.setInt(2, dto.getBgroup());
//	        pstmt.setInt(3, dto.getBindent() + 1); // 자식은 bindent+1
//	        pstmt.setInt(4, dto.getBstep());
//
//	        rs = pstmt.executeQuery();
//	        if (rs.next()) {
//	            hasChild = rs.getInt(1) > 0;  // 자식이 하나라도 있으면 true
//	        }
//			
//			
//		}catch(Exception e) {
//			 e.printStackTrace(); // 꼭 넣으세요!
//		}finally {
//			try {
//				if(pstmt!=null) {pstmt.close();}
//				if(conn!=null) {conn.close();}
//			} catch (Exception e2) {
//				 e2.printStackTrace(); // 꼭 넣으세요!
//			}
//		}
//		
//		return hasChild;
//		
//	}
//	
//	//뭔가잘안되면 이거써라
//	public boolean nondel(String bid,String bgroup,String bindent,String bstep) {
//		
//		conn=null;
//		PreparedStatement pstmt=null;
//		ResultSet rs = null;
//		boolean hasChild = false;
//		try {
//			conn = DBCon.getConnection();
//	        String query = "SELECT COUNT(*) FROM replyboard " +
//	                       "WHERE bid=? and bgroup = ? AND bindent = ? AND bstep > ?";
//
//	        pstmt = conn.prepareStatement(query);
//	        pstmt.setInt(1, Integer.parseInt(bid));
//	        pstmt.setInt(2, Integer.parseInt(bgroup));
//	        pstmt.setInt(3, Integer.parseInt(bindent) + 1); // 자식은 bindent+1
//	        pstmt.setInt(4, Integer.parseInt(bstep));
//
//	        rs = pstmt.executeQuery();
//	        if (rs.next()) {
//	            hasChild = rs.getInt(1) > 0;  // 자식이 하나라도 있으면 true
//	        }
//			
//			
//		}catch(Exception e) {
//			 e.printStackTrace(); // 꼭 넣으세요!
//		}finally {
//			try {
//				if(pstmt!=null) {pstmt.close();}
//				if(conn!=null) {conn.close();}
//			} catch (Exception e2) {
//				 e2.printStackTrace(); // 꼭 넣으세요!
//			}
//		}
//		return hasChild;
//		
//	}

}
