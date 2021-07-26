package com.realtutsgml.neon.window;
import com.realtusgml.neon.framework.KeyInput;
import com.realtusgml.neon.framework.ObjectId;
import com.realtusgml.neon.framework.Texture;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;


public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID=-6261436164361361187l;
    private boolean running=false;
    private Thread thread;                     //The threads  use for implement the other processes at the same time

    public static int WIDTH , HEIGHT;
    public BufferedImage level = null , clouds = null ;

    //Object
    Handler handler ;
    Camera cam ;
    static Texture tex ;
    Random rand =new Random();

    public static int LEVEL = 1;



    private void init (){
        WIDTH=getWidth();
        HEIGHT=getHeight();

        tex =new Texture();
        BufferedImageLoader loader= new BufferedImageLoader();
        level=loader.loadImage("/level.png"); //loading the level
        clouds = loader.loadImage("/bkg_clouds.png");


        cam = new Camera(0 , 0);
        handler = new Handler(cam);

        handler.LoadImageLevel(level);
        //handler.addObject(new Player(100,100,handler, cam ,ObjectId.Player));
        //handler.createLevel();

        this.addKeyListener(new KeyInput(handler));
    }
    public synchronized void start(){
        if(running)                            //This part for security , if the game doesn't works properly ,the method use for close the game
        return;

        running = true;
        thread = new Thread(this);
        thread.start();                        //It calls the different thread

    }

     public void run() {
        init();
        this.requestFocus();
         long lastTime = System.nanoTime();                //The algorithm shows FPS and Tick in the game that it's running
         double amountOfTicks = 60.0;
         double ns = 1000000000 / amountOfTicks;
         double delta = 0;
         long timer = System.currentTimeMillis();
         int updates = 0;
         int frames = 0;
         while (running) {
             long now = System.nanoTime();
             delta += (now - lastTime) / ns;
             lastTime = now;
             while (delta >= 1) {
                 tick();
                 updates++;
                 delta--;
             }
             render();
             frames++;

             if (System.currentTimeMillis() - timer > 1000) {
                 timer += 1000;
                 System.out.println("FPS: " + frames + " TICKS: " + updates);
                 frames = 0;
                 updates = 0;
             }
         }
     }
         private void tick()
         {
            handler.tick();
            for(int i = 0;i<handler.object.size();i++ ){
                if(handler.object.get(i).getId()==ObjectId.Player){
                    cam.tick(handler.object.get(i));
                }
            }

         }
         private void render() //It shows all kind of images on screen
         {
             BufferStrategy bs =this.getBufferStrategy(); // The buffer Strategy upload images in background of the program while the one image was showing on screen.
                                                          //It caus to provide continuity
             if(bs==null){

                 this.createBufferStrategy(3);
                 return;

             }
             Graphics g = bs.getDrawGraphics();
             Graphics2D g2d =(Graphics2D) g ;
             /////////////////////////////////
             //DrawHere
             g.setColor(new Color(250 ,250,250));                                      //filling for window
             g.fillRect(0,0, getWidth(),getHeight());

             g2d.translate(cam.getX() , cam.getY());        //begin of cam



             for(int xx= 0 ; xx < clouds.getWidth()* 5 ; xx +=clouds.getWidth())
                 g.drawImage(clouds , xx , -80, this);


             handler.render(g);
             g2d.translate(-cam.getX() , -cam.getY()); // end of cam

             /////////////////////////////////
             g.dispose();
             bs.show();

         }

            public static Texture getInstance(){
            return tex;
            }


         public static void main(String [] args){

         new Window(1920 , 1080 , "Skywalker", new Game());
    }
}
