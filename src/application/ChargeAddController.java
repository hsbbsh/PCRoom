package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import model.charge.ChargeDAO;
import model.charge.ChargeDTO;

public class ChargeAddController implements Initializable{

    @FXML
    private ComboBox<String> cbxCharge;
    @FXML
    private Button btnChargeAdd;
    
    ChargeDAO dao = new ChargeDAO();
    ChargeDTO dto = new ChargeDTO();
    private String id = UserListController.getId();
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	cbxCharge.setItems(FXCollections.observableArrayList("1시간 1,000원", "2시간 2,000원", "3시간 3,000", "5시간 5,000"));
    	btnChargeAdd.setOnAction(event ->{
    		handlerBtnChargeAdd(event);
    	});
    }

	public void handlerBtnChargeAdd(ActionEvent event) {
		if(cbxCharge.getSelectionModel().getSelectedItem().equals("1시간 1,000원")) {
			dto.setPrepaidMoney(1000);
		}else if(cbxCharge.getSelectionModel().getSelectedItem().equals("2시간 2,000원")) {
			dto.setPrepaidMoney(2000);
		}else if(cbxCharge.getSelectionModel().getSelectedItem().equals("3시간 3,000원")) {
			dto.setPrepaidMoney(3000);
		}else if(cbxCharge.getSelectionModel().getSelectedItem().equals("5시간 5,000원")) {
			dto.setPrepaidMoney(5000);
		}

		dto.setUserId(id);
		dao.getTodayFirstCharge(dto);
	} 
}
