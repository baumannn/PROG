/*
 * TO DO
 * arreglar colisiones
 * 
 * pensar paddle y pelota animaciones
*/

package breakingbad;

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

/**
 *
 * @author Baumann
 */
public class BreakingBad extends JFrame implements Runnable, KeyListener {
    
    //metagame
    private int stageHeight, stageWidth, leftLim, rightLim, topLim;
    private long time;
    private boolean paused, game, running;
    
    //imagenes y sonido
    private Image pauseImage, bg, gameOver, dbImage;
    private Graphics dbg;
    private SoundClip blockBreak, paddleBounce, shoot;
    //game
    private LinkedList <Meth> list;
    private int methRows, methCols;
    private boolean ballMove, ballMoveRight, ballMoveLeft;
    private boolean paddleMove, paddleMoveLeft, paddleMoveRight;
    
    private int ballXSpeed, ballYSpeed, paddleSpeed;
    private int ballX0, ballY0, paddleX0, paddleY0;
    private boolean xSwitched, ySwitched;
    
    private Ball ball;
    private Paddle paddle;
    
    private Power elPoder;
    private boolean isPower;
    private boolean powered;
    private boolean hitonce;
    
    public BreakingBad() {
        addKeyListener(this);
        
        //stage
        stageHeight = 600;
        stageWidth = 416;
        leftLim = 8;
        rightLim = 408;
        topLim = 30;
        setSize(stageWidth,stageHeight);
        
        //game
        paused = false;
        game = running = true;
        
        //ball
        ballXSpeed = 2;
        ballYSpeed = -2;
        ballX0 = 200;
        ballY0 = 570;
        ballMove = ballMoveLeft = ballMoveRight = false;
        
        //paddle
        paddleSpeed = 5;
        paddleX0 =  150;
        paddleY0 = 580;
        paddleMove = paddleMoveLeft = paddleMoveRight = false;
        
        //bricks
        methCols = 8;
        methRows = 10;
        
        isPower = true;
        powered = false;
        hitonce = false;
        
        //images
        URL lURL = this.getClass().getResource("pics/pause.png");
        pauseImage = Toolkit.getDefaultToolkit().getImage(lURL);
        URL lURL1 = this.getClass().getResource("pics/background.png");
        bg = Toolkit.getDefaultToolkit().getImage(lURL1);
        URL lURL3 = this.getClass().getResource("pics/gameover.png");
        gameOver = Toolkit.getDefaultToolkit().getImage(lURL3);
        
        //objects
        list = new LinkedList <Meth>();
        ball = new Ball(ballX0, ballY0, Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("pics/b0.png")));
        paddle = new Paddle(paddleX0, paddleY0, Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("pics/p0.png")));
        elPoder = new Power(-100,-100,Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("pics/arou.png")));
        
        //animations
        Image meth1 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("pics/0.png"));
        Image meth2 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("pics/1.png"));
        Image meth3 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("pics/2.png"));
        Image meth4 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("pics/3.png"));
        
        Image paddle1 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("pics/p0.png"));
        
        Image elPoder1 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("pics/arou.png"));
        
        Image ball1 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("pics/b1.png"));
        Image ball2 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("pics/b0.png"));
        
        ball.addFrame(ball1, 100);
        ball.addFrame(ball2, 100);
        
        paddle.addFrame(paddle1, 100);
        
        elPoder.addFrame(elPoder1, 100);
        
        URL rURL = this.getClass().getResource("pics/0.png");
        for(int i = 0; i < methRows; ++i) {
            for(int j = 0; j < methCols; ++j) {
                Meth block = new Meth( 50 * j - 17, 30 * i + 15
                    , Toolkit.getDefaultToolkit().getImage(rURL));
                
                block.addFrame(meth1, 100);
                block.addFrame(meth2,100);
                block.addFrame(meth3,100);
                block.addFrame(meth4,100);
                list.add(block);
                
            }
        }
        
        //sound
        blockBreak = new SoundClip("sound/Hit.wav");
        paddleBounce = new SoundClip("sound/Miss.aiff");
        shoot = new SoundClip("sound/Throw.wav");
        
        //thread
        Thread th = new Thread(this);
        th.start();
        
    }
    
    public void update() {
        //time
        long timePassed = System.currentTimeMillis() - time;
        time += timePassed;
        
        if(!paused) {
            
            //ball and paddle animations
            if(ballMove) {
                //ball.refresh(timePassed);
                ballMoveLeft = ballMoveRight = false;
            }
            if(paddleMove)
                paddle.refresh(timePassed);
            
            //brick animation and breaking
            for(int i = 0; i < list.size(); i++) {
                //play animation if it gets hit
                if(list.get(i).broken()) {
                    list.get(i).refresh(timePassed);
                }
                //delete brick if animation is over
                if(list.get(i).getFrame() == 1){
                    ball.setFrame(0);
                }
                
                if(list.get(i).realBroken()) {
                    list.remove(i);
                }
                
            }
            
            //move paddle right if within limit
            if(paddleMoveRight && paddle.getXPos() < rightLim - 100){
                paddle.setXPos(paddle.getXPos() + paddleSpeed);
                if(ballMoveRight) {
                    ball.setXPos(ball.getXPos() + paddleSpeed);
                }
            }
            //move paddle left
            if(paddleMoveLeft && paddle.getXPos() > leftLim){
                paddle.setXPos(paddle.getXPos() - paddleSpeed);
                if(ballMoveLeft) {
                    ball.setXPos(ball.getXPos() - paddleSpeed);
                }
            }
            //move ball
            if(ballMove) {
                ball.setXPos(ball.getXPos() + ballXSpeed);
                ball.setYPos(ball.getYPos() + ballYSpeed);
            }
            
            if(!isPower){
                elPoder.setYPos(elPoder.getYPos()+5);
            }
            
            //check for collisions
            collision();   
        }
        
    }
    
    public void collision() {
        
        
        
        //ball bounce with walls
        if(ball.getXPos() <= leftLim && ballXSpeed < 0) {
            ballXSpeed *= -1;
        }
        if(ball.getXPos() >= rightLim - 10 && ballXSpeed > 0) {
            ballXSpeed *= -1;
        }
        if(ball.getYPos() <= topLim && ballYSpeed < 0) {
            ballYSpeed *= -1;
        }
        
        //getpoder
        if(elPoder.intersects(paddle)) {
            powered = true;
            
        }
        
        //ball bounce with paddle
        if(ball.intersects(paddle) && ball.getYPos() < paddle.getYPos() - 7 ) {
            if((paddleMoveLeft && ballXSpeed > 0 && paddle.getXPos() > leftLim) ||
                    (paddleMoveRight && ballXSpeed < 0 && paddle.getXPos() < rightLim - 100)) {
                ballXSpeed *= -1;
            }
            ballYSpeed *= -1;
            if(!isPower){
                if(powered && hitonce){
                    powered=false;
                }
                if(!hitonce){
                    hitonce = true;
                }
            }
        }
        
        if(ball.getYPos() > stageHeight) {
            game = false;
        }
        xSwitched = false;
        ySwitched = false;
        
        for(int i = 0; i < list.size(); ++i) {
            if(ball.intersects2((Meth)list.get(i)) && !list.get(i).broken()) {
                if(!powered){
                    if(ball.getXPos() -2 <= list.get(i).getXPos() + 25) {
                        if(ballXSpeed > 0 && !xSwitched)  {
                            ballXSpeed *= -1;
                            xSwitched = true;
                        }
                    }
                    else if(ball.getXPos()+ 2 >= list.get(i).getXPos() + 75) {
                        if(ballXSpeed < 0 && !xSwitched)  {
                            ballXSpeed *= -1;
                            xSwitched = true;
                        }
                    }
                    if(ball.getYPos() + 2 >= list.get(i).getYPos()+45) {
                        if(ballYSpeed < 0 && !ySwitched) {
                            ballYSpeed *= -1;
                            ySwitched = true;
                        }
                    }
                    else if(ball.getYPos() - 2 <= list.get(i).getYPos()+15) {
                        if(ballYSpeed > 0 && !ySwitched) {
                            ballYSpeed *= -1;
                            ySwitched = true;
                        }
                    }
                }
                ball.setFrame(1);
                explode(i);
                
                
                if(isPower) {
                    elPoder.setXPos(list.get(i).getXPos()+20);
                    elPoder.setYPos(list.get(i).getYPos()+20);
                    isPower = false;
                }
            }
        }
        
    }
    
    public void explode(int i) {
        list.get(i).breakBrick();
        blockBreak.stop();
        blockBreak.play();
        
        //esperar de alguna manera , booleans + frame check ?
        //en el update checar ready to disappear para cada uno que pasa cuando frame ya es 4
        //list.remove(i);
        
    }
    
    //////////////////////////
    
    
    
    public void paint(Graphics g) {
    // Inicializan el DoubleBuffer

        dbImage = createImage(this.getSize().width, this.getSize().height);
        dbg = dbImage.getGraphics();

        // Actualiza la imagen de fondo.
        dbg.setColor(getBackground());
        dbg.fillRect(0, 0, this.getSize().width, this.getSize().height);

        // Actualiza e  l Foreground.
        dbg.setColor(getForeground());
        paint1(dbg);

        // Dibuja la imagen actualizada
        g.drawImage(dbImage, 0, 0, this);
    }
    
    public void paint1(Graphics g) {
        if(!game) {
            g.drawImage(gameOver, leftLim, 0, this);
        }
        
        else if(!list.isEmpty() && ball != null) {
            g.drawImage(bg, leftLim, 0, this);
            g.drawImage(ball.getImage(), ball.getXPos() , ball.getYPos(), this);
            g.drawImage(paddle.getImage(), paddle.getXPos() , paddle.getYPos(), this);
            g.drawImage(elPoder.getImage(), elPoder.getXPos(), elPoder.getYPos(), this);
            for(int i = 0; i < list.size(); ++i){
                Meth block = (Meth)list.get(i);
                g.drawImage(block.getImage(), block.getXPos(), block.getYPos(), this);
            }
            if(paused){
                g.drawImage(pauseImage, 8, 0, this);
            }
        } else {
            //Da un mensaje mientras se carga el dibujo    
            g.drawString("Loading..", 10, 10);
        }
    }

    public static void main(String[] args) {
        BreakingBad juego = new BreakingBad();
        juego.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        juego.setVisible(true);
    }

    public void pause() {
        paused = !paused;
    }
    
    @Override
    public void run() {
        time = System.currentTimeMillis();
        while(running) {
            if(!paused){
//               System.out.println(list.size());
               update();
            }
            repaint(); 

            try {
                Thread.sleep(20);
            } catch (InterruptedException ex) {
//                System.out.println("Error: " + ex.toString());
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if(game){
            if (ke.getKeyCode() == KeyEvent.VK_SPACE ) {
                ballMove = true;
            }
            if (ke.getKeyCode() == KeyEvent.VK_P ) {
                paused = !paused;
            }
            if (ke.getKeyCode() == KeyEvent.VK_RIGHT ) {
                if(!ballMove) {
                    ballMoveRight = true;
                }
                paddleMove = true;
                paddleMoveRight = true;
            }   
            else if (ke.getKeyCode() == KeyEvent.VK_LEFT ) {
                if(!ballMove) {
                    ballMoveLeft = true;
                }
                paddleMove = true;
                paddleMoveLeft = true;
            }
        }
        else {
            if (ke.getKeyCode() == KeyEvent.VK_SPACE ) {
                respring();
                
            }
        }
    }

    public void respring() {
        game = true;
        ballMove = false;
        ball.setXPos(ballX0);
        ball.setYPos(ballY0);
        ballXSpeed = 2;
        ballYSpeed = -2;
        paddle.setXPos(paddleX0);
        
        while(!list.isEmpty()) {
            list.remove();
        }
        
        Image meth1 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("pics/0.png"));
        Image meth2 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("pics/1.png"));
        Image meth3 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("pics/2.png"));
        Image meth4 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("pics/3.png"));
        
        URL rURL = this.getClass().getResource("pics/0.png");
        for(int i = 0; i < methRows; ++i) {
            for(int j = 0; j < methCols; ++j) {
                Meth block = new Meth( 50 * j - 17, 30 * i + 15
                    , Toolkit.getDefaultToolkit().getImage(rURL));
                
                block.addFrame(meth1, 100);
                block.addFrame(meth2,100);
                block.addFrame(meth3,100);
                block.addFrame(meth4,100);
                list.add(block);
                
            }
        }
        
    }
    
    @Override
    public void keyReleased(KeyEvent ke) {
        
        if (ke.getKeyCode() == KeyEvent.VK_RIGHT ) {
            paddleMove = false;
            paddleMoveRight = false;
            ballMoveRight = false;
        } else if (ke.getKeyCode() == KeyEvent.VK_LEFT ) {
            paddleMove = false;
            paddleMoveLeft = false;
            ballMoveLeft = false;
        }
    }
}
