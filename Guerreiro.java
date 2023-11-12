import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.awt.Rectangle;

public class Guerreiro extends Objeto {
    Random rand = new Random();
    private int direcao,movecount,movelim;
    private boolean parado;
    float r = rand.nextFloat();
    float g = rand.nextFloat();
    float b = rand.nextFloat();
    Color randomColor = new Color(r, g, b);
   // private int energia,resistencia;

    public Guerreiro(int x, int y, int larg, int alt) {
		super(x, y, larg, alt);	   
        direcao=rand.nextInt(4);
        parado=false;
        movecount=0;
        movelim=rand.nextInt(100)+50;
	}
    @Override
    public void desenha(Graphics g) {
        g.setColor(randomColor);
        g.fillOval(getX(), getY(), getLarg(), getAlt());
        
    }
    // criar um metodo de colisoes, que diminui a energia caso tenha colisao
    // e zere a energia caso a colisao seja com um projetil
    public boolean Colisao(Guerreiro outro){
        Rectangle thislimite = new Rectangle(getX(),getY(),getLarg(),getAlt());
        Rectangle outrolimite = new Rectangle(outro.getX(),outro.getY(),outro.getLarg(),outro.getAlt());
        //Rectangle intersec = thislimite.intersection(outrolimite);
        return thislimite.intersects(outrolimite);
    }
    public void move() {
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
            y -= 5;
            this.setY(y);
        }
        this.parado=true;
    }
    
    public void moveBaixo(){
        int y=this.getY();
        if(y < 700){
            y += 5;
            this.setY(y);
        }
        this.parado=true;
    }

    public void moveEsq(){
        int x=this.getX();
        if(x > 0){
            x -= 5;
            this.setX(x);
        }
        this.parado=true;
    }

    public void moveDir(){
        int x=this.getX();
        if(x < 1155){
            x += 5;
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
}
