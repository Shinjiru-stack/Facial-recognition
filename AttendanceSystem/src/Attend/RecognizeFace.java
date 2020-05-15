package Attend;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
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
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RecognizeFace extends JFrame {

	private JPanel contentPane;
	JLabel label_name,labeloffice,jlabel10,txt_id_label,label_photo;
	
	
	ModelPerson mod = new ModelPerson();
    ControlPerson cod = new ControlPerson();

    private RecognizeFace.DaemonThread myThread = null;

    //JavaCV 1.5.1
    VideoCapture webSource = null;
    Mat cameraImage = new Mat();
    CascadeClassifier cascade = new CascadeClassifier("C:\\photos\\haarcascade_frontalface_alt.xml");
    LBPHFaceRecognizer recognizer = LBPHFaceRecognizer.create();

    BytePointer mem = new BytePointer();
    RectVector detectedFaces = new RectVector();

    //Vars
    String root, firstNamePerson, lastNamePerson, officePerson, dobPerson, telefone;
    //Social Info
    String facebook, insta, linkedin, git;
    int idPerson;

    //Utils
    Connectdb conecta = new Connectdb();

	
	
	
	

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
					new RecognizeFace().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RecognizeFace() {
		
		
		super();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 885, 546);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		label_photo = new JLabel("");
		label_photo.setBounds(12, 57, 384, 326);
		contentPane.add(label_photo);
		
		label_name = new JLabel("                        First-Lastname");
		label_name.setFont(new Font("Tahoma", Font.BOLD, 20));
		label_name.setBounds(12, 396, 434, 44);
		contentPane.add(label_name);
		
		labeloffice = new JLabel("                           Office");
		labeloffice.setFont(new Font("Tahoma", Font.BOLD, 20));
		labeloffice.setBounds(12, 449, 434, 37);
		contentPane.add(labeloffice);
		
		txt_id_label = new JLabel("");
		txt_id_label.setBounds(674, 99, 56, 16);
		contentPane.add(txt_id_label);
		
		jlabel10 = new JLabel("");
		jlabel10.setBounds(554, 231, 254, 16);
		contentPane.add(jlabel10);
		
		
		
		
		
		
		recognizer.read("C:\\photos\\classifier1LBPH.yml");
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
                            Graphics g = label_photo.getGraphics();

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
                                    label_name.setText("Disconnected");
                                    txt_id_label.setText("");
                                    labeloffice.setText("");
                                    
                                    //label_phone.setText("");
                                   // sendMessage_btn.setText("Send Message");
                                    facebook = "";
                                    insta = "";
                                    git = "";
                                    linkedin = "";
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
                    conecta.executeSQL("SELECT * FROM persons WHERE id = " + String.valueOf(idPerson));
                    while (conecta.rs.next()) {
                        firstNamePerson = conecta.rs.getString("firstname");
                        lastNamePerson = conecta.rs.getString("lastname");
                        jlabel10.setText("Hi, " + firstNamePerson + " " + lastNamePerson);
                        label_name.setText(conecta.rs.getString("firstname") + " " + conecta.rs.getString("lastname"));
                        labeloffice.setText(conecta.rs.getString("office"));
                        String date = new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis()));
                        String sql= "update attendance set attend = 'present',date=? where id='"+idPerson+"'";
                       // String sq="insert into attendance(firstname,lastname,attend,date) values (?,?,?,?)";
                        PreparedStatement ps= conecta.conn.prepareStatement(sql);
                       // String at="present";
                        //ps.setString(1, firstNamePerson);
                        //ps.setString(2, lastNamePerson);
                       // ps.setString(3, at);
                        ps.setString(1, date);
		                ps.execute();
		                System.out.println("Record Updated");
                        //telefone = conecta.rs.getString("phone_number");
                        //label_phone.setText(telefone);
                        //sendMessage_btn.setText("Send Message to " + conecta.rs.getString("phone_number"));
                        txt_id_label.setText(conecta.rs.getString("id"));

                        //Social Info
                        facebook = conecta.rs.getString("profile_facebook");
                        insta = conecta.rs.getString("profile_instagram");
                        linkedin = conecta.rs.getString("profile_linkedin");
                        git = conecta.rs.getString("profile_github");

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
                myThread = new RecognizeFace.DaemonThread();
                Thread t = new Thread(myThread);
                t.setDaemon(true);
                myThread.runnable = true;
                t.start();
            }
        }.start();
    }
}
