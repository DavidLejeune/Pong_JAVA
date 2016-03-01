/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dale.pong;

import java.applet.AudioClip;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
/**
 *
 * @author David
 */
public class Sound {
    public static synchronized void play(final String fileName) 
    {
        // Note: use .wav files             
        new Thread(new Runnable() 
        { 
            public void run() 
            {
                try 
                {
                    //Clip clip = AudioSystem.getClip();
                    
                    //AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(fileName));
                    //clip.open(inputStream);
                    //clip.start(); 
                    
                    AudioClip clip = java.applet.Applet.newAudioClip(getClass().getResource(fileName));
                    clip.play(); 
                    
                    
                    //Media hit = new Media(fileName);
                    //MediaPlayer mediaPlayer = new MediaPlayer(hit);
                    //mediaPlayer.play();
                    
                    
                    
                    
                } catch (Exception e) {
                    System.out.println("play sound error: " + e.getMessage() + " for " + fileName);
                }
            }
        }).start();
    }
}
