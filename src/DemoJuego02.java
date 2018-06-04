
 

import com.entropyinteractive.*;
 
import java.awt.*;
import java.awt.event.*; //eventos

import java.awt.image.*;  //imagenes
import javax.imageio.*; //imagenes

import java.awt.Graphics2D;

import java.awt.geom.*; //Point2d
import java.util.LinkedList;

import java.util.*;
import java.text.*;






public class DemoJuego02 extends JGame {
 

	Date dInit = new Date();
	Date dAhora;
	SimpleDateFormat ft = new SimpleDateFormat ("mm:ss");
	final double NAVE_DESPLAZAMIENTO=150.0;

    BufferedImage img_fondo = null;
    
    Personaje ovni=new Personaje();

    public static void main(String[] args) {

        DemoJuego02 game = new DemoJuego02();
        game.run(1.0 / 60.0);
        System.exit(0);
    }

    public DemoJuego02() {

        super("DemoJuego02", 800, 600);

        System.out.println(appProperties.stringPropertyNames());

    }

    public void gameStartup() {

        try{
            img_fondo= ImageIO.read(getClass().getResource("imagenes/fondo.jpg"));
            ovni.setImagen(ImageIO.read(getClass().getResource("imagenes/ufo.png")));
            ovni.setPosicion(getWidth() / 2,getHeight() / 2 );
        }
        catch(Exception e){

        }
       
    }

    public void gameUpdate(double delta) {
        Keyboard keyboard = this.getKeyboard();
         
        // Procesar teclas de direccion
        if (keyboard.isKeyPressed(KeyEvent.VK_UP)){
            ovni.setY( ovni.getY() - NAVE_DESPLAZAMIENTO * delta);
            //shipY -= NAVE_DESPLAZAMIENTO * delta;
        }

        if (keyboard.isKeyPressed(KeyEvent.VK_DOWN)){
            //shipY += NAVE_DESPLAZAMIENTO * delta;
            ovni.setY( ovni.getY() + NAVE_DESPLAZAMIENTO * delta);
        }

        if (keyboard.isKeyPressed(KeyEvent.VK_LEFT)){
            ///shipX -= NAVE_DESPLAZAMIENTO * delta;
            ovni.setX( ovni.getX() - NAVE_DESPLAZAMIENTO * delta);
        }

        if (keyboard.isKeyPressed(KeyEvent.VK_RIGHT)){
            //shipX += NAVE_DESPLAZAMIENTO * delta;
            ovni.setX( ovni.getX() + NAVE_DESPLAZAMIENTO * delta);
        }
         



        // Esc fin del juego
        LinkedList < KeyEvent > keyEvents = keyboard.getEvents();
        for (KeyEvent event: keyEvents) {
            if ((event.getID() == KeyEvent.KEY_PRESSED) &&
                (event.getKeyCode() == KeyEvent.VK_ESCAPE)) {
                stop();
            }
        }


        ovni.update(delta);

    }

    public void gameDraw(Graphics2D g) {

    	dAhora= new Date( );
    	long dateDiff = dAhora.getTime() - dInit.getTime();
    	long diffSeconds = dateDiff / 1000 % 60;
		long diffMinutes = dateDiff / (60 * 1000) % 60;


        g.drawImage(img_fondo,0,0,null);// imagen de fondo

        g.setColor(Color.black);
        g.drawString("Tiempo de Juego: "+diffMinutes+":"+diffSeconds,12,42);
        g.drawString("Tecla ESC = Fin del Juego ",592,42);

    	g.setColor(Color.white);
    	g.drawString("Tiempo de Juego: "+diffMinutes+":"+diffSeconds,10,40);
		g.drawString("Tecla ESC = Fin del Juego ",590,40);

        
        ovni.draw(g);




        



    }

    public void gameShutdown() {
       Log.info(getClass().getSimpleName(), "Shutting down game");
    }
}

