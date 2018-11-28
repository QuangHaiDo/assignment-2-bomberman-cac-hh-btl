package uet.oop.bomberman.sound;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class SoundEffect {

    private Clip boomExplode;
    private Clip itemCollected;
    public SoundEffect() {
        try{
            URL boomExplodeUrl = BackgroundMusic.class.getResource("/sound/"+"explosion.wav");
            AudioInputStream boomAudioIn1 = AudioSystem.getAudioInputStream(boomExplodeUrl);
            boomExplode = AudioSystem.getClip();
            boomExplode.open(boomAudioIn1);

            URL itemCollectUrl = BackgroundMusic.class.getResource("/sound/"+"itemcollected.wav");
            AudioInputStream itemAudioInl = AudioSystem.getAudioInputStream(itemCollectUrl);
            itemCollected = AudioSystem.getClip();
            itemCollected.open(itemAudioInl);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void play(String from){
        switch (from){
            case "boomExplode": {
                boomExplode.start();
                break;
            }
            case "itemCollected":{
                itemCollected.start();
                break;
            }
        }

    }


}
