
package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.charge.ChargeDAO;
import server.Message;

public class UserMainController implements Initializable{
    @FXML
    private Label txtSeatNo;
    @FXML
    private Button btnSeatChange;
    @FXML
    private Button btnStop;
    @FXML
    private Label txtUserId;
    @FXML
    private Label txtUserUpdate;
    @FXML
    private Label txtFee;
    @FXML
    private Label txtRemainTime;
    @FXML
    private Button btnItemOrder;
    @FXML
    private Button btnTimeAdd;
    @FXML
    private Button btnDetailFee;
    @FXML
    private Button btnChat;
    
    private ExecutorService executorService;
    
    ChargeDAO dao = new ChargeDAO();
    String currentSeatNo = UserLoginController.getUserSeatNo();
	String currentUserId = UserLoginController.getTxtUserId();
	private int prepaidMoney;
	private int countdownSeconds;
	private boolean login = true;
	private int i;
	private Socket socket;
	/*
	ObjectOutputStream out; //직렬화 객체
    ObjectInputStream in;
    */
    private String count;
    private PrintWriter os;
	private BufferedReader in;
    
    Message outMessage = new Message();
    Message inMessage = new Message();
	
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	
    	txtSeatNo.setText(currentSeatNo);
    	txtUserId.setText(currentUserId);
    	
    	connect();
    	
    	txtUserUpdate.setOnMouseClicked(event->{
    		txtUserUpdateAction(event);
    	});
    	
    	btnSeatChange.setOnAction(event -> {
			BtnSeatChangeAction(event);
		});
    	btnStop.setOnAction(event -> {
    		BtnStopAction(event);
		});
    	btnItemOrder.setOnAction(event -> {
    		BtnItemOrderAction(event);
		});
    	btnTimeAdd.setOnAction(event -> {
    		BtnTimeAddAction(event);
		});
    	btnDetailFee.setOnAction(event -> {
    		BtnDetailFeeAction(event);
		});
    	btnChat.setOnAction(event -> {
    		BtnChatAction(event);
		});	
	}

	public void connect() {
    	ChargeDAO dao = new ChargeDAO();
    	countdownSeconds = dao.selectAvailableTime(currentUserId);
    	prepaidMoney = dao.selectPrepaidMoney(currentUserId);

    	try {
        	socket = new Socket("127.0.0.1", 1111);
        	/*
        	out = new ObjectOutputStream(socket.getOutputStream());
        	in = new ObjectInputStream(socket.getInputStream());
        	*/
            os = new PrintWriter(socket.getOutputStream(), true);
        	System.out.println("서버에 연결하였습니다.");
        } catch (IOException e1) {
        	System.out.println("서버연결오류");
        	e1.printStackTrace();
        }

    	System.out.println(prepaidMoney);
    	System.out.println(countdownSeconds);
    	Thread t1 = new Thread() {
			@Override
			public void run() {
				try {
					os.println("ID " + currentUserId);
					os.flush();
					while (login) {
						// int countdownSeconds = hour * 3600 + minute * 60 + second;
						if (countdownSeconds == 0) {
							System.out.println("if");
							login = false;
							os.println("EXIT " + currentUserId);
							os.flush();
							CommonFunc.alertDisplay(3, "이용종료", "이용시간이 지났습니다.", "충전하세요");
							Thread.sleep(2000);
							login = false;
							if (login == false) {
								try {
									os.close();
									socket.close();
									System.out.println("연결끊겼니 " + socket.isConnected());
									System.out.println("소켓닫혔니 " + socket.isClosed());

								} catch (IOException e) {
									e.getStackTrace();
								} finally {
									login = true;
								}
							}
						} else {
							System.out.println("else");
							if (currentUserId == null) {
								CommonFunc.alertDisplay(1, "오류", "X", "X");
							} else {
								for (i = countdownSeconds; i >= 0; i--) {

									Thread.sleep(1000);

									Platform.runLater(() -> {
										txtRemainTime.setText(
												String.valueOf(i / 3600 + ":" + i % 3600 / 60 + ":" + i % 3600 % 60));
										txtFee.setText(prepaidMoney + "원");
									});
								}
								
							}
						}
					} // while
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		t1.start();
    	/*
		try {
			outMessage.setMessage("start");
			out.writeObject(outMessage); //직렬화
            out.flush();
            out.reset();
  
            while (login) {
				inMessage = (Message)in.readObject();
				switch(inMessage.getMessage()) {
					case "first":
						outMessage.setMessage("login");
						out.writeObject(outMessage); //직렬화
			            out.flush();
			            out.reset();
						break;
					case "id":
						outMessage.setMessage(currentUserId);
						out.writeObject(outMessage); //직렬화
			            out.flush();
			            out.reset();
			            outMessage.setMessage(currentSeatNo);
						out.writeObject(outMessage); //직렬화
			            out.flush();
			            out.reset();
			            break;		
				} // switch
						// int countdownSeconds = hour * 3600 + minute * 60 + second;
				if (countdownSeconds == 0) {
					CommonFunc.alertDisplay(1, "오류", "X", "X");
				} else {
					if (currentUserId == null) {
						CommonFunc.alertDisplay(1, "오류", "X", "X");
					} else {
						for (i = countdownSeconds; i >= 0; i--) {

							Thread.sleep(1000);
	
							Platform.runLater(() -> {
								txtRemainTime.setText(
									String.valueOf(i / 3600 + ":" + i % 3600 / 60 + ":" + i % 3600 % 60));
									txtFee.setText(prepaidMoney + "원");
							});
						} // for
					} //else
				} // else
			} // while
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	} //connect
	
    
    public void BtnSeatChangeAction(ActionEvent event) {
    	
    }
    
    private void txtUserUpdateAction(MouseEvent event) {
		
		
	}
    public void BtnChatAction(ActionEvent event) {
		os.println("CHAT " + currentSeatNo);
		os.flush();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		/*
		Parent ChatView = null;
	    Stage ChatStage = null;
	    try {
	    	ChatView = FXMLLoader.load(getClass().getResource("/view/user_chat.fxml"));
	        Scene scene = new Scene(ChatView);
	        ChatStage = new Stage();
	        ChatStage.setTitle("메세지");
	        ChatStage.setScene(scene);
	        ChatStage.setResizable(false);
	        ChatStage.show();
	     } catch(Exception e) {
	        e.getStackTrace();
	     }*/
    }
    public void BtnStopAction(ActionEvent event) {
    	os.println("EXIT " + currentUserId);
		os.flush();
		login = false;
		if (login == false) {
			try {
				os.close();
				socket.close();
				System.out.println("연결끊겼니 " + socket.isConnected());
				System.out.println("소켓닫혔니 " + socket.isClosed());

			} catch (IOException e) {
				e.getStackTrace();
			} finally {
				login = true;
			}
		}
		((Stage) btnStop.getScene().getWindow()).close();
    }
    public void BtnItemOrderAction(ActionEvent event) {
    	Parent LoginView = null;
        Stage LoginStage = null;
        try {
        	LoginView = FXMLLoader.load(getClass().getResource("/view/user_order.fxml"));
        	Scene scene = new Scene(LoginView);
        	LoginStage = new Stage();
        	LoginStage.setTitle("먹거리 주문");
        	LoginStage.setScene(scene);
        	LoginStage.setResizable(false);
        	LoginStage.show();
        } catch(Exception e) {
        	e.getStackTrace();
        }
    }
    public void BtnTimeAddAction(ActionEvent event) {
    	
    }
    public void BtnDetailFeeAction(ActionEvent event) {
    	
    }

}