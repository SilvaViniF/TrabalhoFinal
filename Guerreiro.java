import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.awt.Rectangle;
import java.util.List;

public class Guerreiro extends Objeto {
    Random rand = new Random();
    private int direcao;
    private float energia, resistencia, ataque;
    private String id;
    float r = rand.nextFloat();
    float g = rand.nextFloat();
    float b = rand.nextFloat();
    Color randomColor = new Color(r, g, b);
    int passo = 1;

    public Guerreiro(int x, int y, int larg, int alt, String id) {
        super(x, y, larg, alt);
        this.id = id;
        direcao = rand.nextInt(4);
        energia = 100;
        resistencia = rand.nextInt(5) * rand.nextFloat();
        ataque = rand.nextInt(20) * rand.nextFloat();
    }

    @Override
    public void desenha(Graphics g) {
        if (energia > 0) {
            // desenha guerreiro
            g.setColor(randomColor);
            g.drawRect(getX(), getY(), getLarg(), getAlt());

            // desenha energia
            g.setColor(Color.black);
            String energiaString = String.valueOf(energia);
            int stringWidth = g.getFontMetrics().stringWidth(energiaString);
            int xEnergia = getX() + (getLarg() - stringWidth) / 2;
            int yEnergia = getY() - 2;
            g.drawString(energiaString, xEnergia, yEnergia);

            // desenha id
            int idStringWidth = g.getFontMetrics().stringWidth(id);
            int isStringHeight = g.getFontMetrics().getHeight();
            int xId = getX() + (getLarg() - idStringWidth) / 2;
            int yId = getY() + (getAlt() + isStringHeight) / 2;
            g.drawString(id, xId, yId);
        }
    }

    // criar um metodo de colisoes, que diminui a energia caso tenha colisao
    // e zere a energia caso a colisao seja com um projetil
    public boolean Colisao(Objeto outro) {
        Rectangle thislimite = new Rectangle(getX(), getY(), getLarg(), getAlt());
        Rectangle outrolimite = new Rectangle(outro.getX(), outro.getY(), outro.getLarg(), outro.getAlt());

        return thislimite.intersects(outrolimite);
    }

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

    public void moveCima() {
        int y = this.getY();
        if (y > 0) {
            y -= passo;
            this.setY(y);
        } else {
            this.mudaDirecao();
        }
    }

    public void moveBaixo() {
        int y = this.getY();
        if (y < 738) {
            y += passo;
            this.setY(y);
        } else {
            this.mudaDirecao();
        }
    }

    public void moveEsq() {
        int x = this.getX();
        if (x > 0) {
            x -= passo;
            this.setX(x);
        } else {
            this.mudaDirecao();
        }
    }

    public void moveDir() {
        int x = this.getX();
        if (x < 1170) {
            x += passo;
            this.setX(x);
        } else {
            this.mudaDirecao();
        }
    }

    public void mudaDirecao() {
        int direcaoAtual = direcao;
        while (direcao == direcaoAtual) {
            direcao = rand.nextInt(4);
        }
    }

    public double getEnergia() {
        return energia;
    }

    public double getAtaque() {
        return ataque;
    }

    public double getResistencia() {
        return resistencia;
    }

    public String getId() {
        return this.id;
    }

    public void dimEnergia(Guerreiro outro, List<Objeto> lista) {
        if (lista.size() > 1) {
            // System.out.println("colisao com guerreiro");
            double dano = outro.getAtaque() - this.resistencia;
            if (dano > 0) {
                this.energia -= dano;
            }
        }
    }

    public void dimEnergiaObs(Objeto outro, List<Objeto> lista) {
        if (lista.size() > 1) {
            // System.out.println("colisao com obstaculo");
            double dano = this.resistencia + 1;
            if (dano > 0) {
                this.energia -= dano;
            }
        }
    }
}