package model.order.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dbcon.DatabaseUtil;

public class AdminOrderDAO {

	public AdminOrderDTO getOrderStatusUpdate(AdminOrderDTO aodto, int orderNo) {
		String SQL = "update orders set orderStatus = ? where order_no = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DatabaseUtil.getConnection();
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, aodto.getOrderStatus());
			pstmt.setInt(2, orderNo);

			pstmt.executeUpdate();

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
		return aodto;
	}


	public ArrayList<AdminOrderDTO> getOrderTotal() {
		ArrayList<AdminOrderDTO> list = new ArrayList<AdminOrderDTO>();
		String SQL = "select o.order_No, o.order_Date, c.seat_no, c.user_ID, u.user_Name, i.item_Name, o.order_Price, o.order_Status "
				+ "from orders o, charge c, users u, item i "
				+ "where o.enter_no = c.enter_no and o.item_name = i.item_name and c.user_Id=u.user_Id";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; 
		AdminOrderDTO dto = null;
		try {
			con = DatabaseUtil.getConnection();
			pstmt = con.prepareStatement(SQL);

			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				dto = new AdminOrderDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getInt(7), rs.getString(8));
				list.add(dto); 
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
		
		return list;
	}


	public void getOrderDelete(int orderNo) {
		String SQL = "delete from orders where order_No = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DatabaseUtil.getConnection();
			pstmt = con.prepareStatement(SQL);

			pstmt.setInt(1, orderNo);
			
			pstmt.executeUpdate();
			
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
}
