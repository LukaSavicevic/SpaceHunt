package spacehunt;
import java.awt.Graphics2D;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedDeque;
public abstract class Stage {
    public abstract void render(Graphics2D g);
    public abstract void update(double delta);
    List<DrawableObject> objects = new ArrayList<>();
    List<Enemy> enemies = new ArrayList<>();
    ConcurrentLinkedDeque<DrawableObject> cleaner = new ConcurrentLinkedDeque<>();
    public boolean finished;
    static int currentLevel;
    
    public void endMusic(){};
    
    public boolean Collision(Enemy d1, Ship d2){
        return (Math.abs(d1.x/2 - d2.x/2)<10 && Math.abs(d1.y/2 - d2.y/2)<25);
    }
}