import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;

public class MyPanel extends JPanel {
	private List<Objeto> lista = new LinkedList<>();

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);		
		//desenhando objetos
        for (Objeto f: lista) {
			f.desenha(g);
		}	
	}
     
    public List<Objeto> getLista() {
        
		return lista;
	}
}