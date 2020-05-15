package Attend;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class ChangePass extends JFrame {

	private JPanel contentPane;
	static String var;
	private JTextField textField;
	private JTextField textField_1;
	static JTextField textField_2;
	static Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;
	public String user;
	/**
	 * Launch the application.
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 * 
	 * 
	 * 
	 */
	

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		new ChangePass();
		Class.forName("com.mysql.jdbc.Driver");
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/facial_recognition","root","");
		System.out.println("Database Connected");
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				try {
					ChangePass frame = new ChangePass();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
		public void Reset(String username){
		this.user=username;
		}
	
	/**
	 * Create the frame.
	 */
	public ChangePass() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 643, 467);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New Password");
		lblNewLabel.setBounds(94, 188, 88, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Retype New Password");
		lblNewLabel_1.setBounds(94, 264, 155, 16);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(334, 185, 200, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(334, 261, 200, 22);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

						try
						{
							String sqlUpdate = "UPDATE facultyreg "
					                			+ "SET password = ? "
					                			+ "WHERE email = ?";
							PreparedStatement ps = conn.prepareStatement(sqlUpdate);
							ps.setString(1,textField.getText());
							ps.setString(2,textField_2.getText());
							int x=ps.executeUpdate();
							if(x>0)
							{
							int p1=JOptionPane.showConfirmDialog(null, "Are you sure you want to save password?","Change Password",JOptionPane.YES_NO_OPTION);
							if(p1==0)
							{	
							String msg="New Password Added";
							JOptionPane.showMessageDialog(null,msg,"Project",1);
							//new Login2();
							}
							}
							else
							{
								JOptionPane.showMessageDialog(null,"Enter all fields","Project",1);
							}
						}
						catch(SQLException se)
						{
							se.printStackTrace();
						}
						catch(NullPointerException ne)
						{
							ne.printStackTrace();
						} 
			}
		});
		btnNewButton.setBounds(237, 348, 97, 25);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_3 = new JLabel("Email");
		lblNewLabel_3.setBounds(94, 116, 77, 16);
		contentPane.add(lblNewLabel_3);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setBounds(334, 113, 200, 22);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		
	}
}
