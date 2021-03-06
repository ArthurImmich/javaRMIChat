package rmi.chat;

import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.JTextArea;

public class UserChat extends java.rmi.server.UnicastRemoteObject implements IUserChat {

	private static final long serialVersionUID = 1534830740544346450L;
	String name;
	Registry registry;
	IServerChat server;
    IRoomChat room;
    JTextArea messageArea;
	
	public UserChat() throws RemoteException {
        try {
            System.setProperty("java.security.policy","file:./rmi.policy");
            System.setSecurityManager(new SecurityManager());
            this.registry = LocateRegistry.getRegistry("192.168.2.111", 2020);
            this.server = (IServerChat) this.registry.lookup("Servidor");
            GetNameDialog getNameDialog = new GetNameDialog(this);
            String hostName = InetAddress.getLocalHost().getHostAddress();
            System.setProperty("java.rmi.server.hostname",  hostName);
            System.out.println("Ip do cliente: " + hostName);
			getNameDialog.setVisible(true);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
        }
    }
        
	@Override
	public void deliverMsg(String senderName, String msg) throws RemoteException {
		if(senderName.equals("SERVIDOR") && msg.equals("CLOSE")) {
			this.messageArea.append("["+senderName+"]: SALA FECHADA PELO SERVIDOR\n");
			this.room = null;
			this.messageArea = null;
			return;
		}
		this.messageArea.append("["+senderName+"]: " + msg + "\n");
	}
	
}
