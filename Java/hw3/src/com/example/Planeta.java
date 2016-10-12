package com.example;

/**
 * @(#)Planeta.java
 *
 * Planeta Applet application
 *
 * @author Baumann
 * @version 1.00 29/08/13
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

public class Planeta extends Applet implements Runnable, MouseListener, 
        MouseMotionListener {

    private static final long serialVersionUID = 1L;
    // Se declaran las variables.
    private Image dbImage;	// Imagen a proyectar	
    private Graphics dbg;	// Objeto grafico
    private AudioClip boom;    // Objeto AudioClip
    private Nave ufo;    // Objeto de la clase Nave
    private Image gg;
    private int incX, incY, mseX, mseY, offX, offY, astSpeed, 
            hp, posX, posY, posrX, posrY;
    private boolean move;
    
    private LinkedList <Asteroide> lista;

    public void init() {
        hp = 100;
        astSpeed = 1;
        move = false;
        mseX = mseY = 0;
        offX = offY = 0;
        setSize(600, 600);
        posX = (int) (Math.random() * (getWidth() / 4));    // posicion en x es un cuarto del applet
        posY = (int) (Math.random() * (getHeight() / 4));    // posicion en y es un cuarto del applet
        URL eURL = this.getClass().getResource("nave.gif");
        ufo = new Nave(posX, posY, Toolkit.getDefaultToolkit().getImage(eURL));

        lista = new LinkedList <Asteroide>();
        
        posrX = (int) (Math.random() * (getWidth() / 4)) +  getWidth() / 2;    //posision x es tres cuartos del applet
        posrY = (int) (Math.random() * (getHeight() / 4)) +  getHeight() / 2;    //posision y es tres cuartos del applet

        URL rURL = this.getClass().getResource("asteroide.gif");
        //piedra = new Asteroide(posrX, posrY, Toolkit.getDefaultToolkit().getImage(rURL));
        for(int i = 0; i < 3; i++)
        {
            Asteroide ast = new Asteroide(
                    (int)(Math.random() * (getWidth() / 4)) +  getWidth() / 2,
                    (int)(Math.random() * (getHeight() / 4)) +  getHeight() / 2
                    , Toolkit.getDefaultToolkit().getImage(rURL));
            lista.add(ast);
        }
        setBackground(Color.black);

        //Se carga el boom.
        URL baURL = this.getClass().getResource("boom.wav");
        boom = getAudioClip(baURL);

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
        for(int i = 0; i < lista.size(); i++){
            Asteroide piedra = (Asteroide) lista.get(i);
            // set incX
        //si no es igual la posX de ufo y piedra
        if (ufo.getPosX() != piedra.getPosX()) {
            //si es igual posY
            if (ufo.getPosY() == piedra.getPosY()) {
                //ufo der de ast
                if (ufo.getPosX() > piedra.getPosX()) {
                    incX = astSpeed * 2 + i;
                } //ufo izq del ast
                else {
                    incX = astSpeed * -2 - i;
                }
            } //si no es igual posY
            else {
                //ufo a la derecha de asteroide
                if (ufo.getPosX() - piedra.getPosX() > 0) {
                    //si dist x es mas que dist y
                    if (Math.abs(ufo.getPosX() - piedra.getPosX()) 
                            > Math.abs(ufo.getPosY() - piedra.getPosY())) {
                        incX = 2 * astSpeed + i;
                    } //si dist x es menos que dist y
                    else {
                        incX = 1 * astSpeed + i;
                    }
                } //ufo a la izquierda del asteroide
                else {
                    //si dist x es mas que dist y
                    if (Math.abs(ufo.getPosX() - piedra.getPosX()) > 
                            Math.abs(ufo.getPosY() - piedra.getPosY())) {
                        incX = -2 * astSpeed - i;
                    } //si dist x es menos que dist y
                    else {
                        incX = -1 * astSpeed - i;
                    }
                }
            }
        } else {
            incX = 0;
        }

        // set incY
        //si no es igual la posY de ufo y piedra
        if (ufo.getPosY() != piedra.getPosY()) {
            //si es igual posX
            if (Math.abs(ufo.getPosX() - piedra.getPosX()) == 0) {
                //ufo abajo de ast
                if (ufo.getPosY() - piedra.getPosY() > 0) {
                    incY = astSpeed * 2 + i;
                } //ufo arriba del ast
                else {
                    incY = astSpeed * -2 - i;
                }
            } //si no es igual posY
            else {
                //ufo abajo de asteroide
                if (ufo.getPosY() - piedra.getPosY() > 0) {
                    //si dist x es mas que dist y
                    if (Math.abs(ufo.getPosX() - piedra.getPosX()) > 
                            Math.abs(ufo.getPosY() - piedra.getPosY())) {
                        incY = 1 * astSpeed + i;
                    } //si dist x es menos que dist y
                    else {
                        incY = 2 * astSpeed + i;
                    }
                } //ufo arriba del asteroide
                else {
                    //si dist x es mas que dist y
                    if (Math.abs(ufo.getPosX() - piedra.getPosX()) > 
                            Math.abs(ufo.getPosY() - piedra.getPosY())) {
                        incY = -1 * astSpeed - i;
                    } //si dist x es menos que dist y
                    else {
                        incY = -2 * astSpeed - i;
                    }
                }
            }
        } else {
            incY = 0;
        }
            piedra.setPosX(piedra.getPosX() + incX );
            piedra.setPosY(piedra.getPosY() + incY );
        }
        
        checaColision();


    }

    public void checaColision() {
        //Colision del ufo con el Applet dependiendo a donde se mueve.

        //Colision entre objetos
        for(int i = 0; i < lista.size(); i++){
            Asteroide piedra = (Asteroide)lista.get(i);
            if (piedra.intersecta(ufo)) {
                move = false;
                --hp;
                ++astSpeed;
                boom.play();    //boom al colisionar
                //El ufo se mueve al azar en la mitad izquierda del applet.
                ufo.setPosX((int) (Math.random() * (getWidth() / 2 - ufo.getAncho())));
                ufo.setPosY((int) (Math.random() * (getHeight() / 2 - ufo.getAlto())));
                //El piedra se mueve al azar en la mitad derecha del appler.

                piedra.setPosX((int) (Math.random() * (getWidth() / 4)) +  getWidth() / 2);    //posision x es tres cuartos del applet
                piedra.setPosY((int) (Math.random() * (getHeight() / 4)) +  getHeight() / 2);
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

        // Actualiza el Foreground.
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
        } else if ((!lista.isEmpty()) && (ufo != null)) {
            //Dibuja la imagen en la posicion actualizada
            for(int i = 0; i < lista.size(); i++){
                Asteroide ast = (Asteroide)lista.get(i);
                g.drawImage(ast.getImagen(), ast.getPosX(), ast.getPosY(), this);
            }
        
            g.drawImage(ufo.getImagen(), ufo.getPosX(), ufo.getPosY(), this);
            g.setColor(Color.white);
            //despliega la cantidad de vidas
            g.drawString("Vidas: " + hp, 5, 15);
        } else {
            //Da un mensaje mientras se carga el dibujo    
            g.drawString("Estoy cargando..", 10, 10);
        }

    }
}