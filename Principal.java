import java.util.Random;
import java.util.Vector;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.swing.*;
import java.util.Collections;

public class Principal {
	public static final int HEIGHT = 768;
	public static final int WIDTH = 1200;

	public static void main(String args[]) throws InterruptedException {
		JFrame janela = new JFrame("Trabalho Final");
		janela.setSize(1500, HEIGHT);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		janela.setLayout(null);

		MyPanel panel = new MyPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(0, 0, WIDTH, HEIGHT);
		janela.add(panel);

		PainelLateral1 painelLateral = new PainelLateral1();
		painelLateral.setBackground(Color.LIGHT_GRAY);
		painelLateral.setBounds(1225, 0, 300, HEIGHT);
		janela.add(painelLateral);

		janela.setVisible(true);

		List<Objeto> lista = Collections.synchronizedList(panel.getLista());
		List<Guerreiro> listaGuerreiros = Collections.synchronizedList(new Vector<Guerreiro>());

		int numGuerreiros = 30;

		while (true) {
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
				tempoDeDisparo = (rand.nextInt(5) + 1) * 10 + 10;
				g = new Guerreiro(rand.nextInt(1000), rand.nextInt(500), 30, 30, String.valueOf(i), ataque, direcao,
						resistencia, tempoDeDisparo);
				lista.add(g);
				listaGuerreiros.add(g);
			}
			painelLateral.setLista(listaGuerreiros);

			while (true) {
				// redesenhar tela
				if (listaGuerreiros.size() > 1) {
					ListIterator<Objeto> iterator = lista.listIterator();
					while (iterator.hasNext()) {
						Objeto obj = iterator.next();
						if (obj instanceof Guerreiro) {
							Guerreiro guerreiro = (Guerreiro) obj;
							if (guerreiro.getEnergia() <= 0) {
								listaGuerreiros.remove(obj);
								painelLateral.setLista(listaGuerreiros);
								iterator.remove();
							} else {
								guerreiro.setTimerDisparo(guerreiro.getTimerDisparo() + 1);

								if (guerreiro.getTimerDisparo() == guerreiro.getTempoDeDisparo()) {
									Projetil novoProjetil = guerreiro.atira();

									if (novoProjetil != null) {
										iterator.add(novoProjetil);
									}
									guerreiro.setTimerDisparo(0);
								}

								guerreiro.move();

								boolean colidiu = false;

								ListIterator<Objeto> iterator2 = lista.listIterator();
								while (iterator2.hasNext()) {
									Objeto obj2 = iterator2.next();
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
							if (proj.getX() <= 0 || proj.getX() >= WIDTH || proj.getY() <= 0 || proj.getY() >= HEIGHT) {
								iterator.remove();
							}

							ListIterator<Objeto> iterator3 = lista.listIterator();
							while (iterator3.hasNext()) {
								Objeto obj2 = iterator3.next();

								if (obj2 instanceof Guerreiro) {
									Guerreiro guerreiro2 = (Guerreiro) obj2;
									if (proj.Colisao(guerreiro2)) {
										guerreiro2.dimEnergia(proj.getAtaque(), lista);
										guerreiro2.setEmColisao(true);
										iterator.remove();
										break;
									}
								} else if (obj2 instanceof Obstaculo && proj.Colisao(obj2)) {
									iterator.remove();
									break;
								}
							}
						}
					}

					janela.repaint();
					Thread.sleep(30);
				} else {
					JOptionPane.showMessageDialog(janela,
							"O guerreiro n° " + listaGuerreiros.get(0).getId() + " venceu o jogo!", "FIM DE JOGO",
							JOptionPane.OK_OPTION, UIManager.getIcon("OptionPane.informationIcon"));
					break;
				}
			}

			lista.clear();
		}
	}
}