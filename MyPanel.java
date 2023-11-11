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
        BufferedImage imgoriginal1,imgoriginal2,imgoriginal3,imgoriginal4;
		try {
            //obstaculo 1 - predio
			imgoriginal1 = ImageIO.read(new File("predio.png"));
			BufferedImage imgresize1 = new BufferedImage(150, 150, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2d1 = imgresize1.createGraphics();
			g2d1.drawImage(imgoriginal1, 0, 0, 150, 150, null);
			g2d1.dispose();
			// obstaculo 2 - arvore
            imgoriginal2 = ImageIO.read(new File("tree.png"));
			BufferedImage imgresize2 = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2d2 = imgresize2.createGraphics();
			g2d2.drawImage(imgoriginal2, 0, 0, 100, 100, null);
			g2d2.dispose();
            //obstaculo 3 - casa
            imgoriginal3 = ImageIO.read(new File("casa.png"));
			BufferedImage imgresize3 = new BufferedImage(130, 130, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2d3 = imgresize3.createGraphics();
			g2d3.drawImage(imgoriginal3, 0, 0, 100, 100, null);
			g2d3.dispose();
            //obstaculo 4 - pedra
            imgoriginal4 = ImageIO.read(new File("pedra.png"));
			BufferedImage imgresize4 = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2d4 = imgresize4.createGraphics();
			g2d4.drawImage(imgoriginal4, 0, 0, 100, 100, null);
			g2d4.dispose();
            //desenha obstaculos
			g.drawImage(imgresize1, 150, 200, null);
			g.drawImage(imgresize2, 800, 100, null);
			g.drawImage(imgresize3, 1000, 500, null);
			g.drawImage(imgresize4, 300, 600, null);
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}	
}