package QR;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

import com.github.sarxos.webcam.Webcam;

class  webcamphoto extends Thread {
	
	private Webcam webcam;
	private JLabel texte;
	public webcamphoto(String name, Webcam webcam,JLabel texte)  {
		this.webcam= webcam;
		this.texte=texte;
		
	}
	public void run() {
		
		try {
    		
    		
    			while(true){
			ImageIO.write(webcam.getImage(),"PNG",new File("test.png"));
			File file = new File("test.png");
            String decodedText = main.decodeQRCode(file);
            if(decodedText == null) {
                
                texte.setText("<html>No QR <br> Code found in the image</html>");
                
            }else {
            	
                System.out.println("Decoded text = " + decodedText);
                
                texte.setText(decodedText);
                Fenetre_webcam.ID =decodedText.split("<br>")[decodedText.split("<br>").length-1].substring(0,4);
                
                
               try {
            	  synchronized(this){ 
				this.wait();
				System.out.println("bite");
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
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
