package com.example;
/**
 * The Game
 *  
 * @author Baumann
 * @version 2.00 05/09/13
 */
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.net.URL;
import java.util.LinkedList;
import java.awt.Font;

public class Planeta extends Applet implements Runnable, MouseListener, 
        MouseMotionListener {

    private static final long serialVersionUID = 1L;
    // Se declaran las variables.
    private Image dbImage, gg;	// Imagen a proyectar	
    private Graphics dbg;	// Objeto grafico
    private AudioClip boom, bad;    // Objeto AudioClip
    private Nave ufo;    // Objeto de la clase Nave
    private int mseX, mseY, offX, offY, astSpeed, hp, posX, posY, score,
            falls, starthp, numast;
    private boolean move;
    private LinkedList <Asteroide> lista;

    public void init() {
        hp = starthp = 5;
        numast = 5;
        score = 0;
        falls = 0;
        astSpeed = 1;
        move = false;
        mseX = mseY = 0;
        offX = offY = 0;
        setSize(600, 600);
        posX = (int) (Math.random() * getWidth());    // posicion en x es un cuarto del applet
        posY = (int) (Math.random() * (getHeight() / 4)) + 3*getHeight()/4;    // posicion en y es un cuarto del applet
        URL eURL = this.getClass().getResource("nave.gif");
        ufo = new Nave(posX, posY, Toolkit.getDefaultToolkit().getImage(eURL));

        lista = new LinkedList <Asteroide>();
     
        URL rURL = this.getClass().getResource("asteroide.gif");
        //piedra = new Asteroide(posrX, posrY, Toolkit.getDefaultToolkit().getImage(rURL));
        for(int i = 0; i < numast; ++i)
        {
            Asteroide ast = new Asteroide(
                    (int)(Math.random() * (getWidth() - 2 * 64)) +  64,
                    (int)(Math.random() * getHeight()) -  getHeight()
                    , Toolkit.getDefaultToolkit().getImage(rURL));
            lista.add(ast);
        }
        setBackground(Color.black);

        //Se carga el boom.
        URL baURL = this.getClass().getResource("boom.wav");
        boom = getAudioClip(baURL);

        URL bbURL = this.getClass().getResource("bad.wav");
        bad = getAudioClip(bbURL);

        URL lURL = this.getClass().getResource("gg.png");  //imagen de "perdio"
        gg = Toolkit.getDefaultToolkit().getImage(lURL);

        addMouseListener(this);
        addMouseMotionListener(this);

    }

    public void start() {
        // Declaras un hilo
        Thread th = new Thread(this);
        // Empieza el hilo
        th.start();
    }

    public void run() {
        while (hp > 0) {
            actualiza();
            repaint();    // Se actualiza el <code>Applet</code> repintando el contenido.
            try {
                // El thread se duerme.
                Thread.sleep(20);
            } catch (InterruptedException ex) {
                System.out.println("Error en " + ex.toString());
            }
        }
    }

    /**
     * Metodo usado para actualizar la posicion de objetos ufo y piedra.
     *
     */
    public void actualiza() {

        if (move) {
            //solo se puede mover si se el mouse esta dentro del applet
            if ((mseX > 0) && (mseX < getWidth() - ufo.getAncho()) && (mseY > 0)
                    && (mseY < getHeight() - ufo.getAlto())) {
                ufo.setPosX(mseX);
                ufo.setPosY(mseY);
//                ufo.setPosX(mseX);
//                ufo.setPosY(mseY);
            }
        }

        //Acutalizo la posicion del piedra
        for(int i = 0; i < lista.size(); ++i){
            Asteroide piedra = (Asteroide) lista.get(i);
            // set inc
            astSpeed = 4 + starthp - hp;
            piedra.setPosX(piedra.getPosX());
            piedra.setPosY(piedra.getPosY() + astSpeed );
        }
        
        checaColision();
        hp = starthp - falls/10;

    }

    public void checaColision() {
        //Colision del ufo con el Applet dependiendo a donde se mueve.

        //Colision entre objetos
        for(int i = 0; i < lista.size(); ++i){
            Asteroide piedra = (Asteroide)lista.get(i);
            if (piedra.intersecta2(ufo)) {
                score += 100;
                
                boom.play();    //boom al colisionar
                
                piedra.setPosY((int)(Math.random() * getHeight()) -  getHeight());
                piedra.setPosX((int)(Math.random() * (getWidth() - 2 * 64)) +  64 );
            }
        }
        for(int i = 0; i < lista.size(); ++i){
            Asteroide piedra = (Asteroide)lista.get(i);
            if(piedra.getPosY() > getHeight()){
                piedra.setPosY((int)(Math.random() * getHeight()) -  getHeight());
                piedra.setPosX((int)(Math.random() * (getWidth() - 2 * 64)) +  64);
                
                ++falls;
                bad.play();
            }
        }
    }

    public void update(Graphics g) {
        // Inicializan el DoubleBuffer

        dbImage = createImage(this.getSize().width, this.getSize().height);
        dbg = dbImage.getGraphics();

        // Actualiza la imagen de fondo.
        dbg.setColor(getBackground());
        dbg.fillRect(0, 0, this.getSize().width, this.getSize().height);

        // Actualiza e  l Foreground.
        dbg.setColor(getForeground());
        paint(dbg);

        // Dibuja la imagen actualizada
        g.drawImage(dbImage, 0, 0, this);
    }

    public void mousePressed(MouseEvent e) {
        if (ufo.intersecta(e.getX(), e.getY())) {
            move = true;
            offX = e.getX() - ufo.getPosX();
            offY = e.getY() - ufo.getPosY();
            mseX = e.getX() - offX;
            mseY = e.getY() - offY;
        }
    }

    public void mouseDragged(MouseEvent e) {
        //guardar las posiciones del mouse
        if ((e.getX() < 0) || (e.getX() > getWidth()) || (e.getY() < 0) 
                || (e.getY() > getHeight())) {
            move = false;
        }
        if (move) {
            mseX = e.getX() - offX;
            mseY = e.getY() - offY;
        }
    }

    public void mouseReleased(MouseEvent m) {
        move = false;
    }

    public void mouseClicked(MouseEvent m) {
    }

    public void mouseMoved(MouseEvent m) {
    }

    public void mouseExited(MouseEvent m) {
    }

    public void mouseEntered(MouseEvent m) {
    }

    public void paint(Graphics g) {
        if (hp == 0) {
            //se dibuja la imagen de "perdiste"
            g.drawImage(gg, 0, 0, this);
            g.setColor(Color.red);
            g.setFont(new Font(null, Font.PLAIN, 30));
            g.drawString("Score: " + score, 250, 500);
        } else if ((!lista.isEmpty()) && (ufo != null)) {
            //Dibuja la imagen en la posicion actualizada
            for(int i = 0; i < lista.size(); ++i){
                Asteroide ast = (Asteroide)lista.get(i);
                g.drawImage(ast.getImagen(), ast.getPosX(), ast.getPosY(), this);
            }
        
            g.drawImage(ufo.getImagen(), ufo.getPosX(), ufo.getPosY(), this);
            g.setColor(Color.white);
            //despliega la cantidad de vidas
            g.setFont(new Font(null, Font.PLAIN, 20));
            g.drawString("HP: " + hp, 5, 20);
            g.drawString("Score: " + score, 5, 40);
        } else {
            //Da un mensaje mientras se carga el dibujo    
            g.drawString("Estoy cargando..", 10, 10);
        }

    }
}