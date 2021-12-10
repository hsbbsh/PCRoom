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
	ObservableList seatNoList = FXCollections.observableArrayList("여성","남성");
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
    		CommonFunc.alertDisplay(3, "회원가입", "중복 확인", "사용가능한 아이디입니다.");
    		//txtAddId.setDisable(true);
    	}else if(UserDAO.UserDoubleCheck(txtAddId.getText()) == 0){
    		DoubleCheck = -1;
    		CommonFunc.alertDisplay(1, "회원가입", "아이디 중복", "다른 아이디를 입력해 주세요.");
    	}else if(UserDAO.UserDoubleCheck(txtAddId.getText()) == -1) {
    		CommonFunc.alertDisplay(1, "회원가입", "정보 미입력", "다시 입력해 주세요.");
    	}
    	
    }
    public void BtnUserAddAction(ActionEvent event) {
    	int check;
    	// 중복확인 O, 중복 X
    	if(DoubleCheck == 1) {
    		 birth= txtAddBirthY.getText() + "-" + txtAddBirthM.getText() + "-" + txtAddBirthD.getText();
    	     phone = txtAddPhone1.getText() + "-" + txtAddPhone2.getText() + "-" + txtAddPhone1.getText();
    	     System.out.println(birth);
    	     System.out.println(phone);
    	     check = UserDAO.UserAdd(txtAddName.getText(), txtAddId.getText(), txtAddPw.getText(),
    	    		 				cbxAddGender.getValue().toString(), birth, phone);
    	     // 입력안됨
    	     if(check == 1) {
    	    	 CommonFunc.alertDisplay(1, "회원가입 실패", "정보 미입력", "다시 입력해 주세요.");
    	     }else if(check == 2) {
    	    	 CommonFunc.alertDisplay(1, "회원가입 실패", "데이터베이스 연결 불가", "다시 입력해 주세요.");
    	     }else {
    	    	 CommonFunc.alertDisplay(3, "회원가입", "회원가입 성공", "서대 PC방을 찾아주셔서 감사합니다.");
    	     }
    	} else if(DoubleCheck == -1) { // 중복확인 O, 중복 O
    		CommonFunc.alertDisplay(1, "중복확인 실패", "아이디 중복", "다른 아이디를 입력해 주세요.");
    		
    	} else { // 중복확인 X
    		CommonFunc.alertDisplay(1, "회원가입 실패", "중복확인 안됨", "중복확인 해주세요.");
    	}
    }
    public void BtnCencelAction(ActionEvent event) {
    	
    }

}