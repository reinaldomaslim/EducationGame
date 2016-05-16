package game;

import display.Display;
import gfx.Assets;
import gfx.Maze;
import inputs.KeyManager;
import states.*;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.logging.Handler;

/**
 * Created by adrien on 03/05/16.
 * GameTuto project class
 */
public class Game implements Runnable{

    //variables
    protected Display display;
    private int width, height;
    public String title;

    private boolean running = false;
    private Thread gameThread;

    private BufferStrategy bs;
    private Graphics g;

    //handler
    private handler.Handler handler;

    //timer
    int x;

    //States
    private State gameState;
    private State menuState;
    private State A1State;

    //input
    private KeyManager keymanager;


    //Constructor
    public Game(String title, int width, int height) {
        this.height = height;
        this.width = width;
        this.title = title;
        keymanager = new KeyManager();
    }

    private void init(){
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keymanager);
        Assets.init();
        Maze.init();

        handler = new handler.Handler(this);


        gameState = new GameState(handler);
        menuState = new MenuState(handler);
        A1State = new A1State(handler);
        CurrentState.setState(gameState);
    }

    //updates the screen
    //Changes values of variables etc
    private void tick(){
        keymanager.tick();
        x+=1;
        if (CurrentState.getState() !=null)
            CurrentState.getState().tick();

    }

    //displays the screen
    private void render(){
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null){
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();

        //clear screen
        g.clearRect(0,0,width,height);

        //g.drawImage(Assets.door,x,20,null);
        //g.drawImage(Assets.genius,50,80,null);

        //we draw here
        //g.setColor(Color.green);
        //g.fillRect(0,0,width,height);
        //g.setColor(Color.blue);
        //g.fillRect(10,30,10,10);

        //draw image
        //g.drawImage(testImage, 20,20,null);

        if (CurrentState.getState() !=null)
            CurrentState.getState().render(g);
        //draw subImage


        //end of drawing
        bs.show();
        g.dispose();
    }

    public void run(){
        init();

        //Timer variables
        int fps = 60;
        double timePerTick = 1000000000/fps;
        double delta =0;
        long now;
        long lastTime = System.nanoTime();
        long timer =0;
        int ticks =0;

        while (running){
            now = System.nanoTime();
            //calculate how much time we have until we call tick again
            delta+=(now-lastTime)/timePerTick;
            timer+= now-lastTime;
            lastTime = now;

            //executes tick and render methods 60 times per second
            if (delta>=1) {
                tick();
                render();
                ticks++;
                delta--;
            }

            //displays frames per second
            if (timer>=1000000000){
                //System.out.println("Ticks and frames "+ticks);
                ticks=0;
                timer=0;
            }
        }
        stop();

    }

    public KeyManager getKeyManager(){
        return keymanager;
    }

    public synchronized void start(){
        if (running)return;
        running = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    public synchronized void stop(){
        if (!running)return;
        try {
            gameThread.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
