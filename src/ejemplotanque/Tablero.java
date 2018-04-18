/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplotanque;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Estudiante
 * Jpanel dado que voy a pintar en el Canvas
 * ActionListener: Para poder ejecutar el escenario cada ciertos milisegundos
 */
public class Tablero extends JPanel implements ActionListener{
    private Timer timer ;
    private int x; 
    private int y;
    
    private Image tanque;
    private int posTanque=1;
    
    
    private Image fondo;
    private Image gato;
    
    
    public Tablero(){
        //Lanza un evento de tipo ActionListener cada 25 Milisegundo
        //Se hace referencia a this porque la misma clase (Tablero) procesa el evento
        this.timer = new Timer(100, this);
        this.x=0;
        this.y=0;
       
        //Registrar evento del Teclado
        setFocusable(true); //Debe estar en modo Focus para que puede detectar el evento
        addKeyListener(new EventosTeclado()); //Inner class que procesa los eventos del teclado
        
        this.fondo=this.loadImage("fondo.png");
        
        this.timer.start(); //Inicio con el escenario
       
    }
        
    //Inner class Que captura los eventos del teclado
     private class EventosTeclado extends KeyAdapter {
         
        //Cuando se suelta una tecla
         @Override
        public void keyReleased(KeyEvent e) {
           
          /* int key = e.getKeyCode();
           if (key == KeyEvent.VK_RIGHT) {
               x++;
            System.out.println("VK_SPACE"); //Se  va usar posteriormente 
           }
           if(key==KeyEvent.VK_LEFT){
               
               x--;
           }
*/
        }
        
        
        
        //Cuando se presiona una tecla
        @Override
        public void keyPressed(KeyEvent e) {
           int key=e.getKeyCode();
           if(key==KeyEvent.VK_LEFT){
               x--;
           }
           if(key==KeyEvent.VK_RIGHT){
               x++;
           }
           if(key==KeyEvent.VK_UP){
               y--;
           }
           if(key==KeyEvent.VK_DOWN){
               
               y++;
           }
           
           if(key==KeyEvent.VK_W){
           if(posTanque<10){
           posTanque++;
           }else{
               posTanque=10;
           }
           }
           
           if(key==KeyEvent.VK_S){
               if(posTanque>1){
                   posTanque--;
                   
               }else{
                   
                   posTanque=1;
               }
               
           }
        }
    }
    
    //Metodo donde se pintan los objetos 
     @Override
    public void paintComponent(Graphics g){
       super.paintComponent(g);
       
       //DIbujar tanque con cambio de imagen
      this.tanque=this.loadImage(posTanque+".png");
       g.drawImage(tanque, x, y, this);
       
       //Dibujar fondo
       g.drawImage(fondo, 0, 0, this);
       
       
       
       //Dibujar gato
       this.gato=this.loadImage("cats.gif");
       for(int i=0; i<6;i++){
           int aux=i%6;
           int aux1=10;
           
             g.drawImage(gato, aux1, aux, aux+200, aux+200, 0, 0, 132*aux, 80, this);
             aux+=100;
       }
     
       
       
    }

    //Metodo que se ejecuta cada vez que se lanza un ActionListener
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Repaint");
        repaint();
        
    }
    
    
    //Poder Cargar Imagenes
    protected Image loadImage(String imageName){
        ImageIcon ii= new ImageIcon(imageName);
        Image image=ii.getImage();
        return image;
        
    }
}
