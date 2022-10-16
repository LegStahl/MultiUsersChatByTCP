package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import Server.Server;


public class HelpServer extends Thread {
	
	
	private String nameUser;
	
	private Socket socket;
	
    private PrintWriter out;
    
    private BufferedReader in;
	
	public HelpServer( Socket socket, String nameUser ) {
		super();
	
		this.socket = socket;
		
		this.nameUser = nameUser;
		
        try {
            this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.out = new PrintWriter(this.socket.getOutputStream(), true);
        }
        catch (IOException e){
            e.getMessage();
        }
	}
	
	public void run() {
		String message;
		String nameToSend = new String();
		String newMessage = new String();
		try {
			while((message = in.readLine()) != null) {
				String check = SpecialProtocol.checkMessage(message);
				
				if(check == "oneuser") {
					for(int i = message.indexOf(" ") + 1; message.charAt(i) != ' '; i++) {
						nameToSend = nameToSend + message.charAt(i);
					}
					for(int i = message.indexOf(":") + 1; message.charAt(i) != '#'; i++) {
						newMessage = newMessage + message.charAt(i);
					}
					
						Server.sendOneUser(nameToSend, this, newMessage);
				}
				else if(check == "bye") {
					
					Server.deleteUser(this);
					
					socket.close();
					
					in.close();
					
					out.close();
				}
				else {
					Server.sendAllUsers(this, message);
				}
			}
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void sendMessage(String message) {
		out.println(message);
	}
	
	
	
}
