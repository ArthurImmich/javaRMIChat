package rmi.chat;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

public class RoomChat extends java.rmi.server.UnicastRemoteObject implements IRoomChat {
	
	private Map<String, IUserChat> userList;
	private String roomName;
	 
	protected RoomChat(String name) throws RemoteException {
		super();
		this.userList = new HashMap<String, IUserChat>();
		this.roomName = name;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 35187782159996208L;

	@Override
	public void sendMsg(String usrName, String msg) throws RemoteException {
		userList.forEach((name, userChat) -> {
			try {
				userChat.deliverMsg(usrName, msg);
			} catch (RemoteException e) {
				System.out.println("Erro ao entregar mensagem de " + usrName + " para " + name);
				e.printStackTrace();
			}
		});
		
	}

	@Override
	public void joinRoom(String usrName, IUserChat user) throws RemoteException {
		this.userList.put(usrName, user);
	}

	@Override
	public void leaveRoom(String usrName) throws RemoteException {
		// TODO Auto-generated method stub
		this.userList.remove(usrName);
		sendMsg("SERVIDOR", usrName + " saiu da sala!");
	}

	@Override
	public String getRoomName() throws RemoteException {
		return this.roomName;
	}

	@Override
	public void closeRoom() throws RemoteException {
		sendMsg("SERVIDOR", "CLOSE");
		roomName = null;
		userList.clear();
	}
	
}
