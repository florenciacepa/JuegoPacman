/**
Compilar
javac -cp ".;bucleJuego.jar" LanzadorJuegos.java

Ejecutar
java -cp ".;bucleJuego.jar" LanzadorJuegos
  */


import java.awt.*;
import java.awt.event.*;



import javax.swing.*;
import javax.swing.event.*;

import com.entropyinteractive.*; //las librerias JJGame,JGameLoop,KeyBoard,Mouse,etc...


public class LanzadorJuego extends JPanel implements ActionListener{


	JGame juego;
	Thread t;
    public LanzadorJuego(){
    	int filas=0;
    	int columnas=1;
    	int separacion=10;

    	this.setLayout(new GridLayout(filas,columnas,separacion,separacion));

    	String[] arrEtiquetas={"DemoJuego02","DemoJuego03"};
    	JButton boton;

    	for(String etiqueta:arrEtiquetas){

    		boton=new JButton(etiqueta);

    		boton.addActionListener(this);
    		this.add(boton);
    	}



    }

	public void actionPerformed(ActionEvent e){

				if (e.getActionCommand().equals("DemoJuego02")){
					 	juego = new DemoJuego02();

						t = new Thread() {
						    public void run() {
						        juego.run(1.0 / 60.0);
						    }
						};

						t.start();
				}

				if (e.getActionCommand().equals("DemoJuego03")){
						juego = new DemoJuego03();

						t = new Thread() {
						    public void run() {
						        juego.run(1.0 / 60.0);
						    }
						};

						t.start();
				}
	}
    public static void main(String...z){
		JFrame f=new JFrame("Lanzador");

		f.add(new LanzadorJuego());
		WindowListener l=new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
                        System.exit(0);
                };
        };

        f.addWindowListener(l);
        f.pack();
        f.setVisible(true);
	 	f.setLocationRelativeTo(null);
	}


}
