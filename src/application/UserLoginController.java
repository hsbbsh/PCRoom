package application;

import java.net.URL;
import java.util.ResourceBundle;

import client.Client;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.charge.ChargeDAO;
import model.user.UserDAO;

public class UserLoginController implements Initializable {
	private Client client;
	static int use = 0;
	ObservableList seatNoList = FXCollections.observableArrayList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11",
			"12", "13", "14", "15", "16", "17", "18", "19", "20");
	@FXML
    private ComboBox cbxSeatNo;
	@FXML
    private TextField txtUserId;
    @FXML
    private PasswordField txtUserPw;
    @FXML
    private Button btnLogin;
    @FXML
    private Button btnIdPwSearch;
    @FXML
    private Button btnUserAdd;
    @FXML
    private Button btnAppStop;
    
	public static String getCurrentUserID;
	public static String getCurrentSeatNo;
	public static int currentEnterNo;
	String userId;
	String userSeatNo;
	
	
    public static String getTxtUserId() {
		return getCurrentUserID;
	}
    
	public static String getUserSeatNo() {
		return getCurrentSeatNo;
	}
	public static int getCurrentEnterNo() {
		return currentEnterNo;
	}
	
	public static int getUse() {
		return use;
	}
	@Override
    public void initialize(URL arg0, ResourceBundle arg1) {
		cbxSeatNo.setItems(seatNoList);
		
		btnLogin.setOnAction(event -> {
			BtnUserLoginAction(event);
		});
		btnIdPwSearch.setOnAction(event -> {
			BtnUserSearchAction(event);
		});
		btnUserAdd.setOnAction(event -> {
			BtnUserAddAction(event);
		});
		btnAppStop.setOnAction(event -> {
			BtnAppstopAction(event);
		});
		
		//txtUserId.setText("user");
		//txtUserPw.setText("1234");
	}
	private void BtnUserLoginAction(ActionEvent event) {
		UserDAO dao = new UserDAO();
		ChargeDAO cdao = new ChargeDAO();
		int check = dao.UserLogin(txtUserId.getText(), txtUserPw.getText());
		if(check == 1) {
			getCurrentUserID = txtUserId.getText();
			System.out.println(getCurrentUserID);
			
			getCurrentSeatNo = (String) cbxSeatNo.getValue();
			System.out.println(getCurrentSeatNo);
			
			currentEnterNo = dao.getEnterNo(getCurrentUserID);
			System.out.println(currentEnterNo);

			if(cdao.saveSeatNo(getCurrentUserID, getCurrentSeatNo, currentEnterNo) == 1) {
				Parent LoginView = null;
		        Stage LoginStage = null;
		        try {
		        	LoginView = FXMLLoader.load(getClass().getResource("/view/user_main.fxml"));
		        	Scene scene = new Scene(LoginView);
		        	LoginStage = new Stage();
		        	LoginStage.setTitle("안녕하세요. 서원대 PC방을 찾아주셔서 감사합니다.");
		        	LoginStage.setScene(scene);
		        	LoginStage.setResizable(false);
		        	LoginStage.show();
		        } catch(Exception e) {
		        	e.getStackTrace();
		        }
			} 
		} else if(check == 2) {
			CommonFunc.alertDisplay(1, "로그인 실패", "아이디 혹은 패스워드 미입력", "다시 입력해 주세요.");
		} else {
			CommonFunc.alertDisplay(1, "로그인 실패", "아이디 혹은 패스워드 불일치", "다시 입력해 주세요.");
		}
		/*
		check = UserDAO.UserLogin(txtUserId.getText(), txtUserPw.getText());

		if(check == 1) {
			getCurrentUserID = txtUserId.getText();
			getCurrentSeatNo = (String) cbxSeatNo.getValue();
			Parent LoginView = null;
	        Stage LoginStage = null;
	        try {
	        	LoginView = FXMLLoader.load(getClass().getResource("/view/user_main.fxml"));
	        	Scene scene = new Scene(LoginView);
	        	LoginStage = new Stage();
	        	LoginStage.setTitle("안녕하세요. 서원대 PC방을 찾아주셔서 감사합니다.");
	        	LoginStage.setScene(scene);
	        	LoginStage.setResizable(false);
	        	LoginStage.show();
	        } catch(Exception e) {
	        	e.getStackTrace();
	        }
		} else if(check == -1) { 
			CommonFunc.alertDisplay(1, "로그인 실패", "아이디 혹은 패스워드 불일치", "다시 입력해 주세요.");
		} else if(check == 2) {
			CommonFunc.alertDisplay(1, "로그인 실패", "아이디 혹은 패스워드 미입력", "다시 입력해 주세요.");
		}*/
	}
	private void BtnUserSearchAction(ActionEvent event) {
		use = 2;
		System.out.println(use);
		Parent searchView = null;
        Stage searchStage = null;
        try {
        	searchView = FXMLLoader.load(getClass().getResource("/view/idpw_search.fxml"));
        	Scene scene = new Scene(searchView);
        	searchStage = new Stage();
        	searchStage.setTitle("ID/PW 찾기");
        	searchStage.setScene(scene);
        	searchStage.setResizable(false);
        	searchStage.show();
        } catch(Exception e) {
        	e.getStackTrace();
        }
	}
	private void BtnUserAddAction(ActionEvent event) {
		Parent AddView = null;
        Stage AddStage = null;
        try {
        	AddView = FXMLLoader.load(getClass().getResource("/view/user_add.fxml"));
        	Scene scene = new Scene(AddView);
        	AddStage = new Stage();
        	AddStage.setTitle("회원가입");
        	AddStage.setScene(scene);
        	AddStage.setResizable(false);
        	AddStage.show();
        } catch(Exception e) {
        	e.getStackTrace();
        }
	}
	private void BtnAppstopAction(ActionEvent event) {
		Platform.exit();
	}
}