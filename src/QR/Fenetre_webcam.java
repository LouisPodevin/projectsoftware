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
        
        JButton bouton = new JButton("Capture");
 
        JPanel WEBCAM = new JPanel();
        WEBCAM.setBackground(Color.white);
        WEBCAM.setBounds(80,20,700,500);    
        WEBCAM.add(webcamPanel);
        WEBCAM.add(bouton,BorderLayout.SOUTH);
        
      
        
        
        JPanel TEXTE = new JPanel();
        TEXTE.setBackground(Color.white);
        Border lineborder = BorderFactory.createLineBorder(Color.GRAY, 5); 
        TEXTE.setBorder(lineborder);
        TEXTE.setBounds(1000, 30, 800, 280);
        
        
        JPanel IMAGE = new JPanel();
        IMAGE.setBounds(90, 600, 500, 294);
        IMAGE.setBackground(Color.white);
        BufferedImage image = ImageIO.read(new File("./java.jpg"));
        JLabel label = new JLabel(new ImageIcon(image));
        IMAGE.add(label);
        
        
        Font font = new Font("Arial",Font.BOLD,50);
        JLabel texte = new JLabel();
        texte.setFont(font);
        TEXTE.add(texte);
        
        
        JFrame frame = new JFrame();
        frame.getContentPane().setBackground(Color.white);
        frame.setSize(1000,600);
        frame.add(WEBCAM); 
        frame.add(TEXTE);
        frame.add(IMAGE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        bouton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){	
            	
            	try {
            		
            		
					ImageIO.write(webcam.getImage(),"PNG",new File("test.png"));
					File file = new File("test.png");
		            String decodedText = main.decodeQRCode(file);
		            if(decodedText == null) {
		                System.out.println("No QR Code found in the image");
		                texte.setText("No QR Code found in the image");
		            } else {
		                System.out.println("Decoded text = " + decodedText);
		                texte.setText(decodedText);
		            }
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
          });
}
}

