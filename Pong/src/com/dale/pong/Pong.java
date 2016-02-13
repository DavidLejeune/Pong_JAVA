/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dale.pong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
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
    public boolean scoreChanged=false;
    public int oldScore1;
    public int oldScore2;
    private int countdown;
    
    
    public int updateCount=0;
    
    public static Pong pong;
    public int width = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
    public int height = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
    public Renderer renderer;
    
    public Paddle player1;
    public Paddle player2;
    public Ball ball;
    
    public boolean z,s,up,down;
    
    public boolean bot = false, selectingDifficulty;
    public int botDifficulty, botMoves, botCooldown = 0;
    
    
    public int gameStatus = 0, scoreLimit = 7, playerWon; //0 = Menu, 1 = Paused, 2 = Playing, 3 = Over
    
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

        
        timer.start();
        
    }

    public void start()
    {
        gameStatus=2;
        ball = new Ball(this);
        player1 = new Paddle(this,1);
        player2 = new Paddle(this,2);
    }
  
    public void actionPerformed(ActionEvent e) 
    {
        if(gameStatus==2)
        {
            update();
        }
        
        renderer.repaint();
    }
    
	public void update()
	{
		if (player1.score >= scoreLimit)
		{
			playerWon = 1;
			gameStatus = 3;
		}

		if (player2.score >= scoreLimit)
		{
			gameStatus = 3;
			playerWon = 2;
		}

		if (z)
		{
			player1.move(true);
		}
		if (s)
		{
			player1.move(false);
		}

		if (!bot)
		{
			if (up)
			{
				player2.move(true);
			}
			if (down)
			{
				player2.move(false);
			}
		}
		else
		{
			if (botCooldown > 0)
			{
				botCooldown--;

				if (botCooldown == 0)
				{
					botMoves = 0;
				}
			}

			if (botMoves < 10)
			{
				if (player2.y + player2.height / 2 < ball.y)
				{
					player2.move(false);
					botMoves++;
				}

				if (player2.y + player2.height / 2 > ball.y)
				{
					player2.move(true);
					botMoves++;
				}

				if (botDifficulty == 0)
				{
					botCooldown = 20;
				}
				if (botDifficulty == 1)
				{
					botCooldown = 15;
				}
				if (botDifficulty == 2)
				{
					botCooldown = 10;
				}
			}
		}

		ball.update(player1, player2);
	}
    

    public void render(Graphics2D g) 
    {
        
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
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
        
        if(gameStatus==0)
        {
            g.setColor(Color.DARK_GRAY);
            g.setFont(new Font("Arial",1,80));
            g.drawString("PONG", width /2 -120,height / 4);
            g.setColor(Color.lightGray);
            g.setFont(new Font("Arial",1,80));
            g.drawString("PONG", width /2 -123,height / 4 +3);
            

            
            if (!selectingDifficulty)
            {
                g.setColor(Color.BLUE);
                g.fillRect(width /4 , height / 4 + height / 10 , width / 2 , height / 5);
                g.setColor(Color.WHITE);
                g.setFont(new Font("Arial",1,40));
                g.drawString("Press Space to play", width /2 - 200 , height / 2 - 100);
                g.drawString("Press Shift to play vs CPU", width /2 - 250 , height / 2 - 40);
                g.drawString("<< Score Limit: " + scoreLimit + " >>", width /2 - 175, height / 2 + 20);
            }
                    
        }   
        
        if (selectingDifficulty)
        {
                String string = botDifficulty == 0 ? "Easy" : (botDifficulty == 1 ? "Medium" : "Hard");

                g.setFont(new Font("Arial", 1, 30));

                g.drawString("<< Bot Difficulty: " + string + " >>", width / 2 - 180, height / 2 - 25);
                g.drawString("Press Space to Play", width / 2 - 150, height / 2 + 25);
        }
        
        if(gameStatus==2 || gameStatus == 1)
        {
            g.setColor(Color.ORANGE);
            g.setFont(new Font("Arial",1,20));
            g.drawString("Player 1", width /2 - 100 , 50);
            g.drawString("Player 2", width /2 + 30 , 50);
            
            g.setFont(new Font("Arial",1,24));
            g.setColor(Color.YELLOW);
            
            g.drawString(String.valueOf(player1.score),width /2 - 70 , 75);
            g.drawString(String.valueOf(player2.score),width  /2 + 60 , 75);
            //g.drawString("Press Shift to play vs CPU", width /2 - 250 , height / 2 - 40);
            
            
            
            if(oldScore1!=player1.score || oldScore2!=player2.score )
            {
                scoreChanged=true;
                if(scoreChanged)
                {
                    System.out.println("changed");
                    oldScore1=player1.score;
                    oldScore2=player2.score  ;
                    countdown=60;  
                }
                scoreChanged = false;
            }
            countdown--;
            if(countdown < 61 && countdown > 0)
            {
                    g.setColor(Color.PINK);
                    g.setFont(new Font("Arial",1,80));
                    g.drawString("SCORE", width /2 -120,height / 2);
                    g.setColor(Color.BLACK);
                    g.setFont(new Font("Arial",1,80));
                    g.drawString("SCORE", width /2 -123,height / 2 +3); 
                    //slaap(100);
            }
            else
            {
                ball.render(g);  
            }    
            player1.render(g);
            player2.render(g);    
                    
            
            if (gameStatus == 3)
            {
                    g.setColor(Color.WHITE);
                    g.setFont(new Font("Arial", 1, 50));

                    g.drawString("PONG", width / 2 - 75, 50);

                    if (bot && playerWon == 2)
                    {
                            g.drawString("The Bot Wins!", width / 2 - 170, 200);
                    }
                    else
                    {
                            g.drawString("Player " + playerWon + " Wins!", width / 2 - 165, 200);
                    }

                    g.setFont(new Font("Arial", 1, 30));

                    g.drawString("Press Space to Play Again", width / 2 - 185, height / 2 - 25);
                    g.drawString("Press ESC for Menu", width / 2 - 140, height / 2 + 25);
            }      
           
            
            
            
                
        }
        
        if(gameStatus==1 )
        {            
            g.setColor(Color.BLUE);
            g.setFont(new Font("Arial",1,80));
            g.drawString("PAUSE", width /2 -140,height / 2);
            g.setColor(Color.MAGENTA);
            g.setFont(new Font("Arial",1,80));
            g.drawString("PAUSE", width /2 -143,height / 2 -3);
        }
        
        
        
        
        
    }
    
    public static void main(String[] args) 
    {
        pong = new Pong();
    }
    
   
    	@Override
	public void keyPressed(KeyEvent e)
	{
		int id = e.getKeyCode();

		if (id == KeyEvent.VK_Z)
		{
			z = true;
		}
		else if (id == KeyEvent.VK_S)
		{
			s = true;
		}
		else if (id == KeyEvent.VK_UP)
		{
			up = true;
		}
		else if (id == KeyEvent.VK_DOWN)
		{
			down = true;
		}
		else if (id == KeyEvent.VK_RIGHT)
		{
			if (selectingDifficulty)
			{
				if (botDifficulty < 2)
				{
					botDifficulty++;
				}
				else
				{
					botDifficulty = 0;
				}
			}
			else if (gameStatus == 0)
			{
				scoreLimit++;
			}
		}
		else if (id == KeyEvent.VK_LEFT)
		{
			if (selectingDifficulty)
			{
				if (botDifficulty > 0)
				{
					botDifficulty--;
				}
				else
				{
					botDifficulty = 2;
				}
			}
			else if (gameStatus == 0 && scoreLimit > 1)
			{
				scoreLimit--;
			}
		}
		else if (id == KeyEvent.VK_ESCAPE && (gameStatus == 2 || gameStatus == 3))
		{
			gameStatus = 0;
		}
		else if (id == KeyEvent.VK_SHIFT && gameStatus == 0)
		{
			bot = true;
			selectingDifficulty = true;
		}
		else if (id == KeyEvent.VK_SPACE)
		{
			if (gameStatus == 0 || gameStatus == 3)
			{
				if (!selectingDifficulty)
				{
					bot = false;
				}
				else
				{
					selectingDifficulty = false;
				}

				start();
			}
			else if (gameStatus == 1)
			{
				gameStatus = 2;
			}
			else if (gameStatus == 2)
			{
				gameStatus = 1;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		int id = e.getKeyCode();

		if (id == KeyEvent.VK_Z)
		{
			z = false;
		}
		else if (id == KeyEvent.VK_S)
		{
			s = false;
		}
		else if (id == KeyEvent.VK_UP)
		{
			up = false;
		}
		else if (id == KeyEvent.VK_DOWN)
		{
			down = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e)
	{

	}
    
    
          private static void slaap (int millseconds){		 
		try {
			Thread.sleep(millseconds); 
		} catch (InterruptedException e){ }
	} 
    

}
