import java.util.Random;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
public class Principal {

    public static void main(String args[]) throws InterruptedException {
        JFrame janela = new JFrame("Trabalho Final");
        janela.setSize(1500, 768);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        janela.setLayout(null);

        MyPanel panel = new MyPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setBounds(0, 0, 1200, 768);
        janela.add(panel);
        
        PainelLateral1 painelLateral = new PainelLateral1();
        painelLateral.setBackground(Color.LIGHT_GRAY);
        painelLateral.setBounds(1225, 0, 300, 768);
        janela.add(painelLateral);
        
        janela.setVisible(true);
        
        List<Objeto> lista = panel.getLista();
        List<Guerreiro> listaGuerreiros = new ArrayList<Guerreiro>();
        List<Projetil> projs = new ArrayList<Projetil>();
        List<Projetil> projsRemove = new ArrayList<Projetil>();
        int numGuerreiros = 30;

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
            Guerreiro g;
            float resistencia;
            int tempoDeDisparo;
            // adicionando guerreiros:
            for (int i = 0; i < numGuerreiros; i++) {
                ataque = rand.nextInt(20) * rand.nextFloat();
                direcao = rand.nextInt(4);
                resistencia = rand.nextInt(5) * rand.nextFloat();
                tempoDeDisparo = rand.nextInt(5) * 10 + 10;
                g = new Guerreiro(rand.nextInt(1000), rand.nextInt(500), 30, 30, String.valueOf(i), ataque, direcao, resistencia, tempoDeDisparo);
                lista.add(g);
                listaGuerreiros.add(g);
            }
            painelLateral.setLista(listaGuerreiros);

            Objeto remove = null;
            while (true) {
                // redesenhar tela
                if (listaGuerreiros.size() > 1) {
                    for (Objeto obj : lista) {
                        if (obj instanceof Guerreiro) {
                            Guerreiro guerreiro = (Guerreiro) obj;
                            if (guerreiro.getEnergia() <= 0) {
                                remove = obj;
                                listaGuerreiros.remove(obj);
                                painelLateral.setLista(listaGuerreiros);
                            } else {
                                guerreiro.setTimerDisparo(guerreiro.getTimerDisparo() + 1);

                                if (guerreiro.getTimerDisparo() == guerreiro.getTempoDeDisparo()) {
                                    projs.add(guerreiro.atira());
                                    guerreiro.setTimerDisparo(0);
                                }

                                guerreiro.move();

                                boolean colidiu = false;
                                for (Objeto obj2 : lista) {
                                    if (obj2 instanceof Guerreiro && obj2 != obj) {
                                        Guerreiro guerreiro2 = (Guerreiro) obj2;
                                        if (guerreiro.Colisao(guerreiro2)) {
                                            guerreiro.dimEnergia(guerreiro2.getAtaque(), lista);
                                            guerreiro2.dimEnergia(guerreiro.getAtaque(), lista);
                                            guerreiro.mudaDirecao();
                                            guerreiro2.mudaDirecao();
                                            colidiu = true;
                                        }
                                    } else if (obj2 instanceof Obstaculo && guerreiro.Colisao(obj2)) {
                                        guerreiro.dimEnergiaObs(obj2, lista);
                                        guerreiro.mudaDirecao();
                                        colidiu = true;
                                    }
                                }
                                guerreiro.setEmColisao(colidiu);
                            }
                        } else if (obj instanceof Projetil) {
                            Projetil proj = (Projetil) obj;
                            proj.move();
                            if (proj.getX() <= 0 || proj.getX() >= 1200 || proj.getY() <= 0 || proj.getY() >= 768) {
                                projs.remove(proj);
                                projsRemove.add(proj);
                            }

                            for (Objeto obj2 : lista) {
                                if (obj2 instanceof Guerreiro) {
                                    Guerreiro guerreiro2 = (Guerreiro) obj2;
                                    if (proj.Colisao(guerreiro2)) {
                                        guerreiro2.dimEnergia(proj.getAtaque(), lista);
                                        guerreiro2.setEmColisao(true);
                                        projs.remove(proj);
                                        projsRemove.add(proj);
                                    }
                                } else if (obj2 instanceof Obstaculo && proj.Colisao(obj2)) {
                                    projs.remove(proj);
                                    projsRemove.add(proj);
                                }
                            }
                        }
                    }

                    janela.repaint();
                    Thread.sleep(50);
                } else {
                    JOptionPane.showMessageDialog(janela, "O guerreiro n° " + listaGuerreiros.get(0).getId() + " venceu o jogo!",
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