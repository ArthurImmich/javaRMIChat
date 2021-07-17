package rmi.chat;


import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import javax.swing.border.EtchedBorder;

public class ChatGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8735240252697827867L;
	private JPanel contentPane;
	private JTextField sendMessageField;


	/**
	 * Create the frame.
	 */
	public ChatGUI(UserChat userChat) {
		setResizable(false);
		try {
			setTitle(userChat.room.getRoomName());
		} catch (RemoteException e2) {
			e2.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 560, 413);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton sendMessageButton = new JButton("Enviar");
		sendMessageButton.setBounds(443, 339, 92, 31);
		sendMessageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(userChat.room == null) {
					JOptionPane.showMessageDialog(null, "Sala fechada!");
					return;
				}
				String message = sendMessageField.getText();
				if(message.isBlank()) {
					return;
				}
				sendMessageField.setText("");
				try {
					userChat.room.sendMsg(userChat.name, message);
				} catch (RemoteException e1) {
					JOptionPane.showMessageDialog(null, "Erro ao enviar mensagem!");
					e1.printStackTrace();
				}
			}
		});
		
		sendMessageField = new JTextField();
		sendMessageField.setBounds(10, 339, 423, 31);
		sendMessageField.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 525, 317);
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setLayout(new BorderLayout(0, 0));
		contentPane.setLayout(null);
		contentPane.add(sendMessageButton);
		contentPane.add(sendMessageField);
		contentPane.add(panel);
		
		JTextArea messageArea = new JTextArea();
		panel.add(messageArea, BorderLayout.CENTER);
		userChat.messageArea = messageArea;
		messageArea.setEditable(false);
		messageArea.setToolTipText("");
	}
}
