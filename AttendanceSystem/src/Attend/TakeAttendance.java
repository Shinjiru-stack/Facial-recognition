package Attend;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Desktop;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Array;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.IntPointer;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imencode;
import org.bytedeco.opencv.global.opencv_imgproc;
import static org.bytedeco.opencv.global.opencv_imgproc.COLOR_BGRA2GRAY;
import static org.bytedeco.opencv.global.opencv_imgproc.cvtColor;
import static org.bytedeco.opencv.global.opencv_imgproc.rectangle;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_core.RectVector;
import org.bytedeco.opencv.opencv_core.Scalar;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_face.LBPHFaceRecognizer;
import org.bytedeco.opencv.opencv_objdetect.CascadeClassifier;
import org.bytedeco.opencv.opencv_videoio.VideoCapture;


public class TakeAttendance extends JFrame {

	private JPanel contentPane;
	JLabel label_photo1,fname,lname,txt_id_label1;
	JLabel lblNewLabel_8;

	
	
	ModelPerson mod = new ModelPerson();
    ControlPerson cod = new ControlPerson();

    private DaemonThread myThread = null;

    //JavaCV 1.5.1
    VideoCapture webSource = null;
    Mat cameraImage = new Mat();
    CascadeClassifier cascade = new CascadeClassifier("C:\\photos\\haarcascade_frontalface_alt.xml");
    LBPHFaceRecognizer recognizer = LBPHFaceRecognizer.create();

    BytePointer mem = new BytePointer();
    RectVector detectedFaces = new RectVector();

    String root, firstNamePerson, lastNamePerson, officePerson, dobPerson, telefone;

    int idPerson;

    Connectdb conecta = new Connectdb();
    static JLabel cid;
    static JLabel lec;

    private JLabel detail;
    private JLabel lblNewLabel_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RecognizeFace.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RecognizeFace.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RecognizeFace.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RecognizeFace.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
		
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TakeAttendance frame = new TakeAttendance();
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
	public TakeAttendance() {
		
		super();
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
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				new FacultyPortal().setVisible(true);
				dispose();
			}
		});
		lblNewLabel_2.setForeground(SystemColor.window);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setBounds(308, 56, 249, 16);
		contentPane.add(lblNewLabel_2);
		
		label_photo1 = new JLabel("");
		label_photo1.setBounds(45, 108, 814, 301);
		contentPane.add(label_photo1);
		
		fname=new JLabel("fname");
		fname.setFont(new Font("Tahoma", Font.BOLD, 16));
		fname.setBounds(282, 472, 84, 28);
		contentPane.add(fname);
		
		lname = new JLabel("lname");
		lname.setFont(new Font("Tahoma", Font.BOLD, 16)); 
		lname.setBounds(378, 472, 73, 28);
		contentPane.add(lname);
		
		lblNewLabel_8 = new JLabel(": Present");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_8.setBounds(459, 472, 110, 28);
		contentPane.add(lblNewLabel_8);
		
		txt_id_label1 = new JLabel("00");
		txt_id_label1.setFont(new Font("Tahoma", Font.BOLD, 16));
		txt_id_label1.setBounds(399, 509, 36, 16);
		contentPane.add(txt_id_label1);
		
		cid = new JLabel("New label");
		cid.setBounds(768, 472, 56, 16);
		contentPane.add(cid);
		
		lec = new JLabel("New label");
		lec.setBounds(768, 509, 56, 16);
		contentPane.add(lec);
		
		detail = new JLabel("Details>>");
		detail.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Details d1=new Details();
				Details.showdata();
				d1.setVisible(true);
				dispose();
				stopCamera();
				
			}

			private void stopCamera() {
				
			        myThread.runnable = false;
			        webSource.release();
			        //dispose();
			    
				
			}
		});
		detail.setFont(new Font("Tahoma", Font.BOLD, 16));
		detail.setBounds(768, 56, 91, 39);
		contentPane.add(detail);
		
		lblNewLabel_3 = new JLabel("> Attendance");
		lblNewLabel_3.setForeground(SystemColor.window);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_3.setBounds(549, 55, 120, 16);
		contentPane.add(lblNewLabel_3);
		
		recognizer.read("C:\\photos\\classifierLBPH.yml");
        recognizer.setThreshold(80);
        startCamera();
		
	}
	
	class DaemonThread implements Runnable {

        protected volatile boolean runnable = false;

        @Override
        public void run() {
            synchronized (this) {
                while (runnable) {
                    try {
                        if (webSource.grab()) {
                            webSource.retrieve(cameraImage);
                            Graphics g = label_photo1.getGraphics();

                            Mat imageGray = new Mat();
                            cvtColor(cameraImage, imageGray, COLOR_BGRA2GRAY);

                            RectVector detectedFace = new RectVector();
                            cascade.detectMultiScale(imageGray, detectedFace, 1.1, 2, 0, new Size(150, 150), new Size(500, 500));

                            for (int i = 0; i < detectedFace.size(); i++) {
                                Rect dadosFace = detectedFace.get(i);
                                rectangle(cameraImage, dadosFace, new Scalar(0, 255, 0, 3), 3, 0, 0);
                                Mat faceCapturada = new Mat(imageGray, dadosFace);
                                opencv_imgproc.resize(faceCapturada, faceCapturada, new Size(160, 160));

                                IntPointer rotulo = new IntPointer(1);
                                DoublePointer confidence = new DoublePointer(1);
                                recognizer.predict(faceCapturada, rotulo, confidence);
                                int prediction = rotulo.get(0);
                                String nome;
                                nome = firstNamePerson;

                                if (prediction == -1) {
                                    rectangle(cameraImage, dadosFace, new Scalar(0, 0, 255, 3), 3, 0, 0);
                                    idPerson = 0;
                                    fname.setText("No");
                                    lname.setText("Data");
                                    txt_id_label1.setText("");
                                    lblNewLabel_8.setText("");
                                    //labeloffice.setText("");
                                    //label_phone.setText("");
                                   // sendMessage_btn.setText("Send Message");
                                   
                                } else {
                                    rectangle(cameraImage, dadosFace, new Scalar(0, 255, 0, 3), 3, 0, 0);
                                    System.out.println(confidence.get(0));
                                    idPerson = prediction;
//                                    System.out.println("PESSOA RECONHECIDA COMO: " + idPerson);
                                    rec();
                                }
                            }

                            imencode(".bmp", cameraImage, mem);
                            Image im = ImageIO.read(new ByteArrayInputStream(mem.getStringBytes()));
                            BufferedImage buff = (BufferedImage) im;

                            try {
                                if (g.drawImage(buff, 0, 0, 360, 390, 0, 0, buff.getWidth(), buff.getHeight(), null)) {
                                    if (runnable == false) {
                                        this.wait();
                                    }
                                }
                            } catch (Exception e) {
                            }
                        }
                    } catch (Exception ex) {
                    }
                }
            }
        }
    }

    private void rec() {
        //Retrieve data from database
        new Thread() {
            @Override
            public void run() {
                try {
					conecta.connect();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                try {
                    conecta.executeSQL("SELECT * FROM student WHERE id = " + String.valueOf(idPerson));
                    while (conecta.rs.next()) 
                    {
                        firstNamePerson = conecta.rs.getString("firstname");
                        lastNamePerson = conecta.rs.getString("lastname");
                        fname.setText(conecta.rs.getString("firstname"));
                        lname.setText(conecta.rs.getString("lastname"));
                        lblNewLabel_8.setText("Present");
                        String date = new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis()));
                        String sql= "INSERT INTO attendance (id,firstname,lastname,date,lecture,courseid,attendance) VALUES (?,?,?,?,?,?,?)";
                        PreparedStatement ps= conecta.conn.prepareStatement(sql);
                       // String at="present";
                        ps.setLong(1, idPerson);
                        ps.setString(2, fname.getText());
                        ps.setString(3, lname.getText());
                        ps.setString(4, date);
                        ps.setString(5, lec.getText());
                        ps.setString(6, cid.getText());
                        ps.setString(7, lblNewLabel_8.getText());
                        int x=ps.executeUpdate();
						if(x>0)
							{
								System.out.println("Record Updated");
							}
						else
							{
							System.out.println("error");
							}
                        txt_id_label1.setText(conecta.rs.getString("id"));

                        

                        Array ident = conecta.rs.getArray("first_name");
                        String[] person = (String[]) ident.getArray();

                        for (String person1 : person) {
                            System.out.println(person1);
                        }

                    }
                    
                    
                } catch (Exception e) {
                }
                conecta.disconnect();
            }
        }.start();
    }

    public void startCamera() {
        new Thread() {
            @Override
            public void run() {
                webSource = new VideoCapture(0);
                myThread = new TakeAttendance.DaemonThread();
                Thread t = new Thread(myThread);
                t.setDaemon(true);
                myThread.runnable = true;
                t.start();
            }
        }.start();
    }
}
