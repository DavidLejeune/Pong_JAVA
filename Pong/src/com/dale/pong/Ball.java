/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dale.pong;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 *
 * @author David
 */
public class Ball 
{
    
    public int x, y,width = 25 , height = 25;
    public int motionX, motionY;
    
    public Random random;
    
    public Ball(Pong pong)
    {
        this.random = new Random();
        this.x = pong.width / 2  - this.width /2;
        this.y = pong.height / 2  - this.height /2;
        this.motionY = -2 + random.nextInt(4);
        if (motionY==0)
        {
            motionY=1;
        }
        if(random.nextBoolean())
        {
            motionX = 1;
        }
        else
        {
            motionX = -1;
        }
        //this.motionX = -1 + random.nextInt(1);
        
    }
    
    public void update(Paddle paddle1, Paddle paddle2)
    {
        this.x += motionX;
        this.y += motionY;
        
        if(checkCollision(paddle1)==1)
        {
            this.motionX = 1;
            this.motionY = -2 + random.nextInt(4);
            if (motionY==0)
            {
                motionY=1;
            }
        }
        else if(checkCollision(paddle2)==1)
        {
            this.motionX = -1;
            this.motionY = -2 + random.nextInt(4);
            if (motionY==0)
            {
                motionY=1;
            }
        }
        
        if(checkCollision(paddle1)==2)
        {
            paddle2.score++;
        }
        else if(checkCollision(paddle2)==2)
        {
            paddle1.score++;
        }

    }
    
    
    
    public int checkCollision(Paddle paddle)
    {        

        if (paddle.x < x + width)
        {
            if (paddle.y > y + height || paddle.y + height < y  )
            {
                return 1; //hit
            }
            else
            {
                return 2; //score
            }
        }
        return 0; //nothing
    }
    
    
    
    public void render(Graphics g)
    {
        g.setColor(Color.YELLOW);
        g.fillOval(x, y, width, height);
        g.setColor(Color.BLACK);
        g.drawOval(x, y, width, height);
        
        
        //g.drawArc(x, height / 2 ,25 , 25, 90, 90);
    }
    
}
