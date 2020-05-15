package Attend;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JPasswordField;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Admin1 extends JFrame {

	private JPanel contentPane;
	static Connection conn=null;
	JLabel lblNewLabel_1 = new JLabel("");

	/**
	 * Launch the application.
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/facial_recognition","root","");
		System.out.println("Database Connected");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin1 frame = new Admin1();
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
	public Admin1() {
		
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 534);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 882, 26);
		contentPane.add(menuBar);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Home");
		mntmNewMenuItem_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new Admin1().setVisible(true);
				dispose();
			}
		});
		menuBar.add(mntmNewMenuItem_4);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Admin");
		menuBar.add(mntmNewMenuItem_5);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Faculty");
		menuBar.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Student");
		menuBar.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Contact");
		menuBar.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Help");
		menuBar.add(mntmNewMenuItem_3);
		
		JLabel lblNewLabel_4 = new JLabel("Side Menu");
		lblNewLabel_4.setFont(new Font("Dialog", Font.BOLD, 15));
		lblNewLabel_4.setBounds(746, 305, 78, 31);
		contentPane.add(lblNewLabel_4);
		
		JButton btnNewButton_1 = new JButton("Faculty");
		btnNewButton_1.setBounds(746, 352, 97, 25);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Student");
		btnNewButton_2.setBounds(746, 390, 97, 25);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Contact");
		btnNewButton_3.setBounds(746, 428, 97, 25);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Help");
		btnNewButton_4.setBounds(746, 462, 97, 25);
		contentPane.add(btnNewButton_4);
		
		
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_1.setBounds(682, 62, 188, 26);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("ADMIN");
		lblNewLabel.setFont(new Font("Cambria", Font.BOLD, 28));
		lblNewLabel.setBounds(407, 62, 164, 26);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Add Faculty");
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(161, 136, 138, 43);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_5 = new JButton("Add Student");
		btnNewButton_5.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_5.setBounds(161, 211, 138, 43);
		contentPane.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("Add Subject");
		btnNewButton_6.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton_6.setBounds(161, 288, 138, 43);
		contentPane.add(btnNewButton_6);
		
		JButton btnNewButton_7 = new JButton("Log Out");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Home1().setVisible(true);
				dispose();
			}
		});
		btnNewButton_7.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnNewButton_7.setBounds(0, 462, 97, 25);
		contentPane.add(btnNewButton_7);
		
		JButton btnNewButton_8 = new JButton("Report");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_8.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnNewButton_8.setBounds(161, 364, 138, 43);
		contentPane.add(btnNewButton_8);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\SHINJIRU\\Downloads\\imageedit_1_7210607431.jpg"));
		lblNewLabel_2.setBounds(0, 0, 882, 487);
		contentPane.add(lblNewLabel_2);
	}
	public void username(String user) {
		// TODO Auto-generated method stub
		lblNewLabel_1.setText("Welcome "+user);
		
	}
}
