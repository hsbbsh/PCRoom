package model.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import application.CommonFunc;
import dbcon.DatabaseUtil;

public class UserDAO {
	public static int UserLogin(String id, String pw) {
		String SQL = "select * from users where user_id=?";
		UserDTO dto = new UserDTO();
		if(id == "") {
			return 2;
		}else if(pw == "") {
			return 2;
		}
		try {
			Connection conn = DatabaseUtil.getConnection();
			
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(rs.getString(7).contentEquals(pw)) {
					return 1; // 로그인 성공
				} else {
					return -1; // 비밀번호 불일치
				}
			}
			return -1; // 아이디 없음
		} catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	public static String UserIdSearch(String name, String phone) {
		String SQL = "select * from users where user_name = ? and user_phone = ?";
		// 입력이 되었는지 확인
		if(name == "" || phone == "") {
			return "1";
		}
		
		try {
			Connection conn = DatabaseUtil.getConnection();
			
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, name);
			pstmt.setString(2, phone);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return rs.getString(1);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		// 찾을 수 없으면
		return "2";
	}

	public static String UserPwSearch(String id, String name, String phone) {
		String SQL = "select * from users where user_id = ? and user_name = ? and user_phone = ?";
		// 입력이 되었는지 확인
		if(id == "" || name == "" || phone == "") {
			return "1";
		}
		try {
			Connection conn = DatabaseUtil.getConnection();
			
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, phone);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return rs.getString(7);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		// 찾을 수 없으면
		return "2";
	}
	public static int UserDoubleCheck(String id) {
		String SQL = "select * from users where user_id = ?";
		if(id == "") {
			return -1;
		}
		try {
			Connection conn = DatabaseUtil.getConnection();
			
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, id);

			ResultSet rs = pstmt.executeQuery();

			if(rs.next()) {
				if(rs.getString(1).equals(id)) {
					return 0;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return 1;
	}
	//회원 가입
	public static int UserAdd(String name, String id, String pw, String gender, String birth, String phone) {
		String SQL = "INSERT INTO users VALUES (?, ?, ?, ?, ?, ?, ?)";
		if( id == "" || name == "" || gender == "" || birth == "" || phone == "" || pw == "") {
			return 1;
		}
		try {
			Connection conn = DatabaseUtil.getConnection();
			
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, gender);
			pstmt.setString(4, birth);
			pstmt.setString(5, phone);
			pstmt.setString(6, "로그아웃");
			pstmt.setString(7, pw);
			
			ResultSet rs = pstmt.executeQuery();
			
			return 0;
		} catch(Exception e) {
			e.printStackTrace();
		}
		// 추가안됨
		return 2;
	}
	// 회원 삭제
	public void getUserDelete(String id) {
		String SQL = "delete from users where user_id = ?";
		try {
			Connection conn = DatabaseUtil.getConnection();
			
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			
			int i = pstmt.executeUpdate();
		
			if(i == 1) {
				CommonFunc.alertDisplay(3, "사용자 삭제", "사용자 삭제 완료", "사용자 삭제 성공");
			} 
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	// 회원 검색
	public ArrayList<UserDTO> getUserSearch(String s) {
		String SQL = "select * from users where user_Name like ? or user_id like ? or user_gender like ? or user_birth like ? or user_phone like ? or user_status like ? or user_pw like ?";
		
		ArrayList<UserDTO> list = new ArrayList<UserDTO>(); 
		UserDTO user = null;
		String string = "%"+s+"%";
		try {
			Connection conn = DatabaseUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, string);
			pstmt.setString(2, string);
			pstmt.setString(3, string);
			pstmt.setString(4, string);
			pstmt.setString(5, string);
			pstmt.setString(6, string);
			pstmt.setString(7, string);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				user = new UserDTO(rs.getString(1), rs.getString(2), rs.getString(3), 
						rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
				
				list.add(user);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public ArrayList<UserDTO> list() {
		String SQL = "select * from users";
		ArrayList<UserDTO> list = new ArrayList<UserDTO>();
		
		try {
			Connection conn = DatabaseUtil.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			
			while(rs.next()) {
				UserDTO user = new UserDTO();
				user.setId(rs.getString("user_id"));
				user.setName(rs.getString("user_name"));
				user.setGender(rs.getString("user_gender"));
				user.setBirth(rs.getString("user_birth"));
				user.setPhone(rs.getString("user_phone"));
				user.setStatus(rs.getString("user_status"));
				user.setPw(rs.getString("user_pw"));
				
				list.add(user);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public int getEnterNo(String userId) {
		String SQL = "select enter_No from charge where user_id = ?";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		int enterNo = 0;
		System.out.println(userId);
		try {
			con = DatabaseUtil.getConnection();
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, userId);
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				enterNo=rs.getInt(1);
				System.out.println(userId);
				System.out.println(enterNo);
				return enterNo;
			}
		} catch (SQLException e1) {
			System.out.println("selectLabelNameTime = [" + e1 + "]");
		} catch (Exception e2) {
			System.out.println("selectLabelNameTime = [" + e2 + "]");
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (SQLException e1) {
			}
		}
		return enterNo;
	}
	
}