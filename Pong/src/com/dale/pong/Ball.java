/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dale.pong;

import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

/**
 *
 * @author David
 */


public class Ball
{

	public int x, y, width = 25, height = 25;

	public int motionX, motionY;

	public Random random;

	private Pong pong;

	public int amountOfHits;
	public int prevamountOfHits;
        private String[] soundArray;

	public Ball(Pong pong)
	{
		this.pong = pong;

		this.random = new Random();
                
                soundArray = new String[] {"pong0.wav","pong1.wav","pong2.wav","pong3.wav","pong4.wav","pong5.wav","pong6.wav","pong7.wav","pong8.wav","pong9.wav"};
        

 
		spawn();
	}

	public void update(Paddle paddle1, Paddle paddle2)
	{
		int speed = 5;

		this.x += motionX * speed;
		this.y += motionY * speed;

		if (this.y + height - motionY > pong.height + pong.ballAdjust || this.y + motionY < 0)
		{
			if (this.motionY < 0)
			{
				this.y = 0;
				this.motionY = random.nextInt(4);

				if (motionY == 0)
				{
					motionY = 1;
				}
			}
			else
			{
				this.motionY = -random.nextInt(4);
				this.y = pong.height - height + pong.ballAdjust;

				if (motionY == 0)
				{
					motionY = -1;
				}
			}
		}

		if (checkCollision(paddle1) == 1)
		{
			this.motionX = 1 + (amountOfHits / 5);
			this.motionY = -2 + random.nextInt(4);

			if (motionY == 0)
			{
				motionY = 1;
			}

			amountOfHits++;
                        Random r = new Random();
                        int Low = 0;
                        int High = 10;
                        int Result = r.nextInt(High-Low) + Low;
                        String iValue2 = soundArray[Result];
                        
                        Sound.play(iValue2); 
                        System.out.println("" + iValue2);
                        //AudioClip clip = java.applet.Applet.newAudioClip(getClass().getResource(iValue2));
                        //clip.play();
		}
		else if (checkCollision(paddle2) == 1)
		{
			this.motionX = -1 - (amountOfHits / 5);
			this.motionY = -2 + random.nextInt(4);

			if (motionY == 0)
			{
				motionY = 1;
			}
                            
			amountOfHits++;
                        Random r = new Random();
                        int Low = 0;
                        int High = 10;
                        int Result = r.nextInt(High-Low) + Low;
                        String iValue2 = soundArray[Result];
                        Sound.play(iValue2); 
                        System.out.println("" + iValue2);
                        //AudioClip clip = java.applet.Applet.newAudioClip(getClass().getResource(iValue2));
                        //clip.play();
		}

		if (checkCollision(paddle1) == 2)
		{
                        prevamountOfHits = amountOfHits;
			amountOfHits++;
                        
			paddle2.score++;
  
                        
			spawn();
		}
		else if (checkCollision(paddle2) == 2)
		{
                        prevamountOfHits = amountOfHits;
			paddle1.score++;
			spawn();
		}
	}

	public void spawn()
	{
		this.amountOfHits = 0;
		this.x = pong.width / 2 - this.width / 2;
		this.y = pong.height / 2 - this.height / 2;

		this.motionY = -2 + random.nextInt(4);

		if (motionY == 0)
		{
			motionY = 1;
		}

		if (random.nextBoolean())
		{
			motionX = 1;
		}
		else
		{
			motionX = -1;
		}
	}

	public int checkCollision(Paddle paddle)
	{
		if (this.x < paddle.x + paddle.width && this.x + width > paddle.x && this.y < paddle.y + paddle.height && this.y + height > paddle.y)
		{
			return 1; //bounce
		}
		else if ((paddle.x > x && paddle.paddleNumber == 1) || (paddle.x < x - width && paddle.paddleNumber == 2))
		{
			return 2; //score
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

      private static void slaap (int millseconds){		 
		try {
			Thread.sleep(millseconds); 
		} catch (InterruptedException e){ }
	} 
    
    
    
    
}







/*
public class Ball 
{
    
    public int x, y,width = 25 , height = 25;
    public int motionX, motionY;
    
    public Random random;
    
    private Pong pong;
    
    public int amountOfHits;
    
    public Ball(Pong pong)
    {
        
        this.pong = pong;
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
        int speed =5;
        
        this.x += motionX * speed;
        this.y += motionY * speed;
        
        
            if (motionY==0)
            {
                motionY=1;
            }
        
        if (this.y + height - motionY > pong.height - 50 || this.y + motionY < 0)
        {
            if(this.motionY < 0)
            {
                this.y =0;
                this.motionY = random.nextInt(4);
            }
            else
            {
                this.motionY = -random.nextInt(4);
                this.y = pong.height - 90;
            }
        }
         
        if (this.x + width > pong.width || this.x < 0)
        {
            if(this.motionY < 0)
            {
                this.motionY = random.nextInt(4);
            }
            else
            {
                this.motionY = -random.nextInt(4);
            }
        }
        
        
        if(checkCollision(paddle1)==1)
        {
            this.motionX = 1 + (amountOfHits/5);
            this.motionY = -2 + random.nextInt(4);
            if (motionY==0)
            {
                motionY=1;
            }
            amountOfHits++;
        }
        else if(checkCollision(paddle2)==1)
        {
            
            this.motionX = -1 - (amountOfHits/5);
            this.motionY = -2 + random.nextInt(4);
            if (motionY==0)
            {
                motionY=1;
            }
            amountOfHits++;
        }
        
        if(checkCollision(paddle1)==2)
        {
            paddle2.score++;
            spawn();
        }
        else if(checkCollision(paddle2)==2)
        {
            paddle1.score++;
            spawn();
        }

    }
    
    public void spawn()
    {
        this.amountOfHits = 0;
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
    
    public int checkCollision(Paddle paddle)
    {        
        
        if(paddle.paddleNumber==1)
        {
            if (this.x < paddle.x  + paddle.width && this.x + width > paddle.x && this.y < paddle.x  + paddle.height && this.y + height > paddle.y)
            {
               return 1;
            }
//            else if ((paddle.x < x +width && paddle.paddleNumber==1) ||  (paddle.x > x  && paddle.paddleNumber==2))
//            {
//                System.out.println("1");
//                return 2;
//            }
            else if (this.x < 0)
            {
                System.out.println("1");
                return 2;
            }
        }
        
        if(paddle.paddleNumber==2)
        {
            if (this.x < paddle.x  + paddle.width && this.x + width > paddle.x && this.y < paddle.x  + paddle.height && this.y + height > paddle.y)
            {
                return 1;
            }
            //else if ((paddle.x < x + width && paddle.paddleNumber==1) ||  (paddle.x > x  && paddle.paddleNumber==2))
            else if (this.x > Pong.pong.width)
            {
                System.out.println("2");
                return 2;
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
*/