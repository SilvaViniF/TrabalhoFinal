import javax.swing.JFrame;
import java.util.List;

public class Principal {
	
	public static void main(String args[]) throws InterruptedException {
		JFrame janela = new JFrame();
		janela.setSize(1200, 768);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
        MyPanel panel = new MyPanel();		
		janela.add(panel);	

        List<Objeto> lista = panel.getLista();
        
        janela.setVisible(true);
		
        lista.add(new Obstaculo(100, 150, 150, 150, "predio.png"));
        lista.add(new Obstaculo(800,300,150,300,"casa.png"));
        
        // while (true) {
          
        // // redesenhar tela
		// 	janela.repaint();
				
		// 	Thread.sleep(100);		
        // }
    }
}