import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MyPanel extends JPanel {
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);		
		//desenhando objetos
        BufferedImage imgoriginal1,imgoriginal2,imgoriginal3;
		try {
            //obstaculo 1 - predio
			imgoriginal1 = ImageIO.read(new File("predio.png"));
			BufferedImage imgresize1 = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2d1 = imgresize1.createGraphics();
			g2d1.drawImage(imgoriginal1, 0, 0, 100, 100, null);
			g2d1.dispose();
			// obstaculo 2 - arvore
            imgoriginal2 = ImageIO.read(new File("tree.png"));
			BufferedImage imgresize2 = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2d2 = imgresize2.createGraphics();
			g2d2.drawImage(imgoriginal2, 0, 0, 100, 100, null);
			g2d2.dispose();




            //desenha obstaculos
			g.drawImage(imgresize1, 150, 200, null);
			g.drawImage(imgresize2, 800, 100, null);
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}	
}