package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.user.UserDAO;

public class UserAddController implements Initializable {
	ObservableList seatNoList = FXCollections.observableArrayList("����","����");
    @FXML
    private TextField txtAddId;
    @FXML
    private TextField txtAddPw;
    @FXML
    private ComboBox cbxAddGender;
    @FXML
    private TextField txtAddBirthY;
    @FXML
    private TextField txtAddBirthM;
    @FXML
    private TextField txtAddBirthD;
    @FXML
    private TextField txtAddPhone1;
    @FXML
    private TextField txtAddPhone2;
    @FXML
    private TextField txtAddPhone3;
    @FXML
    private TextField txtAddName;
    @FXML
    private Button btnAddUser;
    @FXML
    private Button btnCencel;
    @FXML
    private Button btnDoubleCheck;
    
    private int DoubleCheck = 0;
    
    private String birth;
    private String phone;
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	cbxAddGender.setItems(seatNoList);
    	btnDoubleCheck.setOnAction(event -> {
			BtnDoubleCheckAction(event);
		});
    	btnAddUser.setOnAction(event -> {
			BtnUserAddAction(event);
		});
    	btnCencel.setOnAction(event -> {
			BtnCencelAction(event);
		});
	}
    public void BtnDoubleCheckAction(ActionEvent event) {
    	System.out.println(UserDAO.UserDoubleCheck(txtAddId.getText()));
    	System.out.println(txtAddId.getText());
    	if(UserDAO.UserDoubleCheck(txtAddId.getText()) == 1) {
    		DoubleCheck = 1;
    		CommonFunc.alertDisplay(3, "ȸ������", "�ߺ� Ȯ��", "��밡���� ���̵��Դϴ�.");
    		//txtAddId.setDisable(true);
    	}else if(UserDAO.UserDoubleCheck(txtAddId.getText()) == 0){
    		DoubleCheck = -1;
    		CommonFunc.alertDisplay(1, "ȸ������", "���̵� �ߺ�", "�ٸ� ���̵� �Է��� �ּ���.");
    	}else if(UserDAO.UserDoubleCheck(txtAddId.getText()) == -1) {
    		CommonFunc.alertDisplay(1, "ȸ������", "���� ���Է�", "�ٽ� �Է��� �ּ���.");
    	}
    	
    }
    public void BtnUserAddAction(ActionEvent event) {
    	int check;
    	// �ߺ�Ȯ�� O, �ߺ� X
    	if(DoubleCheck == 1) {
    		 birth= txtAddBirthY.getText() + "-" + txtAddBirthM.getText() + "-" + txtAddBirthD.getText();
    	     phone = txtAddPhone1.getText() + "-" + txtAddPhone2.getText() + "-" + txtAddPhone1.getText();
    	     System.out.println(birth);
    	     System.out.println(phone);
    	     check = UserDAO.UserAdd(txtAddName.getText(), txtAddId.getText(), txtAddPw.getText(),
    	    		 				cbxAddGender.getValue().toString(), birth, phone);
    	     // �Է¾ȵ�
    	     if(check == 1) {
    	    	 CommonFunc.alertDisplay(1, "ȸ������ ����", "���� ���Է�", "�ٽ� �Է��� �ּ���.");
    	     }else if(check == 2) {
    	    	 CommonFunc.alertDisplay(1, "ȸ������ ����", "�����ͺ��̽� ���� �Ұ�", "�ٽ� �Է��� �ּ���.");
    	     }else {
    	    	 CommonFunc.alertDisplay(3, "ȸ������", "ȸ������ ����", "���� PC���� ã���ּż� �����մϴ�.");
    	     }
    	} else if(DoubleCheck == -1) { // �ߺ�Ȯ�� O, �ߺ� O
    		CommonFunc.alertDisplay(1, "�ߺ�Ȯ�� ����", "���̵� �ߺ�", "�ٸ� ���̵� �Է��� �ּ���.");
    		
    	} else { // �ߺ�Ȯ�� X
    		CommonFunc.alertDisplay(1, "ȸ������ ����", "�ߺ�Ȯ�� �ȵ�", "�ߺ�Ȯ�� ���ּ���.");
    	}
    }
    public void BtnCencelAction(ActionEvent event) {
    	
    }

}