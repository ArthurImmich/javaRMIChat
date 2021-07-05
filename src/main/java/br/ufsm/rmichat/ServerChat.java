package br.ufsm.rmichat;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerChat extends java.rmi.server.UnicastRemoteObject implements IServerChat {
    
    final static String SERVER_URL = "rmi://127.0.0.1/ServerChat";
    
    ServerChat() throws RemoteException {
        try {
            LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
            Naming.bind(SERVER_URL, this);
        } catch (AlreadyBoundException | MalformedURLException ex) {
            Logger.getLogger(ServerChat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private ArrayList<String> roomList;

    @Override
    public ArrayList<String> getRooms() throws RemoteException {
        return this.roomList;
    }

    @Override
    public void createRoom(String roomName) throws RemoteException {
            // TODO Auto-generated method stub

    }

    public static void main(String[] args) {
        try {
            new ServerChat();
        } catch (RemoteException ex) {
            Logger.getLogger(ServerChat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}