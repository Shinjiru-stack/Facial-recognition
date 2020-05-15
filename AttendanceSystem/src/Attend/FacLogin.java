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

import java.awt.Font;

import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



public class FacLogin extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txtUser;
	private JPasswordField passwordField;
	final JButton btnSubmit;
	ResultSet rs;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new FacLogin();
		MyConnection.getConnection();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FacLogin frame = new FacLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FacLogin() 
	{
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1085, 574);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setLayout(null);
	
		
		
		
		JLabel lblEmployeeLogin = new JLabel("Employee Login");
		lblEmployeeLogin.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblEmployeeLogin.setBounds(483, 54, 211, 39);
		contentPane.add(lblEmployeeLogin);
		
		JLabel lblEmailid = new JLabel("Email:");
		lblEmailid.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblEmailid.setBounds(426, 175, 95, 39);
		contentPane.add(lblEmailid);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblPassword.setBounds(426, 247, 116, 39);
		contentPane.add(lblPassword);
		
		txtUser = new JTextField();
		txtUser.setBounds(573, 185, 181, 22);
		contentPane.add(txtUser);
		txtUser.setColumns(10);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setIcon(new ImageIcon("C:\\Users\\ANUJ PANDEY\\Workspaces\\MyEclipse Professional 2014\\Project\\images\\Ok-icon.png"));
		btnSubmit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				Object ob=e.getSource();
				if(ob==btnSubmit)
				{
					
					try
					{
						
						String st="SELECT * FROM facultyreg WHERE email=? and password=?";
						Connection con=MyConnection.getConnection();
						PreparedStatement ps=con.prepareStatement(st);
						ps.setString(1,txtUser.getText());
						ps.setString(2,passwordField.getText());
						rs=ps.executeQuery();
						if(rs.next())
					   
						{
							
							JOptionPane.showMessageDialog(null,"Login Successful","Project", 1);
							Emphomepage nf= new Emphomepage();
							nf.setVisible(true);
							dispose();
						}
						else
						{
							JOptionPane.showMessageDialog(null,"INVALID ID OR PASSWORD","MIMS",0);
						}
					}
					catch(SQLException se)
						{
						se.printStackTrace();
						}
				}
			}
		});
		btnSubmit.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnSubmit.setBounds(426, 359, 135, 41);
		contentPane.add(btnSubmit);
		
		final JButton btnReset = new JButton("Reset");
		btnReset.setIcon(new ImageIcon("C:\\Users\\ANUJ PANDEY\\Workspaces\\MyEclipse Professional 2014\\Project\\images\\erase-128.png"));
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(e.getSource()==btnReset)
				{
					txtUser.setText("");
					passwordField.setText("");
				}
			
			}
		});
		btnReset.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnReset.setBounds(591, 359, 124, 41);
		contentPane.add(btnReset);
		
		JButton btnForgotPassword = new JButton("Forgot Password");
		btnForgotPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new VerifyOtp().setVisible(true);
				dispose();
			}
		});
		btnForgotPassword.setIcon(new ImageIcon("C:\\Users\\ANUJ PANDEY\\Workspaces\\MyEclipse Professional 2014\\Project\\images\\Emoticon-Realize-Shock-Forgot-icon.png"));
		btnForgotPassword.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnForgotPassword.setBounds(451, 444, 243, 47);
		contentPane.add(btnForgotPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(577, 257, 177, 22);
		contentPane.add(passwordField);
	
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}




}
