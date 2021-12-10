package model.order.user;

public class UserOrderDTO {
	// orderDB의 7개 변수
	private int orderNo;
	private int enterNo;
	private String itemName;
	private int itemOrderAmount;
	private int orderPrice;
	private String orderDate;
	private String orderStatus;
	// itemTable의 변수
	private String orderList;
	// orderTable의 변수
	private int orderSum;
	private String seatNo;
	
	public UserOrderDTO() {
	}
	
	public UserOrderDTO(int orderNo, int enterNo, String itemCode, int itemOrderAmount, int orderPrice, String orderDate,
			String orderStatus) {
		super();
		this.orderNo = orderNo;
		this.enterNo = enterNo;
		this.itemName = itemCode;
		this.itemOrderAmount = itemOrderAmount;
		this.orderPrice = orderPrice;
		this.orderDate = orderDate;
		this.orderStatus = orderStatus;
	}
	
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public int getEnterNo() {
		return enterNo;
	}
	public void setEnterNo(int enterNo) {
		this.enterNo = enterNo;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getItemOrderAmount() {
		return itemOrderAmount;
	}
	public void setItemOrderAmount(int itemOrderAmount) {
		this.itemOrderAmount = itemOrderAmount;
	}
	public int getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(int orderPrice) {
		this.orderPrice = orderPrice;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getOrderList() {
		return orderList;
	}
	public void setOrderList(String orderList) {
		this.orderList = orderList;
	}
	public int getOrderSum() {
		return orderSum;
	}
	public void setOrderSum(int orderSum) {
		this.orderSum = orderSum;
	}
	public String getSeatNo() {
		return seatNo;
	}
	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}
	
}
