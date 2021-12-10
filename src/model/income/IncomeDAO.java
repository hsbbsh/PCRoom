package model.income;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dbcon.DatabaseUtil;

public class IncomeDAO {
	// 오늘 총 방문자 수
	public int getDayUserCount(String date) {
		String SQL = "select count(enter_No) from charge where Start_Date=?";
		int count = 0;
		try {
			Connection conn = DatabaseUtil.getConnection();
			
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, date);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return count;
	}
	// 현재 회원 수
	public int getCurrentMemberCount() {
		String SQL = "select count(user_Id) from users";
		int count = 0;
		try {
			Connection conn = DatabaseUtil.getConnection();
			
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return count;
	}
	// 일 PC 매출
	public int getDayPCIncomeInfo(String date) {
		String SQL = "select day_PC_Income from income where income_Day = to_char(extract(day from to_date(?)))and income_month = to_char(extract(month from to_date(?)))and income_year = to_char(extract(year from to_date(?)))";
		
		int todayPCIncome = 0;
		
		try {
			Connection conn = DatabaseUtil.getConnection();
			
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			
			pstmt.setString(1, date);
			pstmt.setString(2, date);
			pstmt.setString(3, date);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				todayPCIncome = rs.getInt(1);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return todayPCIncome;
	}
	// 일 상품 매출
	public int getDayItemIncomeInfo(String date) {
		String SQL = "select day_item_come from income where income_Day = to_char(extract(day from to_date(?)))and income_month = to_char(extract(month from to_date(?)))and income_year = to_char(extract(year from to_date(?)))";
		
		int todayItemIncome = 0;
		
		try {
			Connection conn = DatabaseUtil.getConnection();
			
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			
			pstmt.setString(1, date);
			pstmt.setString(2, date);
			pstmt.setString(3, date);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				todayItemIncome = rs.getInt(1);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return todayItemIncome;
	}
	// 일 총 매출
	public int getDayTotalIncomeInfo(String date) {
		String SQL = "select day_total_income from income where income_Day = to_char(extract(day from to_date(?)))and income_month = to_char(extract(month from to_date(?))) and income_year = to_char(extract(year from to_date(?)))";
		
		int todayTotalIncome = 0;
		
		try {
			Connection conn = DatabaseUtil.getConnection();
			
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			
			pstmt.setString(1, date);
			pstmt.setString(2, date);
			pstmt.setString(3, date);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				todayTotalIncome = rs.getInt(1);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return todayTotalIncome;
	}
	// 년 월 총 매출
	public ArrayList<IncomeDTO> getIncomeTotal(String year, String month) {
		
		ArrayList<IncomeDTO> list = new ArrayList<IncomeDTO>();
		
		String SQL = "select * from income where income_Year = ? and income_Month = ? order by income_day";
		
		IncomeDTO incomeDTO = null;
		
		try {
			Connection conn = DatabaseUtil.getConnection();
			
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			
			pstmt.setString(1, year);
			pstmt.setString(2, month);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				incomeDTO = new IncomeDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5),
						rs.getInt(6));
				list.add(incomeDTO);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	// 연 pc 매출
	public int getYearPCIncomeInfo(String year) {
		String SQL = "select sum(day_PC_Income) from income where income_Year = ?";
		
		int yearPCIncome = 0;
		
		try {
			Connection conn = DatabaseUtil.getConnection();
			
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			
			pstmt.setString(1, year);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				yearPCIncome = rs.getInt(1);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return yearPCIncome;
	}
	// 연 상품 매출
	public int getYearItemIncomeInfo(String year) {
		String SQL = "select sum(day_Item_come) from income where income_Year = ?";
		
		int yearItemIncome = 0;
		
		try {
			Connection conn = DatabaseUtil.getConnection();
			
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			
			pstmt.setString(1, year);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				yearItemIncome = rs.getInt(1);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return yearItemIncome;
	}
	// 연 총 매출
	public int getYearTotalIncomeInfo(String year) {
		String SQL = "select sum(day_total_income) from income where income_Year = ?";
		int yearTotalIncome = 0;
		
		try {
			Connection conn = DatabaseUtil.getConnection();
			
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			
			pstmt.setString(1, year);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				yearTotalIncome = rs.getInt(1);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return yearTotalIncome;
	}
}
