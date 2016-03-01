/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dale.pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author David
 */
public class Paddle 
{

    public int paddleNumber;
    public int x, y, width =50, height = 250;
    public int score; 
    
    public Paddle(Pong pong,int paddleNumber) 
    {
        this.paddleNumber = paddleNumber;
        
        if (paddleNumber ==1 )
        {
            this.x = 5;
        }
        if (paddleNumber ==2 )
        {
            this.x = pong.width - width -25;
        }
        this.y = pong.height /2 - this.height / 2;
        
        
        
    }
    
    public void render(Graphics2D g)
    {
        
        g.setColor(Color.MAGENTA);
        g.fillRect(x,y,width,height);
        
        //g.setColor(Color.BLACK);
        //g.drawRect(x,y,width,height);
    }
    
    public void move(boolean up)
    {
        int speed = 15;
        
        if(up)
        {
            //if (y + height - speed > 0)
            if (y  > 0)
            {
                y-=speed;
            }
            else
            {
                y = 0;
            }
        }
        else
        {
            if (y + height < Pong.pong.height+50)
            {
                y+=speed;
            }
            else
            {
                y = Pong.pong.height - height +50;
            }
        }
    }
    
    
}
