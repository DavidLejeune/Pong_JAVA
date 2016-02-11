/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dale.pong;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;


/**
 *
 * @author David
 */
public class Renderer extends JPanel
{

    private static final long serialVersionUID = 1L;

    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        
        Pong.pong.render((Graphics2D) g);
        
    }
    
    
    
    public Renderer() 
    {
        
    }
    
    
    
}
