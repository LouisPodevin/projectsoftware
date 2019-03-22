package QR;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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

public class Fenetre_webcam extends JFrame {
	
	
	public Fenetre_webcam() throws IOException {
		 
		Webcam webcam = Webcam.getDefault();
        webcam.setViewSize(WebcamResolution.VGA.getSize());
        
        WebcamPanel webcamPanel = new WebcamPanel(webcam);
        webcamPanel.setSize(640,480);
        

 
        JPanel WEBCAM = new JPanel();
        WEBCAM.setBackground(Color.white);
        WEBCAM.setBounds(200,100,660,500);    
        WEBCAM.add(webcamPanel);
        Border lineborder = BorderFactory.createLineBorder(Color.GRAY, 6); 
        WEBCAM.setBorder(lineborder);
        
        JLabel titreWebcam = new JLabel("SHOW A QR CODE");
        titreWebcam.setBounds(200, 60, 280, 30);
        Font font = new Font("Arial",Font.BOLD,30);
        titreWebcam.setForeground(Color.gray);
        titreWebcam.setFont(font);
        
        JLabel titreBouton = new JLabel("PUSH FOR TAKE A PICTURE");
        titreBouton.setBounds(200, 610, 500, 30);
        titreBouton.setForeground(Color.gray);
        titreBouton.setFont(font);
        
        JLabel titreInformation = new JLabel("INFORMATIONS");
        titreInformation.setBounds(1000, 60, 500, 30);
        titreInformation.setForeground(Color.gray);
        titreInformation.setFont(font);
        
        
        JPanel TEXTE = new JPanel();
        TEXTE.setBackground(Color.WHITE);
        TEXTE.setBounds(1000, 100, 600, 850);
        TEXTE.setBorder(lineborder);
        
        
        JButton IMAGE = new JButton();
        IMAGE.setBounds(200, 650, 640, 294);
        IMAGE.setBackground(Color.white);
        BufferedImage image = ImageIO.read(new File("./java.jpg"));
        JLabel label = new JLabel(new ImageIcon(image));
        IMAGE.add(label);
        IMAGE.setBorder(lineborder);
        
    
        
        
        Font font1 = new Font("Arial",Font.ITALIC,50);
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
        frame.add(titreBouton);
        frame.add(titreInformation);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
       
          	
            	 new webcamthread("t1",webcam,texte);
            	
            
          
}
}


class  webcamthread extends Thread {
	
	public webcamthread(String name, Webcam webcam,JLabel texte) {
		
		super(name);
		this.start();
		try {
    		
    		while(true) {
			ImageIO.write(webcam.getImage(),"PNG",new File("test.png"));
			File file = new File("test.png");
            String decodedText = main.decodeQRCode(file);
            if(decodedText == null) {
                
                texte.setText("<html>No QR <br> Code found in the image</html>");
                
            }else {
            	
                System.out.println("Decoded text = " + decodedText);
                texte.setText(decodedText);
                try {
					this.sleep(10000);
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
            } }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.stop();
		
		
	}
	}
	
	
	
	
	


