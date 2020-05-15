package Attend;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class VerifyOtp extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7394227629545322263L;
	/**
	 * 
	 */

	private JPanel contentPane;
	private JTextField txtEmail;
	private JTextField txtVer;
	static String otp;
	static int randomPin;
	JTextArea txtrYourPasswordWill ;
	PreparedStatement ps;
	 public static String generateOTP()
	 {
	randomPin   =(int)(Math.random()*9000)+1000;
	otp  =String.valueOf(randomPin);
	return otp;
	 }
	

	/**
	 * Launch the application.
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		new VerifyOtp();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VerifyOtp frame = new VerifyOtp();
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
	public VerifyOtp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 780, 532);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel=new JLabel("Email");
		lblNewLabel.setBounds(118, 109, 56, 16);
		contentPane.add(lblNewLabel);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(323, 106, 183, 22);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		String otpSting  =generateOTP();
		System.out.println("OTP : "+otpSting);
		JButton btnNewButton = new JButton("Send");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				Mailer.send("anujpandey51.ap@gmail.com","Anuj@2005",txtEmail.getText(),"OTP",otp);
				
			}
		});
		btnNewButton.setBounds(323, 162, 97, 25);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Code");
		lblNewLabel_1.setBounds(118, 255, 56, 16);
		contentPane.add(lblNewLabel_1);
		
		txtVer = new JTextField();
		txtVer.setBounds(323, 252, 183, 22);
		contentPane.add(txtVer);
		txtVer.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Verify");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try
				{
					if(Integer.valueOf(txtVer.getText())==randomPin)
						{
							JOptionPane.showMessageDialog(null, "code matched");
							String st="SELECT * FROM facultyreg WHERE email=?";
							Connection con=MyConnection.getConnection();
							PreparedStatement ps=con.prepareStatement(st);
							ps.setString(1,txtEmail.getText());
							ResultSet rs=ps.executeQuery();
							while(rs.next())
							{
								
								String pass=rs.getString("password");
								txtrYourPasswordWill.setText(pass);
								
							}
						}
					else
					{
						JOptionPane.showMessageDialog(null,"Code do not match","MIMS",0);
					}
								
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}

			}
		});
		btnNewButton_1.setBounds(323, 313, 97, 25);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Login");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				new FacLogin().setVisible(true);
				dispose();
				
			}
		});
		btnNewButton_2.setBounds(653, 460, 97, 25);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setBounds(118, 406, 56, 16);
		contentPane.add(lblNewLabel_2);
		
		txtrYourPasswordWill = new JTextArea();
		txtrYourPasswordWill.setText("Your Password will appear\r\nhere once OTP is verified");
		txtrYourPasswordWill.setBounds(323, 400, 215, 47);
		contentPane.add(txtrYourPasswordWill);

		
		


		
	}
		static class Mailer{  
		    public static void send(String from,String password,String to,String sub,String msg){  
	          //Get properties object    
	          Properties props = new Properties();    
	          props.put("mail.smtp.host", "smtp.gmail.com");    
	          props.put("mail.smtp.socketFactory.port", "465");    
	          props.put("mail.smtp.socketFactory.class",    
	                    "javax.net.ssl.SSLSocketFactory");    
	          props.put("mail.smtp.auth", "true");    
	          props.put("mail.smtp.port", "465");    
	          //get Session   
	          Session session = Session.getDefaultInstance(props,    
	           new javax.mail.Authenticator() {    
	           protected PasswordAuthentication getPasswordAuthentication() {    
	           return new PasswordAuthentication(from,password);  
	           }    
	          });    
	          //compose message    
	          try {    
	           MimeMessage message = new MimeMessage(session);    
	           message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));    
	           message.setSubject(sub);    
	           message.setText(msg);    
	           //send message  
	           Transport.send(message);    
	           System.out.println("message sent successfully");    
	          } catch (MessagingException e) {throw new RuntimeException(e);}    
	             
	    }  
	}  
}
