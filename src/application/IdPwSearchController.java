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
    		CommonFunc.alertDisplay(1, "�α��� ����", "���� ���Է�", "�ٽ� �Է��� �ּ���.");
    	} else if(SearchId == "2") {
    		CommonFunc.alertDisplay(1, "�α��� ����", "����ڰ� ���ų� ������ �ٸ��ϴ�.", "�ٽ� �Է��� �ּ���.");
    	} else {
    		CommonFunc.alertDisplay(3, "ID Ȯ��", txtSearchIdName.getText() +" ���� ���̵�� "+ SearchId + " �Դϴ�.", "");
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
    		CommonFunc.alertDisplay(1, "�α��� ����", "���� ���Է�", "�ٽ� �Է��� �ּ���.");
    	} else if(SearchPw == "2") {
    		CommonFunc.alertDisplay(1, "�α��� ����", "����ڰ� ���ų� ������ �ٸ��ϴ�.", "�ٽ� �Է��� �ּ���.");
    	} else {
    		CommonFunc.alertDisplay(3, "PW Ȯ��", txtSearchPwName.getText() +" ���� ��й�ȣ�� "+ SearchPw + " �Դϴ�.", "");
    	}
    }
}
