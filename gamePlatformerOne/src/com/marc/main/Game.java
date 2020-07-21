package com.marc.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
//import java.awt.Font;
import java.awt.Graphics;
//import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.marc.entities.Entity;
import com.marc.entities.Player;
import com.marc.graphics.Spritesheet;

/**
 * Graphics
 */
public class Game extends Canvas implements Runnable
{


    private static final long serialVersionUID = 1L;

    public static JFrame frame;

    private Thread thread;

    private boolean isRunning = true;

    private final int WIDTH = 240;
    private final int HEIGHT = 160;
    private final int SCALE = 4;

    private BufferedImage image;
    
    public List<Entity> entities;
    
    public Spritesheet spritesheet;
    
    // Constructor
    public Game() {
    	setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
    	initFrame();
    	//Starting objects
    	image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    	entities = new ArrayList<Entity>();
    	spritesheet = new Spritesheet("/spriteSheetNewPosition.png");
    	
    	//Based on the spriteSheetNewPosition.png File - set the coordinates in getSprite
    	Player player = new Player(0, 0, 16, 16, spritesheet.getSprite(32, 0, 16, 16));
    	
    	entities.add(player);
    }

    public void initFrame()
    {
        frame = new JFrame("Game #PlatformerOne");
        frame.add(this);
        frame.setResizable(false);
        frame.pack();
        // Centered window
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public synchronized void startGame()
    {
        thread = new Thread(this);
        isRunning = true;
        thread.start();
    }

    public synchronized void stopGame()
    {
        isRunning = false;
        try
        {
            thread.join();
        }
        catch (InterruptedException exception)
        {
            exception.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }


    public static void main(String[] args)
    {
        Game game = new Game();
        game.startGame();
    }

    public void beatUpdateGame()
    {
        for (int index = 0; index < entities.size(); index++)
        {
        	Entity entity = entities.get(index);
        	entity.updateEntity();
        }
    }


    public void renderGame()
    {
        //To organize complex memory on the Canvas
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null)
        {
            this.createBufferStrategy(3);
            return;
        }
        //To draw onto Canvas
        Graphics gameGraphics = image.getGraphics();
        gameGraphics.setColor(new Color(19,19,19));
        gameGraphics.fillRect(0, 0, WIDTH, HEIGHT);
        
        //Game Rendering
        //Graphics2D graphic2D = (Graphics2D) gameGraphics;
        for (int index = 0; index < entities.size(); index++)
        {
        	Entity entity = entities.get(index);
        	entity.renderEntity(gameGraphics);
        }

        /***/
        gameGraphics.dispose();
        gameGraphics = bs.getDrawGraphics();
        gameGraphics.drawImage(image, 0, 0, WIDTH*SCALE, HEIGHT*SCALE,null);
        bs.show();
    }

    @Override
    public void run()
    {
        long lastTime = System.nanoTime();
        double amountOfBeats = 60.0;
        double nanosecPerAmountOfBeats = 1000000000 / amountOfBeats;
        double delta = 0;
        int framesPerSecond = 0;
        double timer = System.currentTimeMillis();
        while (isRunning)
        {
            long now = System.nanoTime();
            delta+= (now - lastTime) / nanosecPerAmountOfBeats;
            lastTime = now;

            if (delta >= 1)
            {
                beatUpdateGame();
                renderGame();
                framesPerSecond++;
                delta--;
            }

            if ((System.currentTimeMillis() - timer) >= 1000)
            {
                System.out.println("FPS: " + framesPerSecond);
                framesPerSecond = 0;
                timer += 1000;
            }
            /**System.out.println("My game is running!...");*/
        }
        stopGame();

    }

    
}