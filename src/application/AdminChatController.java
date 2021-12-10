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

public class AdminChatController implements Initializable {
	@FXML
    private TextArea txtArea;

    @FXML
    private TextField txtField;

    @FXML
    private Button btnSend;

    @FXML
    private Button btnClose;

    private ServerSocket serverSocket; //서버 소켓(서비스를 제공하기 위한 용도) 생성
	private Socket clientSocket;//들어오는 정보가 저장되는, 클라이언트와 통신을 위한 소켓

	private DataInputStream dataInputStream;//서버가 받은 데이터
	private DataOutputStream dataOutputStream;
	
	String userId = UserLoginController.getUserSeatNo();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		serverSetting(); 
		StreamSetting();
		btnSend.setOnAction(event -> {
			BtnSendAction(event);
		});
		btnClose.setOnAction(event -> {
			BtnCloseAction(event);
		});

	}
	
	public void serverSetting() { // O
		try {
			serverSocket = new ServerSocket(1010);//생성과 바인드. IP주소를 안주면 localhost가 default 값.
			clientSocket = serverSocket.accept(); // 어셉트의 결과로 클라이언트가 접속하면 해당 클라이언트를 관리할 소켓을 생성하여 리턴. 이걸  clientSocket에 받음.
			//실질적으로 소켓에 접속 완료된 시점
			System.out.println("클라이언트 소켓 연결");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void closeAll() { // O
		try {
			serverSocket.close(); //소켓 사용 후 반납
			clientSocket.close();
			dataInputStream.close();
			dataOutputStream.close();
		}catch(Exception e) {
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

	public void dataRecv() {
		new Thread(new Runnable() {
			boolean isThread = true;
			@Override
			public void run() {
				while(isThread) {
					try {
						String recvData = dataInputStream.readUTF();//연결된 InputSteram 객체의 readUTF 메소드를 호출하여 데이터 읽어들임
						txtArea.appendText(userId + "번 PC : "+ recvData +"\n");
						System.out.println(recvData);
					} catch (Exception e) {
					}
				}
			}
		}).start();
	}
	
	public void dataSend() {
		new Thread(new Runnable() {
			boolean isThread = true;
			@Override
			public void run() {
				while(isThread){
					try {
						String sendData = txtField.getText();

						dataOutputStream.writeUTF(sendData);//연결된 출력스트림에 메세지 실어보냄
						txtArea.appendText("나 : "+ sendData + "\n");

					} catch (Exception e) {
					}
				}
			}
		}).start();
	}

	private void BtnCloseAction(ActionEvent event) {
		closeAll();
		
		((Stage) btnClose.getScene().getWindow()).close();
	}

	private void BtnSendAction(ActionEvent event) {
		dataSend();
	}
}
