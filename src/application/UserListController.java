package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.user.UserDAO;
import model.user.UserDTO;

public class UserListController implements Initializable {

    @FXML
    private TableView<UserDTO> tableViewUserDB;
    @FXML
    private TextField txtSelect;
    @FXML
    private Button btnSelect;
    @FXML
    private Button btnUserDelete;
    @FXML
    private Button btnUserUpdate;
    @FXML
    private Button btnCharge;
    
    private ObservableList<UserDTO> userData;
    private int selectedUserIndex;
	private ObservableList<UserDTO> selectedUser;
	
	private boolean editDelete = false;
	public static String id;
	public static int money;
	
	public static String getId() {
		return id;
	}
	public static int getMoney() {
		return money;
	}

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		userTableViewSettings();
		
		// 회원 삭제
		btnUserDelete.setOnAction(event -> {
			handlerUserDeleteAction(event);
		});
		
		
		
		tableViewUserDB.setOnMousePressed(event -> {
			handlerUserChargeAction(event);
		});

		btnUserUpdate.setOnAction(event -> {
			handlerUserUpdateAction(event);
		});
		
		btnSelect.setOnAction(event -> {
			handlerUserSearchAction(event);
		});
	}


	private void handlerUserUpdateAction(ActionEvent event) {

	}

	private void handlerUserChargeAction(MouseEvent event) {
		selectedUserIndex = tableViewUserDB.getSelectionModel().getSelectedIndex();
		selectedUser = tableViewUserDB.getSelectionModel().getSelectedItems();
		id = userData.get(selectedUserIndex).getId();
		
		btnCharge.setOnAction(ev->{
			Parent mainView = null;
			Stage mainStage = null;
			try {
				mainView = FXMLLoader.load(getClass().getResource("/view/charge_add.fxml"));
				Scene scene = new Scene(mainView);
				mainStage = new Stage();
				mainStage.setTitle("요금 충전");
				mainStage.setScene(scene);
				mainStage.setResizable(false);

				mainStage.show();
			} catch (IOException e) {
				CommonFunc.alertDisplay(1, "요금 충전 실패", "요금 충전을 로드할 수 없습니다.", e.toString());
			}
		});

	}

	private void handlerUserSearchAction(ActionEvent event) {
		try {
			ArrayList<UserDTO> list = new ArrayList<UserDTO>();
			UserDAO userDAO = new UserDAO();
			list = userDAO.getUserSearch(txtSelect.getText());
//				System.out.println("list.size = " +list.size() );	// 디버깅부분
			if (list == null) {
				throw new Exception("검색 오류");
			}
			userData.removeAll(userData);
			for (UserDTO uvo : list) {
				userData.add(uvo);
			}
			if(userData == null) {
				CommonFunc.alertDisplay(1, "검색 결과", "검색 결과 없음", "검색하신 단어가 존재하지 않습니다.");
			}
		} catch (Exception e) {
			CommonFunc.alertDisplay(1, "검색 결과", "검색 결과 없음", "검색하신 단어가 존재하지 않습니다." + e.toString());
		}
	}



	private void handlerUserDeleteAction(ActionEvent event) {
		try {
			editDelete = true;

			selectedUserIndex = tableViewUserDB.getSelectionModel().getSelectedIndex();
			selectedUser = tableViewUserDB.getSelectionModel().getSelectedItems();

			UserDAO userDAO = new UserDAO();
			userDAO.getUserDelete(selectedUser.get(0).getId());
			userData.removeAll(userData);
			totalUserList();
		} catch (Exception e) {
			CommonFunc.alertDisplay(1, "삭제 오류", "삭제오류", "다시 시도하시기 바랍니다." + e.toString());
		}
		editDelete = false;
		
	}

	private void userTableViewSettings() {
		userData = FXCollections.observableArrayList();
		tableViewUserDB.setEditable(true);
		
		TableColumn colUserId = new TableColumn("아이디");
		colUserId.setPrefWidth(96);
		colUserId.setStyle("-fx-alignment: CENTER;");
		colUserId.setCellValueFactory(new PropertyValueFactory<>("id"));
		//
		TableColumn colUserName = new TableColumn("이름");
		colUserName.setPrefWidth(100);
		colUserName.setStyle("-fx-alignment: CENTER;");
		colUserName.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		TableColumn colUserGender = new TableColumn("성별");
		colUserGender.setPrefWidth(60);
		colUserGender.setStyle("-fx-alignment: CENTER;");
		colUserGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
		//
		TableColumn colUserBirth = new TableColumn("생년월일");
		colUserBirth.setPrefWidth(120);
		colUserBirth.setStyle("-fx-alignment: CENTER;");
		colUserBirth.setCellValueFactory(new PropertyValueFactory<>("birth"));
		
		TableColumn colUserPhone = new TableColumn("휴대폰");
		colUserPhone.setPrefWidth(120);
		colUserPhone.setStyle("-fx-alignment: CENTER;");
		colUserPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
		
		TableColumn colUserStatus = new TableColumn("이용상태");
		colUserStatus.setPrefWidth(100);
		colUserStatus.setStyle("-fx-alignment: CENTER;");
		colUserStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
		
		TableColumn colUserPw = new TableColumn("비밀번호");
		colUserPw.setPrefWidth(80);
		colUserPw.setStyle("-fx-alignment: CENTER;");
		colUserPw.setCellValueFactory(new PropertyValueFactory<>("pw"));
		
		tableViewUserDB.setItems(userData);
		tableViewUserDB.getColumns().addAll(colUserId, colUserName, colUserGender, colUserBirth,
				colUserPhone, colUserStatus, colUserPw);
		
		totalUserList();
	}

	private void totalUserList() {
		ArrayList<UserDTO> list = null;
		UserDAO userDAO = new UserDAO();
		UserDTO userDTO = null;
		list = userDAO.list();
		if (list == null) {
			CommonFunc.alertDisplay(1, "경고", "DB 가져오기 오류", "다시 한 번 점검해 주세요.");
			return;
		} else {
			userData.removeAll(userData);
			for (int i = 0; i < list.size(); i++) {
				userDTO = list.get(i);
				userData.add(userDTO);
			}
		}
	}

}
