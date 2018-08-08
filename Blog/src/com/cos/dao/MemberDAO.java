package com.cos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.cos.dto.MemberVO;
import com.cos.util.DBManager;

public class MemberDAO {
	
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	//insert
	public int insert(MemberVO member) {
		
		String SQL = "INSERT INTO member VALUES(?,?,?,?,?,false)";
		Connection conn = DBManager.getConnection();
		
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getUsername());
			pstmt.setString(4, member.getEmail());
			pstmt.setString(5, member.getSalt());
			pstmt.executeUpdate();
			return 1;
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}finally {
			
			DBManager.close(conn, pstmt);
						
		}
		
		return -1;
	}
	// 구글인증 체크
	public int select_emailcheck(String id) {
		
		String SQL ="SELECT emailcheck FROM member where id = ?";
				Connection conn = DBManager.getConnection();
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				boolean emailcheck = rs.getBoolean("emailcheck");
				if(emailcheck == true) {
					return 1; //인증
				}else {
					return 2; //비인증
				}
				
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}finally {
			
			DBManager.close(conn, pstmt, rs);
						
		}
		
		return -1;
	}
	// 구글인증 이메일
	public String select_email(String id) {
		
		String SQL ="SELECT email FROM member where id = ?";
				Connection conn = DBManager.getConnection();
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				String email = rs.getString("email");
				return email;
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}finally {
			
			DBManager.close(conn, pstmt);
						
		}
		
		return null;
	}
	// 구글인증 암호
	public String select_salt(String id) {
		
		String SQL ="SELECT salt FROM member where id = ?";
				Connection conn = DBManager.getConnection();
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				String salt = rs.getString("salt");
				return salt;
				
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		return null; //DB오류
	}
	// 구글 체크
	public int update_emailcheck(String id) {
		
		String SQL = "UPDATE member SET emailcheck =true WHERE id = ?";
		Connection conn = DBManager.getConnection();
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			return 1;
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}finally {
			
			DBManager.close(conn, pstmt);
						
		}
		
		return -1; //DB오류
	}
	// 로그인
	public int login(MemberVO member) {
		
		String SQL = "SELECT id,password FROM member where id = ? and password = ?";
		Connection conn = DBManager.getConnection();
		
		try {
			
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPassword());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				return 1; //로그인 성공
				
			}else {
				
				return 2; //로그인 실패
			}
		} catch (Exception e) {
			System.out.println("login sql error");
			e.printStackTrace();
			
		}finally {
			
			DBManager.close(conn, pstmt);
			
		}
		return -1; //데이터베이스 오류
	}
	
	// update
	public int update(MemberVO member) {
		
		String SQL = "UPDATE member SET password = ?, email = ?, useremail = ? WHERE id = ?";
		Connection conn = DBManager.getConnection();
		
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, member.getPassword());
			pstmt.setString(2, member.getEmail());
			pstmt.setString(3, member.getUsername());
			pstmt.setString(4, member.getId());
			return pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}finally {
			
			DBManager.close(conn, pstmt);
			
		}return -1; //데이터베이스 오류
		
	}
	
}
