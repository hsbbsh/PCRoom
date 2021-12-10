package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.order.admin.AdminOrderDAO;
import model.order.admin.AdminOrderDTO;

public class OrderListController implements Initializable {
    @FXML
    private TableView<AdminOrderDTO> tableView;
    @FXML
    private Button btnOrderDelete;
    @FXML
    private Button btnOrderClear;
    
    private ObservableList<AdminOrderDTO> selectedOrder;
    
    private ObservableList<AdminOrderDTO> data;
    private int selectedOrderIndex;
    
    private boolean editDelete = false;
    
    
    AdminOrderDTO adminOrderDTO = new AdminOrderDTO();
    AdminOrderDAO adminOrderDAO = new AdminOrderDAO();
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		orderTableViewSettings();
		
		tableView.setOnMousePressed(event -> {
			handlerOrderDoneAndCancel(event);
		});
		
	}

	private void handlerOrderDoneAndCancel(MouseEvent event) {
		editDelete = true;

		selectedOrderIndex = tableView.getSelectionModel().getSelectedIndex();
		selectedOrder = tableView.getSelectionModel().getSelectedItems();	
		
		btnOrderDelete.setOnAction(e -> {
			AdminOrderDAO dao = new AdminOrderDAO();
			dao.getOrderDelete(selectedOrder.get(selectedOrderIndex).getOrderNo());
			
			data.removeAll(data);
			totalOrderList();
		});
		
		btnOrderClear.setOnAction(e -> {
			int i = selectedOrderIndex;
			AdminOrderDTO aodto = new AdminOrderDTO(selectedOrder.get(i).getOrderNo(), selectedOrder.get(i).getOrderDate(), 
					selectedOrder.get(i).getSeatNo(), selectedOrder.get(i).getOrderUserId(), 
					selectedOrder.get(i).getOrderUserName(), selectedOrder.get(i).getOrderList(), 
					selectedOrder.get(i).getOrderTotalPrice(), "�����Ϸ�");
			
			AdminOrderDAO dao = new AdminOrderDAO();
			AdminOrderDTO dto = dao.getOrderStatusUpdate(aodto, selectedOrder.get(i).getOrderNo());
			
			if (editDelete == true && dto != null) {
				data.remove(selectedOrderIndex);
				data.add(selectedOrderIndex, dto);
				editDelete = false;
			}
		});
	}

	private void totalOrderList() {
		ArrayList<AdminOrderDTO> list = null;
		AdminOrderDAO dao = new AdminOrderDAO();
		AdminOrderDTO dto = null;
		list = dao.getOrderTotal();
		if (list == null) {
			CommonFunc.alertDisplay(1, "���", "DB �������� ����", "�ٽ� �� �� ������ �ּ���.");
			return;
		} else {
			for (int i = 0; i < list.size(); i++) {
				dto = list.get(i);
				data.add(dto);
			}
		}
		
	}

	private void orderTableViewSettings() {
		data = FXCollections.observableArrayList();
		tableView.setEditable(true);
		
		TableColumn colOrderNo = new TableColumn("�ֹ���ȣ");
		colOrderNo.setPrefWidth(70);
		colOrderNo.setStyle("-fx-alignment: CENTER;");
		colOrderNo.setCellValueFactory(new PropertyValueFactory<>("orderNo"));

		TableColumn colOrderDate = new TableColumn("�ֹ��Ͻ�");
		colOrderDate.setPrefWidth(120);
		colOrderDate.setStyle("-fx-alignment: CENTER;");
		colOrderDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
		
		TableColumn colSeatNo = new TableColumn("�¼���ȣ");
		colSeatNo.setPrefWidth(70);
		colSeatNo.setStyle("-fx-alignment: CENTER;");
		colSeatNo.setCellValueFactory(new PropertyValueFactory<>("seatNo"));
		
		
		TableColumn colOrderId = new TableColumn("�ֹ���ID");
		colOrderId.setPrefWidth(120);
		colOrderId.setStyle("-fx-alignment: CENTER;");
		colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
		
		TableColumn colOrderUser = new TableColumn("�ֹ��ڸ�");
		colOrderUser.setPrefWidth(100);
		colOrderUser.setStyle("-fx-alignment: CENTER;");
		colOrderUser.setCellValueFactory(new PropertyValueFactory<>("orderUser"));
		
		TableColumn colOrderList = new TableColumn("�ֹ�����");
		colOrderList.setPrefWidth(120);
		colOrderList.setStyle("-fx-alignment: CENTER;");
		colOrderList.setCellValueFactory(new PropertyValueFactory<>("orderList"));
		
		TableColumn colOrderPrice= new TableColumn("�ֹ��ݾ�");
		colOrderPrice.setPrefWidth(120);
		colOrderPrice.setStyle("-fx-alignment: CENTER;");
		colOrderPrice.setCellValueFactory(new PropertyValueFactory<>("orderTotalPrice"));
		
		TableColumn colOrderStatus = new TableColumn("�ֹ�����");
		colOrderStatus.setPrefWidth(120);
		colOrderStatus.setStyle("-fx-alignment: CENTER;");
		colOrderStatus.setCellValueFactory(new PropertyValueFactory<>("orderStatus"));
		
		tableView.setItems(data);
		tableView.getColumns().addAll(colOrderNo, colOrderDate, colOrderId, colOrderUser, colOrderList,
				colOrderPrice, colOrderStatus);

		totalOrderList();
	}

}
