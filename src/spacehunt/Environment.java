package spacehunt;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class Environment extends DrawableObject{
    Font font;

    public Environment(Game game) {
        super(game);
        this.font = new Font("Arial", Font.BOLD, 40);
    }
    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.white);
        g.setFont(this.font);
        g.drawString("Score: " + GamePlay.score , game.getWidth()-1100, game.getHeight()-50);
    }
    @Override
    public void update(double delta) {
        
    }
}
