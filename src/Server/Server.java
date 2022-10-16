package Server;

import java.io.BufferedReader;
//import Server.HelpServer;
import java.io.IOException;

import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Server {
	
	public static Map<String, HelpServer> listOfUsers;
	
	
	
	public static void main(String[] argv) {
		
		
		
		int portNumber = Integer.parseInt(argv[0]);
		
		listOfUsers = new HashMap<>();
		
		try {
			BufferedReader nameReader;
			ServerSocket serverSocket= new ServerSocket(portNumber);
			while(true) {
			
				Socket clientSocket = serverSocket.accept();
				
				System.out.println("Server:35");
				
				nameReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				
				String clientName = nameReader.readLine();
				
				clientName = nameReader.readLine();
				
				HelpServer helpServer = new HelpServer(clientSocket, clientName);
				
				helpServer.start();
				
				System.out.println("Server:47");
				
				listOfUsers.put(clientName, helpServer);
				
			}
		
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void sendAllUsers(HelpServer sender, String message) {
		String fromWho = new String();
		for (Map.Entry<String, HelpServer> entry : listOfUsers.entrySet()) {
			if((entry.getValue().equals(sender))) {
				fromWho = entry.getKey();
				break;
			}
		}
		
		for (Map.Entry<String, HelpServer> entry : listOfUsers.entrySet()) {
			if(!(entry.getValue().equals(sender))) {
				entry.getValue().sendMessage("Private " + fromWho + " : " + message);
				System.out.println("Server:54");
			}
			else {
				System.out.println("Server:57");
				continue;
				
			}
	    }
	}
	
	public static void sendOneUser(String name, HelpServer send,  String message) {
		HelpServer check = listOfUsers.get(name);
		if(check != null) {
			
			String fromWho = new String();
			
			for (Map.Entry<String, HelpServer> entry : listOfUsers.entrySet()) {
				
				if(entry.getValue().equals(send)) {
					
					fromWho = entry.getKey();
					
					break;
				
				}
			}
	
			check.sendMessage("From " + fromWho + ":" + message);
		}
		else {
			
			send.sendMessage("There is no user witn this name");
			
		}
	}
	
	public static void deleteUser(HelpServer user) {
		
		String nameUser = new String();
		
		for (Map.Entry<String, HelpServer> entry : listOfUsers.entrySet()) {
			
			if(entry.getValue().equals(user)) {
				
				nameUser = entry.getKey();
				
				break;
			
			}
		}
		
		sendAllUsers(user, "has left the chat");
		
		listOfUsers.remove(nameUser);
		
	}
	
}




