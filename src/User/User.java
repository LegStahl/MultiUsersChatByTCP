package User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class User {
	public static String nameOfUser;
	
	public static boolean inChat;
	
	public static void main(String[] argv) {
		try {
			InetAddress ipServer;
			
			ipServer = InetAddress.getByName(argv[0]);
			
			int serverPort = Integer.parseInt(argv[1]);
			
			
			
			System.out.println("Hello! Welcome to multi chat!");
			System.out.print("You need to choose your name to continue:");
		
			Socket userSocket = new Socket(ipServer, serverPort);
            PrintWriter nameSender = new PrintWriter(userSocket.getOutputStream(), true);
            nameSender.println(userSocket);
			User.inChat = true;
			Sender sender = new Sender(userSocket);
			Receiver receiver = new Receiver(userSocket);
			System.out.println("If you want to send one message to certain person you will need to write: @sendUser name : message#");
			System.out.println("If you want to leave the chat you will have to write:@bye");
			sender.start();
			receiver.start();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}

class Sender extends Thread{
	
	private Socket userSocket;
	
	private BufferedReader input;
	
	private PrintWriter sendToServer;
	
	
	
	public Sender(Socket socket) throws IOException {
		super();
		
		userSocket = socket; 
		
		input = new BufferedReader(new InputStreamReader(System.in));
		
		sendToServer =  new PrintWriter(userSocket.getOutputStream(), true);
	}
	
	public void run() {
		try {
			while(User.inChat) {
                String message = this.input.readLine();
                sendToServer.println(message);

                if (message.equals("@bye")) {
                 
                    input.close();
                    sendToServer.close();
                    User.inChat = false;
                }
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
}


class Receiver extends Thread{
	
	private Socket server;
	
	private BufferedReader printer;
	
	public Receiver(Socket socket) throws IOException {
		
		server = socket;
		
		printer = new BufferedReader(new InputStreamReader(server.getInputStream()));
	}
	
	public void run() {
		try {
			
			while(User.inChat) {
				
				String message;
				
				while((message = printer.readLine()) != null) {
					
					System.out.println(message);
					
				}
				
			}
		}catch(Exception e) {
			
			System.out.println(e.getMessage());
			
		}
	}
	
}
