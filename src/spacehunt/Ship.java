package spacehunt;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Ship extends DrawableObject{
    
    BufferedImage ship;
    BufferedImage img;
    AudioInputStream audioIn;
    Clip clip;
    int[][] eksplozija = {{0,0},{10,10}};
    public boolean dead;
    public double speed = 400;
    int cooldown = 30;
    List<Rocket> rockets = new ArrayList<>();
    int ekspIndex = 0;

    public Ship(Game game) throws IOException {
        super(game);
        ship = ImageIO.read(this.getClass().getResourceAsStream("/Resourses/shipic.png"));
    }
    public void hited() throws IOException{
        img = ImageIO.read(this.getClass().getResourceAsStream("/Resourses/explosions.png"));
        this.dead = true;
    }
    @Override
    public void render(Graphics2D g) {
        for (Rocket rocket : rockets) {
            rocket.render(g);
        }
        if(dead){
            try{
            g.drawImage(img.getSubimage(eksplozija[ekspIndex][0], eksplozija[ekspIndex][1], 30, 30), (int)this.x, (int)this.y, game);
            } catch (Exception ex){}
        } else {
            g.drawImage(this.ship, (int)this.x, (int)this.y, game);
        }
    }
    public void launch() throws IOException, UnsupportedAudioFileException, LineUnavailableException{
        if(this.cooldown<=0){
            this.cooldown = 30;
            this.rockets.add(new Rocket(game, x+60, y+30));
            URL url = this.getClass().getClassLoader().getResource("Resourses/photon.wav");
            this.audioIn = AudioSystem.getAudioInputStream(url);
            this.clip = AudioSystem.getClip();
            this.clip.open(audioIn);
            this.clip.flush();
            this.clip.stop();
            this.clip.start();
        }
    }
    @Override
    public void update(double delta) {
        for (Rocket rocket : rockets) {
            rocket.update(delta);
        }
        rockets.removeIf(new Predicate<Rocket>() {
            @Override
            public boolean test(Rocket t) {
                return t.expired;
            }
        });
        for(Enemy ene : this.game.stage.enemies){
            if(ene.Collision((int)this.x, (int)this.y, this.ship.getWidth(), this.ship.getHeight())){
                ene.shot();
                try {
                    this.hited();
                } catch (IOException ex) {
                    Logger.getLogger(Ship.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if(this.cooldown>0){
            cooldown--;
        }
        if(!this.dead){
            if(game.keys.contains(37)&&this.x>=3){
                this.x-=(speed*delta);
            } 
            if(game.keys.contains(39)&&this.x<=980){
                this.x+=(speed*delta);
            } 
            if(game.keys.contains(38)&&this.y>=5){
                this.y-=(speed*delta);
            } 
            if(game.keys.contains(40)&&this.y<=480){
                this.y+=(speed*delta);
            }
            if(game.keys.contains(32)){
                try {
                    this.launch();
                } catch (IOException ex) {
                    Logger.getLogger(Ship.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedAudioFileException ex) {
                    Logger.getLogger(Ship.class.getName()).log(Level.SEVERE, null, ex);
                } catch (LineUnavailableException ex) {
                    Logger.getLogger(Ship.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            ekspIndex++;
            if(ekspIndex>1) ekspIndex = 0;
                this.y+=4;
                this.x+=4;
        }
    }
}
