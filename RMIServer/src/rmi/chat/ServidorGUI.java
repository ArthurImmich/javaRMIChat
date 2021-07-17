package rmi.chat;

import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.awt.event.ActionEvent;

public class ServidorGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6489900659699377809L;
	private JPanel contentPane;
	private JList<String> roomsList;
	ServerChat server;

	/**
	 * Launch the application.
	 */
    public static void main(String[] args) {
        try {
        	ServidorGUI servidorGUI = new ServidorGUI();
			servidorGUI.server = new ServerChat();
			servidorGUI.setVisible(true);
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
        }
    }
	
	
	/**
	 * Create the frame.
	 */
	public ServidorGUI() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 406, 284);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.roomsList = new JList<String>();
		roomsList.setBounds(10, 8, 266, 226);
		roomsList.setBorder(new TitledBorder(null, "Salas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(roomsList);
		
		JButton btnNewButton_1 = new JButton("Desligar");
		btnNewButton_1.setBounds(286, 211, 93, 23);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(ABORT);
			}
		});
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Atualizar");
		btnNewButton_2.setBounds(286, 21, 93, 23);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					setRoomsList(server.getRooms());
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Não foi possível atualizar a lista de salas!");
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton = new JButton("Fechar");
		btnNewButton.setBounds(286, 51, 93, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String room = roomsList.getSelectedValue();
				if(room == null) {
					JOptionPane.showMessageDialog(null, "Selecione uma sala para fechar!");
					return;
				}
				try {
					IRoomChat roomChat = (IRoomChat) server.registry.lookup(room);
					roomChat.closeRoom();
					server.registry.unbind(room);
					server.removeRoom(room);
				} catch (RemoteException | NotBoundException e1) {
					JOptionPane.showMessageDialog(null, "Erro ao encontrar sala!");
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(btnNewButton);
	}

	public void setRoomsList(ArrayList<String> rooms) {
		DefaultListModel<String> newRoomsList = new DefaultListModel<>();
		newRoomsList.addAll(rooms);
		this.roomsList.setModel(newRoomsList);
	}
}
