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
    ObjectOutputStream out; //����ȭ ��ü
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
			out = new ObjectOutputStream(client.getOutputStream()); //������ ����
      		in = new ObjectInputStream(client.getInputStream()); //�޴� ����
      		System.out.println("����ȭ �����̺� ����"+(count+1)+"�� ������");
      		
      		inMessage = (Message)in.readObject(); //������ȭ
      		boolean isLogout=false;
            
            while(true) {
            	System.out.println(inMessage.getMessage());
            	switch(inMessage.getMessage()) {
            		case "start":
            			outMessage.setMessage("ready");
                		out.writeObject(outMessage); //����ȭ
                		out.flush();
                		out.reset();
                		inMessage = (Message)in.readObject(); //������ȭ
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
				System.out.println("�����̺� ����"+(count+1)+"�� ����");
			}catch(IOException e) {
				System.out.println("���� ����");
			}
		}
	}
}
