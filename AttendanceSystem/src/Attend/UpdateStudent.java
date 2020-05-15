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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class UpdateStudent extends JFrame {

	private JPanel contentPane;
	private JTextField id;
	private JTextField id1;
	private JTextField fname;
	private JTextField lname;
	private JTextField email;
	private JTextField dob;
	private JTextField contact;
	private JTextField address;
	private JTextField branch;
	private JTextField year;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateStudent frame = new UpdateStudent();
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
	public UpdateStudent() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 919, 596);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("  Update Student");
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
		
		JLabel lblNewLabel_1 = new JLabel("> Management Portal");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				new ManagementPortal().setVisible(true);
				dispose();
				
			}
			
			
		});
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(173, 56, 191, 16);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_3 = new JLabel("> Update");
		lblNewLabel_3.setForeground(SystemColor.window);
		lblNewLabel_3.setBackground(SystemColor.window);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_3.setBounds(363, 56, 100, 16);
		contentPane.add(lblNewLabel_3);
		
		id = new JTextField();
		id.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				
				id.setText("");
			}
		});
		id.setFont(new Font("Tahoma", Font.BOLD, 16));
		id.setText("Enter student id to search");
		id.setBounds(173, 104, 513, 45);
		contentPane.add(id);
		id.setColumns(10);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try
				{
					Connection con=MyConnection.getConnection();
					String sql ="select * from student where id=? ";
					PreparedStatement ps=con.prepareStatement(sql);
					String s=id.getText();
					int id2=Integer.valueOf(s);
		            ps.setLong(1,id2);
		            ResultSet rs=ps.executeQuery();
		            while(rs.next())
		            {
		            
		            	String add1 =rs.getString("id");
		                id1.setText(add1);
		            	
		                String add2 =rs.getString("firstname");
		                fname.setText(add2);

		                String add3 =rs.getString("lastname");
		                lname.setText(add3);

		                String add4 =rs.getString("dob");
		                dob.setText(add4);

		                String add6 =rs.getString("email");
		                email.setText(add6);
		                
		                String add8 =rs.getString("contact");
		                contact.setText(add8);
		                
		                String add9 =rs.getString("address");
		                address.setText(add9);
		                
		                String add10 =rs.getString("branch");
		                branch.setText(add10);
		                
		                String add11 =rs.getString("year");
		                year.setText(add11);
		                
		            }
				}
				catch(Exception e1)
				{
		            e1.printStackTrace();
		        }
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(719, 104, 100, 45);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("ID");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setBounds(346, 199, 43, 27);
		contentPane.add(lblNewLabel_2);
		
		id1 = new JTextField();
		id1.setEditable(false);
		id1.setBounds(419, 202, 116, 22);
		contentPane.add(id1);
		id1.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("First Name");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_4.setBounds(86, 250, 87, 36);
		contentPane.add(lblNewLabel_4);
		
		fname = new JTextField();
		fname.setBounds(197, 258, 116, 22);
		contentPane.add(fname);
		fname.setColumns(10);
		
		lname = new JTextField();
		lname.setBounds(197, 316, 116, 22);
		contentPane.add(lname);
		lname.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Last Name");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_7.setBounds(86, 314, 99, 25);
		contentPane.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Email");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_8.setBounds(86, 376, 87, 27);
		contentPane.add(lblNewLabel_8);
		
		email = new JTextField();
		email.setBounds(197, 379, 116, 22);
		contentPane.add(email);
		email.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("Date of Birth");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_9.setBounds(86, 439, 99, 27);
		contentPane.add(lblNewLabel_9);
		
		dob = new JTextField();
		dob.setBounds(197, 442, 116, 22);
		contentPane.add(dob);
		dob.setColumns(10);
		
		JLabel lblNewLabel_10 = new JLabel("Contact");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_10.setBounds(503, 261, 78, 25);
		contentPane.add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("Address");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_11.setBounds(503, 308, 100, 36);
		contentPane.add(lblNewLabel_11);
		
		JLabel lblNewLabel_12 = new JLabel("Branch");
		lblNewLabel_12.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_12.setBounds(503, 371, 73, 36);
		contentPane.add(lblNewLabel_12);
		
		JLabel lblNewLabel_13 = new JLabel("Year");
		lblNewLabel_13.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_13.setBounds(503, 445, 56, 16);
		contentPane.add(lblNewLabel_13);
		
		contact = new JTextField();
		contact.setBounds(625, 258, 116, 22);
		contentPane.add(contact);
		contact.setColumns(10);
		
		address = new JTextField();
		address.setBounds(625, 316, 116, 22);
		contentPane.add(address);
		address.setColumns(10);
		
		branch = new JTextField();
		branch.setBounds(625, 379, 116, 22);
		contentPane.add(branch);
		branch.setColumns(10);
		
		year = new JTextField();
		year.setBounds(625, 442, 116, 22);
		contentPane.add(year);
		year.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("UPDATE");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int p = JOptionPane.showConfirmDialog(null, "Are you sure you want to update student?","Update Request",JOptionPane.YES_NO_OPTION);
		        if(p==0){
				
				try
				{

	                String value1 = fname.getText();
	                String value2 = lname.getText();
	                String value3 = dob.getText();
	                String value4 = branch.getText();
	                String value5 = email.getText();
	                String value6 = year.getText();
	                String value7 = contact.getText();
	                String value8 = address.getText();
	             
	                String sql= "update student set firstname='"+value1+"', lastname='"+value2+"', "
	                        + "dob='"+value3+"',branch='"+value4+"',email='"+value5+"',"
	                        + "year='"+value6+"',contact='"+value7+"', address = '"+value8+"' where id=?";

	                Connection con=MyConnection.getConnection();
	                PreparedStatement ps=con.prepareStatement(sql);
	                ps.setString(1,id1.getText());
	                ps.execute();
	                JOptionPane.showMessageDialog(null, "Record Updated");

	            }
				catch(Exception e1)
				{
	                JOptionPane.showMessageDialog(null, e1);
	            }
		        }
				
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_1.setBounds(389, 500, 97, 25);
		contentPane.add(btnNewButton_1);
	}
}
