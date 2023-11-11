import javax.swing.JFrame;

public class Principal {
	
	public static void main(String args[]) throws InterruptedException {
		JFrame janela = new JFrame();
		janela.setSize(1200, 768);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MyPanel panel = new MyPanel();		
		janela.add(panel);		
		
		janela.setVisible(true);

    }
}