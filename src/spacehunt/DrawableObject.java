package spacehunt;

import java.awt.Graphics2D;

public abstract class DrawableObject {
    Game game;
    public DrawableObject(Game game){
        this.game = game;
    }
    public double x,y;
    public abstract void render(Graphics2D g);
    public abstract void update(double delta);
}
