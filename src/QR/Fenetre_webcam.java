package QR;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;



public class Fenetre_webcam extends JFrame {
	
	
	 public static String ID ;
	
	public Fenetre_webcam() throws IOException {
		 
		Webcam webcam = Webcam.getDefault();
        webcam.setViewSize(WebcamResolution.VGA.getSize());
        
        WebcamPanel webcamPanel = new WebcamPanel(webcam);
        webcamPanel.setSize(640,480);
        

 
        JPanel WEBCAM = new JPanel();
        WEBCAM.setBackground(Color.white);
        WEBCAM.setBounds(100,100,660,500);    
        WEBCAM.add(webcamPanel);
        Border lineborder = BorderFactory.createLineBorder(Color.GRAY, 6); 
        WEBCAM.setBorder(lineborder);
        
        JLabel titreWebcam = new JLabel("SHOW A QR CODE");
        titreWebcam.setBounds(100, 60, 280, 30);
        Font font = new Font("Arial",Font.BOLD,30);
        titreWebcam.setForeground(Color.gray);
        titreWebcam.setFont(font);
        
        JLabel titreVideo = new JLabel("VIDEO");
        titreVideo.setBounds(800, 450, 500, 30);
        titreVideo.setForeground(Color.gray);
        titreVideo.setFont(font);
        
        JLabel titreInformation = new JLabel("INFORMATIONS");
        titreInformation.setBounds(800, 60, 500, 30);
        titreInformation.setForeground(Color.gray);
        titreInformation.setFont(font);
        
        JLabel titreSound = new JLabel("SOUND");
        titreSound.setBounds(1350, 450, 500, 30);
        titreSound.setForeground(Color.gray);
        titreSound.setFont(font);
        
        
        JPanel TEXTE = new JPanel();
        TEXTE.setBackground(Color.WHITE);
        TEXTE.setBounds(800, 100, 1000, 300);

        TEXTE.setBorder(lineborder);
        
        JPanel VIDEO = new JPanel();
       
        VIDEO.setBounds(800, 500, 460, 460);
        VIDEO.setBorder(lineborder);
        Canvas c = new Canvas();
        VIDEO.add(c);
        c.setBounds(800, 500, 460, 460);
        c.setBackground(Color.WHITE);
        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(),"C:/Program Files/VideoLAN/VLC");
        Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
        MediaPlayerFactory mpf = new MediaPlayerFactory();
        EmbeddedMediaPlayer emp =mpf.newEmbeddedMediaPlayer();
        emp.setVideoSurface(mpf.newVideoSurface(c));
        
       
        
        emp.setEnableKeyInputHandling(false);
        emp.setEnableMouseInputHandling(false);
        
        
        
        JPanel SOUND = new JPanel();
        SOUND.setBackground(Color.WHITE);
        SOUND.setBounds(1350, 500, 460, 460);
        SOUND.setBorder(lineborder);
        
        
        JPanel IMAGE = new JPanel();
        IMAGE.setBounds(100, 550, 640, 400);
        IMAGE.setBackground(Color.white);
        BufferedImage image = ImageIO.read(new File("./java.jpg"));
        JLabel label = new JLabel(new ImageIcon(image));
        IMAGE.add(label);
        
        
        
        
    
        
        
        Font font1 = new Font("Arial",Font.ITALIC,30);
        JLabel texte = new JLabel();
        texte.setBounds(0,10, 100, 50);
        texte.setFont(font1);
        texte.setForeground(Color.BLACK);
        TEXTE.add(texte);
        
        
        JFrame frame = new JFrame();
        frame.getContentPane().setBackground(Color.white);
        frame.setSize(1000,600);
        frame.setTitle("QUICK TIMETABLE");
        frame.add(WEBCAM); 
        frame.add(TEXTE);
        frame.add(IMAGE);
        frame.add(titreWebcam);
        frame.add(titreVideo);
        frame.add(titreInformation);
        frame.add(titreSound);
        frame.add(VIDEO);
        frame.add(SOUND);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
        frame.setVisible(true);
        String file2 ="small.mp4";
        System.out.println("cul");
		Thread	 t =new webcamphoto("t1",webcam,texte);
		t.run();
        
        System.out.println("cul");
        emp.setRepeat(true);
        
        emp.prepareMedia(file2);
		emp.play();
       
        

         }   	
	}
            	 
            	 
            	 



	
	
	
	
	


