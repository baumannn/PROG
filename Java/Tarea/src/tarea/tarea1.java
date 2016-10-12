package tarea;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 *
 * @author Baumann
 */
public class tarea1 extends Applet implements Runnable, KeyListener{
    
    private static final long serialVersionUID = 1L;
    // Se declaran las variables.
    private int x_pos; // Posicion x
    private int y_pos; // Posicion y
    private int direccion;    // Direccion
    private ImageIcon camina; // Imagen caminando.
    private Image dbImage; // Imagen a proyectar.
    private Graphics dbg; // Objeto grafico.
    private int vel;
    private AudioClip sonido;
    
    private ImageIcon choca;
    private boolean choco;
    private int cont;
    
    public void run(){
        while(true){
            
            actualiza();
            checaColision();
            repaint();
            try{
                Thread.sleep(20);
            }catch(InterruptedException ex){
                System.out.println("Error en "+ ex.toString());
            }
        }
    }
    
    public void checaColision() {

        //Dependiendo de la direccion del elefante es hacia donde se mueve.
        switch (direccion) { 

            case 1: { //Revisa colision cuando sube.

                if (y_pos < 0) {

                    direccion = 2;
                    sonido.play();
                    choco = true;
                }
                break;
            }
            case 2: { //Revisa colision cuando baja.

                if (y_pos + camina.getIconHeight() > getHeight()) {

                    direccion = 1;
                    sonido.play();
                    choco = true;
                }
                break;
            }
            case 3: { // Revisa colision cuando va izquierda

                if (x_pos < 0) {

                    direccion = 4;
                    sonido.play();
                    choco = true;
                }
                break;
            }
            case 4: { //Revisa colision cuando va derecha.
                if (x_pos + camina.getIconWidth() > getWidth()) {
                    direccion = 3;
                    sonido.play();
                    choco = true;
                }
                break;
            }
        }
    }
    
    public void actualiza() { 
                                                         
        switch(direccion) {
            case 1: {
                y_pos -= vel;
                break; //se mueve hacia arriba
            }
            case 2: {
                y_pos += vel;
                break; //se mueve hacia abajo
            }
            case 3: {
                x_pos -= vel;
                break; //se mueve hacia la izquierda
            }
            case 4: {
                x_pos += vel;
                break; //se mueve hacia la derecha
            }
        }
    }
    
    public void keyPressed(KeyEvent e) {

        //Presiono flecha arriba
        if (e.getKeyCode() == KeyEvent.VK_UP){ 
            if(direccion == 3 || direccion == 4){
                vel=1;
                direccion = 1;
            }
            else if(direccion == 1){
                vel ++;
            }
            else if(direccion == 2){
                if(vel > 1)
                    vel --;
                else direccion = 1;
            }
            
        //Presiono flecha abajo
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            if(direccion == 3 || direccion == 4){
                vel=1;
                direccion = 2;
            }
            else if(direccion == 1){
                if(vel > 1)
                    vel --;
                else direccion = 2;
            }
            else if(direccion == 2){
                vel ++;
            }
        //Presiono flecha izquierda
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) { 
            if(direccion == 1 || direccion == 2){
                vel=1;
                direccion = 3;
            }
            else if(direccion == 3){
                vel ++;
            }
            else if(direccion == 4){
                if(vel > 1)
                    vel --;
                else direccion = 3;
            }
        //Presiono flecha derecha
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) { 
            if(direccion == 1 || direccion == 2){
                vel=1;
                direccion = 4;
            }
            else if(direccion == 3){
                if(vel > 1)
                    vel --;
                else direccion = 4;
            }
            else if(direccion == 4){
                vel ++;
            }
        }
    }
    
    public void init() {
        direccion=4;
        vel = 1;
        // posicion en x es un cuarto del applet;
        x_pos = (int) (Math.random() * (getWidth() / 4));
        // posicion en y es un cuarto del applet 
        y_pos = (int) (Math.random() * (getHeight() / 4));
        
        URL eURL = this.getClass().getResource("walk.gif");
        camina = new ImageIcon(Toolkit.getDefaultToolkit().getImage(eURL));
        
        URL eURL2 = this.getClass().getResource("rock.png");
        choca = new ImageIcon(Toolkit.getDefaultToolkit().getImage(eURL2));
        
        cont = 0;
        choco = false;
        
        setBackground(Color.blue);
        addKeyListener(this);
        URL eaURL = this.getClass().getResource("sound.wav");
        sonido = getAudioClip (eaURL);
    }
    
    public void start () {
        // Declaras un hilo
        Thread th = new Thread (this);
        // Empieza el hilo
        th.start();
    }
    public void stop() {
    }
                                                          
    public void destroy() {
    }
    
    public void paint (Graphics g) {            
        
        if (camina != null) {
            
            if(choco)
            {
                if(cont <10)
                {
                    g.drawImage(choca.getImage(), x_pos, y_pos, this);
                    cont++;
                }
                else
                {
                    cont = 0;
                    choco = false;
                }
            }
            else
            {
                g.drawImage(camina.getImage(), x_pos, y_pos, this);
            }
            //Dibuja la imagen en la posicion actualizada
           // g.drawImage(camina.getImage(), x_pos, y_pos, this);
        } else {
            //Da un mensaje mientras se carga el dibujo
            g.drawString("Estoy cargando...", 10, 10);
        }    
    }
    
    public void update (Graphics g) {

        // Inicializan el DoubleBuffer
        if (dbImage == null){ 
            
            dbImage = createImage(this.getSize().width, this.getSize().height);
            dbg = dbImage.getGraphics();
        }
        // Actualiza la imagen de fondo
        dbg.setColor(getBackground());
        dbg.fillRect(0, 0, this.getSize().width, this.getSize().height);
        // Actualiza el Foreground
        dbg.setColor(getForeground());
        paint(dbg);
        // Dibuja la imagen actualizada y con esto ya no se ve parpadeo
        g.drawImage(dbImage, 0, 0, this);
    }
    public void keyTyped(KeyEvent e) {
    }
    public void keyReleased(KeyEvent e) {
    }
}
