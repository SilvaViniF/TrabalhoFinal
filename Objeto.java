import java.awt.Graphics;

public abstract class Objeto {
	private int x, y, larg, alt;
	
	
	public Objeto() {}
	
	public Objeto(int x, int y, int larg, int alt) {
		super();
		this.x = x;
		this.y = y;
		this.larg = larg;
		this.alt = alt;

	}

	public abstract void desenha(Graphics g);
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getLarg() {
		return larg;
	}

	public void setLarg(int larg) {
		this.larg = larg;
	}

	public int getAlt() {
		return alt;
	}

	public void setAlt(int alt) {
		this.alt = alt;
	}
	
}
