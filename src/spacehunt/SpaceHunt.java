package spacehunt;

import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SpaceHunt {
    public static void main(String[] args) throws IOException, UnsupportedAudioFileException, LineUnavailableException{
        Game.start();
    }
}
