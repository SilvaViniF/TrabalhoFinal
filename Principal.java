import javax.swing.JFrame;
import java.util.List;

public class Principal {
	
	public static void main(String args[]) throws InterruptedException {
		JFrame janela = new JFrame();
		janela.setSize(1200, 768);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
        MyPanel panel = new MyPanel();		
		janela.add(panel);	
        janela.setVisible(true);
        List<Objeto> lista = panel.getLista();
        
        lista.add(new Obstaculo(100, 150, 180, 180, "predio.png"));
        lista.add(new Obstaculo(300,500,120,120,"tree.png"));
        lista.add(new Obstaculo(900,200,120,120,"casa.png"));
        lista.add(new Obstaculo(900,600,100,100,"pedra.png"));
        
        while (true) {
        // redesenhar tela
			janela.repaint();
            
				
			Thread.sleep(100);		
        }
    }
}