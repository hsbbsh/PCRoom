package application;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UserChatController implements Initializable {
	@FXML
    private TextArea txtArea;

    @FXML
    private TextField txtField;

    @FXML
    private Button btnSend;

    @FXML
    private Button btnClose;
    
    private Socket clientSocket;
	private DataInputStream dataInputStream;
	private DataOutputStream dataOutputStream;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		txtArea.setEditable(false);
		connect();
		StreamSetting();
		btnSend.setOnAction(event -> {
			BtnSendAction(event);
		});
		btnClose.setOnAction(event -> {
			BtnCloseAction(event);
		});

	}
	public void connect() { // O
		try {
			System.out.println("���� �õ�");
			clientSocket = new Socket("127.0.0.1",1010);
			System.out.println("���� �Ϸ�");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void StreamSetting() { // O
		try {
			dataInputStream = new DataInputStream(clientSocket.getInputStream()); // clientSocket�� InputStream ��ü�� ����
			dataOutputStream = new DataOutputStream(clientSocket.getOutputStream()); //clientSocket�� OutputStream ��ü�� ����
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void dataSend() {  // O
		new Thread(new Runnable() {
			boolean isThread = true;
			@Override
			public void run() {
				while(isThread){
					try {
						String sendData = txtField.getText();

						dataOutputStream.writeUTF(sendData);//����� ��½�Ʈ���� �޼��� �Ǿ��
						txtArea.appendText("�� : "+ sendData +"\n");
					} catch (Exception e) {
					}
				}
			}
		}).start();
	}
	
	public void dataRecv() {

		new Thread(new Runnable() {
			boolean isThread = true;
			@Override
			public void run() {
				while(isThread) {
					try {
						String recvData = dataInputStream.readUTF();//����� InputSteram ��ü�� readUTF �޼ҵ带 ȣ���Ͽ� ������ �о����
						txtArea.appendText("ī���� : "+ recvData + "\n");
					} catch (Exception e) {
					}
				}
			}
		}).start();
	}
	public void closeAll() {
		try {
			//���� ��� �� �ݳ�
			clientSocket.close();
			dataInputStream.close();
			dataOutputStream.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void BtnCloseAction(ActionEvent event) {
		closeAll();
		((Stage) btnClose.getScene().getWindow()).close();
	}

	private void BtnSendAction(ActionEvent event) {
		dataSend();
	}
}
