package model.charge;

public class ChargeDTO {
	private String userId;
	private String startDate;
	private String startTime;
	private int availableTime;
	private String seatNo;
	private int prepaidMoney;
	private int enter_no;
	
	public ChargeDTO(){
		
	}
	public ChargeDTO(int availableTime, int prepaidMoney){
		super();
		this.availableTime = availableTime;
		this.prepaidMoney = prepaidMoney;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public int getAvailableTime() {
		return availableTime;
	}
	public void setAvailableTime(int availableTime) {
		this.availableTime = availableTime;
	}
	public String getSeatNo() {
		return seatNo;
	}
	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}
	public int getPrepaidMoney() {
		return prepaidMoney;
	}
	public void setPrepaidMoney(int prepaidMoney) {
		this.prepaidMoney = prepaidMoney;
	}
	public int getEnter_no() {
		return enter_no;
	}
	public void setEnter_no(int enter_no) {
		this.enter_no = enter_no;
	}
}
