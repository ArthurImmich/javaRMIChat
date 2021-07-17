package rmi.chat;

public class Chat {
	
	
	public static void main(String[] args) {
		
		UserChat userChat;
		
		try {
			//Shows the GetNameDialog GUI to get the user input and creates a new user
			userChat = new UserChat();
            RoomGUI roomGui = new RoomGUI(userChat);
			roomGui.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}