package Attend;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FacultyAttendancePortal extends JFrame {

	private JPanel contentPane;
	static JLabel cid1;
	static JLabel lec1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FacultyAttendancePortal frame = new FacultyAttendancePortal();
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
	public FacultyAttendancePortal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 919, 596);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("  Faculty Attendance Portal");
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
				
				new Dashboard().setVisible(true);
				dispose();
				
				
			}
		});
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_6.setForeground(new Color(255, 255, 255));
		lblNewLabel_6.setBounds(100, 55, 73, 16);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_1 = new JLabel("> Faculty Portal");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				new Faculty().setVisible(true);
				dispose();
			}
		});
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(173, 56, 131, 16);
		contentPane.add(lblNewLabel_1);
		
	
		JLabel lblNewLabel_2 = new JLabel("> Faculty Attendance Portal");
		lblNewLabel_2.setForeground(SystemColor.window);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setBounds(308, 56, 249, 16);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Select an option to choose from:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_3.setBounds(235, 140, 416, 55);
		contentPane.add(lblNewLabel_3);
		
		JButton btnNewButton = new JButton("Recognize");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String c=cid1.getText();
				String l=lec1.getText();
				TakeAttendance ta=new TakeAttendance();
				TakeAttendance.cid.setText(c);
				TakeAttendance.lec.setText(l);
				ta.setVisible(true);
				dispose();
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton.setBounds(140, 236, 313, 220);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Manual Attendance");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton_1.setBounds(496, 237, 328, 219);
		contentPane.add(btnNewButton_1);
		
		cid1 = new JLabel("New label");
		cid1.setBounds(777, 68, 56, 16);
		contentPane.add(cid1);
		
		lec1 = new JLabel("New label");
		lec1.setBounds(777, 112, 56, 16);
		contentPane.add(lec1);
		
		
	
		
	}

}
