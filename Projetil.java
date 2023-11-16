import java.awt.Color;
import java.awt.Graphics;

public class Projetil extends Objeto {
    private int direcao;
    private int passo=10;

    @Override
    public void desenha(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(getX(), getY(),5, 5);
        
    }

    public Projetil(int x, int y,int direcao){
        this.setX(x);
        this.setY(y);
        this.direcao=direcao;
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

    public void moveCima(){
        int y = this.getY();
        if (y > 0) {
            y -= passo;
            this.setY(y);
        } else {
            
        }
    }

    public void moveBaixo(){
        int y = this.getY();
        if (y < 738) {
            y += passo;
            this.setY(y);
        } else {
            
        }
    }

    public void moveEsq(){
        int x = this.getX();
        if (x > 0) {
            x -= passo;
            this.setX(x);
        } else {
        
        }
    }
    
    public void moveDir(){
        int x = this.getX();
        if (x < 1170) {
            x += passo;
            this.setX(x);
            
        } else {

        }
    }
}
