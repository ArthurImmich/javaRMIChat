package rmi.chat;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ServerChat extends java.rmi.server.UnicastRemoteObject implements IServerChat {
    
	private static final long serialVersionUID = -8380623031223339290L;
	Registry registry;
    private ArrayList<String> roomList;
    
    public ServerChat() throws RemoteException {
        super();
        try {
            this.roomList = new ArrayList<>();
            System.setProperty("java.security.policy","file:./rmi.policy");
            System.setSecurityManager(new SecurityManager());
            this.registry  = LocateRegistry.createRegistry(2020);
            this.registry.rebind("Servidor", this);
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
        }
    }
    
    public void removeRoom(String name) {
    	this.roomList.remove(this.roomList.indexOf(name));
    }
    
    @Override
    public ArrayList<String> getRooms() throws RemoteException {
        return this.roomList;
    }
    
    @Override
    public void createRoom(String roomName) throws RemoteException {
        if (this.roomList.contains(roomName)) {
            JOptionPane.showMessageDialog(null, "Sala já existe");
            return;
        }
        this.roomList.add(roomName);
        try {
			this.registry.rebind(roomName, new RoomChat(roomName));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
            JOptionPane.showMessageDialog(null, "Erro ao criar objeto remoto da sala");
            this.roomList.remove(this.roomList.indexOf(roomName));
			e.printStackTrace();
		}
    }
}