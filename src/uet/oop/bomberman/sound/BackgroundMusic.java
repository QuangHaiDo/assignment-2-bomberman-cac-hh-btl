package uet.oop.bomberman.sound;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class BackgroundMusic {

    private static Clip clip;

    public BackgroundMusic() {
        try{
            URL url = BackgroundMusic.class.getResource("/sound/"+"backgroundmusic.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
            clip.open(audioIn);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }

    }

    public void play(){
        clip.start();
    }

    public static void stop(){
        BackgroundMusic.gameOverSound();
        if(clip.isRunning()) clip.stop();
    }

    public static void gameOverSound(){
        try{
            URL url = BackgroundMusic.class.getResource("/sound/"+"gameover.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            Clip overSound;
            overSound = AudioSystem.getClip();
            overSound.open(audioIn);
            overSound.start();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

}
