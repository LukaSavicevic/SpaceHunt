package spacehunt;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Rocket extends DrawableObject{
    BufferedImage ammo;
    double speed = 600;
    boolean dead = false;
    boolean expired = false;
    
    public Rocket(Game game, double x, double y) throws IOException {
        super(game);
        ammo = ImageIO.read(this.getClass().getResourceAsStream("/Resourses/raketa.png"));           
        this.x = x;
        this.y = y;
    }
    @Override
    public void render(Graphics2D g) {
        g.drawImage(ammo, (int)this.x, (int)this.y, game);
    }
    @Override
    public void update(double delta) {
        if(this.x>1200){
            expired = true;
        }
        for(Enemy ene : this.game.stage.enemies){
            if(ene.Collision((int)this.x, (int)this.y, this.ammo.getWidth(), this.ammo.getHeight())){
                ene.shot();
                ene.dead = true;
                this.dead = true;
                this.expired = true;
            }
        }
        this.x += (this.speed*delta);
    } 
}
