package User;

import java.net.InetAddress;
import java.util.Scanner;

public class User {
	public static String nameOfUser;
	
	public static void main(String[] argv) {
		try {
			InetAddress ipServer;
			
			ipServer = InetAddress.getByName(argv[0]);
			
			int serverPort = Integer.parseInt(argv[1]);
			
			Scanner scanner = new Scanner (System.in);
			
			String nameUser;
			
			System.out.println("Hello! Welcome to multi chat!");
			System.out.print("You need to choose your name to continue:");
			nameUser = scanner.nextLine();
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
