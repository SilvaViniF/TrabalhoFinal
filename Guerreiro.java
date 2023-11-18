import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.awt.Rectangle;
import java.util.List;
import java.util.ArrayList;

public class Guerreiro extends ElementoLivre {
    private String id;
    private float energia, resistencia;
    private int tempoDeDisparo = 0;
    private int timerDisparo = 0;
    private Random rand = new Random();

    // float r = rand.nextFloat();
    // float g = rand.nextFloat();
    // float b = rand.nextFloat();
    // Color randomColor = new Color(r, g, b);

    public Guerreiro(int x, int y, int larg, int alt, String id, float ataque, int direcao) {
        super(x, y, larg, alt, 1, ataque, direcao);
        this.id = id;
        this.energia = 100;
        this.resistencia = rand.nextInt(5) * rand.nextFloat();
        this.tempoDeDisparo = rand.nextInt(5) * 10;
    }

    @Override
    public void desenha(Graphics g) {
        if (energia > 0) {
            // Desenha guerreiro
            // g.setColor(randomColor);
            g.drawRect(this.getX(), this.getY(), this.getLarg(), this.getAlt());

            // Desenha energia
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

    @Override
    public void moveCima() {
        int y = this.getY();
        if (y > 0) {
            y -= this.getPasso();
            this.setY(y);
        } else {
            this.mudaDirecao();
        }
    }

    @Override
    public void moveBaixo() {
        int y = this.getY();
        if (y < 738) {
            y += this.getPasso();
            this.setY(y);
        } else {
            this.mudaDirecao();
        }
    }

    @Override
    public void moveEsq() {
        int x = this.getX();
        if (x > 0) {
            x -= this.getPasso();
            this.setX(x);
        } else {
            this.mudaDirecao();
        }
    }

    @Override
    public void moveDir() {
        int x = this.getX();
        if (x < 1170) {
            x += this.getPasso();
            this.setX(x);
        } else {
            this.mudaDirecao();
        }
    }

    public void mudaDirecao() {
        int direcaoAtual = this.getDirecao();
        while (this.getDirecao() == direcaoAtual) {
            this.setDirecao(rand.nextInt(4));
        }
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

    public Projetil atira() {

        Projetil projetil = new Projetil(this.getX() + 15, this.getY() + 15, 5, 5, this.getDirecao(), this.getAtaque());
        return projetil;
        // projs.add(projetil);
        // for (Objeto p : projs) {
        // Projetil pr = (Projetil) p;
        // pr.move();
        // if (pr.getX() < 0 || pr.getX() > 1200 || pr.getY() < 0 || pr.getY() > 768) {
        // projsremove.add(pr);
        // }
        // }
        // projs.removeAll(projsremove);
    }

    // public List<Objeto> getProjs() {
    // return this.projs;
    // }

    public String getId() {
        return this.id;
    }

    public float getEnergia() {
        return energia;
    }

    public double getResistencia() {
        return resistencia;
    }

    public int getTempoDeDisparo() {
        return tempoDeDisparo;
    }

    public void setTempoDeDisparo(int valor) {
        this.tempoDeDisparo = valor;
    }

    public int getTimerDisparo() {
        return timerDisparo;
    }

    public void setTimerDisparo(int valor) {
        this.timerDisparo = valor;
    }
}