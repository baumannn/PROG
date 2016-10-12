package parabola;

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
import java.util.ArrayList;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JFrame;

public class Parabola extends JFrame implements Runnable, MouseListener, 
        MouseMotionListener, KeyListener{
    
    private int hp, score, boxSpeed, ballXSpeed, ballYSpeed, ballYSpeedStart, gravity, 
                stageHeight, stageWidth, x0, y0, xh0, yh0, leftLim, rightLim, scoreChange,
                trueHp;
    private boolean paused, shot, boxMoveRight, boxMoveLeft, sounds, instructions, handMove;
    
    private Ball bBall;
    private Box hoop;
    
    private Animacion animBall;
    private Animacion animHoop;
    
    private SoundClip hit;
    private SoundClip miss;
    private SoundClip shoot;
    
    private Image pauseImage, bgImage, instructionsImage, gameOver;
    
    String file = "save.txt";
    
    private Image dbImage, background;	// Imagen a proyectar	
    private Graphics dbg;	// Objeto grafico
    
    private long time;
    private long initialTime;
    
    public Parabola() {
        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this);
        hp = 5;
        stageHeight = 600;
        stageWidth = 800;
        setSize(stageWidth,stageHeight);
        leftLim = 200;
        rightLim = stageWidth - 200;
        sounds = true;
        handMove = false;
        x0 = 50;
        y0 = 250;
        xh0 = 500;
        yh0 = 450;
        gravity = 1;
        trueHp = 3*hp;
        score = 0;
        scoreChange = 2;
        boxSpeed = 10;
        ballXSpeed = 10;
        ballYSpeed = -20;
        ballYSpeedStart = -20;
        paused = shot = false;
     /*   
        URL bURL = this.getClass().getResource("sphere.png");
        bBall = new Ball(x0, y0, Toolkit.getDefaultToolkit().getImage(bURL));
        URL hURL = this.getClass().getResource("hoop.gif");
        hoop = new Box(xh0, yh0, Toolkit.getDefaultToolkit().getImage(hURL));
        * 
       */ 
        
        URL lURL = this.getClass().getResource("pause.png");  //imagen de "perdio"
        pauseImage = Toolkit.getDefaultToolkit().getImage(lURL);
        URL lURL1 = this.getClass().getResource("bg.jpg");  //imagen de "perdio"
        bgImage = Toolkit.getDefaultToolkit().getImage(lURL1);
        URL lURL2 = this.getClass().getResource("Instrucciones.png");  //imagen de "perdio"
        instructionsImage = Toolkit.getDefaultToolkit().getImage(lURL2);
        URL lURL3 = this.getClass().getResource("gameover.jpg");  //imagen de "perdio"
        gameOver = Toolkit.getDefaultToolkit().getImage(lURL3);
        
        bBall = new Ball(x0, y0, Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("pics/ball1.gif")));
        hoop = new Box(xh0, yh0, Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("pics/hoop1.gif")));
        
        Image b1 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("pics/ball1.gif"));
        Image b2 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("pics/ball2.gif"));
        Image b3 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("pics/ball3.gif"));
        Image b4 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("pics/ball4.gif"));
        Image b5 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("pics/ball5.gif"));
        Image b6 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("pics/ball6.gif"));
        
        
        Image h1 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("pics/hoop1.gif"));
        Image h2 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("pics/hoop2.gif"));
        Image h3 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("pics/hoop3.gif"));
        Image h4 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("pics/hoop4.gif"));
        
        animBall = new Animacion();
        animBall.sumaCuadro(b1,100);
        animBall.sumaCuadro(b2,100);
        animBall.sumaCuadro(b3,100);
        animBall.sumaCuadro(b4,100);
        animBall.sumaCuadro(b5,100);
        animBall.sumaCuadro(b6,100);
        
        animHoop = new Animacion();
        animHoop.sumaCuadro(h1,100);
        animHoop.sumaCuadro(h2,100);
        animHoop.sumaCuadro(h3,100);
        animHoop.sumaCuadro(h4,100);
        
        
        hit = new SoundClip("Hit.wav");
        miss = new SoundClip("Miss.aiff");
        shoot = new SoundClip("Throw.wav");
        
        //START
        // Declaras un hilo
        Thread th = new Thread(this);
        // Empieza el hilo
        th.start();
    }
    
    
    public class Animacion{
        
        private ArrayList cuadros;
        private int indice;
        private long tiempo;
        private long duracion;
        
        public Animacion(){
            cuadros = new ArrayList();
            duracion = 0;
            iniciar();
        }
        
        public synchronized void sumaCuadro(Image imagen, long dur){
            duracion += dur;
            cuadros.add(new cuadroDeAnimacion(imagen, duracion));
        }
        
        public synchronized void iniciar(){
            tiempo = 0;
            indice = 0;
        }
        
        
        public synchronized void actualiza(long tiempoTranscurrido){
            if (cuadros.size() > 1){      
                tiempo += tiempoTranscurrido;    
                if (tiempo >= duracion){
                    tiempo = tiempo % duracion;  
                    indice = 0; 
                }
                while (tiempo > getCuadro(indice).tiempoFinal){
                    indice++;
                }
            }
         }
        
         public synchronized Image getImagen(){
            if (cuadros.isEmpty())
                return null;
            return getCuadro(indice).imagen;
         }

         private cuadroDeAnimacion getCuadro(int i){
                     return (cuadroDeAnimacion)cuadros.get(i);
         }
    }
    public class cuadroDeAnimacion{

        Image imagen;
        long tiempoFinal;
        
        public cuadroDeAnimacion(){
            this.imagen = null;
            this.tiempoFinal = 0;
        }

        public cuadroDeAnimacion(Image imagen, long tiempoFinal){
            this.imagen = imagen;
            this.tiempoFinal = tiempoFinal;
        }
    
        public Image getImagen(){
            return imagen;
        }

        public long getTiempoFinal(){
            return tiempoFinal;
        }

        public void setImagen (Image imagen){
            this.imagen = imagen;
        }

        public void setTiempoFinal(long tiempoFinal){

            this.tiempoFinal = tiempoFinal;
        }
    }
    
    public void update() {
        
        long timePassed = System.currentTimeMillis() - time;
        time += timePassed;
        if(!paused){
            if(shot)
                animBall.actualiza(timePassed);
            if(handMove)
                animHoop.actualiza(timePassed);
        
            boxSpeed = hp * 2;
            if(boxMoveRight && hoop.getXPos() < rightLim){
                hoop.setXPos(hoop.getXPos()+boxSpeed);
            }
            if(boxMoveLeft && hoop.getXPos() > leftLim){
                hoop.setXPos(hoop.getXPos()-boxSpeed);
            }
            if(shot) {
                //System.out.println(bBall.getYPos());
                bBall.setXPos(bBall.getXPos() + ballXSpeed);
                bBall.setYPos(bBall.getYPos() + ballYSpeed);
                ballYSpeed += gravity;
            }
        }
        collision();
    }
    
    public void collision() {
        
        if(bBall.getYPos() > stageHeight ) {
            shot = false;
            if(sounds) {
                miss.play();
            }
            bBall.setXPos(x0);
            bBall.setYPos(y0);
            ballYSpeed = ballYSpeedStart;
            --trueHp;
            hp = (trueHp+2)/3;
        }
        else if(bBall.intersects2(hoop)) {
            shot = false;
            if(sounds) {
                hit.play();
            }
            bBall.setXPos(x0);
            bBall.setYPos(y0);
            ballYSpeed = ballYSpeedStart;
            score += scoreChange;
        }
        
    }

    @Override
    public void run() {
        time = System.currentTimeMillis();
        while(hp > 0) {
            if(!paused){
               
            update();
            }
            repaint(); 
            try {
                Thread.sleep(20);
            } catch (InterruptedException ex) {
                System.out.println("Error: " + ex.toString());
            }
            
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {   
        if(bBall.intersects(me.getX(), me.getY()) && !shot) {
            
            if(sounds) {
                shoot.play();
            }
            
            ballYSpeed = (int)(-1*(Math.random()*15)-6);
            ballXSpeed = (int)(Math.random()*3)+10;
            
            shot = true;
            System.out.println("test");
            
        }
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if (ke.getKeyCode() == KeyEvent.VK_RIGHT ) {
            handMove = true;
            boxMoveRight = true;
        }   
        else if (ke.getKeyCode() == KeyEvent.VK_LEFT ) {
            handMove = true;
            boxMoveLeft = true;
        }
        if (ke.getKeyCode() == KeyEvent.VK_G) {
            save();
        }
        if (ke.getKeyCode() == KeyEvent.VK_P) {
            pause();
        }
        if (ke.getKeyCode() == KeyEvent.VK_I) {
            instr();
        }
        if (ke.getKeyCode() == KeyEvent.VK_C) {
            load();
        }
        if (ke.getKeyCode() == KeyEvent.VK_S) {
            sound();
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    if (ke.getKeyCode() == KeyEvent.VK_RIGHT ) {
        handMove = false;
        boxMoveRight = false;
    } else if (ke.getKeyCode() == KeyEvent.VK_LEFT ) {
        handMove = false;
        boxMoveLeft = false;
        }
    }
    
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
        if(hp == 0) {
            g.drawImage(gameOver, hp, hp, this);
            g.setColor(Color.white);
            g.setFont(new Font(null, Font.PLAIN, 50));
            g.drawString("Score: " + score, 200, 400);
        }
        
        else if(bBall != null) {
            g.drawImage(bgImage, 0, 0, this);
            g.drawImage(animBall.getImagen(), bBall.getXPos() , bBall.getYPos(), this);
            g.drawImage(animHoop.getImagen(), hoop.getXPos() , hoop.getYPos(), this);
            g.setColor(Color.red);
            g.setFont(new Font(null, Font.PLAIN, 20));
            g.drawString("Lives: " + hp, 10, 75);
            g.drawString("Score: " + score, 10, 50);
            if(paused && !instructions){
                g.drawImage(pauseImage, 0, 0, this);
            }
            if(instructions) {
                g.drawImage(instructionsImage, 0,0,this);
            }
            
        } else {
            //Da un mensaje mientras se carga el dibujo    
            g.drawString("Loading..", 10, 10);
        }
    }
    
    
        public void pause() {
            paused = !paused;
        }
        
        public void instr() {
            if(!instructions){
                if(!paused)
                    paused = true;
                instructions = !instructions;
            }
            else {
                instructions = !instructions;
                paused = false;
            }
        }
        
        public void sound(){
            sounds = !sounds;
        }
        
        public void save() {
        
        try {
            PrintWriter fileOut = new PrintWriter(new FileWriter(file));
            fileOut.println(hp + "," + score + "," + ballXSpeed + "," +
                    ballYSpeed + "," + shot + "," + bBall.getXPos() + "," + 
                    bBall.getYPos() + "," + sounds);
            fileOut.close();
        }
        catch (IOException ioe) {
            System.out.println("Error: " + ioe.toString());
        }
    }
    
    public void load() {
        try {
            BufferedReader fileIn = new BufferedReader(new FileReader(file));
            String data = fileIn.readLine();
            String arr[] = data.split (",");
            
            hp = Integer.parseInt (arr[0]);
            score = Integer.parseInt (arr[1]);
            ballXSpeed = Integer.parseInt (arr[2]);
            ballYSpeed = Integer.parseInt (arr[3]);
            shot = Boolean.parseBoolean(arr[4]);
            bBall.setXPos(Integer.parseInt (arr[5]));
            bBall.setYPos(Integer.parseInt (arr[6]));
            sounds = Boolean.parseBoolean(arr[7]);
            fileIn.close();
        }
        catch (IOException ioe) {
            System.out.println("Error: " + ioe.toString());
        }
    }
    
    public static void main(String[] args) {
        Parabola juego = new Parabola();
        juego.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        juego.setVisible(true);
    }
    
    @Override
    public void mouseEntered(MouseEvent me) {
        
    }
    @Override
    public void mouseExited(MouseEvent me) {
        
    }
    @Override
    public void mouseDragged(MouseEvent me) {
        
    }
    @Override
    public void mouseMoved(MouseEvent me) {
       
    }
    @Override
    public void keyTyped(KeyEvent ke) {
        
    }
    @Override
    public void mouseClicked(MouseEvent me) {
        
    }
    @Override
    public void mousePressed(MouseEvent me) {

    }
}
