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

    private ServerSocket serverSocket; //���� ����(���񽺸� �����ϱ� ���� �뵵) ����
	private Socket clientSocket;//������ ������ ����Ǵ�, Ŭ���̾�Ʈ�� ����� ���� ����

	private DataInputStream dataInputStream;//������ ���� ������
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
			serverSocket = new ServerSocket(1010);//������ ���ε�. IP�ּҸ� ���ָ� localhost�� default ��.
			clientSocket = serverSocket.accept(); // ���Ʈ�� ����� Ŭ���̾�Ʈ�� �����ϸ� �ش� Ŭ���̾�Ʈ�� ������ ������ �����Ͽ� ����. �̰�  clientSocket�� ����.
			//���������� ���Ͽ� ���� �Ϸ�� ����
			System.out.println("Ŭ���̾�Ʈ ���� ����");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void closeAll() { // O
		try {
			serverSocket.close(); //���� ��� �� �ݳ�
			clientSocket.close();
			dataInputStream.close();
			dataOutputStream.close();
		}catch(Exception e) {
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

	public void dataRecv() {
		new Thread(new Runnable() {
			boolean isThread = true;
			@Override
			public void run() {
				while(isThread) {
					try {
						String recvData = dataInputStream.readUTF();//����� InputSteram ��ü�� readUTF �޼ҵ带 ȣ���Ͽ� ������ �о����
						txtArea.appendText(userId + "�� PC : "+ recvData +"\n");
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

						dataOutputStream.writeUTF(sendData);//����� ��½�Ʈ���� �޼��� �Ǿ��
						txtArea.appendText("�� : "+ sendData + "\n");

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
