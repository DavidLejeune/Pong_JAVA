/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dale.pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 *
 * @author David
 */
public class Pong implements ActionListener
{
    
    public int updateCount=0;
    
    public static Pong pong;
    public int width = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
    public int height = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height - 25;
    public Renderer renderer;
    
    public Paddle player1;
    public Paddle player2;
    
    public Pong() 
    {
        Timer timer = new Timer (20,this);
        
        JFrame jframe = new JFrame("Pong");
        renderer = new Renderer();  
        
        jframe.setSize(width,height);
        jframe.setVisible(true);
        jframe.add(renderer);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //drawing paddles
        start();
        
        timer.start();
        
    }

    public void start()
    {
        player1 = new Paddle(this,1);
        player2 = new Paddle(this,2);
    }
  
    public void actionPerformed(ActionEvent e) 
    {
        update();
        renderer.repaint();
    }
    
    public void update()
    {
        updateCount++;
        System.out.println("Update nr " + updateCount);
    }
    

    public void render(Graphics g) 
    {
        g.setColor(Color.BLACK);
        g.fillRect(0,0,width,height);
        
        player1.render(g);
        player2.render(g);
    }
    
    public static void main(String[] args) 
    {
        pong = new Pong();
    }

}
