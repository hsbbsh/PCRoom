module SBPCRoom {
	requires javafx.controls;
	requires java.sql;
	requires javafx.fxml;
	requires javafx.base;
	
	opens application to javafx.graphics, javafx.fxml;
	opens model.income to javafx.fxml;
	opens model.user to javafx.fxml;
	opens model.charge to javafx.fxml;
	opens model.item to javafx.fxml;
	opens model.order.user to javafx.fxml;
	//opens model.order.admin to javafx.fxml;
	
	exports model.income;
	exports model.user;
	exports model.charge;
	exports model.item;
	exports model.order.user;
}
