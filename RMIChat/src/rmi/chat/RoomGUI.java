package rmi.chat;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.awt.event.ActionEvent;

public class RoomGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5067460482627008573L;
	private JPanel contentPane;
	private JTextField createRoomInput;
	private JList<String> roomsList;
	
	public void setRoomsList(ArrayList<String> rooms) {
		DefaultListModel<String> newRoomsList = new DefaultListModel<>();
		newRoomsList.addAll(rooms);
		this.roomsList.setModel(newRoomsList);
	}

	/**
	 * Create the frame.
	 */
	public RoomGUI(UserChat userChat) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel roomsListPanel = new JPanel();
		roomsListPanel.setBorder(new TitledBorder(null, "Lista de Salas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		roomsListPanel.setBounds(5, 82, 424, 174);
		contentPane.add(roomsListPanel);
		roomsListPanel.setLayout(null);
		
		roomsList = new JList<String>();
        try {	
        	this.setRoomsList(userChat.server.getRooms());
    	} catch (RemoteException e2) {
			e2.printStackTrace();
		}
		roomsList.setBounds(10, 22, 404, 108);
		roomsListPanel.add(roomsList);
		JButton enterRoomButton = new JButton("Entrar");
		enterRoomButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String room = roomsList.getSelectedValue();
				if(room == null) {
					JOptionPane.showMessageDialog(null, "Selecione uma sala!");
					return;
				}
				try {
					userChat.room = (IRoomChat) userChat.serverRegistry.lookup(room);
					userChat.room.joinRoom(userChat.name, (IUserChat) userChat.userRegistry.lookup(userChat.name));
					ChatGUI chatGUI = new ChatGUI(userChat);
					chatGUI.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosing(WindowEvent e) {
							if(userChat.room != null) {								
								try {
									userChat.room.leaveRoom(userChat.name);
									userChat.messageArea = null;
									userChat.room = null;
								} catch (RemoteException e1) {
									e1.printStackTrace();
								}
							}
							setEnabled(true);
						}
					});
					chatGUI.setVisible(true);
					setEnabled(false);
				} catch (RemoteException | NotBoundException e1) {
					JOptionPane.showMessageDialog(null, "Não foi possível conectar a sala!");
					e1.printStackTrace();
				}
			}
		});
		enterRoomButton.setBounds(328, 141, 86, 23);
		roomsListPanel.add(enterRoomButton);
		
		JButton btnNewButton = new JButton("Atualizar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					setRoomsList(userChat.server.getRooms());
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Erro ao atualizar lista de salas!");
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(229, 141, 89, 23);
		roomsListPanel.add(btnNewButton);
		
		JPanel createRoomPanel = new JPanel();
		createRoomPanel.setBorder(new TitledBorder(null, "Criar Sala", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		createRoomPanel.setBounds(5, 11, 424, 65);
		contentPane.add(createRoomPanel);
		createRoomPanel.setLayout(null);
		
		createRoomInput = new JTextField();
		createRoomInput.setBounds(10, 25, 307, 29);
		createRoomPanel.add(createRoomInput);
		createRoomInput.setColumns(10);
		
		JButton createRoomButton = new JButton("Criar");
		createRoomButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String roomName = createRoomInput.getText();
				if(roomName.isBlank()) {
					JOptionPane.showMessageDialog(null, "O nome da sala não pode estar vazio!");
					return;
				}
				try {
					userChat.server.createRoom(roomName);
					createRoomInput.setText("");
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Erro ao criar sala!");
					e1.printStackTrace();
					return;
				}
				try {
					setRoomsList(userChat.server.getRooms());
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Erro ao atualizar lista de salas!");
					e1.printStackTrace();
				}
			}
		});
		createRoomButton.setBounds(325, 28, 89, 23);
		createRoomPanel.add(createRoomButton);
	}
 
}
