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
    
    // Create a copy of the list
    List<Guerreiro> copyList = new ArrayList<>(lista);
    
    // Iterate over the copy list
    for (Guerreiro f : copyList) {
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