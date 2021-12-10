package model.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import dbcon.DatabaseUtil;

public class AdminDAO {
	public static int AdminLogin(String id, String pw) {
		String SQL = "select * from admin where admin_id=?";
		AdminDTO dto = new AdminDTO();
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
				if(rs.getString(2).contentEquals(pw)) {
					return 1; // �α��� ����
				} else {
					return -1; // ��й�ȣ ����ġ
				}
			}
			return -1; // ���̵� ����
		} catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	public static String AdminIdSearch(String name, String phone){
		String SQL = "select * from admin where admin_name = ? and admin_phone = ?";
		// �Է��� �Ǿ����� Ȯ��
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
		// ã�� �� ������
		return "2";
	}
	public static String AdminPwSearch(String id, String name, String phone){
		String SQL = "select * from admin where admin_id = ? and admin_name = ? and admin_phone = ?";
		// �Է��� �Ǿ����� Ȯ��
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
				return rs.getString(2);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		// ã�� �� ������
		return "2";
	}
}
