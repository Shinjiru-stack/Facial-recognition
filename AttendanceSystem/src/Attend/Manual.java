package Attend;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class Manual extends JFrame {

	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private final ButtonGroup buttonGroup_2 = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Manual frame = new Manual();
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
	public Manual() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 751, 592);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Manual Attendance");
		lblNewLabel.setBounds(300, 23, 144, 34);
		contentPane.add(lblNewLabel);
		
		JLabel l1 = new JLabel("New label");
		l1.setBounds(110, 233, 56, 16);
		contentPane.add(l1);
		
		JLabel l2 = new JLabel("New label");
		l2.setBounds(110, 298, 56, 16);
		contentPane.add(l2);
		
		JLabel l3 = new JLabel("New label");
		l3.setBounds(110, 373, 56, 16);
		contentPane.add(l3);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("New radio button");
		buttonGroup.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setBounds(251, 229, 127, 25);
		contentPane.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("New radio button");
		buttonGroup.add(rdbtnNewRadioButton_1);
		rdbtnNewRadioButton_1.setBounds(448, 229, 127, 25);
		contentPane.add(rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("New radio button");
		buttonGroup_1.add(rdbtnNewRadioButton_2);
		rdbtnNewRadioButton_2.setBounds(251, 294, 127, 25);
		contentPane.add(rdbtnNewRadioButton_2);
		
		JRadioButton rdbtnNewRadioButton_3 = new JRadioButton("New radio button");
		buttonGroup_1.add(rdbtnNewRadioButton_3);
		rdbtnNewRadioButton_3.setBounds(448, 294, 127, 25);
		contentPane.add(rdbtnNewRadioButton_3);
		
		JRadioButton rdbtnNewRadioButton_4 = new JRadioButton("New radio button");
		buttonGroup_2.add(rdbtnNewRadioButton_4);
		rdbtnNewRadioButton_4.setBounds(251, 373, 127, 25);
		contentPane.add(rdbtnNewRadioButton_4);
		
		JRadioButton rdbtnNewRadioButton_5 = new JRadioButton("New radio button");
		buttonGroup_2.add(rdbtnNewRadioButton_5);
		rdbtnNewRadioButton_5.setBounds(448, 369, 127, 25);
		contentPane.add(rdbtnNewRadioButton_5);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				try
				{
					String s="select firstname from persons";
					Connection con=MyConnection.getConnection();
					PreparedStatement ps=con.prepareStatement(s);
					ResultSet rs=ps.executeQuery();
					while(rs.next())
					{
						
						String a1=rs.getString(1);
						String a2=rs.getString(2);
						//String a3=rs.getString(3);
						l1.setText(a1);
						l1.setText(a2);
						//l1.setText(a3);
						
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			
			
			
			
		});
		btnNewButton.setBounds(300, 494, 97, 25);
		contentPane.add(btnNewButton);
	}
}
