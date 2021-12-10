package model.charge;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.CommonFunc;
import dbcon.DatabaseUtil;

public class ChargeDAO {
	public void getTodayFirstCharge(ChargeDTO dto) {
		String SQL = "insert into charge(enter_no, user_id, start_date, start_time, prepaid_money, available_time, seat_no) values (enter_no_SEQ.NEXTVAL, ?, TO_CHAR(sysdate, 'YYYY-MM-DD'), TO_char(SYSDATE, 'hh24:mm:ss'),?, ?, '1')";
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DatabaseUtil.getConnection();
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, dto.getUserId());
			pstmt.setInt(2, dto.getPrepaidMoney());
			pstmt.setInt(3, (dto.getPrepaidMoney() / 1000) * 3600);

			int i = pstmt.executeUpdate();

			if (i == 1) {
				CommonFunc.alertDisplay(1, "��� ����", "������� �Ϸ�", dto.getUserId() + "�� ��� ����");
			} else {
				CommonFunc.alertDisplay(1, "��� ����", "����", "��� ���� ����");
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
	}

	// ������ �α����� �¼� ��ȣ ����
	public int saveSeatNo(String userId, String currentSeatNo, int enterNo) {
		String SQL = "update charge set seat_No= ? where user_Id = ? and enter_no = ?";
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DatabaseUtil.getConnection();
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, currentSeatNo);
			pstmt.setString(2, userId);
			pstmt.setInt(3, enterNo);

			int i = pstmt.executeUpdate();

			if (i == 1) {
				CommonFunc.alertDisplay(1, "�α���", "�α��� ����", "ȯ���մϴ�~");

			} else {
				CommonFunc.alertDisplay(1, "�α���", "�α��� ����", "�ð��� �����մϴ�. �������ּ���~");
			}
			return i;
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
		return 0;
	}

	// �α��� ���� �ڸ�
	public String getLoginUserSeatNo(String userId) {
		String SQL = "select seat_No from charge where user_Id = ? and rownum < 2 order by start_date desc";
		Connection con = null;
		PreparedStatement pstmt = null;
		String seatNo = null;

		try {
			con = DatabaseUtil.getConnection();
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, userId);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				seatNo = rs.getString(1);
				System.out.println(seatNo);
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
		return seatNo;
	}

	public int selectPrepaidMoney(String userId) {
		String SQL = "select prepaid_money from charge where user_Id = ? and rownum < 2 order by Start_date desc";
		int prepaidMoney = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DatabaseUtil.getConnection();
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, userId);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				prepaidMoney = rs.getInt(1);
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
		return prepaidMoney;
	}

	public int selectAvailableTime(String userId) {
		String SQL = "select available_time from charge where user_Id = ? and rownum < 2 order by Start_date desc";
		Connection con = null;
		PreparedStatement pstmt = null;
		int AvailableTime = 0;
		try {
			con = DatabaseUtil.getConnection();
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, userId);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				AvailableTime = rs.getInt(1);
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

		return AvailableTime;
	}
}
