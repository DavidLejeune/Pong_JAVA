/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dale.pong;

import java.awt.Graphics;
import javax.swing.JFrame;

/**
 *
 * @author David
 */
public class Pong 
{
    
    public static Pong pong;
    public int width = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
    public int height = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height - 50;
    public Renderer renderer;
    
    public Pong() 
    {
        JFrame jframe = new JFrame("Pong");
        renderer = new Renderer();
        
        jframe.setSize(width,height);
        jframe.setVisible(true);
        jframe.add(renderer);
        
    }

    public void render(Graphics g) 
    {
        
    }
    
    public static void main(String[] args) 
    {
        pong = new Pong();
    }

}
