import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class PainelLateral1 extends JPanel {
	private List<Guerreiro> lista = new ArrayList<>();

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawRect(0, 0, 300, 768);
		// desenhando objetos
		for (Guerreiro f : lista) {
			f.desenhaDados(g, lista.indexOf(f));
		}
	}

	public List<Guerreiro> getLista() {
		return lista;
	}

    public void setLista(List<Guerreiro> list) {
		this.lista = list;
	}
}