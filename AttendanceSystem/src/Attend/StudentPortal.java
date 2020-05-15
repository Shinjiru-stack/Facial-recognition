package Attend;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StudentPortal extends JFrame {

	private JPanel contentPane;
	static JLabel username,id ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentPortal frame = new StudentPortal();
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
	public StudentPortal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 919, 596);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("  Student Portal");
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
		
		JLabel lblNewLabel_1 = new JLabel("> Student Portal");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(173, 56, 191, 16);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("View Attendance");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String a=id.getText();
				StudentRp a1=new StudentRp();
				StudentRp.id.setText(a);
				StudentRp.showdata();
				a1.setVisible(true);
				
				dispose();
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.setBounds(585, 207, 191, 49);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Raise Query");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_1.setBounds(585, 307, 191, 49);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_2 = new JLabel("Hi");
		lblNewLabel_2.setBounds(601, 94, 24, 16);
		contentPane.add(lblNewLabel_2);
		
		username = new JLabel("New label");
		username.setBounds(637, 94, 94, 16);
		contentPane.add(username);
		
		id = new JLabel("");
		id.setBounds(799, 70, 36, 16);
		contentPane.add(id);
		
		
		
	
		
	}
	
	public static void data()
	{
		
		try
		{
			
			String s="select * from student where email=?";
			Connection con=MyConnection.getConnection();
			PreparedStatement ps=con.prepareStatement(s);
			ps.setString(1,username.getText());
			ResultSet rs=ps.executeQuery();
			
			if(rs.next())
		   
			{
				String a=rs.getString("id");
				id.setText(a);
				
				
			}
		}
		catch(SQLException se)
			{
			se.printStackTrace();
			}
	}
}
