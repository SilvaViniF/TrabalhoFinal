import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class MyPanel extends JPanel {
	private List<Objeto> lista = new ArrayList<>();

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawRect(0, 0, 1200, 768);
		// desenhando objetos
		for (Objeto f : lista) {
			f.desenha(g);
		}
	}

	public List<Objeto> getLista() {
		return lista;
	}
}