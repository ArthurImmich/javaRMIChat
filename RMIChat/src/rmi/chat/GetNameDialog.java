package rmi.chat;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.Set;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GetNameDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4668576812755053654L;
	private final JPanel contentPanel = new JPanel();
	private JTextField getNameField;

	/**
	 * Create the dialog.
	 */
	public GetNameDialog(UserChat userChat) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(ABORT);
			}
		});
		setResizable(false);
		setBounds(100, 100, 473, 98);
		getContentPane().setLayout(new BorderLayout(0, 0));
		contentPanel.setBorder(new TitledBorder(null, "Digite seu nome:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		{
			JButton getNameButton = new JButton("Escolher");
			getNameButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String newName = getNameField.getText();
					if(newName.isBlank()) {
						JOptionPane.showMessageDialog(null, "O campo do nome não pode estar vazio!");
						return;
					}
					try {
						String[] userList = userChat.registry.list();
						if(Set.of(userList).contains(newName)) {
							JOptionPane.showMessageDialog(null, "O nome já está em uso, escolha outro nome!");
							return;
						}
						userChat.name = newName;
						dispose();
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "Não foi possível checar se o nome está em uso, tente novamente!");
						e1.printStackTrace();
						return;
					}
				}
			});
			getNameButton.setBounds(362, 19, 85, 30);
			contentPanel.add(getNameButton);
			getNameButton.setActionCommand("OK");
			getRootPane().setDefaultButton(getNameButton);
			setModalityType(DEFAULT_MODALITY_TYPE);
		}
		
		getNameField = new JTextField();
		getNameField.setBounds(10, 21, 346, 27);
		contentPanel.add(getNameField);
		getNameField.setColumns(10);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}
}
