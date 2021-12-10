package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import dbcon.DatabaseUtil;
import java.sql.Connection;

public class MasterServer {
	public MasterServer(){
		ArrayList<ServerThread> serverList = new ArrayList<>();
		DatabaseUtil dbcon = new DatabaseUtil();
		Connection con = dbcon.getConnection();
		int count=0;
        System.out.println("마스터 서버가 실행중입니다.");
		try {
 
            ServerSocket server = new ServerSocket(6001);
            while (true) {
                Socket client = server.accept();
                ServerThread serverThread = new ServerThread(client, count, con);
                serverList.add(serverThread);
                serverList.get(count).start();
                count++;
            }
 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
