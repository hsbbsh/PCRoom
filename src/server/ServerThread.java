package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;

public class ServerThread extends Thread{
	Socket client;
	
	int count=0;
    String action;
    String obj;
    ObjectOutputStream out; //직렬화 객체
    ObjectInputStream in;
    Connection con;
	
    Message outMessage = new Message();
    Message inMessage = new Message();
    
    ServerThread(Socket client, int count, Connection con) {
        this.client = client;
        this.count=count;
        this.con=con;
    }
    @Override
	public void run() {
		try {
			out = new ObjectOutputStream(client.getOutputStream()); //보내는 소켓
      		in = new ObjectInputStream(client.getInputStream()); //받는 소켓
      		System.out.println("직렬화 슬레이브 서버"+(count+1)+"번 실행중");
      		
      		inMessage = (Message)in.readObject(); //역직렬화
      		boolean isLogout=false;
            
            while(true) {
            	System.out.println(inMessage.getMessage());
            	switch(inMessage.getMessage()) {
            		case "start":
            			outMessage.setMessage("ready");
                		out.writeObject(outMessage); //직렬화
                		out.flush();
                		out.reset();
                		inMessage = (Message)in.readObject(); //역직렬화
                		break;
            		case "login":
            			
            			break;
            		case "logout":
            			isLogout=true;
            			break;
            		case "chat":
            			break;
            			
            		case "order":
            			break;
            			
            			
            	}
            	if(isLogout) {
                	break;
                }
            }
            
		} catch(Exception e) {
        	e.printStackTrace();
        }
        finally {
			try {
				client.close();
				System.out.println("슬레이브 서버"+(count+1)+"번 종료");
			}catch(IOException e) {
				System.out.println("소켓 오류");
			}
		}
	}
}
