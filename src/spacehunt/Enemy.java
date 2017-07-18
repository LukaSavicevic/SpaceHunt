package spacehunt;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Enemy extends DrawableObject{
    
    double speed = 400;
    boolean dead = false;
    final int SHOT_SCORE = 10;
    BufferedImage img;
    int[][] eksplozija = {{0,0},{10,10}};
    int ekspIndex = 0;
    
    public Enemy(Game game, double y,String imgurl) throws IOException {
        super(game);
        img = ImageIO.read(this.getClass().getResourceAsStream(imgurl));
        this.y = y;
        this.x = game.getWidth();
    }
    public boolean Collision(int x, int y, int width, int height){
        return (Math.abs(x/2 - this.x/2)<10 && Math.abs((y/2-12) - this.y/2)<23);
    }
    public void shot(){
        try {
            img = ImageIO.read(this.getClass().getResourceAsStream("/Resourses/explosions.png"));
        } catch (IOException ex) {
            Logger.getLogger(Enemy.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.dead = true;
        this.game.stage.cleaner.add(this);
        GamePlay.score+=SHOT_SCORE;
    }
    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.red);
        if(dead)
            g.drawImage(img.getSubimage(eksplozija[ekspIndex][0], eksplozija[ekspIndex][1], 30, 30), (int)this.x, (int)this.y, game);
        else 
            g.drawImage(img, (int)this.x, (int)this.y, game);  
    }
    @Override
    public void update(double delta) {
        if(dead){ 
            ekspIndex++;
            if(ekspIndex>1) ekspIndex = 0;
                this.y+=4; 
        }
        if(this.x<-40){
            this.game.stage.cleaner.add(this);
            
        }
        this.x -= (this.speed*delta);
    }
}
