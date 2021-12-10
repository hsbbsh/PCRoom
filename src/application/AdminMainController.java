package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.charge.ChargeDAO;
import server.Message;

public class AdminMainController implements Initializable {
	@FXML
    private Label lbCurrentTime;
    @FXML
    private Label lbCurrentWorkerName;
    @FXML
    private Button btnAdminLogout;
    @FXML
    private Rectangle recSeat01;
    @FXML
    private Label SeatNo01;
    @FXML
    private Label User01;
    @FXML
    private Label Time01;
    @FXML
    private Rectangle recSeat02;
    @FXML
    private Label SeatNo02;
    @FXML
    private Label User02;
    @FXML
    private Label Time02;
    @FXML
    private Rectangle recSeat03;
    @FXML
    private Label SeatNo03;
    @FXML
    private Label User03;
    @FXML
    private Label Time03;
    @FXML
    private Rectangle recSeat05;
    @FXML
    private Label SeatNo05;
    @FXML
    private Label User05;
    @FXML
    private Label Time05;
    @FXML
    private Rectangle recSeat04;
    @FXML
    private Label SeatNo04;
    @FXML
    private Label User04;
    @FXML
    private Label Time04;
    @FXML
    private Rectangle recSeat06;
    @FXML
    private Label SeatNo06;
    @FXML
    private Label User06;
    @FXML
    private Label Time06;
    @FXML
    private Rectangle recSeat07;
    @FXML
    private Label SeatNo07;
    @FXML
    private Label User07;
    @FXML
    private Label Time07;
    @FXML
    private Rectangle recSeat08;
    @FXML
    private Label SeatNo08;
    @FXML
    private Label User08;
    @FXML
    private Label Time08;
    @FXML
    private Rectangle recSeat10;
    @FXML
    private Label SeatNo10;
    @FXML
    private Label User10;
    @FXML
    private Label Time10;
    @FXML
    private Rectangle recSeat09;
    @FXML
    private Label SeatNo9;
    @FXML
    private Label User09;
    @FXML
    private Label Time09;
    @FXML
    private Rectangle recSeat11;
    @FXML
    private Label SeatNo11;
    @FXML
    private Label User11;
    @FXML
    private Label Time11;
    @FXML
    private Rectangle recSeat12;
    @FXML
    private Label SeatNo12;
    @FXML
    private Label User12;
    @FXML
    private Label Time12;
    @FXML
    private Rectangle recSeat13;
    @FXML
    private Label User13;
    @FXML
    private Label Time13;
    @FXML
    private Rectangle recSeat15;
    @FXML
    private Label SeatNo15;
    @FXML
    private Label User15;
    @FXML
    private Label Time15;
    @FXML
    private Rectangle recSeat14;
    @FXML
    private Label SeatNo14;
    @FXML
    private Label User14;
    @FXML
    private Label Time14;
    @FXML
    private Rectangle recSeat16;
    @FXML
    private Label SeatNo16;
    @FXML
    private Label User16;
    @FXML
    private Label Time16;
    @FXML
    private Rectangle recSeat17;
    @FXML
    private Label SeatNo17;
    @FXML
    private Label User17;
    @FXML
    private Label Time17;
    @FXML
    private Rectangle recSeat18;
    @FXML
    private Label SeatNo18;
    @FXML
    private Label User18;
    @FXML
    private Label Time18;
    @FXML
    private Rectangle recSeat20;
    @FXML
    private Label SeatNo20;
    @FXML
    private Label User20;
    @FXML
    private Label Time20;
    @FXML
    private Rectangle recSeat19;
    @FXML
    private Label SeatNo19;
    @FXML
    private Label User19;
    @FXML
    private Label Time19;
    @FXML
    private Button btnIncome;
    @FXML
    private Button btnAdminMenu;
    @FXML
    private Button btnUserManage;
    @FXML
    private Button btnOrderCheck;
    
    final DateFormat format = DateFormat.getInstance();
    // MasterServer ms = new MasterServer(); // master���� ����
    
    private ExecutorService executorService;
    private int countdownSeconds;
    // �α��� ����
    private ServerSocket serverSocket;
	private Socket socket;
	private List<Player> list = new Vector<Player>();
	private boolean login = false;
	private int i;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1){
		lbCurrentWorkerName.setText(AdminLoginController.getCurrentAdminId());
		
		serverSocket();
		
		// �α׾ƿ� ��ư
		btnAdminLogout.setOnAction(event -> {
			BtnAdminLogoutAction(event);
		});
		// ���� ���� ��ư
		btnIncome.setOnAction(event -> {
			BtnIncomeAction(event);
		});
		// ������ �޴� ��ư
		btnAdminMenu.setOnAction(event -> {
			BtnAdminMenuAction(event);
		});
		// ȸ�� ���� ��ư
		btnUserManage.setOnAction(event -> {
			BtnUserManageAction(event);
		});
		// �ֹ� Ȯ�� ��ư
		btnOrderCheck.setOnAction(event -> {
			BtnOrderCheckAction(event);
		});
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler() {
			@Override
			public void handle(Event event) {
				final Calendar cal = Calendar.getInstance();
				lbCurrentTime.setText(format.format(cal.getTime()));
			}
		}));
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}
	public void serverSocket() {
		try {
			serverSocket = new ServerSocket();
			serverSocket.bind(new InetSocketAddress("127.0.0.1", 1111));
		} catch (IOException e) {
			e.printStackTrace();
			if(!serverSocket.isClosed()) {
				stopServer();
			}
		}
		Runnable thread = new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						System.out.println("���Ӵ����...");
						Socket client = serverSocket.accept();
							Player player = new Player(client);
							list.add(player);
//						System.out.println(LoginController.getCurrentSeatNo);
							System.out.println("[Ŭ���̾�Ʈ ����]"
									+ client.getRemoteSocketAddress()
									+ ": "+ Thread.currentThread().getName());
			
					} catch (IOException e) {
						if (serverSocket.isClosed()) {
							break;
						}
						e.printStackTrace();
					}
				}

			}
		};
		executorService = Executors.newCachedThreadPool();
		executorService.submit(thread);
	}
	
	public class Player{
		private Socket socket = null;
		private String userId = null;
		
		/*
		ObjectOutputStream out;
		ObjectInputStream in;
		*/
		private BufferedReader in;
		private String seatNo;
		private boolean startTime = false;
		
		Message outMessage = new Message();
		Message inMessage = new Message();
		
		public Player(Socket socket) {
			this.socket = socket;
			receive();
		}
		void receive() {
			try {
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			} catch (IOException e1) {
				System.out.println("�����߻�");
			}
//			System.out.println("receive"+count++);
			
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					try {
						login = true;
						String str;
						while (login) {
							str = in.readLine();
							if (str.startsWith("ID")) {
								System.out.println("�մ��� �α����߽��ϴ�.");
								System.out.println(str);
								String command = str.substring(3);
								userId = command;
								ChargeDAO cdo = new  ChargeDAO();
								seatNo = cdo.getLoginUserSeatNo(userId);
								System.out.println("�¼���ȣ " + seatNo);		// �����
						    	countdownSeconds = cdo.selectAvailableTime(userId);
						    	int prepaidMoney = cdo.selectPrepaidMoney(userId);
//								int prepaidMoney = 2000;	// �����
								System.out.println("prepaidMoney "+ prepaidMoney);
								startTime = true;
								// ���� �ð� ǥ��
								Runnable runn = new Runnable() {

									@Override
									public void run() {
										if (userId == null) {// 4
											CommonFunc.alertDisplay(1, "����", "���� �̸��� �����ϴ�.", "�ٽ� Ȯ���� �ּ���.");
										} else {// 5
											if (seatNo.equals("1")) {
												while (startTime) {
													for (i = countdownSeconds; i >= 0; i--) {
														try {
															Thread.sleep(1000);
														} catch (InterruptedException e) {
															e.printStackTrace();
														}
														Platform.runLater(() -> {
															User01.setText(userId);
															Time01.setText(String.valueOf(
																	i / 3600 + ":" + i % 3600 / 60 + ":" + i % 3600 % 60));
														});
														if(startTime == false) {
															break;
														}
														//System.out.println("i="+i+" "+startTime);
														///////db �ҷ��� �ð� ���� �ϴ� �ڸ� /////////////
														
													} // for
		
												} // while
											} else if (seatNo.equals("2")) {
												while (startTime) {
													for (i = countdownSeconds; i >= 0; i--) {
														try {
															Thread.sleep(1000);
														} catch (InterruptedException e) {
															e.printStackTrace();
														}
														Platform.runLater(() -> {
															User02.setText(userId);
															Time02.setText(String.valueOf(
																	i / 3600 + ":" + i % 3600 / 60 + ":" + i % 3600 % 60));
														});
														
														if(startTime == false) {
															break;
														}
														//System.out.println("i="+i+" "+startTime);
														///////db �ҷ��� �ð� ���� �ϴ� �ڸ� /////////////
														
													} // for
		
												} // while
											} else if (seatNo.equals("8")) {
												while (startTime) {
													for (i = countdownSeconds; i >= 0; i--) {
														try {
															Thread.sleep(1000);
														} catch (InterruptedException e) {
															e.printStackTrace();
														}
														Platform.runLater(() -> {
															User08.setText(userId);
															Time08.setText(String.valueOf(
																	i / 3600 + ":" + i % 3600 / 60 + ":" + i % 3600 % 60));
														});
														if(startTime == false) {
															break;
														}
														//System.out.println("i="+i+" "+startTime);
														///////db �ҷ��� �ð� ���� �ϴ� �ڸ� /////////////
														
													} // for
		
												} // while
											} // if
		
										} // else
									}// run()
								}; // runn
								executorService.submit(runn);
//								Thread t1 = new Thread(runn);
//								t1.start();
							} else if (str.startsWith("EXIT")) {
								login = false;
								startTime = false;
								System.out.println(str);
									//no[no1] =0;
								try {
									Thread.sleep(1000);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								if (seatNo.equals("1")) {
									Platform.runLater(() -> {// 6
										User01.setText("���¼�");
										Time01.setText("00:00");

									});
								} else if (seatNo.equals("2")) {
									Platform.runLater(() -> {// 6
										User02.setText("���¼�");
										Time02.setText("00:00");

									});
								} else if (seatNo.equals("8")) {
									Platform.runLater(() -> {// 6
										User08.setText("���¼�");
										Time08.setText("00:00");

									});
								}
		//						System.out.println("���ϲ���?"+socket.isClosed());
								socket.close();
								for( Player player : list) {
									if(player.userId.equals(userId)) {
										list.remove(player);
									}
								}
								break;

//								System.out.println("�մ��� �α׾ƿ��߽��ϴ�.");

							} if (str.startsWith("CHAT")){
								System.out.println(str);
								Runnable chat = new Runnable() {
									@Override
									public void run() {
										Chat();
									}
								};
								executorService.submit(chat);
								
							}
						} // while
					} catch (IOException e1) {
						e1.printStackTrace();
					} // readLine��
				}// run
			};// runnable
//		Thread t1 = new Thread(runnable);
//		t1.start();
			executorService.submit(runnable);

		}// receive
		/*
		void receive() {
			
			try {
				
				out = new ObjectOutputStream(client.getOutputStream());
				in = new ObjectInputStream(client.getInputStream());
				

				login = true;
				while(login) {
					switch(inMessage.getMessage()) {
						case "start":
							outMessage.setMessage("first");
			                out.writeObject(outMessage); //����ȭ
			                out.flush();
			                out.reset();
			                inMessage = (Message)in.readObject(); //������ȭ
			                break;
						case "login":
							outMessage.setMessage("id");
			                out.writeObject(outMessage); //����ȭ
			                out.flush();
			                out.reset();
							userId = ((Message)in.readObject()).toString();
							seatNo = ((Message)in.readObject()).toString();
							System.out.println(userId + " ");
							System.out.println("�մ��� �α����߽��ϴ�.");
									
			            	ChargeDAO cdo = new ChargeDAO();
			            	int prepaidMoney = cdo.selectAvailableTime(userId);
			            			
			            	startTime = true;
									// ���� �ð� ǥ��
							if (prepaidMoney == 1000) {
								countdownSeconds = 3600;
							} else if (prepaidMoney == 2000) {
								countdownSeconds = 7200;
							} else if (prepaidMoney == 3000) {
								countdownSeconds = 10800;
							} else if (prepaidMoney == 5000) {
								countdownSeconds = 18000;
							}else if (prepaidMoney == 20000) {
								countdownSeconds = 86400;
							}
							Runnable runn = new Runnable() {
								@Override
								public void run() {
									if(userId == null) {
										CommonFunc.alertDisplay(1, "����", "���� �̸��� �����ϴ�.", "�ٽ� Ȯ���� �ּ���.");
									} else {
										if(seatNo == "1") {
											while(startTime) {
												for(i = countdownSeconds; i >= 0; i--) {
													try {
														Thread.sleep(1000);
													}catch (InterruptedException e) {
														e.printStackTrace();
													}
													Platform.runLater(() -> {
														User01.setText(userId);
														Time01.setText(String.valueOf(
																	i / 3600 + ":" + i % 3600 / 60 + ":" + i % 3600 % 60));
													});
													
													if(startTime == false) {
														break;
													}
															
												} // for
											} // while
										} // if
									} // else
								} // run
							}; // runn
							executorService.submit(runn);
							break;
								case "logout":
			            			login = false;
									startTime = false;
					
									try {
										Thread.sleep(1000);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
									if (seatNo == "1") {
										Platform.runLater(() -> {
											User01.setText("�� �� ��");
											Time01.setText("00:00:00");

										});
									} else if (seatNo == "2") {
										Platform.runLater(() -> {
											User02.setText("�� �� ��");
											Time02.setText("00:00:00");

										});
									} 
									client.close();
									for(Player player : list) {
										if(player.userId.equals(userId)) {
											list.remove(player);
										}
									}
									break;
									
								// ä��
								case "order":
									break;
									
								case "chat":
									break;
							} // switch 
						} // while
					} catch (IOException e) {
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
					finally {
						try {
							client.close();
							//System.out.println("�����̺� ����"+(count+1)+"�� ����");
						}catch(IOException e) {
							System.out.println("���� ����");
						}
					}

		} //receive*/
	} // class
	public void Chat(){
		Parent LoginView = null;
	    Stage LoginStage = null;
	    try {
	        LoginView = FXMLLoader.load(getClass().getResource("/view/user_list.fxml"));
	        Scene scene = new Scene(LoginView);
	        LoginStage = new Stage();
	        LoginStage.setTitle("�޼���");
	        LoginStage.setScene(scene);
	        LoginStage.setResizable(false);
	        LoginStage.show();
	     } catch(Exception e) {
	    	 e.getStackTrace();
	     }
	}
	public void BtnAdminLogoutAction(ActionEvent event) {
		stopServer();
		
		Parent mainView = null;
		Stage mainStage = null;
		try {
			mainView = FXMLLoader.load(getClass().getResource("/view/admin_login.fxml"));
			Scene scene = new Scene(mainView);
			mainStage = new Stage();
			mainStage.setTitle("������ �α���");
			mainStage.setScene(scene);
			mainStage.setResizable(false);
			
			// ���罺������(����â)�� �ݰ� ���ο� â�� ����.
			((Stage) btnAdminLogout.getScene().getWindow()).close();
			mainStage.show();
			
		} catch (IOException e) {
			CommonFunc.alertDisplay(1, "�α׾ƿ� ����", "�α׾ƿ��� �� �����ϴ�.", e.toString());
		}
    }
	public void BtnIncomeAction(ActionEvent event) {
		Parent mainView = null;
		Stage mainStage = null;
		try {
			mainView = FXMLLoader.load(getClass().getResource("/view/income.fxml"));
			Scene scene = new Scene(mainView);
			mainStage = new Stage();
			mainStage.setTitle("���� ����");
			mainStage.setScene(scene);
			mainStage.setResizable(false);

			mainStage.show();
		} catch (IOException e) {
			CommonFunc.alertDisplay(1, "������� ����", "��������� �ε��� �� �����ϴ�.", e.toString());
		}
    }
	public void BtnAdminMenuAction(ActionEvent event) {
    	
    }
	public void BtnUserManageAction(ActionEvent event) {
		Parent mainView = null;
		Stage mainStage = null;
		try {
			mainView = FXMLLoader.load(getClass().getResource("/view/user_list.fxml"));
			Scene scene = new Scene(mainView);
			mainStage = new Stage();
			mainStage.setTitle("ȸ�� ����");
			mainStage.setScene(scene);
			mainStage.setResizable(false);

			mainStage.show();
		} catch (IOException e) {
			CommonFunc.alertDisplay(1, "ȸ������ ����", "ȸ�������� �ε��� �� �����ϴ�.", e.toString());
		}
	}

	public void BtnOrderCheckAction(ActionEvent event) {
		Parent mainView = null;
		Stage mainStage = null;
		try {
			mainView = FXMLLoader.load(getClass().getResource("/view/order_list.fxml"));
			Scene scene = new Scene(mainView);
			mainStage = new Stage();
			mainStage.setTitle("�ֹ� ����");
			mainStage.setScene(scene);
			mainStage.setResizable(false);

			mainStage.show();
		} catch (IOException e) {
			CommonFunc.alertDisplay(1, "ȸ������ ����", "ȸ�������� �ε��� �� �����ϴ�.", e.toString());
		}
	}
	public void stopServer() {
		try {
			// ���� �۵� ���� ��� ���� �ݱ�
			Iterator<Player> iterator = list.iterator();
			while(iterator.hasNext()) {
				Player player = iterator.next();
				player.socket.close();
				iterator.remove();
			}
			// ���� ���� ���� �ݱ�
			if(serverSocket != null && !serverSocket.isClosed()) {
				serverSocket.close();
			}
			// ������ Ǯ �����ϱ�
			if(executorService != null && !executorService.isShutdown()) {
				executorService.shutdown();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
