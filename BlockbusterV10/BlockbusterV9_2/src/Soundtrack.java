import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;


public class Soundtrack {
    public Soundtrack() throws LineUnavailableException, UnsupportedAudioFileException, IOException {

        //The file made the game itself too big so I cut this out. I will upload this separately in GitHub
        //File fileOne = new File("BlockbusterV7.1/FallingShapes.wav");
        //AudioInputStream audioStreamOne = AudioSystem.getAudioInputStream(fileOne);

        File fileTwo = new File("Twisterion.wav");
        AudioInputStream audioStreamTwo = AudioSystem.getAudioInputStream(fileTwo);

        Clip clip = AudioSystem.getClip();
        clip.open(audioStreamTwo); //To switch between songs, change the number mentioned (for example switch audioStreamOne to audioStreamTwo
        clip.loop(Clip.LOOP_CONTINUOUSLY);

    }
}