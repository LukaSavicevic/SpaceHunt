package spacehunt;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.Timer;

public class Game extends JComponent{
    static final Logger log = Logger.getLogger(Game.class.getName());
    long lastUpdate;
    Stage stage;
    Set<Integer> keys;
    
    public Game() throws IOException, UnsupportedAudioFileException, LineUnavailableException{
        log.log(Level.INFO, "Initializing main loop...");
        Timer t = new Timer(7, (evt)-> { mainLoop(); });
        t.start();
        log.log(Level.INFO, "Main loop initialized.");
        keys = new HashSet<>();
        lastUpdate = System.nanoTime();
        log.log(Level.INFO, "Key input initializing...");
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                keys.add(e.getKeyCode());
            }
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                keys.remove(e.getKeyCode());
            }
        });
        log.log(Level.INFO, "Finished key input init.");
        stage = new GamePlay(this,1,new Background(this, 150, 0,"/Resourses/Star_Back.jpg"));
    }
    public static void start() throws IOException, UnsupportedAudioFileException, LineUnavailableException{
        log.log(Level.INFO, "SpaceHunt is starting....");
        JFrame window = new JFrame();
        window.setSize(1100, 600);
        window.setLocation(450, 250);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Game g = new Game();
        g.setSize(window.getWidth(), window.getHeight());
        window.add(g);
        g.setFocusable(true);
        g.requestFocusInWindow();
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D)g;
        if(stage!=null){
            if(stage.finished){
                stage.endMusic();
                stage = new Menu(this,0,0);
            }
            if(GamePlay.score == 300 && Stage.currentLevel == 1){
                stage.endMusic();
                stage = new Menu(this,1,300);
            }
            if(GamePlay.score == 600 && Stage.currentLevel == 2){
                stage.endMusic();
                stage = new Menu(this,2,600);
            }
            if(GamePlay.score == 1000 && Stage.currentLevel == 3){
                stage.endMusic();
                stage = new Menu(this,3,0);
            }
            stage.render(g2);
        }
    }
    @Override
    public void update(Graphics g) {
        paint(g);
    }
    public void mainLoop(){
        long currentTime = System.nanoTime();
        double delta = ((double)currentTime - (double)lastUpdate)/1000000000;
        if(stage!=null){
            stage.update(delta);
        }
        repaint();
        lastUpdate = currentTime;
    }
}
