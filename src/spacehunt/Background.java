package spacehunt;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
public class Background extends DrawableObject{
    BufferedImage back1;
    double spd;
    double position1=0,position2=0;

    public Background(Game game, double spd, int y,String imgurl) throws IOException {
        super(game);
        this.spd = spd;
        this.y = y;
        back1 = ImageIO.read(this.getClass().getResourceAsStream(imgurl));
        position2 = back1.getWidth();
    }
    @Override
    public void render(Graphics2D g) {
        g.drawImage(back1, (int)position1, (int)this.y, game);
        g.drawImage(back1, (int)position2, (int)this.y, game);
    }
    @Override
    public void update(double delta) {
        position1 -= (this.spd*delta);
        if(position1 + back1.getWidth()<=0){
            position1 = position2+back1.getWidth()-8;
        }
        position2 -= (this.spd*delta);
        if(position2 + back1.getWidth()<=0){
            position2 = this.position1+back1.getWidth()-8;
        }
    }
}
