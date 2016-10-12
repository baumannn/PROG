/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parabola;

import java.applet.AudioClip;
import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.LinkedList;
import java.util.ArrayList;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JFrame;
import javax.swing.ImageIcon;

/**
 *
 * @author Baumann
 */
public class Parabola extends JFrame implements Runnable, MouseListener, 
        MouseMotionListener, KeyListener{
    
    private int hp, score, angle, boxSpeed, ballXSpeed, ballYSpeed, gravity, 
                stageHeight, stageWidth, x0, y0, leftLim, rightLim, scoreChange,
                trueHp;
    private boolean paused, shot, canMoveLeft, canMoveRight;
    
    private String file;
    
    private Ball bBall;
    private Box hoop;
    
    public Parabola() {
        file = "archivo.txt";
        stageHeight = 500;
        stageWidth = 800;
        setSize(stageWidth,stageHeight);
        leftLim = 200;
        rightLim = stageWidth - 100;
        x0 = 50;
        y0 = 400;
        trueHp = 5;
        score = 0;
        scoreChange = 10;
        boxSpeed = 2;
        paused = shot = false;
        
    }
    
    public void update() {
        
        if(shot) {
            bBall.setXPos(bBall.getXPos() + ballXSpeed);
            bBall.setYPos(bBall.getYPos() + ballYSpeed);
            
            ballYSpeed += gravity;
        }
        collision();
    }
    
    public void collision() {
        
        if(bBall.getXPos() > stageHeight) {
            shot = false;
            bBall.setXPos(x0);
            bBall.setYPos(y0);
            --trueHp;
        }
        else if(bBall.intersects(hoop)) {
            shot = false;
            bBall.setXPos(x0);
            bBall.setYPos(y0);
            score += scoreChange;
        }
        if(hoop.getXPos() < leftLim) {
            canMoveLeft = false;
        }
        else if(hoop.getXPos() > rightLim) {
            canMoveRight = false;
        }
        
    }

    @Override
    public void run() {
        while(hp > 0 && !paused) {
            
            update();
            repaint();
            try {
                Thread.sleep(20);
            } catch (InterruptedException ex) {
                System.out.println("Error: " + ex.toString());
            }
            
        }
    }
    
 

    @Override
    public void mouseClicked(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
