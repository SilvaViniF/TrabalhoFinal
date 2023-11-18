import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.util.List;

public class Guerreiro extends ElementoLivre{
    private String id;
    private float energia, resistencia;
    private int tempoDeDisparo;
    private int timerDisparo;
    private boolean emColisao;

    public Guerreiro(int x, int y, int larg, int alt, String id, float ataque, int direcao, float resistencia, int tempoDeDisparo) {
        super(x, y, larg, alt, 2, ataque, direcao);
        this.id = id;
        this.energia = 100;
        this.resistencia = resistencia;
        this.tempoDeDisparo = tempoDeDisparo;
        this.emColisao = false;
    }

    @Override
    public void desenha(Graphics g) {
        if (this.energia > 0) {
            // Desenha guerreiro
            if(this.emColisao){
                g.setColor(Color.red);
                g.fillRect(this.getX(), this.getY(), this.getLarg(), this.getAlt());
            } else {
                g.setColor(Color.black);
                g.drawRect(this.getX(), this.getY(), this.getLarg(), this.getAlt());
            }

            // Desenha energia
            g.setColor(Color.black);
            String energiaString = String.valueOf(this.energia);
            int stringWidth = g.getFontMetrics().stringWidth(energiaString);
            int xEnergia = getX() + (getLarg() - stringWidth) / 2;
            int yEnergia = getY() - 2;
            g.drawString(energiaString, xEnergia, yEnergia);

            // Desenha id
            int idStringWidth = g.getFontMetrics().stringWidth(id);
            int isStringHeight = g.getFontMetrics().getHeight();
            int xId = getX() + (getLarg() - idStringWidth) / 2;
            int yId = getY() + (getAlt() + isStringHeight) / 2;
            g.drawString(this.id, xId, yId);
        }
    }

    public void desenhaDados(Graphics g, int indiceNaLista) {
        if (this.energia > 0) {
            int yInicial = 25;
            int alturaLinha = g.getFontMetrics().getHeight() + 3;
            int x = 15;
            int y = yInicial + indiceNaLista * alturaLinha;;
            g.drawString("ID: " + this.id + " En: " + this.energia + " At: " + this.getAtaque() + " Res: " + this.resistencia, x, y);
        }
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
        Random rand = new Random();
        while (this.getDirecao() == direcaoAtual) {
            this.setDirecao(rand.nextInt(4));
        }
    }

    public void dimEnergia(float ataqueOutro, List<Objeto> lista) {
        if (lista.size() > 1) {
            // System.out.println("colisao com guerreiro");
            double dano = ataqueOutro - this.resistencia;
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
        if ((this.getDirecao() == 0 && this.getY() > 15) || (this.getDirecao() == 1 && this.getY() >= 723)) {
            return new Projetil(this.getX() + 15, this.getY() - 5, 5, 5, 0,
                    this.getAtaque());
        } else if ((this.getDirecao() == 1 && this.getY() < 723) || (this.getDirecao() == 0 && this.getY() <= 15)) {
            return new Projetil(this.getX() + 15, this.getY() + 35, 5, 5, 1,
                    this.getAtaque());
        } else if ((this.getDirecao() == 2 && this.getX() > 15) || (this.getDirecao() == 3 && this.getX() >= 1155)) {
            return new Projetil(this.getX() - 5, this.getY() + 15, 5, 5, 2,
                    this.getAtaque());
        } else {
            return new Projetil(this.getX() + 35, this.getY() + 15, 5, 5, 3,
                    this.getAtaque());
        }
        //if ((this.getDirecao() == 3 && this.getX() < 1155) || (this.getDirecao() == 2 && this.getX() <= 15))
    }

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

    public boolean getEmColisao() {
        return emColisao;
    }

    public void setEmColisao(boolean valor) {
        this.emColisao = valor;
    }
}