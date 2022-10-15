package Server;

import java.io.BufferedReader;
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
				
				nameReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				
				String clientName = nameReader.readLine();
				
				HelpServer helpServer = new HelpServer(clientSocket, clientName);
				
				listOfUsers.put(clientName, helpServer);
				
			}
		
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void sendAllUsers(HelpServer sender, String message) {
		for (Map.Entry<String, HelpServer> entry : listOfUsers.entrySet()) {
			if(!(entry.getValue().equals(sender))) {
				entry.getValue().sendMessage(message);
			}
			else {
				continue;
			}
	    }
	}
	
	public static void sendOneUser(String name, String message) {
		HelpServer check = listOfUsers.get(name);
		if(check != null) {
			check.sendMessage(message);
		}
	}
	
}




