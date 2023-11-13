import javax.swing.JFrame;
import java.util.Random;
import java.awt.Color;
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
            System.out.println(lista.size());
        }
    }
    
private static void attGuerreiros(List<Objeto> lista) {
    for (Objeto G1 : lista) {
        if (G1 instanceof Guerreiro) {
            Guerreiro g1 = (Guerreiro) G1;
            g1.move();
            if (g1.obj_parado()) {
                g1.mudadirecao();
                g1.move();
            }
            for (Objeto G2 : lista) {
                if (G2 instanceof Guerreiro && G2 != G1) {
                    Guerreiro g2 = (Guerreiro) G2;
                    g2.move();
                    if (g2.obj_parado()) {
                        g2.mudadirecao();
                        g2.move();
                    }
                    // check collisions
                    if (g1.Colisao(g2)) {
                        g1.dimenergia(g2, lista);
                        g2.dimenergia(g1, lista);
                        g1.mudadirecao();
                        g2.mudadirecao();
                    }
                }
                if (g1.Colisao(G2)) {
                    g1.dimenergiaobs(G2, lista);
                    g1.mudadirecao();
                }
            }
        }
    }
}
        

    
}