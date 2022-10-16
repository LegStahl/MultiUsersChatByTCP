package Server;

public class SpecialProtocol {
	public static String checkMessage (String message) {
		String command;
		
		if(message.startsWith("@sendUser")) {
			command = "oneuser";
		}
		else if(message.startsWith("@bye")) {
			command = "bye";
		}
		else {
			command = "all";
		}
		return command;
	}
}
