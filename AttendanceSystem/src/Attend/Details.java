package Attend;

import java.awt.BorderLayout;
import net.proteanit.sql.DbUtils;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Details extends JFrame {

	private JPanel contentPane;
	private static JTable table;
	private JTextField id;
	private JTextField date;
	private JTextField lec;
	private JTextField cid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Details frame = new Details();
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
	public Details() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1010, 655);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Attendance Details");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBounds(374, 13, 204, 25);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		
		scrollPane.setBounds(34, 139, 574, 335);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				try
				{
					int row=table.getSelectedRow();
					String click=(table.getModel().getValueAt(row, 0).toString());
					Connection con=MyConnection.getConnection();
					String sql="select * from attendance where id='"+click+"'";
					PreparedStatement ps=(PreparedStatement) con.prepareStatement(sql);
					ResultSet rs=ps.executeQuery();
					if(rs.next())
					{
						    String add2 =rs.getString("id");
						    id.setText(add2);
			    
						    String add3 =rs.getString("lecture");
						    lec.setText(add3);
						    
						    String add4 =rs.getString("courseid");
						    cid.setText(add4);
						    
			                String add6 =rs.getString("date");
			                date.setText(add6);

			              
					}
				}
					catch(Exception ex)
					{
						JOptionPane.showMessageDialog(null,ex);
					}
				
				
			}
		});
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"id", "firstname", "lastname", "date", "lecture", "courseid", "attendance"
			}
		));
		
		JLabel lblNewLabel_1 = new JLabel("UPDATE");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(780, 137, 96, 31);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("ID");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setBounds(688, 193, 37, 25);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Date");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_3.setBounds(685, 253, 62, 31);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Attendance");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_4.setBounds(688, 438, 106, 31);
		contentPane.add(lblNewLabel_4);
		
		id = new JTextField();
		id.setEditable(false);
		id.setBounds(839, 195, 116, 22);
		contentPane.add(id);
		id.setColumns(10);
		
		date = new JTextField();
		date.setEditable(false);
		date.setBounds(839, 258, 116, 22);
		contentPane.add(date);
		date.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"present", "absent"}));
		comboBox.setBounds(839, 443, 116, 22);
		contentPane.add(comboBox);
		
		JButton btnNewButton = new JButton("UPDATE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				int p = JOptionPane.showConfirmDialog(null, "Are you sure you want to update request?","Update Request",JOptionPane.YES_NO_OPTION);
		        if(p==0){
				
				if(e.getSource()==btnNewButton)
				{
					try
					{
		                String value8 = id.getText();
		                String value9 = comboBox.getSelectedItem().toString();
		                String value10 = lec.getText();
		                String value11 = cid.getText();
		                
						
						String sql="update attendance set attendance='"+value9+"' where id=? and lecture=? and courseid=?";
						Connection con=MyConnection.getConnection();
						PreparedStatement ps=con.prepareStatement(sql);
			            ps.setString(1,id.getText());
			            ps.setString(2,lec.getText());
			            ps.setString(3,cid.getText());
			            ps.execute();
			                JOptionPane.showMessageDialog(null, "Request Updated");
						
						
					}
					catch(Exception ex)
					{
						ex.printStackTrace();
					}
				}
		        }		
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.setBounds(741, 526, 123, 25);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_7 = new JLabel("Lecture");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_7.setBounds(688, 315, 96, 34);
		contentPane.add(lblNewLabel_7);
		
		JLabel lblNewLabel_5 = new JLabel("Courseid");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_5.setBounds(688, 380, 82, 25);
		contentPane.add(lblNewLabel_5);
		
		lec = new JTextField();
		lec.setEditable(false);
		lec.setBounds(839, 322, 116, 22);
		contentPane.add(lec);
		lec.setColumns(10);
		
		cid = new JTextField();
		cid.setEditable(false);
		cid.setBounds(839, 382, 116, 22);
		contentPane.add(cid);
		cid.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("<< Back");
		lblNewLabel_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				new FacultyPortal().setVisible(true);
				dispose();
			}
		});
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_6.setBounds(12, 53, 106, 25);
		contentPane.add(lblNewLabel_6);
	}
	
	
	public static void showdata()
	{
		try
		{
			Connection con=MyConnection.getConnection();
			String sql="select distinct id,firstname,lastname,date,lecture,courseid,attendance from attendance";
			PreparedStatement ps=(PreparedStatement) con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
