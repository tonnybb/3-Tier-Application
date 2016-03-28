package userinterface;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import businesslayer.AppData;
import businesslayer.Person;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainGUI extends JDialog {

	public static void main(String[] args) {
		new MainGUI().setVisible(true);
	}

	private final JPanel contentPanel = new JPanel();
	private JTextField firstName_textField;
	private JTextField lastName_textField;
	private JTextField phone_textField;
	private JTextField email_textField;

	/**
	 * Create the dialog.
	 */
	public MainGUI() {
		setBounds(100, 100, 226, 254);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("First name:");
		lblNewLabel.setBounds(28, 21, 93, 14);
		contentPanel.add(lblNewLabel);

		JLabel lblLastName = new JLabel("Last name:");
		lblLastName.setBounds(28, 52, 104, 14);
		contentPanel.add(lblLastName);

		JLabel lblNewLabel_1 = new JLabel("Email:");
		lblNewLabel_1.setBounds(28, 114, 61, 14);
		contentPanel.add(lblNewLabel_1);

		JLabel lblPhone = new JLabel("Phone:");
		lblPhone.setBounds(28, 83, 61, 14);
		contentPanel.add(lblPhone);

		firstName_textField = new JTextField();
		firstName_textField.setBounds(106, 18, 86, 20);
		contentPanel.add(firstName_textField);
		firstName_textField.setColumns(10);

		lastName_textField = new JTextField();
		lastName_textField.setColumns(10);
		lastName_textField.setBounds(106, 49, 86, 20);
		contentPanel.add(lastName_textField);

		phone_textField = new JTextField();
		phone_textField.setBounds(106, 80, 86, 20);
		contentPanel.add(phone_textField);
		phone_textField.setColumns(10);

		email_textField = new JTextField();
		email_textField.setBounds(106, 111, 86, 20);
		contentPanel.add(email_textField);
		email_textField.setColumns(10);

		JButton btnClearFields = new JButton("Clear fields");
		btnClearFields.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				firstName_textField.setText("");
				lastName_textField.setText("");
				phone_textField.setText("");
				email_textField.setText("");
			}
		});
		btnClearFields.setBounds(86, 149, 114, 23);
		contentPanel.add(btnClearFields);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {

						AppData.getAppData().addPerson(
								new Person(firstName_textField.getText(),
										lastName_textField.getText(),
										phone_textField.getText(),
										email_textField.getText()));

						// clear text field input
						firstName_textField.setText("");
						lastName_textField.setText("");
						phone_textField.setText("");
						email_textField.setText("");
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						System.exit(0);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}