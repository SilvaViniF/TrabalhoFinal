import java.awt.Color;
import java.awt.Graphics;

public class Projetil extends ElementoLivre {

    public Projetil(int x, int y, int larg, int alt, int direcao, float ataque) {
        super(x, y, larg, alt, 2, ataque, direcao);
        this.setX(x);
        this.setY(y);
        this.setDirecao(direcao);
        this.setAtaque(ataque);
    }

    @Override
    public void desenha(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(getX(), getY(), this.getLarg(), this.getAlt());

    }

    public void moveCima() {
        int y = this.getY();
        if (y >= 0) {
            y -= this.getPasso();
            this.setY(y);
        }
    }

    public void moveBaixo() {
        int y = this.getY();
        if (y <= 768) {
            y += this.getPasso();
            this.setY(y);
        }
    }

    @Override
    public void moveEsq() {
        int x = this.getX();
        if (x >= 0) {
            x -= this.getPasso();
            this.setX(x);
        }
    }

    @Override
    public void moveDir() {
        int x = this.getX();
        if (x <= 1200) {
            x += this.getPasso();
            this.setX(x);
        }
    }
}
