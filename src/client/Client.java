package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import application.CommonFunc;
import application.UserLoginController;
import javafx.application.Platform;
import model.charge.ChargeDAO;
import model.charge.ChargeDTO;
import server.Message;



public class Client {
	String currentSeatNo = UserLoginController.getUserSeatNo();
	String currentUserId = UserLoginController.getTxtUserId();
	private int prepaidMoney;
	private int countdownSeconds;
	private boolean login = true;
	
	Socket socket;
	ObjectOutputStream out; //직렬화 객체
    ObjectInputStream in;
    
    Message outMessage = new Message();
    Message inMessage = new Message();
    
    public Client(){
    	try {
    		socket = new Socket("127.0.0.1", 9999);
    		out = new ObjectOutputStream(socket.getOutputStream()); //보내는 소켓
       	 	in = new ObjectInputStream(socket.getInputStream()); //받는 소켓
    		System.out.println("서버에 연결하였습니다.");
    	} catch (IOException e1) {
    		System.out.println("서버연결오류");
    		e1.printStackTrace();	
    	}
    }
    
    public void Chat(int SeatNo, String action) {
    	
    }
    /*
    public void Order(OrderDTO orderDTO, String action) {
    	
    }*/
}
