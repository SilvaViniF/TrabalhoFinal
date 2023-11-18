import java.awt.Graphics;
import java.awt.Rectangle;

abstract public class ElementoLivre extends Objeto {
    private int direcao;
    private int passo;
    private float ataque;

    public ElementoLivre(int x, int y, int larg, int alt, int passo, float ataque, int direcao) {
        super(x, y, larg, alt);
        this.passo = passo;
        this.ataque = ataque;
        this.direcao = direcao;
    }

    @Override
    abstract public void desenha(Graphics g);

    public void move() {
        switch (direcao) {
            case 0:
                moveCima();
                break;
            case 1:
                moveBaixo();
                break;
            case 2:
                moveEsq();
                break;
            case 3:
                moveDir();
                break;
        }
    }

    public boolean Colisao(Objeto outro) {
        Rectangle thislimite = new Rectangle(getX(), getY(), getLarg(), getAlt());
        Rectangle outrolimite = new Rectangle(outro.getX(), outro.getY(), outro.getLarg(), outro.getAlt());

        return thislimite.intersects(outrolimite);
    }

    abstract public void moveCima();

    abstract public void moveBaixo();

    abstract public void moveEsq();

    abstract public void moveDir();

    public float getAtaque() {
        return this.ataque;
    }

    public void setAtaque(float valor) {
        this.ataque = valor;
    }

    public int getPasso() {
        return this.passo;
    }

    public void setPasso(int valor) {
        this.passo = valor;
    }

    public int getDirecao() {
        return this.direcao;
    }

    public void setDirecao(int valor) {
        this.direcao = valor;
    }
}
