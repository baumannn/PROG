package com.example;

// Roland alexander Baumann A00814393
// Gilberto Garcia A01280019


import java.applet.AudioClip;
import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.net.URL;
import java.util.LinkedList;
import java.util.ArrayList;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.ImageIcon;

public class Planeta extends JFrame implements Runnable, MouseListener, 
        MouseMotionListener {

    private static final long serialVersionUID = 1L;
    // Se declaran las variables.
    private Image dbImage, gg;	// Imagen a proyectar	
    private Graphics dbg;	// Objeto grafico
    private AudioClip boom;    // Objeto AudioClip
    private Nave ufo;    // Objeto de la clase Nave
    private int astSpeed, hp, posX, posY, score,
            starthp, numast, movedir, ufoSpeed, choques;
    private boolean move;
    private LinkedList <Asteroide> lista;
    
    private Animacion animAst;
    private Animacion animNav;
    
    private long tiempoActual;
    private long tiempoInicial;
    
    
    public Planeta() {
        setSize(800, 600);
        
        hp = starthp = 3;
        ufoSpeed = 3;
        numast = (int)(Math.random()*7 + 3);
        choques = 0;
        score = 0;
        astSpeed = 1;
        move = false;
        posX = (int) (Math.random() * getWidth());    // posicion en x es un cuarto del applet
        posY = (int) (Math.random() * (getHeight() / 4)) + 2*getHeight()/4;    // posicion en y es un cuarto del applet
        ufo = new Nave(posX, posY, Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("nave_0.gif")));

        lista = new LinkedList <Asteroide>();
        
        Image ast1 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("asteroide_0.gif"));
        Image ast2 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("asteroide_1.gif"));
        Image ast3 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("asteroide_2.gif"));
        Image ast4 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("asteroide_3.gif"));
        Image ast5 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("asteroide_4.gif"));
        Image ast6 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("asteroide_5.gif"));
        Image ast7 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("asteroide_6.gif"));
        Image ast8 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("asteroide_7.gif"));
        Image ast9 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("asteroide_8.gif"));
        Image ast10 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("asteroide_9.gif"));
        Image ast11 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("asteroide_10.gif"));
        Image ast12 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("asteroide_11.gif"));
        Image ast13 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("asteroide_12.gif"));
        Image ast14 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("asteroide_13.gif"));
        Image ast15 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("asteroide_14.gif"));
        Image ast16 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("asteroide_15.gif"));
        Image ast17 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("asteroide_16.gif"));
        Image ast18 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("asteroide_17.gif"));
        
        Image nav1 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("nave_0.gif"));
        Image nav2 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("nave_1.gif"));
        Image nav3 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("nave_2.gif"));
        Image nav4 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("nave_3.gif"));
        Image nav5 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("nave_4.gif"));
        Image nav6 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("nave_5.gif"));
        Image nav7 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("nave_6.gif"));
        Image nav8 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("nave_7.gif"));
        Image nav9 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("nave_8.gif"));
        Image nav10 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("nave_9.gif"));
        
        
        animNav = new Animacion();
        animNav.sumaCuadro(nav1,100);
        animNav.sumaCuadro(nav2,100);
        animNav.sumaCuadro(nav3,100);
        animNav.sumaCuadro(nav4,100);
        animNav.sumaCuadro(nav5,100);
        animNav.sumaCuadro(nav6,100);
        animNav.sumaCuadro(nav7,100);
        animNav.sumaCuadro(nav8,100);
        animNav.sumaCuadro(nav9,100);
        animNav.sumaCuadro(nav10,100);
        
        animAst = new Animacion();
        animAst.sumaCuadro(ast1,100);
        animAst.sumaCuadro(ast2,100);
        animAst.sumaCuadro(ast3,100);
        animAst.sumaCuadro(ast4,100);
        animAst.sumaCuadro(ast5,100);
        animAst.sumaCuadro(ast6,100);
        animAst.sumaCuadro(ast7,100);
        animAst.sumaCuadro(ast8,100);
        animAst.sumaCuadro(ast9,100);
        animAst.sumaCuadro(ast10,100);
        animAst.sumaCuadro(ast11,100);
        animAst.sumaCuadro(ast12,100);
        animAst.sumaCuadro(ast13,100);
        animAst.sumaCuadro(ast14,100);
        animAst.sumaCuadro(ast15,100);
        animAst.sumaCuadro(ast16,100);
        animAst.sumaCuadro(ast17,100);
        animAst.sumaCuadro(ast18,100);
        
        
        
     
        URL rURL = this.getClass().getResource("asteroide_0.gif");
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
        //boom = getAudioClip(baURL);


        URL lURL = this.getClass().getResource("gg.png");  //imagen de "perdio"
        gg = Toolkit.getDefaultToolkit().getImage(lURL);

        addMouseListener(this);
        addMouseMotionListener(this);

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

            if (cuadros.size() == 0)
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

    
    
    
    public void run() {
        tiempoActual = System.currentTimeMillis();
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
        
        long tiempoTranscurrido = System.currentTimeMillis() - tiempoActual;
        tiempoActual+=tiempoTranscurrido;
        animAst.actualiza(tiempoTranscurrido);
        animNav.actualiza(tiempoTranscurrido);

        if (move) {

            if(movedir == 1){
                ufo.setPosX(ufo.getPosX() - ufoSpeed);
                ufo.setPosY(ufo.getPosY() - ufoSpeed);
            }
            else if (movedir == 2){
                ufo.setPosX(ufo.getPosX() + ufoSpeed);
                ufo.setPosY(ufo.getPosY() - ufoSpeed);
            }
            else if (movedir == 3){
                ufo.setPosX(ufo.getPosX() - ufoSpeed);
                ufo.setPosY(ufo.getPosY() + ufoSpeed);
            }
            else if (movedir == 4){
                ufo.setPosX(ufo.getPosX() + ufoSpeed);
                ufo.setPosY(ufo.getPosY() + ufoSpeed);
            
            }
            
        }

        //Acutalizo la posicion del piedra
        for(int i = 0; i < lista.size(); ++i){
            Asteroide piedra = (Asteroide) lista.get(i);
            // set inc
            astSpeed = 4 + 2*(starthp - hp);
            piedra.setPosX(piedra.getPosX());
            piedra.setPosY(piedra.getPosY() + astSpeed );
        }
        
        checaColision();
        hp = starthp - choques/5;

    }

    public void checaColision() {
        //Colision del ufo con el Applet dependiendo a donde se mueve.
        if(ufo.getPosX() > getWidth() - ufo.getAncho() || ufo.getPosX() < 0 || ufo.getPosY() > getHeight()-ufo.getAlto() || ufo.getPosY() < 30){
            move = false;
        }
        //Colision entre objetos
        for(int i = 0; i < lista.size(); ++i){
            Asteroide piedra = (Asteroide)lista.get(i);
            if (piedra.intersecta(ufo)) {
                ++choques;
                //boom.play();    //boom al colisionar
                
                piedra.setPosY((int)(Math.random() * getHeight()) -  getHeight());
                piedra.setPosX((int)(Math.random() * (getWidth() - 2 * 64)) +  64 );
            }
        }
        for(int i = 0; i < lista.size(); ++i){
            Asteroide piedra = (Asteroide)lista.get(i);
            if(piedra.getPosY() > getHeight()){
                piedra.setPosY((int)(Math.random() * getHeight()) -  getHeight());
                piedra.setPosX((int)(Math.random() * (getWidth() - 2 * 64)) +  64);
                score+=10;
            }
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

    public void mousePressed(MouseEvent e) {
    }
    
    public void mouseClicked(MouseEvent m) {

    }
  
    public void mouseDragged(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent m) {
        move = true;
        if(ufo.getPosX()+ufo.getAncho()/2 > m.getX() && ufo.getPosY()+ufo.getAlto()/2 > m.getY()){
            movedir = 1;
        }
        else if(ufo.getPosX()+ufo.getAncho()/2 < m.getX() && ufo.getPosY()+ufo.getAlto()/2 > m.getY()){
            movedir = 2;
        }
        if(ufo.getPosX()+ufo.getAncho()/2 > m.getX() && ufo.getPosY()+ufo.getAlto()/2 < m.getY()){
            movedir = 3;
        }
        if(ufo.getPosX()+ufo.getAncho()/2 < m.getX() && ufo.getPosY()+ufo.getAlto()/2 < m.getY()){
            movedir = 4;
        }
    }

    public void mouseMoved(MouseEvent m) {
    }

    public void mouseExited(MouseEvent m) {
    }

    public void mouseEntered(MouseEvent m) {
    }

    public void paint1(Graphics g) {
        if (hp == 0) {
            //se dibuja la imagen de "perdiste"
            g.drawImage(gg, 150, 0, this);
            g.setColor(Color.red);
            g.setFont(new Font(null, Font.PLAIN, 30));
            g.drawString("Score: " + score, 350, 500);
        } else if ((!lista.isEmpty()) && (ufo != null)) {
            //Dibuja la imagen en la posicion actualizada
            for(int i = 0; i < lista.size(); ++i){
                Asteroide ast = (Asteroide)lista.get(i);
                g.drawImage(animAst.getImagen(), ast.getPosX(), ast.getPosY(), this);
            }
        
            g.drawImage(animNav.getImagen(), ufo.getPosX(), ufo.getPosY(), this);
            g.setColor(Color.white);
            //despliega la cantidad de vidas
            g.setFont(new Font(null, Font.PLAIN, 20));
            g.drawString("HP: " + hp, 5, 20);
            g.drawString("Score: " + score, 10, 50);
        } else {
            //Da un mensaje mientras se carga el dibujo    
            g.drawString("Estoy cargando..", 10, 10);
        }

    }
      public static void main(String[] args) {
            Planeta planeta = new Planeta();
            planeta.setSize(800, 600);
            planeta.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            planeta.setVisible(true);
        }
}