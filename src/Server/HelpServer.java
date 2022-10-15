package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

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
		try {
			while((message = in.readLine()) != null) {
				
			}
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void sendMessage(String message) {
		out.println(message);
	}
	
	
	
}
