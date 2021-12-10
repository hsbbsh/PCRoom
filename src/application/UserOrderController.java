package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.item.ItemDTO;
import model.order.user.UserOrderDAO;
import model.order.user.UserOrderDTO;
import model.user.UserDAO;

public class UserOrderController implements Initializable {
	@FXML
    private Button btnAll;
    @FXML
    private Button btnPop;
    @FXML
    private Button btnLa;
    @FXML
    private Button btnBe;
    @FXML
    private Button btnBap;
    @FXML
    private ImageView image01;
    @FXML
    private ImageView image02;
    @FXML
    private ImageView image03;
    @FXML
    private ImageView image04;
    @FXML
    private ImageView image05;
    @FXML
    private ImageView image06;
    @FXML
    private ImageView image07;
    @FXML
    private ImageView image08;
    @FXML
    private ImageView image09;
    @FXML
    private ImageView image10;
    @FXML
    private ImageView image11;
    @FXML
    private ImageView image12;
    @FXML
    private ImageView image13;
    @FXML
    private ImageView image14;
    @FXML
    private ImageView image15;
    @FXML
    private ImageView image16;
    @FXML
    private Label itemName01;
    @FXML
    private Label itemPrice01;
    @FXML
    private Label itemName02;
    @FXML
    private Label itemPrice02;
    @FXML
    private Label itemName03;
    @FXML
    private Label itemPrice03;
    @FXML
    private Label itemName04;
    @FXML
    private Label itemPrice04;
    @FXML
    private Label itemName05;
    @FXML
    private Label itemPrice05;
    @FXML
    private Label itemName06;
    @FXML
    private Label itemPrice06;
    @FXML
    private Label itemName07;
    @FXML
    private Label itemPrice07;
    @FXML
    private Label itemName08;
    @FXML
    private Label itemPrice08;
    @FXML
    private Label itemName09;
    @FXML
    private Label itemPrice09;
    @FXML
    private Label itemName10;
    @FXML
    private Label itemPrice10;
    @FXML
    private Label itemName11;
    @FXML
    private Label itemPrice11;
    @FXML
    private Label itemName12;
    @FXML
    private Label itemPrice12;
    @FXML
    private Label itemName13;
    @FXML
    private Label itemPrice13;
    @FXML
    private Label itemName14;
    @FXML
    private Label itemPrice14;
    @FXML
    private Label itemName15;
    @FXML
    private Label itemPrice15;
    @FXML
    private Label itemName16;
    @FXML
    private Label itemPrice16;
    @FXML
    private TableView<ItemDTO> tableView;
    @FXML
    private Label totalPrice;
    @FXML
    private Button btnItemOrder;
    @FXML
    private Button btnDelete;
    @FXML
    private RadioButton rdCard;
    @FXML
    private RadioButton rdMoney;
    @FXML
    private TextField txtSelect;
    
    private int selectedIndex;
	private ObservableList<ItemDTO> selectOrder;
	
	ArrayList<ItemDTO> list = null;
	ObservableList<ItemDTO> data;
	private UserOrderDAO orderDAO = new UserOrderDAO();
	private UserOrderDTO uodto = new UserOrderDTO();
	private ItemDTO idto = null;
	int orderNo; //삭제시 테이블에서 선택한 아이템의 번호 저장(아이템 순서?)
	int enterNo=0;
	UserOrderDAO od = new UserOrderDAO();
	UserDAO ud = new UserDAO();
	int total = 0;
	
	private int selectedOrderIndex;
	private ObservableList<ItemDTO> selectedItem;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tableViewSetting();
		
		tableView.setOnMousePressed(event -> {
			handlerOrderDelete(event);
		});
		

		// 상품 클릭
		image11.setOnMouseClicked(event ->{
			handlerimage11Click(event);
		});
		image07.setOnMouseClicked(event ->{
			handlerimage07Click(event);
		});
		image14.setOnMouseClicked(event ->{
			handlerimage14Click(event);
		});
		image02.setOnMouseClicked(event ->{
			handlerimage02Click(event);
		});
		
		btnItemOrder.setOnAction(event ->{
			handlerItemOrder(event);
		});
		
		enterNo=ud.getEnterNo(UserLoginController.getCurrentUserID);
	}


	private void handlerItemOrder(ActionEvent event) {
		int size = selectOrder.size();
		for(int i=0; i<size; i++) {
			uodto.setEnterNo(enterNo);
			uodto.setItemName(selectOrder.get(i).getItemName());
			uodto.setOrderPrice(selectOrder.get(i).getItemPrice());
			orderDAO.UserOrderSave(uodto);
		}
		CommonFunc.alertDisplay(3, "상품 주문", "상품이 주문되었습니다.", "조금만 기다려주세요~~");
		selectOrder.removeAll(selectOrder);
	}


	private void handlerOrderDelete(MouseEvent event) {
		btnDelete.setOnAction(e -> {
			selectedOrderIndex = tableView.getSelectionModel().getSelectedIndex();
			selectedItem = tableView.getSelectionModel().getSelectedItems();
			
			selectOrder.remove(selectedOrderIndex);
			total = 0;
			totalPrice.setText("0");
			tableViewSetting();
		});
	}

	private void handlerimage14Click(MouseEvent event) {
		idto = new ItemDTO(itemName14.getText(), od.getItemPrice(itemName14.getText()),1);
		selectOrder.add(idto);
		total = total + idto.getItemPrice();
		totalPrice.setText(Integer.toString(total));
	}
	private void handlerimage07Click(MouseEvent event) {
		idto = new ItemDTO(itemName07.getText(), od.getItemPrice(itemName07.getText()),1);
		selectOrder.add(idto);
		total = total + idto.getItemPrice();
		totalPrice.setText(Integer.toString(total));
	}
	private void handlerimage11Click(MouseEvent event) {
		idto = new ItemDTO(itemName11.getText(), od.getItemPrice(itemName11.getText()),1);
		selectOrder.add(idto);
		total = total + idto.getItemPrice();
		totalPrice.setText(Integer.toString(total));
	}
	private void handlerimage02Click(MouseEvent event) {
		idto = new ItemDTO(itemName02.getText(), od.getItemPrice(itemName02.getText()),1);
		selectOrder.add(idto);
		total = total + idto.getItemPrice();
		totalPrice.setText(Integer.toString(total));
	}
	private void tableViewSetting() {
		tableView.setEditable(true);
		selectOrder = FXCollections.observableArrayList();
		
		TableColumn colItemName = new TableColumn("상품이름");
		colItemName.setMaxWidth(200);
		colItemName.setStyle("-fx-alignment: CENTER;");
		colItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
		
		TableColumn colItemCount = new TableColumn("개수");
		colItemCount.setMaxWidth(30);
		colItemCount.setStyle("-fx-alignment: CENTER;");
		colItemCount.setCellValueFactory(new PropertyValueFactory<>("itemOrderAmount"));
		
		TableColumn colItemPrice = new TableColumn("금액");
		colItemPrice.setMaxWidth(120);
		colItemPrice.setStyle("-fx-alignment: CENTER;");
		colItemPrice.setCellValueFactory(new PropertyValueFactory<>("itemPrice"));
		
		tableView.setItems(selectOrder);
		tableView.getColumns().addAll(colItemName, colItemCount, colItemPrice);
	}
	
	
}

