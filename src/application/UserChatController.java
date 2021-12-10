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
			System.out.println("접속 시도");
			clientSocket = new Socket("127.0.0.1",1010);
			System.out.println("접속 완료");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void StreamSetting() { // O
		try {
			dataInputStream = new DataInputStream(clientSocket.getInputStream()); // clientSocket에 InputStream 객체를 연결
			dataOutputStream = new DataOutputStream(clientSocket.getOutputStream()); //clientSocket에 OutputStream 객체를 연결
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

						dataOutputStream.writeUTF(sendData);//연결된 출력스트림에 메세지 실어보냄
						txtArea.appendText("나 : "+ sendData +"\n");
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
						String recvData = dataInputStream.readUTF();//연결된 InputSteram 객체의 readUTF 메소드를 호출하여 데이터 읽어들임
						txtArea.appendText("카운터 : "+ recvData + "\n");
					} catch (Exception e) {
					}
				}
			}
		}).start();
	}
	public void closeAll() {
		try {
			//소켓 사용 후 반납
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
