package model.order.admin;

public class AdminOrderDTO {
		private int orderNo;
		private String orderDate;
		private String orderUserId;
		private String orderUserName;
		private String orderList;
		private String seatNo;
		private int orderTotalPrice;
		private String orderStatus;
		
		public AdminOrderDTO() {
			
		}
		public AdminOrderDTO(int orderNo, String orderDate, String seatNo, String orderId, String orderUser, String orderList,
				int orderTotalPrice, String orderStatus) {
			super();
			this.orderNo = orderNo;
			this.orderDate = orderDate;
			this.seatNo = seatNo;
			this.orderUserId = orderId;
			this.orderUserName = orderUser;
			this.orderList = orderList;
			this.orderTotalPrice = orderTotalPrice;
			this.orderStatus = orderStatus;
		}
		
		public int getOrderNo() {
			return orderNo;
		}
		public void setOrderNo(int orderNo) {
			this.orderNo = orderNo;
		}
		public String getOrderDate() {
			return orderDate;
		}
		public void setOrderDate(String orderDate) {
			this.orderDate = orderDate;
		}
		public String getOrderUserId() {
			return orderUserId;
		}
		public void setOrderUserId(String orderUserId) {
			this.orderUserId = orderUserId;
		}
		public String getOrderUserName() {
			return orderUserName;
		}
		public void setOrderUserName(String orderUserName) {
			this.orderUserName = orderUserName;
		}
		public String getOrderList() {
			return orderList;
		}
		public void setOrderList(String orderList) {
			this.orderList = orderList;
		}
		public String getSeatNo() {
			return seatNo;
		}
		public void setSeatNo(String seatNo) {
			this.seatNo = seatNo;
		}
		public int getOrderTotalPrice() {
			return orderTotalPrice;
		}
		public void setOrderTotalPrice(int orderTotalPrice) {
			this.orderTotalPrice = orderTotalPrice;
		}
		public String getOrderStatus() {
			return orderStatus;
		}
		public void setOrderStatus(String orderStatus) {
			this.orderStatus = orderStatus;
		}
}
