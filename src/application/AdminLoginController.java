package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.admin.AdminDAO;

public class AdminLoginController implements Initializable {
	static int use = 0;
	@FXML
	private TextField txtAdminId;
	@FXML
	private PasswordField txtAdminPw;
	@FXML
	private Button btnAdminLogin;
	@FXML
	private Button btnAdminSearch;
	@FXML
	private Button btnAdminStop;
	
	public static String currentAdminId;
	
	public static String getCurrentAdminId() {
		return currentAdminId;
	}
	
	public static int getUse() {
		return use;
	}
	
	@Override
    public void initialize(URL arg0, ResourceBundle arg1) {
		btnAdminLogin.setOnAction(event -> {
			BtnAdminLoginAction(event);
		});
		btnAdminSearch.setOnAction(event -> {
			BtnAdminSearchAction(event);
		});
		btnAdminStop.setOnAction(event -> {
			btnAdminStopAction(event);
		});
		//txtAdminId.setText("admin");
		//txtAdminPw.setText("1234");
	}
	// 아이디/비밀번호 찾기 버튼 눌렀을 때
	private void BtnAdminSearchAction(ActionEvent event) {
		use = 1;
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

	// 로그인 버튼 눌렀을 때
	public void BtnAdminLoginAction(ActionEvent event) {
		int check;
		
		check = AdminDAO.AdminLogin(txtAdminId.getText(), txtAdminPw.getText());
		
		if(check == 1) {
			System.out.println("로그인 성공");
			currentAdminId = txtAdminId.getText();
			Parent LoginView = null;
	        Stage LoginStage = null;
			try {
	        	LoginView = FXMLLoader.load(getClass().getResource("/view/admin_main.fxml"));
	        	Scene scene = new Scene(LoginView);
	        	LoginStage = new Stage();
	        	LoginStage.setTitle("관리자님 환영합니다.");
	        	LoginStage.setScene(scene);
	        	LoginStage.setResizable(false);
	        	LoginStage.show();
	        	((Stage) btnAdminLogin.getScene().getWindow()).close();
	        } catch(Exception e) {
	        	e.getStackTrace();
	        }
			currentAdminId = txtAdminId.getText(); 
		} else if(check == -1) { 
			CommonFunc.alertDisplay(1, "로그인 실패", "아이디 혹은 패스워드 불일치", "다시 입력해 주세요.");
		} else if(check == 2) {
			CommonFunc.alertDisplay(1, "로그인 실패", "아이디 혹은 패스워드 미입력", "다시 입력해 주세요.");
		}
	}
	// 사용종료 버튼 눌렀을 때
	private void btnAdminStopAction(ActionEvent event) {
		((Stage) btnAdminStop.getScene().getWindow()).close();
	}

}