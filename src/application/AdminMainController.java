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
    // MasterServer ms = new MasterServer(); // master서버 실행
    
    private ExecutorService executorService;
    private int countdownSeconds;
    // 로그인 감지
    private ServerSocket serverSocket;
	private Socket socket;
	private List<Player> list = new Vector<Player>();
	private boolean login = false;
	private int i;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1){
		lbCurrentWorkerName.setText(AdminLoginController.getCurrentAdminId());
		
		serverSocket();
		
		// 로그아웃 버튼
		btnAdminLogout.setOnAction(event -> {
			BtnAdminLogoutAction(event);
		});
		// 매출 관리 버튼
		btnIncome.setOnAction(event -> {
			BtnIncomeAction(event);
		});
		// 관리자 메뉴 버튼
		btnAdminMenu.setOnAction(event -> {
			BtnAdminMenuAction(event);
		});
		// 회원 관리 버튼
		btnUserManage.setOnAction(event -> {
			BtnUserManageAction(event);
		});
		// 주문 확인 버튼
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
						System.out.println("접속대기중...");
						Socket client = serverSocket.accept();
							Player player = new Player(client);
							list.add(player);
//						System.out.println(LoginController.getCurrentSeatNo);
							System.out.println("[클라이언트 접속]"
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
				System.out.println("문제발생");
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
								System.out.println("손님이 로그인했습니다.");
								System.out.println(str);
								String command = str.substring(3);
								userId = command;
								ChargeDAO cdo = new  ChargeDAO();
								seatNo = cdo.getLoginUserSeatNo(userId);
								System.out.println("좌석번호 " + seatNo);		// 디버깅
						    	countdownSeconds = cdo.selectAvailableTime(userId);
						    	int prepaidMoney = cdo.selectPrepaidMoney(userId);
//								int prepaidMoney = 2000;	// 디버깅
								System.out.println("prepaidMoney "+ prepaidMoney);
								startTime = true;
								// 남은 시간 표시
								Runnable runn = new Runnable() {

									@Override
									public void run() {
										if (userId == null) {// 4
											CommonFunc.alertDisplay(1, "오류", "유저 이름이 없습니다.", "다시 확인해 주세요.");
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
														///////db 불러서 시간 저장 하는 자리 /////////////
														
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
														///////db 불러서 시간 저장 하는 자리 /////////////
														
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
														///////db 불러서 시간 저장 하는 자리 /////////////
														
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
										User01.setText("빈좌석");
										Time01.setText("00:00");

									});
								} else if (seatNo.equals("2")) {
									Platform.runLater(() -> {// 6
										User02.setText("빈좌석");
										Time02.setText("00:00");

									});
								} else if (seatNo.equals("8")) {
									Platform.runLater(() -> {// 6
										User08.setText("빈좌석");
										Time08.setText("00:00");

									});
								}
		//						System.out.println("소켓끊김?"+socket.isClosed());
								socket.close();
								for( Player player : list) {
									if(player.userId.equals(userId)) {
										list.remove(player);
									}
								}
								break;

//								System.out.println("손님이 로그아웃했습니다.");

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
					} // readLine끝
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
			                out.writeObject(outMessage); //직렬화
			                out.flush();
			                out.reset();
			                inMessage = (Message)in.readObject(); //역직렬화
			                break;
						case "login":
							outMessage.setMessage("id");
			                out.writeObject(outMessage); //직렬화
			                out.flush();
			                out.reset();
							userId = ((Message)in.readObject()).toString();
							seatNo = ((Message)in.readObject()).toString();
							System.out.println(userId + " ");
							System.out.println("손님이 로그인했습니다.");
									
			            	ChargeDAO cdo = new ChargeDAO();
			            	int prepaidMoney = cdo.selectAvailableTime(userId);
			            			
			            	startTime = true;
									// 남은 시간 표시
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
										CommonFunc.alertDisplay(1, "오류", "유저 이름이 없습니다.", "다시 확인해 주세요.");
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
											User01.setText("빈 좌 석");
											Time01.setText("00:00:00");

										});
									} else if (seatNo == "2") {
										Platform.runLater(() -> {
											User02.setText("빈 좌 석");
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
									
								// 채팅
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
							//System.out.println("슬레이브 서버"+(count+1)+"번 종료");
						}catch(IOException e) {
							System.out.println("소켓 오류");
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
	        LoginStage.setTitle("메세지");
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
			mainStage.setTitle("관리자 로그인");
			mainStage.setScene(scene);
			mainStage.setResizable(false);
			
			// 현재스테이지(기존창)를 닫고 새로운 창을 연다.
			((Stage) btnAdminLogout.getScene().getWindow()).close();
			mainStage.show();
			
		} catch (IOException e) {
			CommonFunc.alertDisplay(1, "로그아웃 실패", "로그아웃할 수 없습니다.", e.toString());
		}
    }
	public void BtnIncomeAction(ActionEvent event) {
		Parent mainView = null;
		Stage mainStage = null;
		try {
			mainView = FXMLLoader.load(getClass().getResource("/view/income.fxml"));
			Scene scene = new Scene(mainView);
			mainStage = new Stage();
			mainStage.setTitle("매출 관리");
			mainStage.setScene(scene);
			mainStage.setResizable(false);

			mainStage.show();
		} catch (IOException e) {
			CommonFunc.alertDisplay(1, "매출관리 실패", "매출관리를 로드할 수 없습니다.", e.toString());
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
			mainStage.setTitle("회원 관리");
			mainStage.setScene(scene);
			mainStage.setResizable(false);

			mainStage.show();
		} catch (IOException e) {
			CommonFunc.alertDisplay(1, "회원관리 실패", "회원관리를 로드할 수 없습니다.", e.toString());
		}
	}

	public void BtnOrderCheckAction(ActionEvent event) {
		Parent mainView = null;
		Stage mainStage = null;
		try {
			mainView = FXMLLoader.load(getClass().getResource("/view/order_list.fxml"));
			Scene scene = new Scene(mainView);
			mainStage = new Stage();
			mainStage.setTitle("주문 관리");
			mainStage.setScene(scene);
			mainStage.setResizable(false);

			mainStage.show();
		} catch (IOException e) {
			CommonFunc.alertDisplay(1, "회원관리 실패", "회원관리를 로드할 수 없습니다.", e.toString());
		}
	}
	public void stopServer() {
		try {
			// 현재 작동 중인 모든 소켓 닫기
			Iterator<Player> iterator = list.iterator();
			while(iterator.hasNext()) {
				Player player = iterator.next();
				player.socket.close();
				iterator.remove();
			}
			// 서버 소켓 객제 닫기
			if(serverSocket != null && !serverSocket.isClosed()) {
				serverSocket.close();
			}
			// 쓰레드 풀 종료하기
			if(executorService != null && !executorService.isShutdown()) {
				executorService.shutdown();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
