package Attend;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;

import com.Train;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import org.bytedeco.javacpp.BytePointer;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imencode;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imwrite;
import org.bytedeco.opencv.global.opencv_imgproc;
import static org.bytedeco.opencv.global.opencv_imgproc.COLOR_BGRA2GRAY;
import static org.bytedeco.opencv.global.opencv_imgproc.cvtColor;
import static org.bytedeco.opencv.global.opencv_imgproc.rectangle;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_core.RectVector;
import org.bytedeco.opencv.opencv_core.Scalar;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_objdetect.CascadeClassifier;
import org.bytedeco.opencv.opencv_videoio.VideoCapture;

import com.mysql.jdbc.Statement;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.SystemColor;
import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class RegisterFace extends JFrame {

	private JPanel contentPane;
	JLabel label_photo ;
	JLabel txt_id_label ;
	JButton saveButton ;
	JLabel counterLabel ;
	static Connection conn=null;
	JDateChooser dob ;
	JComboBox branch,year;

	
	public static void main(String[] args) {
		
		 try {
	            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
	                if ("Windows".equals(info.getName())) {
	                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
	                    break;
	                }
	            }
	        } catch (ClassNotFoundException ex) {
	            java.util.logging.Logger.getLogger(RegisterFace.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        } catch (InstantiationException ex) {
	            java.util.logging.Logger.getLogger(RegisterFace.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        } catch (IllegalAccessException ex) {
	            java.util.logging.Logger.getLogger(RegisterFace.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
	            java.util.logging.Logger.getLogger(RegisterFace.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        }
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new RegisterFace().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Method responsible for registering the users in the database.
	 * <br><br>
	 * Is a JDialog that you can register a person and register data like: name,
	 * surname, phone, post, and information about social network.
	 */
	
	private RegisterFace.DaemonThread myThread = null;

    //JavaCV
    VideoCapture webSource = null;
    Mat cameraImage = new Mat();
    CascadeClassifier cascade = new CascadeClassifier("C://photos//haarcascade_frontalface_alt.xml");

    BytePointer mem = new BytePointer();
    RectVector detectedFaces = new RectVector();
    
    
    String root,firstnameperson,lastnameperson,officeperson;
    int numSamples = 26, sample = 1, idPerson;
    Connectdb conecta = new Connectdb();
    //private final Action actionxt_first_name;
    private JTextField txt_last_name,txt_first_name;
    private JTextField txt_address;
    private JTextField email;
    private JTextField contact;
    
	
	
	
	

	/**
	 * Create the frame.
	 * @param b 
	 * @param jFrame 
	 * @throws ClassNotFoundException 
	 */
	public RegisterFace() throws ClassNotFoundException 
	{
		
		
		super();
		//this.actionxt_first_name = null;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 925, 725);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		label_photo = new JLabel("");
		label_photo.setBounds(12, 87, 412, 511);
		contentPane.add(label_photo);
		
		counterLabel = new JLabel("00");
		counterLabel.setBounds(183, 611, 87, 16);
		contentPane.add(counterLabel);
		
		saveButton = new JButton("Capture And Register Data");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		saveButton.setBounds(353, 640, 259, 25);
		contentPane.add(saveButton);
		
		JLabel lblNewLabel_1 = new JLabel("First name");
		lblNewLabel_1.setBounds(492, 149, 87, 16);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Last Name");
		lblNewLabel_2.setBounds(492, 205, 87, 16);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Address");
		lblNewLabel_3.setBounds(492, 445, 56, 16);
		contentPane.add(lblNewLabel_3);
		
		txt_first_name = new JTextField();
		txt_first_name.setBounds(640, 146, 116, 22);
		contentPane.add(txt_first_name);
		txt_first_name.setColumns(10);
		
		txt_last_name = new JTextField();
		txt_last_name.setBounds(640, 202, 116, 22);
		contentPane.add(txt_last_name);
		txt_last_name.setColumns(10);
		
		txt_address = new JTextField();
		txt_address.setBounds(640, 442, 116, 22);
		contentPane.add(txt_address);
		txt_address.setColumns(10);
		
		txt_id_label = new JLabel("New label");
		txt_id_label.setBounds(658, 67, 26, 16);
		contentPane.add(txt_id_label);
		
		JLabel lblNewLabel = new JLabel("Registration");
		lblNewLabel.setBackground(SystemColor.windowText);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setForeground(SystemColor.window);
		lblNewLabel.setBounds(0, 0, 907, 37);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_4 = new JLabel("Dashboard");
		lblNewLabel_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				new Dashboard().setVisible(true);
				dispose();
				stopCamera();
			}
		});
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_4.setForeground(SystemColor.window);
		lblNewLabel_4.setBounds(0, 38, 87, 16);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("> Login");
		lblNewLabel_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				new Management().setVisible(true);
				dispose();
				stopCamera();
				
			}
		});
		lblNewLabel_5.setForeground(SystemColor.window);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_5.setBounds(99, 38, 62, 16);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel(">Registration");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_6.setForeground(SystemColor.window);
		lblNewLabel_6.setBounds(166, 37, 127, 16);
		contentPane.add(lblNewLabel_6);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaptionBorder);
		panel.setBounds(0, 0, 907, 37);
		contentPane.add(panel);
		
		JLabel lblNewLabel_7 = new JLabel("Email");
		lblNewLabel_7.setBounds(492, 268, 56, 16);
		contentPane.add(lblNewLabel_7);
		
		email = new JTextField();
		email.setBounds(640, 265, 116, 22);
		contentPane.add(email);
		email.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("Date of Birth");
		lblNewLabel_8.setBounds(492, 334, 87, 16);
		contentPane.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Contact");
		lblNewLabel_9.setBounds(492, 387, 56, 16);
		contentPane.add(lblNewLabel_9);
		
		contact = new JTextField();
		contact.setBounds(640, 384, 116, 22);
		contentPane.add(contact);
		contact.setColumns(10);
		
		dob = new JDateChooser();
		dob.setBounds(642, 334, 114, 22);
		contentPane.add(dob);
		
		JLabel lblNewLabel_10 = new JLabel("Branch");
		lblNewLabel_10.setBounds(492, 491, 56, 16);
		contentPane.add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("Year");
		lblNewLabel_11.setBounds(492, 533, 56, 16);
		contentPane.add(lblNewLabel_11);
		
		year = new JComboBox();
		year.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8"}));
		year.setBounds(640, 530, 116, 22);
		contentPane.add(year);
		
		branch = new JComboBox();
		branch.setModel(new DefaultComboBoxModel(new String[] {"CS", "ME", "IT", "CE"}));
		branch.setBounds(640, 488, 116, 22);
		contentPane.add(branch);
		
		JLabel lblNewLabel_12 = new JLabel("ID:");
		lblNewLabel_12.setBounds(620, 67, 26, 16);
		contentPane.add(lblNewLabel_12);
		
		JLabel lblNewLabel_13 = new JLabel("Add Another");
		lblNewLabel_13.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				try {
					new RegisterFace().setVisible(true);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				dispose();
				
			}
		});
		lblNewLabel_13.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_13.setBounds(755, 636, 97, 31);
		contentPane.add(lblNewLabel_13);
		
		
		
		 
	        //initComponents();
	        
            getIdUser();

	        startCamera();

		
		
	}
		
	private int getIdUser() throws ClassNotFoundException {
		
		
		
		int id = 0;
        conecta.connect();
        conecta.executeSQL("SELECT * FROM student ORDER BY id DESC LIMIT 1");
        try {
            conecta.rs.first();
            txt_id_label.setText(String.valueOf(conecta.rs.getInt("id")));
            id = Integer.parseInt(txt_id_label.getText());
            id++;
            txt_id_label.setText(String.valueOf(id));
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
        }
        return id;
	}
		
		
		
		
		
		
       /** int id = 0;
        try {
        	Class.forName("com.mysql.jdbc.Driver");
    		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/facial_recognition","root","");
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
        conecta.executeSQL("SELECT * FROM persons ORDER BY id DESC LIMIT 1");
        try {
        	String s="SELECT * FROM persons ORDER BY id DESC LIMIT 1";
        	Statement st = (Statement) conn.createStatement();
        	ResultSet rs = st.executeQuery(s);
        	while(rs.next())
        	{
            txt_id_label.setText(String.valueOf(rs.getInt("id")));
            id = Integer.parseInt(txt_id_label.getText());
            id++;
            txt_id_label.setText(String.valueOf(id));
        	}
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
        }
        return id; */
    
	
    /**
     * This class is responsible for: displaying the image in JLabel, Detect
     * Face, and Save Images.
     * <br><br>
     * To understand more about the parameters used in the class, download
     * JavaDOC from JavaCV.
     */
    class DaemonThread implements Runnable {

        protected volatile boolean runnable = false;

        @Override
        public void run() {
            synchronized (this) {
                while (runnable) {
                    try {
                        if (webSource.grab()) {
                            webSource.retrieve(cameraImage);
                            Graphics g = label_photo.getGraphics();

                            Mat imageColor = new Mat(); 
                            imageColor = cameraImage;

                            Mat imageGray = new Mat();
                            cvtColor(imageColor, imageGray, COLOR_BGRA2GRAY);
//                            

                            RectVector detectedFaces = new RectVector();
                            cascade.detectMultiScale(imageColor, detectedFaces, 1.1, 1, 1, new Size(150, 150), new Size(500, 500));

                            for (int i = 0; i < detectedFaces.size(); i++) { 
                                Rect dadosFace = detectedFaces.get(0);

                                rectangle(imageColor, dadosFace, new Scalar(255, 255, 0, 2), 3, 0, 0);

                                Mat face = new Mat(imageGray, dadosFace);
                                opencv_imgproc.resize(face, face, new Size(160, 160));

                                if (saveButton.getModel().isPressed()) 
                                {
                                
                                    if (txt_first_name.getText().equals("") || txt_first_name.getText().equals(" ")) 
                                    {
                                        JOptionPane.showMessageDialog(null, "Campo vazio");
                                    } 
                                    else if (txt_first_name.getText().equals("") || txt_first_name.getText().equals(" "))
                                    {
                                        JOptionPane.showMessageDialog(null, "Campo vazio");
                                    } 
                                    else if (txt_last_name.getText().equals("") || txt_last_name.getText().equals(" "))
                                    {
                                        JOptionPane.showMessageDialog(null, "Campo vazio");
                                    } 
                                    else if (txt_address.getText().equals("") || txt_address.getText().equals(" ")) 
                                    {
                                        JOptionPane.showMessageDialog(null, "Campo vazio");
                                    } 
                                    else 
                                    {
                                        if (sample <= numSamples) 
                                        {
//                                     
                                            String cropped = "C:\\photos\\person." + txt_id_label.getText() + "." + sample + ".jpg";
                                            imwrite(cropped, face);

                                         
                                            counterLabel.setText(String.valueOf(sample) + "/25");
                                            sample++;
                                        }
                                        if (sample > 25) 
                                        {
                                        	stopCamera();
                                        	JOptionPane.showMessageDialog(null,"Files Generated");
                                        	insertDatabase();
                                            new Train().trainPhotos();
										
                                        }

                                    }
                                }
                            }

                            imencode(".bmp", cameraImage, mem);
                            Image im = ImageIO.read(new ByteArrayInputStream(mem.getStringBytes()));
                            BufferedImage buff = (BufferedImage) im;
                            try {
                                if (g.drawImage(buff, 0, 0, 360, 390, 0, 0, buff.getWidth(), buff.getHeight(), null)) {
                                    if (runnable == false) {
                                        System.out.println("Data Inserted");
                                        this.wait();
                                    }
                                }
                            } catch (Exception e) {
                            }
                        }

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * This method inserts the information into the database.
     //
     * @throws ClassNotFoundException 
     * @throws NumberFormatException */
    public void insertDatabase() throws ClassNotFoundException, NumberFormatException {
    	
    	ControlPerson cod = new ControlPerson();
        ModelPerson mod = new ModelPerson();
        java.util.Date date =dob.getDate();
		String strDate = DateFormat.getDateInstance().format(date);
        mod.setId(Integer.parseInt(txt_id_label.getText()));
        mod.setFirst_name(txt_first_name.getText());
        mod.setLast_name(txt_last_name.getText());
        mod.setEmail(email.getText());
        mod.setDob(strDate);
        mod.setContactNum(contact.getText());
        mod.setAddress(txt_address.getText());
        String a=branch.getSelectedItem().toString();
        String b=year.getSelectedItem().toString();
        mod.setBranch(a);
        mod.setYear(b);
       // mod.setGithub(txt_github.getText());
        cod.insert(mod);
        
    	
    }
    	
    	
    	
    	/**try
		{
		String st="insert into persons(id,firstname,lastname,office)values(?,?,?,?)";
		PreparedStatement ps=(PreparedStatement) conn.prepareStatement(st);
		ps.setString(1,txt_id_label.getText());
		ps.setString(2,txt_first_name.getText());
		ps.setString(3,txt_last_name.getText());
		ps.setString(4,txt_office.getText());
		
		
		int x=ps.executeUpdate();
		if(x>0)
		{
			String msg="New Person Added";
			JOptionPane.showMessageDialog(null,msg,"Project",1);
			//new Login2();
			
		}
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		} */
    

    /**
     * This method turns off the software connection with your web cam.
     */
    public void stopCamera() {
        myThread.runnable = false;
        webSource.release();
        //dispose();
    }

    /**
     * This method connects the software to the web cam.
     * <br><br>
     * VideoCapture(0); is the default camera on your computer.
     */
    public void startCamera() {
        new Thread() {
            @Override
            public void run() {
                webSource = new VideoCapture(0);

                myThread = new RegisterFace.DaemonThread();
                Thread t = new Thread(myThread);
                t.setDaemon(true);
                myThread.runnable = true;
                t.start();
            }
        }.start();
    }
}
