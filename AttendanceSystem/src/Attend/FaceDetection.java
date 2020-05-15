package Attend;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import com.googlecode.javacv.CanvasFrame;
import com.googlecode.javacv.OpenCVFrameGrabber;
import com.googlecode.javacv.cpp.opencv_core;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
import com.googlecode.javacv.cpp.opencv_highgui;
import com.googlecode.javacv.cpp.opencv_highgui.CvCapture;
import static com.googlecode.javacv.cpp.opencv_core.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FaceDetection extends JFrame {

	private JPanel contentPane;
	JButton btnNewButton_1 ;
	private DaemonThread myThread = null;
	int count = 0;
	VideoCapture webSource = null;
	Mat frame = new Mat();
	MatOfByte mem = new MatOfByte();
	CascadeClassifier faceDetector = new CascadeClassifier(FaceDetection.class.getResource("haarcascade_frontalface_alt.xml").getPath().substring(1));
	MatOfRect faceDetections = new MatOfRect();
	private JButton btnNewButton_2;
	
	class DaemonThread implements Runnable {

        protected volatile boolean runnable = false;

        @Override
        public void run() {
            synchronized (this) {
                while (runnable) {
                    if (webSource.grab()) {
                        try {
                            webSource.retrieve(frame);
                            Graphics g = contentPane.getGraphics();
                            faceDetector.detectMultiScale(frame, faceDetections);
                            for (Rect rect : faceDetections.toArray()) {
                               // System.out.println("ttt");
                                Core.rectangle(frame, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
                                        new Scalar(0, 255,0));
                            }
                            Highgui.imencode(".bmp", frame, mem);
                            Image im = ImageIO.read(new ByteArrayInputStream(mem.toArray()));
                            BufferedImage buff = (BufferedImage) im;
                            if (g.drawImage(buff, 0, 0, getWidth(), getHeight()-150 , 0, 0, buff.getWidth(), buff.getHeight(), null)) {
                                if (runnable == false) {
                                    System.out.println("Paused ..... ");
                                    this.wait();
                                }
                            }
                        } catch (Exception ex) {
                            System.out.println("Error!!");
                            ex.printStackTrace();
                        }
                    }
                }
            }
        }
    }

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FaceDetection frame = new FaceDetection();
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
	public FaceDetection() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1113, 625);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(82, 23, 925, 479);
		contentPane.add(panel);

		JButton btnNewButton = new JButton("Start");
		btnNewButton.setBounds(238, 540, 97, 25);
		contentPane.add(btnNewButton);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				/*Thread wcam=new Thread()
				{
					public void run()
					{
						CvCapture cap=opencv_highgui.cvCreateCameraCapture(0);
						opencv_highgui.cvSetCaptureProperty(cap, opencv_highgui.CV_CAP_PROP_FRAME_HEIGHT, 20);
						opencv_highgui.cvSetCaptureProperty(cap, opencv_highgui.CV_CAP_PROP_FRAME_WIDTH, 20);
						IplImage gimg=opencv_highgui.cvQueryFrame(cap);
						CanvasFrame can=new CanvasFrame("Title");
						
						while(can.isVisible() && (gimg=opencv_highgui.cvQueryFrame(cap)) !=null)
						{
							can.showImage(gimg);
							
						}
						
					}
				};
					wcam.start();*/
				
				
				webSource = new VideoCapture(0);
				//webSource = new VideoCapture(0); // video capture from default cam
		        myThread = new DaemonThread(); //create object of threat class
		        Thread t = new Thread(myThread);
		        t.setDaemon(true);
		        myThread.runnable = true;
		        t.start();                 //start thrad
		        btnNewButton.setEnabled(false);  // deactivate start button
		        btnNewButton_1.setEnabled(true);   //  activate stop button


				
			}
		});
		
		btnNewButton_1 = new JButton("Pause");
		btnNewButton_1.setBounds(636, 540, 97, 25);
		contentPane.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 myThread.runnable = false;            // stop thread
				 btnNewButton.setEnabled(true);   // activate start button 
				 btnNewButton_1.setEnabled(false);     // deactivate stop button

			        webSource.release();  // stop caturing fron cam

				
			}
		});
		
		
		btnNewButton_2 = new JButton("Take Picture");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				

				
				final long serialVersionUID = 1L;

			    CanvasFrame frame = new CanvasFrame("Web Cam");
			    boolean running = false;
			    int frameWidth = 800;
			    int frameHeight = 600;
			    OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);
			    BufferedImage bufImg;
			   
			        try
			        {

			            grabber.setImageWidth(frameWidth);
			            grabber.setImageHeight(frameHeight);
			            grabber.start();
			            while (running)
			            {

			                final IplImage cvimg = grabber.grab();
			                if (cvimg != null)
			                {

			                    // cvFlip(cvimg, cvimg, 1); // mirror

			                    // show image on window
			                    bufImg = cvimg.getBufferedImage();
			                    frame.showImage(bufImg);
			                }
			            }
			            grabber.stop();
			            grabber.release();
			            frame.dispose();
			        }
			        catch (Exception e)
			        {
			            e.printStackTrace();
			        }
			    
				
			}
		});
		
		
		btnNewButton_2.setBounds(455, 540, 119, 25);
		contentPane.add(btnNewButton_2);
	}
}
