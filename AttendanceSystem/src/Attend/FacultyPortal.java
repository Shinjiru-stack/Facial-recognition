package Attend;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class FacultyPortal extends JFrame {

	private JPanel contentPane;
	static JLabel name ;
	public String user;
	static JLabel nametxt;
	static JLabel username;
	static JLabel date;
	JComboBox comboBox,comboBox_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FacultyPortal frame = new FacultyPortal();
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
	public FacultyPortal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 919, 596);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("  Login");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(0, 0, 901, 55);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(192, 192, 192));
		panel.setBounds(0, 0, 901, 55);
		contentPane.add(panel);
		
		
		JLabel lblNewLabel_5 = new JLabel("Dashboard");
		lblNewLabel_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				new Dashboard().setVisible(true);
				dispose();
				
			}
		});
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_5.setForeground(new Color(255, 255, 255));
		lblNewLabel_5.setBounds(0, 55, 100, 16);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("> Login");
		lblNewLabel_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				new Management().setVisible(true);
				dispose();
				
				
			}
		});
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_6.setForeground(new Color(255, 255, 255));
		lblNewLabel_6.setBounds(100, 55, 73, 16);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_1 = new JLabel("> Faculty Portal");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(173, 56, 191, 16);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Course ID :");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_2.setBounds(78, 172, 177, 31);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Lecture No:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_3.setBounds(78, 322, 177, 31);
		contentPane.add(lblNewLabel_3);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"CS102", "CS103", "CS104"}));
		comboBox.setBounds(78, 244, 177, 38);
		contentPane.add(comboBox);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6"}));
		comboBox_1.setBounds(78, 395, 177, 38);
		contentPane.add(comboBox_1);
		
		JLabel lblNewLabel_4 = new JLabel("Hi");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_4.setBounds(553, 81, 22, 16);
		contentPane.add(lblNewLabel_4);
		
		name = new JLabel("New label");
		name.setFont(new Font("Tahoma", Font.BOLD, 16));
		name.setBounds(577, 82, 56, 16);
		contentPane.add(name);
		
		JLabel lblNewLabel_8 = new JLabel("Name");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_8.setBounds(465, 238, 147, 44);
		contentPane.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Username");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_9.setBounds(465, 392, 168, 41);
		contentPane.add(lblNewLabel_9);
		
		nametxt = new JLabel("New label");
		nametxt.setFont(new Font("Tahoma", Font.BOLD, 20));
		nametxt.setBounds(624, 245, 124, 30);
		contentPane.add(nametxt);
		
		username = new JLabel("New label");
		username.setFont(new Font("Tahoma", Font.BOLD, 20));
		username.setBounds(624, 396, 147, 30);
		contentPane.add(username);
		
		JLabel lblNewLabel_12 = new JLabel("Date");
		lblNewLabel_12.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_12.setBounds(465, 330, 73, 19);
		contentPane.add(lblNewLabel_12);
		
		JLabel lblNewLabel_7 = new JLabel("Continue>>");
		lblNewLabel_7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String c=comboBox.getSelectedItem().toString();
				String l=comboBox_1.getSelectedItem().toString();
				FacultyAttendancePortal fap=new FacultyAttendancePortal();
				fap.setVisible(true);
				FacultyAttendancePortal.cid1.setText(c);
				FacultyAttendancePortal.lec1.setText(l);
				
				dispose();
			}
		});
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_7.setBounds(790, 518, 111, 31);
		contentPane.add(lblNewLabel_7);
		
		date = new JLabel("New label");
		date.setFont(new Font("Tahoma", Font.BOLD, 20));
		date.setBounds(624, 324, 214, 31);
		contentPane.add(date);
		
		
	
		
	}
	public static void data()
	{
		try
		{
			
			String st="SELECT * FROM faculty WHERE name=?";
			Connection con=MyConnection.getConnection();
			PreparedStatement ps=con.prepareStatement(st);
			ps.setString(1,name.getText());
			ResultSet rs=ps.executeQuery();	
			if(rs.next())
			{
				String a=rs.getString("name");
				String b=rs.getString("username");
				String date1= new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis()));
				nametxt.setText(a);
				username.setText(b);
				date.setText(date1);
				System.out.println("Done");
				
			}
			else
			{
				System.out.println("error");
			}
		}
		catch(SQLException se)
			{
			se.printStackTrace();
			}
		
	}

	
}

