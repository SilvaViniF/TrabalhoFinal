import java.util.Random;
import java.awt.Color;
import java.util.ArrayList;
//import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class Principal {

    public static void main(String args[]) throws InterruptedException {
        JFrame janela = new JFrame();
        janela.setSize(1200, 768);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MyPanel panel = new MyPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        janela.add(panel);
        janela.setVisible(true);
        List<Objeto> lista = panel.getLista();
        List<Projetil> projs = new ArrayList<Projetil>();
        List<Projetil> projsRemove = new ArrayList<Projetil>();
        while (true) {
            // lista.addAll(projs);

            // adicionando os obstaculos
            lista.add(new Obstaculo(100, 150, 180, 180, "predio.png"));
            lista.add(new Obstaculo(300, 500, 120, 120, "tree.png"));
            lista.add(new Obstaculo(900, 200, 120, 120, "casa.png"));
            lista.add(new Obstaculo(900, 600, 100, 100, "pedra.png"));

            Random rand = new Random();
            float ataque;
            int direcao;
            Guerreiro a;
            // adicionando guerreiros:
            for (int i = 0; i < 5; i++) {
                ataque = rand.nextInt(20) * rand.nextFloat();
                direcao = rand.nextInt(4);
                a = new Guerreiro(rand.nextInt(1000), rand.nextInt(500), 30, 30, String.valueOf(i), ataque, direcao);
                lista.add(a);
            }

            Objeto remove = null;

            while (true) {
                // redesenhar tela
                if (lista.size() > 5) {
                    for (Objeto G1 : lista) {
                        if (G1 instanceof Guerreiro) {
                            Guerreiro g1 = (Guerreiro) G1;
                            if (g1.getEnergia() <= 0) {
                                remove = G1;
                            } else {
                                g1.setTimerDisparo(g1.getTimerDisparo() + 1);

                                if (g1.getTimerDisparo() == g1.getTempoDeDisparo()) {
                                    projs.add(g1.atira());
                                    g1.setTimerDisparo(0);
                                }

                                g1.move();
                                // projs.addAll(g1.getProjs());

                                for (Objeto G2 : lista) {
                                    if (G2 instanceof Guerreiro && G2 != G1) {
                                        Guerreiro g2 = (Guerreiro) G2;
                                        g2.move();
                                        // checa colisoes
                                        if (g1.Colisao(g2)) {
                                            g1.dimEnergia(g2, lista);
                                            g2.dimEnergia(g1, lista);
                                            g1.mudaDirecao();
                                            g2.mudaDirecao();
                                        }
                                    } else if (G2 instanceof Obstaculo && g1.Colisao(G2)) {
                                        g1.dimEnergiaObs(G2, lista);
                                        g1.mudaDirecao();
                                    }
                                }
                            }
                        } else if (G1 instanceof Projetil) {
                            Projetil g1 = (Projetil) G1;
                            g1.move();
                            if (g1.getX() <= 0 || g1.getX() >= 1200 || g1.getY() <= 0 || g1.getY() >= 768) {
                                projs.remove(g1);
                                projsRemove.add(g1);
                            }
                        }
                    }

                    janela.repaint();
                    Thread.sleep(50);
                } else {
                    Guerreiro vencedor = (Guerreiro) lista.get(lista.size() - 1);
                    JOptionPane.showMessageDialog(janela, "O guerreiro n° " + vencedor.getId() + " venceu o jogo!",
                            "FIM DE JOGO", JOptionPane.OK_OPTION, UIManager.getIcon("OptionPane.informationIcon"));
                    break;
                }
                if (remove != null) {
                    lista.remove(remove);
                }
                remove = null;
                lista.addAll(projs);
                lista.removeAll(projsRemove);
                projsRemove.clear();
            }

            lista.clear();
        }
    }
}