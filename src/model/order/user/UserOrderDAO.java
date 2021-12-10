package model.order.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import application.CommonFunc;
import dbcon.DatabaseUtil;

public class UserOrderDAO {
	public void UserOrderSave(UserOrderDTO dto) {
		String SQL = "insert into orders values(order_no_SEQ.NEXTVAL, ?, ?, 1, ?, TO_CHAR(sysdate, 'YYYY-MM-DD'), '주문대기')";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DatabaseUtil.getConnection();
			pstmt = con.prepareStatement(SQL);
			System.out.println(dto.getEnterNo() + dto.getItemName() + dto.getOrderPrice());
			pstmt.setInt(1, dto.getEnterNo());
			pstmt.setString(2, dto.getItemName());
			pstmt.setInt(3, dto.getOrderPrice());
			
			ResultSet rs = pstmt.executeQuery();
			
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
	public int getItemPrice(String itemName) {
		String SQL = "select item_price from item where item_name = ?";
		
		int price = 0;
		
		UserOrderDTO orderDTO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DatabaseUtil.getConnection();
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, itemName);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				price = rs.getInt(1);
			}
			System.out.println(itemName + price);
			
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
		return price;
	}
	
	public ArrayList<UserOrderDTO> getOrderTotal() {
		ArrayList<UserOrderDTO> list = new ArrayList<UserOrderDTO>();
		String SQL = "select * from ordert";
		
		UserOrderDTO orderDTO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		String seatNo=null;
		
		try {
			con = DatabaseUtil.getConnection();
			pstmt = con.prepareStatement(SQL);

			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				orderDTO = new UserOrderDTO(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getInt(5),
						rs.getString(6), rs.getString(7));
				list.add(orderDTO);
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
}
