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
        //adicionando os obstaculos
        lista.add(new Obstaculo(100, 150, 180, 180, "predio.png"));
        lista.add(new Obstaculo(300,500,120,120,"tree.png"));
        lista.add(new Obstaculo(900,200,120,120,"casa.png"));
        lista.add(new Obstaculo(900,600,100,100,"pedra.png"));
        // adicionando guerreiros:
        for(int i=0;i<100;i+=10){
            lista.add(new Guerreiro(i,i,30,30));
        }

        while (true) {
        // redesenhar tela
        for(Objeto G1 : lista){
            if(G1 instanceof Guerreiro){
                Guerreiro g1 = (Guerreiro) G1;
                g1.move();
                if(g1.obj_parado()){
                    g1.mudadirecao();
                }
                for(Objeto G2 : lista){
                    if(G2 instanceof Guerreiro && G2 != G1){
                        Guerreiro g2 = (Guerreiro) G2;
                        g2.move();
                        // if(g2.obj_parado()){
                        //     g2.mudadirecao();
                        // }
                        // if(g1.Colisao(g2)){
                        //     g1.move();
                        //     g2.move();
                        // }
                    }
                }
            }
        }
		janela.repaint();
            
				
		Thread.sleep(100);		
        }
    }
}