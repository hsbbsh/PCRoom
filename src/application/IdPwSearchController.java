package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.admin.AdminDAO;
import model.user.UserDAO;

public class IdPwSearchController implements Initializable{

    @FXML
    private TextField txtSearchIdName;
    @FXML
    private TextField txtSearchIdPhone;
    @FXML
    private TextField txtSearchPwName;
    @FXML
    private TextField txtSearchPwPhone;
    @FXML
    private TextField txtSearchPwId;
    @FXML
    private Button btnPwSearch;
    @FXML
    private Button btnIdSearch;
    
    private String SearchId;
    private String SearchPw;
   
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
		btnIdSearch.setOnAction(event -> {
			IdSearch(event);
		});
		btnPwSearch.setOnAction(event -> {
			PwSearch(event);
		});
	}
    public void IdSearch(ActionEvent event) {
		System.out.println(UserLoginController.getUse());
    	if(AdminLoginController.getUse() == 1) {
    		SearchId = AdminDAO.AdminIdSearch(txtSearchIdName.getText(), txtSearchIdPhone.getText());
    	} else if(UserLoginController.getUse() == 2) {
    		SearchId = UserDAO.UserIdSearch(txtSearchIdName.getText(), txtSearchIdPhone.getText());
    	}

    	if(SearchId == "1") {
    		CommonFunc.alertDisplay(1, "로그인 실패", "정보 미입력", "다시 입력해 주세요.");
    	} else if(SearchId == "2") {
    		CommonFunc.alertDisplay(1, "로그인 실패", "사용자가 없거나 정보가 다릅니다.", "다시 입력해 주세요.");
    	} else {
    		CommonFunc.alertDisplay(3, "ID 확인", txtSearchIdName.getText() +" 님의 아이디는 "+ SearchId + " 입니다.", "");
    	}
    }
    public void PwSearch(ActionEvent event) {
    	System.out.println(UserLoginController.getUse());
    	if(AdminLoginController.getUse() == 1) {
    		SearchPw = AdminDAO.AdminPwSearch(txtSearchPwId.getText(), txtSearchPwName.getText(), txtSearchPwPhone.getText());
    	} else if(UserLoginController.getUse() == 2) {
    		SearchPw = UserDAO.UserPwSearch(txtSearchPwId.getText(), txtSearchPwName.getText(), txtSearchPwPhone.getText());
    	}
 
    	if(SearchPw == "1") {
    		CommonFunc.alertDisplay(1, "로그인 실패", "정보 미입력", "다시 입력해 주세요.");
    	} else if(SearchPw == "2") {
    		CommonFunc.alertDisplay(1, "로그인 실패", "사용자가 없거나 정보가 다릅니다.", "다시 입력해 주세요.");
    	} else {
    		CommonFunc.alertDisplay(3, "PW 확인", txtSearchPwName.getText() +" 님의 비밀번호는 "+ SearchPw + " 입니다.", "");
    	}
    }
}
