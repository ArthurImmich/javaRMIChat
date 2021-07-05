package br.ufsm.rmichat;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

public class UserChat extends java.rmi.server.UnicastRemoteObject implements IUserChat {

	String name;
	IServerChat server;
        final static String SERVER_URL = "rmi://127.0.0.1/ServerChat";
        
	UserChat() throws RemoteException {
            try {
                LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
                this.server = (IServerChat) Naming.lookup(SERVER_URL);
            } catch (NotBoundException | MalformedURLException ex) {
                Logger.getLogger(UserChat.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
	@Override
	public void deliverMsg(String senderName, String msg) throws RemoteException {
		
	}
	
	public static void main(String[] args) throws RemoteException {
                getNameDialog dialogName = new getNameDialog(new javax.swing.JFrame(), true);
                dialogName.setVisible(true);
                UserChat userChat = new UserChat();
                userChat.name = dialogName.getName();
                roomFrame roomsFrame = new roomFrame();
                roomsFrame.setRoomsList(userChat.server.getRooms().toArray(new String[0]));
                roomsFrame.setVisible(true);
	}

}
