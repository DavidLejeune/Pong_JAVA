/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dale.pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 *
 * @author David
 */
public class Pong implements ActionListener, KeyListener 
{
    
    public int updateCount=0;
    
    public static Pong pong;
    public int width = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
    public int height = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
    public Renderer renderer;
    
    public Paddle player1;
    public Paddle player2;
    public Ball ball;
    
    public boolean bot = false;
    public boolean z,s,up,down;
    
    public Pong() 
    {
        Timer timer = new Timer (20,this);
        
        JFrame jframe = new JFrame("Pong");
        renderer = new Renderer();  
        
        jframe.setSize(width,height);
        jframe.setVisible(true);
        jframe.add(renderer);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.addKeyListener(this);
        //drawing paddles
        start();
        
        timer.start();
        
    }

    public void start()
    {
        ball = new Ball(this);
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
        //updateCount++;
        //System.out.println("Update nr " + updateCount);
        if(z)
        {
            player1.move(true);
        }
        if(s)
        {
            player1.move(false);
        }
        if(up)
        {
            player2.move(true);
        }
        if(down)
        {
            player2.move(false);
        }
    }
    

    public void render(Graphics2D g) 
    {
        
        // Tennis court
        g.setColor(Color.WHITE);
        g.fillRect(0,0,width,height);
        
        g.setColor(Color.RED);
        g.fillRect(20,20 ,width-60,height-90);
        
        
        g.setColor(Color.WHITE);
        g.fillRect(width/2 -  width/ 5 -20,0,20,height);
        g.fillRect(width/2 +  width/ 5 -20,0,20,height);
        g.fillRect(width/2 -  width/ 5 -20 ,height/2 -10, 2 * width/5,20);
        
        g.setColor(Color.WHITE);
        g.fillRect(width/2 - 5,0,10,height);
        g.setColor(Color.BLACK);
        g.drawRect(width/2 - 5,0,10,height);        
        
        
        
        player1.render(g);
        player2.render(g);
        
        ball.render(g);
    }
    
    public static void main(String[] args) 
    {
        pong = new Pong();
    }
    
    public void keyTyped(KeyEvent e)
    {
        
    }
    public void keyPressed(KeyEvent e)
    {
        int id  = e.getKeyCode();
        if(id==KeyEvent.VK_Z)
        {
            z = true;
        }
        if(id==KeyEvent.VK_S)
        {
            s = true;
        }
        if(id==KeyEvent.VK_UP)
        {
            up = true;
        }
        if(id==KeyEvent.VK_DOWN)
        {
            down = true;
        }
    }
    public void keyReleased(KeyEvent e)
    {
        int id  = e.getKeyCode();
        if(id==KeyEvent.VK_Z)
        {
            z = false;
        }
        if(id==KeyEvent.VK_S)
        {
            s = false;
        }
        if(id==KeyEvent.VK_UP)
        {
            up = false;
        }
        if(id==KeyEvent.VK_DOWN)
        {
            down = false;
        }     
    }

}
