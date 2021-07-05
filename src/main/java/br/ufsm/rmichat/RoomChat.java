package br.ufsm.rmichat;
import java.rmi.RemoteException;
import java.util.Map;

public class RoomChat extends java.rmi.server.UnicastRemoteObject implements IRoomChat  {
	
	protected RoomChat() throws RemoteException {
		super();
	}

	private Map<String, IUserChat> userList;

	@Override
	public void sendMsg(String usrName, String msg) throws RemoteException {
		// TODO Auto-generated method stub
	}

	@Override
	public void joinRoom(String usrName, IUserChat user) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void leaveRoom(String usrName) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getRoomName() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void closeRoom() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

}
