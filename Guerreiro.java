import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.awt.Rectangle;
import java.util.List;

public class Guerreiro extends Objeto {
    Random rand = new Random();
    private int direcao,movecount,movelim,energia,resistencia,ataque;
    private boolean parado,morto;
    float r = rand.nextFloat();
    float g = rand.nextFloat();
    float b = rand.nextFloat();
    Color randomColor = new Color(r, g, b);
    

    public Guerreiro(int x, int y, int larg, int alt) {
		super(x, y, larg, alt);	   
        direcao=rand.nextInt(4);
        parado=false;
        movecount=0;
        movelim=rand.nextInt(100)+500;
        energia=100;
        resistencia=rand.nextInt(5);
        ataque=rand.nextInt(10);
        morto=false;
	}
    @Override
    public void desenha(Graphics g) {
    if (energia > 0) {
        // desenha guerreiro
        g.setColor(randomColor);
        g.fillOval(getX(), getY(), getLarg(), getAlt());
        // desenha energia
        g.setColor(Color.black);
        String energiaString = String.valueOf(energia);
        int stringWidth = g.getFontMetrics().stringWidth(energiaString);
        int stringHeight = g.getFontMetrics().getHeight();
        int x = getX() + (getLarg() - stringWidth) / 2;
        int y = getY() + (getAlt() + stringHeight) / 2;
        g.drawString(energiaString, x, y);
    }else{morto=true;}
}
    // criar um metodo de colisoes, que diminui a energia caso tenha colisao
    // e zere a energia caso a colisao seja com um projetil
    public boolean Colisao(Objeto outro){
        Rectangle thislimite = new Rectangle(getX(),getY(),getLarg(),getAlt());
        Rectangle outrolimite = new Rectangle(outro.getX(),outro.getY(),outro.getLarg(),outro.getAlt());
        Rectangle intersec = thislimite.intersection(outrolimite);
        if(intersec.getWidth()>0 && intersec.getHeight()>0){
            return true;
        }
        return false;
    }    
    
    public void move() {
        parado = false;
        movecount++;
        if(movecount >= movelim){
            mudadirecao();
            movecount=0;
            movelim=rand.nextInt(100)+50;
        }
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

    public void moveCima(){
        int y=this.getY();
        if(y>0){
            y -= 3;
            this.setY(y);
        }
        this.parado=true;
    }
    
    public void moveBaixo(){
        int y=this.getY();
        if(y < 700){
            y += 3;
            this.setY(y);
        }
        this.parado=true;
    }

    public void moveEsq(){
        int x=this.getX();
        if(x > 0){
            x -= 3;
            this.setX(x);
        }
        this.parado=true;
    }

    public void moveDir(){
        int x=this.getX();
        if(x < 1155){
            x += 3;
            this.setX(x);
        }
        this.parado=true;
    }

    public boolean obj_parado(){
        return parado;
    }

    public void mudadirecao(){
        direcao = rand.nextInt(4);
        parado = false;
    }

    public int getEnergia(){
        return energia;
    }

    public int getAtaque(){
        return ataque;
    }

    public int getResistencia(){
        return resistencia;
    }

    public boolean getMorto(){
        return morto;
    }
  
    public void dimenergiaobs(Objeto outro, List<Objeto> lista) {
        if (outro instanceof Guerreiro) {
            Guerreiro g = (Guerreiro) outro;
            int dano = g.getAtaque() - this.resistencia;
            if (dano > 0) {
                this.energia -= dano;
            }
        } else {
            int dano = this.resistencia + 1;
            if (dano > 0) {
                this.energia -= dano;
            }
        }
    }
}