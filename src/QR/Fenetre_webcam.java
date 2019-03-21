package QR;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;

public class Fenetre_webcam extends JFrame {
  
	
	public Fenetre_webcam() {
		 
		Webcam webcam = Webcam.getDefault();
        webcam.setViewSize(WebcamResolution.QVGA.getSize());
        
        WebcamPanel webcamPanel = new WebcamPanel(webcam);
        webcamPanel.setSize(320,240);
        
        JButton bouton = new JButton("Capture");
        
        JFrame frame = new JFrame();
        frame.setSize(1080,1040);
        frame.add(webcamPanel); 
        frame.add(bouton,BorderLayout.SOUTH);
        frame.setLocationRelativeTo(null);
        frame.pack();
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
		            } else {
		                System.out.println("Decoded text = " + decodedText);
		            }
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
          });
}
}

