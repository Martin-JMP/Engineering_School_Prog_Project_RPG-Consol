import com.isep.rpg.Dragon;
import com.isep.rpg.Warrior;
import com.isep.rpg.Game;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;


public class Main {
    public static void main(String[] args) throws UnsupportedAudioFileException, LineUnavailableException, IOException, InterruptedException {
        Game game = new Game(null);
        game.start();

    }

}