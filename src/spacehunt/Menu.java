package spacehunt;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Menu extends Stage{

    GamePlay gameplay;
    Game game;
    int currentScore;
    int currentLevel;
    Font f;
    
    public Menu(Game game,int newLevel,int newScore){
        this.currentScore = newScore;
        this.currentLevel = newLevel;
        this.game = game;
        this.f = new Font("Arial", Font.BOLD, 50);
    }
    
    @Override
    public void render(Graphics2D g) {
        int x,y,z;
        FontMetrics fm = g.getFontMetrics();
        switch(currentLevel){
            case 0:
                g.setColor(Color.BLUE);
                g.setFont(f);
                x = fm.stringWidth("GAME OVER");
                g.drawString("GAME OVER", game.getWidth()/2 - x/2-120, game.getHeight()/2-100);
                y = fm.stringWidth("YOUR SCORE IS : ");
                g.drawString("YOUR SCORE IS : " + GamePlay.score, (game.getWidth()/2-200) - y/2, game.getHeight()/2);
                z = fm.stringWidth("PRESS ENTER TO PLAY AGAIN!");
                g.drawString("PRESS ENTER TO PLAY AGAIN!", game.getWidth()/2 - z/2-290, game.getHeight()/2+100);
                break;
            case 1:
                g.setColor(Color.BLUE);
                g.setFont(f);
                x = fm.stringWidth("Well Done!");
                g.drawString("Well Done!", game.getWidth()/2 - x/2-100, game.getHeight()/2-100);
                y = fm.stringWidth("You just finish LEVEL 1");
                g.drawString("You just finish LEVEL 1", (game.getWidth()/2-210) - y/2, game.getHeight()/2);
                z = fm.stringWidth("PRESS ENTER TO START LEVEL 2");
                g.drawString("PRESS ENTER TO START LEVEL 2", game.getWidth()/2 - z/2-320, game.getHeight()/2+100);
                break;
            case 2:
                g.setColor(Color.BLUE);
                g.setFont(f);
                x = fm.stringWidth("Well Done!");
                g.drawString("Well Done!", game.getWidth()/2 - x/2-100, game.getHeight()/2-100);
                y = fm.stringWidth("You just finish LEVEL 2");
                g.drawString("You just finish LEVEL 2", (game.getWidth()/2-210) - y/2, game.getHeight()/2);
                z = fm.stringWidth("PRESS ENTER TO START LEVEL 3");
                g.drawString("PRESS ENTER TO START LEVEL 3", game.getWidth()/2 - z/2-320, game.getHeight()/2+100);
                break;
            case 3:
                g.setColor(Color.BLUE);
                g.setFont(f);
                x = fm.stringWidth("Congratulations");
                g.drawString("Congratulations", game.getWidth()/2 - x/2-150, game.getHeight()/2-100);
                y = fm.stringWidth("You have successfully defend the Earth");
                g.drawString("You have successfully defend the Earth", (game.getWidth()/2-370) - y/2, game.getHeight()/2);
                z = fm.stringWidth("PRESS ENTER TO PLAY AGAIN!");
                g.drawString("PRESS ENTER TO PLAY AGAIN!", game.getWidth()/2 - z/2-300, game.getHeight()/2+100);
                break;
        }
    }

    @Override
    public void update(double delta) {
        if(game.keys.contains(10)){
            switch(currentLevel){
                case 0:
                    try {
                        GamePlay.score = currentScore;
                        game.stage = new GamePlay(game,1,new Background(game, 150, 0,"/Resourses/Star_Back.jpg"));
                    } catch (IOException ex) {
                        Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (UnsupportedAudioFileException ex) {
                        Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (LineUnavailableException ex) {
                        Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case 1:
                    try {
                        GamePlay.score = currentScore;
                        game.stage = new GamePlay(game,2,new Background(game, 150, 0,"/Resourses/back2.jpg"));
                    } catch (IOException ex) {
                        Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (UnsupportedAudioFileException ex) {
                        Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (LineUnavailableException ex) {
                        Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case 2:
                    try {
                        GamePlay.score = currentScore;
                        game.stage = new GamePlay(game,3,new Background(game, 150, 0,"/Resourses/back3.jpg"));
                    } catch (IOException ex) {
                        Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (UnsupportedAudioFileException ex) {
                        Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (LineUnavailableException ex) {
                        Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case 3:
                    try {
                        GamePlay.score = 0;
                        game.stage = new GamePlay(game,1,new Background(game, 150, 0,"/Resourses/Star_Back.jpg"));
                    } catch (IOException ex) {
                        Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (UnsupportedAudioFileException ex) {
                        Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (LineUnavailableException ex) {
                        Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
            }  
        }
    }
}
