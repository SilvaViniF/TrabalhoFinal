import javax.swing.JFrame;
import java.util.Random;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Principal {
    
	public static void main(String args[]) throws InterruptedException {
		JFrame janela = new JFrame();
		janela.setSize(1200, 768);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Random rand = new Random();
        MyPanel panel = new MyPanel();	
        panel.setBackground(Color.LIGHT_GRAY);
		janela.add(panel);	
        janela.setVisible(true);
        List<Objeto> lista = panel.getLista();
        //adicionando os obstaculos
        lista.add(new Obstaculo(100, 150, 180, 180, "predio.png"));
        lista.add(new Obstaculo(300,500,120,120,"tree.png"));
        lista.add(new Obstaculo(900,200,120,120,"casa.png"));
        lista.add(new Obstaculo(900,600,100,100,"pedra.png"));
        
        // adicionando guerreiros:
        for(int i=0;i<10;i++){
            lista.add(new Guerreiro(rand.nextInt(1000) ,rand.nextInt(500),30,30));
        }

        while (true) {
            attGuerreiros(lista);
            janela.repaint();
            Thread.sleep(100);
            
        }
    }
    
private static void attGuerreiros(List<Objeto> lista) {
    List<Guerreiro> guerreirosMortos = new ArrayList<>();

    for (Objeto obj : lista) {
        if (obj instanceof Guerreiro) {
            Guerreiro guerreiro = (Guerreiro) obj;
                guerreiro.move();
                for (Objeto outro : lista) {
                if (outro != guerreiro) {
                    if (guerreiro.Colisao(outro)) {
                        guerreiro.mudadirecao();
                        guerreiro.dimenergiaobs(outro, lista);
                        if (guerreiro.getEnergia() <= 0) {
                            guerreirosMortos.add(guerreiro);
                            }
                        }
                    }
            }
            
        }
    }
    
    lista.removeAll(guerreirosMortos);
}
        

    
}