import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import java.io.File;
import java.io.IOException;

public class Obstaculo extends Objeto {

    private String nome;

    public Obstaculo(int x,int y,int larg,int alt,String nome){
        super(x,y,larg,alt);
        this.nome=nome;
    }

    @Override
    public void desenha(Graphics g) {
     try{
        BufferedImage imgoriginal = ImageIO.read(new File(this.nome));
        g.drawImage(imgoriginal,this.getX(),this.getY(),this.getLarg(),this.getAlt(),null);
        g.dispose();
        } catch (IOException e) {;
            e.printStackTrace();
        } 
    }
    
}
