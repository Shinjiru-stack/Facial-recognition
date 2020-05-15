package Attend;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.text.DateFormat;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;





import java.sql.*;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.SystemColor;

public class FacultyReg extends JFrame {
	private JTextField fname;
	private JTextField email;
	private JTextField contact;
	private JTextField dept;
	private JTextField desig;
	private JComboBox comboBox;
	private JPasswordField passwordField;
	private JTextField textField;
	JLabel label_1;
	JDateChooser dateChooser;
	static Connection conn=null;
	String filename=null;
	byte[] person_image=null;
	private byte[] picture;
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		new FacultyReg();
		Class.forName("com.mysql.jdbc.Driver");
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/facial_recognition","root","");
		System.out.println("Database Connected");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FacultyReg frame = new FacultyReg();
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
	public FacultyReg() {
		
		setTitle("EMPLOYEE REGISTRATION PAGE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1054, 806);
		getContentPane().setLayout(null);
		
		JLabel lblFirstName = new JLabel("Name");
		lblFirstName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFirstName.setBounds(145, 145, 91, 30);
		getContentPane().add(lblFirstName);
		
		JLabel lblDateOfBirth = new JLabel("Date Of Birth");
		lblDateOfBirth.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDateOfBirth.setBounds(145, 146, 91, -2);
		getContentPane().add(lblDateOfBirth);
		
		JLabel lblDateOfBirth_1 = new JLabel("Date Of Birth");
		lblDateOfBirth_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDateOfBirth_1.setBounds(145, 188, 91, 30);
		getContentPane().add(lblDateOfBirth_1);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblGender.setBounds(145, 231, 91, 30);
		getContentPane().add(lblGender);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEmail.setBounds(145, 281, 91, 23);
		getContentPane().add(lblEmail);
		
		JLabel lblContact = new JLabel("Contact");
		lblContact.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblContact.setBounds(145, 377, 91, 30);
		getContentPane().add(lblContact);
		
		JLabel lblDepartment = new JLabel("Department");
		lblDepartment.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDepartment.setBounds(145, 420, 91, 30);
		getContentPane().add(lblDepartment);
		
		JLabel lblDesignation = new JLabel("Designation");
		lblDesignation.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDesignation.setBounds(145, 463, 91, 30);
		getContentPane().add(lblDesignation);
		
		final JButton add = new JButton("ADD RECORD");
		add.setIcon(new ImageIcon("C:\\Users\\ANUJ PANDEY\\Workspaces\\MyEclipse Professional 2014\\Project\\images\\Add_Employee.png"));
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				Object ob=e.getSource();
				if(ob==add)
				{
				java.util.Date date =dateChooser.getDate();
				String strDate = DateFormat.getDateInstance().format(date);
				
				int p = JOptionPane.showConfirmDialog(null, "Are you sure you want to add new employee?","New Employee",JOptionPane.YES_NO_OPTION);
		        if(p==0){
				
				{	
						try
						{
						//String st="insert into facultyreg(id,name,dob,gender,email,password,contact,dept,desig,image)values(?,?,?,?,?,?,?,?,?,?)";
						String st="insert into facultyreg(id,name,dob,gender,email,password)values(?,?,?,?,?,?)";
						PreparedStatement ps=(PreparedStatement) conn.prepareStatement(st);
						ps.setString(1,textField.getText());
						ps.setString(2,fname.getText());
						ps.setString(3,strDate);
						ps.setString(4,comboBox.getSelectedItem().toString());
						ps.setString(5,email.getText());
						ps.setString(6,passwordField.getText());
						//ps.setString(7,contact.getText());
						//ps.setString(8,dept.getText());
						//ps.setString(9,desig.getText());
						//ps.setBytes(10,person_image);
						
						
						int x=ps.executeUpdate();
						if(x>0)
						{
							String msg="New Faculty Added";
							JOptionPane.showMessageDialog(null,msg,"Project",1);
							new FacLogin().setVisible(true);
							dispose();
							
						}
						}
						catch(SQLException se)
						{
							se.printStackTrace();
						}
					}
		        }
				}
			}
		});
		add.setFont(new Font("Tahoma", Font.BOLD, 14));
		add.setBounds(504, 594, 205, 53);
		getContentPane().add(add);
		
		final JButton Clear = new JButton("CLEAR");
		Clear.setIcon(new ImageIcon("C:\\Users\\ANUJ PANDEY\\Workspaces\\MyEclipse Professional 2014\\Project\\images\\erase-128.png"));
		Clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(e.getSource()==Clear)
				{
					textField.setText("");
					fname.setText("");
					//dob.setText("");
					email.setText("");
					contact.setText("");

					dept.setText("");
					desig.setText("");
					//doh.setText("");

					passwordField.setText("");
				}
			}
		});
		Clear.setFont(new Font("Tahoma", Font.BOLD, 14));
		Clear.setBounds(504, 669, 205, 53);
		getContentPane().add(Clear);
		
		JLabel label = new JLabel("");
		label.setBounds(340, 128, 56, 16);
		getContentPane().add(label);
		
		fname = new JTextField();
		fname.setBounds(312, 150, 241, 22);
		getContentPane().add(fname);
		fname.setColumns(10);
		
		
		
		email = new JTextField();
		email.setBounds(312, 282, 241, 22);
		getContentPane().add(email);
		email.setColumns(10);
		
		contact = new JTextField();
		contact.setBounds(312, 382, 241, 22);
		getContentPane().add(contact);
		contact.setColumns(10);
		
		dept = new JTextField();
		dept.setBounds(312, 425, 241, 22);
		getContentPane().add(dept);
		dept.setColumns(10);
		
		desig = new JTextField();
		desig.setBounds(312, 468, 241, 22);
		getContentPane().add(desig);
		desig.setColumns(10);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Male", "Female"}));
		comboBox.setBounds(312, 236, 241, 22);
		getContentPane().add(comboBox);
		
		JLabel lblNewEmployee = new JLabel("Faculty Register");
		lblNewEmployee.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewEmployee.setBounds(453, 39, 205, 30);
		getContentPane().add(lblNewEmployee);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPassword.setBounds(145, 331, 91, 23);
		getContentPane().add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(312, 332, 241, 22);
		getContentPane().add(passwordField);
		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblId.setBounds(145, 110, 56, 16);
		getContentPane().add(lblId);
		
		textField = new JTextField();
		textField.setBounds(312, 108, 241, 22);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		final JButton btnBrowse = new JButton("Browse");
		btnBrowse.setIcon(new ImageIcon("C:\\Users\\ANUJ PANDEY\\Workspaces\\MyEclipse Professional 2014\\Project\\images\\attach.png"));
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
						
						if(e.getSource()==btnBrowse){
						JFileChooser file=new JFileChooser();
						file.showOpenDialog(null);
						File selectedFile=file.getSelectedFile(); 
						filename=selectedFile.getAbsolutePath();
						ImageIcon imageIcon=new ImageIcon(new ImageIcon(filename).getImage().getScaledInstance(label_1.getWidth(),label_1.getHeight(),Image.SCALE_SMOOTH));
						label_1.setIcon(imageIcon);
						try
						{
							File image=new File(filename);
							FileInputStream fis=new FileInputStream(image);
							ByteArrayOutputStream bos=new ByteArrayOutputStream();
							byte[] buf=new byte[1024];
							for(int readNum;(readNum=fis.read(buf))!=-1;) {
								bos.write(buf,0,readNum);
							}
							person_image=bos.toByteArray();
						}
						catch(Exception e1)
						{
							JOptionPane.showMessageDialog(null,e1);
						}
						}
			}
		});
		btnBrowse.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnBrowse.setBounds(708, 422, 152, 37);
		getContentPane().add(btnBrowse);
		
		label_1 = new JLabel("");
		label_1.setBackground(SystemColor.textHighlight);
		label_1.setBounds(677, 171, 231, 203);
		getContentPane().add(label_1);
		
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(312, 196, 241, 22);
		getContentPane().add(dateChooser);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//new adminhomepage().setVisible(true);
				dispose();
			}
		});
		button.setIcon(new ImageIcon("C:\\Users\\ANUJ PANDEY\\Workspaces\\MyEclipse Professional 2014\\Project\\images\\Go-back-icon.png"));
		button.setBounds(0, 697, 97, 25);
		getContentPane().add(button);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1286, 26);
		getContentPane().add(menuBar);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Faculty");
		menuBar.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Home");
		menuBar.add(mntmNewMenuItem);
		
		 
	}
}