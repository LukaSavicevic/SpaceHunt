package spacehunt;

import java.awt.Graphics2D;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class GamePlay extends Stage {
    Enemy enemy;
    Game game;
    Ship ship;
    Environment environment;
    static int score = 0;
    AudioInputStream audioIn;
    Clip clip;
    Random r = new Random();
    int incomingEnemy = 50;
    
    public void clear(){
        for (DrawableObject drawableObject : cleaner) {
            enemies.remove(drawableObject);
            objects.remove(drawableObject);
            cleaner.remove();
        }
    }
    public GamePlay(Game game, int newLevel, Background bcg) throws IOException, UnsupportedAudioFileException, LineUnavailableException{
        currentLevel = newLevel;
        this.game = game;
        this.objects.add(bcg);
        this.environment = new Environment(game);
        this.ship = new Ship(game);
        this.objects.add(this.environment);
        this.objects.add(ship);
        URL url = this.getClass().getClassLoader().getResource("Resourses/theme.wav");
        this.audioIn = AudioSystem.getAudioInputStream(url);
        this.clip = AudioSystem.getClip();
        this.clip.open(audioIn);
        this.clip.loop(999);
    }
    @Override
    public void endMusic(){
        try {
            clip.stop();
        } catch (Exception ex) {
            Logger.getLogger(GamePlay.class.getName()).log(Level.SEVERE, null, ex);
        }
        clip.close();
    }
    @Override
    public void render(Graphics2D g) {
        g.fillRect(0, 0, game.getWidth(), game.getHeight());
        for (DrawableObject object : objects) {
            object.render(g);
        }
        for (DrawableObject enemy : enemies) {
            enemy.render(g);
        }
    }
    @Override
    public void update(double delta) {
        if(incomingEnemy--<=0){
            try {
                Enemy ene;
                switch(currentLevel){
                    case 1:
                        ene = new Enemy(this.game, r.nextInt(game.getHeight()-300),"/Resourses/enemi.png");
                        this.enemies.add(ene);
                        break;
                    case 2:
                        ene = new Enemy(this.game, r.nextInt(game.getHeight()-300),"/Resourses/enemy2.png");
                        this.enemies.add(ene);
                        break;
                    case 3:
                        ene = new Enemy(this.game, r.nextInt(game.getHeight()-300),"/Resourses/enemy3.png");
                        this.enemies.add(ene);
                        break;
                }
            } catch (IOException ex) {
                Logger.getLogger(GamePlay.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        incomingEnemy = r.nextInt(100);
        if(this.ship.y > this.game.getHeight()){
            this.finished = true;
        }    
        for (DrawableObject object : objects) {
            object.update(delta);
        }
        for (Enemy enemy : enemies) {
            enemy.update(delta);
            if(this.Collision(enemy, ship)){
                ship.dead = true;
            }
        }
    }
}
