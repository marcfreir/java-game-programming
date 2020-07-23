package com.marc.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
//import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
//import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.marc.entities.Entity;
import com.marc.entities.Player;
import com.marc.graphics.Spritesheet;
import com.marc.world.World;

/**
 * Graphics
 */
public class Game extends Canvas implements Runnable, KeyListener
{


    private static final long serialVersionUID = 1L;

    public static JFrame frame;

    private Thread thread;

    private boolean isRunning = true;

    private final int WIDTH = 320;
    private final int HEIGHT = 320;
    private final int SCALE = 2;

    private BufferedImage image;
    
    public static List<Entity> entities;
    
    public static Spritesheet spritesheet;
    
    public static World world;
    
    public static Player player;
    
    // Constructor
    public Game() {
    	addKeyListener(this);
    	setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
    	initFrame();
    	//Starting objects
    	image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    	entities = new ArrayList<Entity>();
    	spritesheet = new Spritesheet("/spriteSheet.png");
    	//Based on the spriteSheetNewPosition.png File - set the coordinates in getSprite
    	player = new Player(0, 0, 16, 16, spritesheet.getSprite(32, 0, 16, 16));
    	entities.add(player);
    	world = new World("/map.png");
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
            //Thread.currentThread().interrupt();
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
        	//Entity entity = entities.get(index);
        	//entity.updateEntity();
        	//Changed to player - return to the original code above in case of errors
        	Entity player = entities.get(index);
        	player.updateEntity();
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
        world.renderWorld(gameGraphics);
        for (int index = 0; index < entities.size(); index++)
        {
        	Entity entity = entities.get(index);
        	entity.renderEntity(gameGraphics);
        }

        /***/
        gameGraphics.dispose();
        gameGraphics = bs.getDrawGraphics();
        gameGraphics.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
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

	@Override
	public void keyTyped(KeyEvent event)
	{
		//TODO
	}

	@Override
	public void keyPressed(KeyEvent event)
	{
		//Keyboard Event Directions - Move
		//Right
		if (event.getKeyCode() == KeyEvent.VK_RIGHT || event.getKeyCode() == KeyEvent.VK_D)
		{
			player.playerRight = true;
			System.out.println("Right Direction Key - Moved");
		}
		//Left
		else if (event.getKeyCode() == KeyEvent.VK_LEFT || event.getKeyCode() == KeyEvent.VK_A)
		{
			player.playerLeft = true;
			System.out.println("Left Direction Key - Moved");
		}
		//Up
		if (event.getKeyCode() == KeyEvent.VK_UP || event.getKeyCode() == KeyEvent.VK_W)
		{
			player.playerUp = true;
			System.out.println("Up Direction Key - Moved");
		}
		//Down
		else if (event.getKeyCode() == KeyEvent.VK_DOWN || event.getKeyCode() == KeyEvent.VK_S)
		{
			player.playerDown = true;
			System.out.println("Down Direction Key - Moved");
		}
		
	}

	@Override
	public void keyReleased(KeyEvent event)
	{
		//Keyboard Event Directions - Stop
		//Right
		if (event.getKeyCode() == KeyEvent.VK_RIGHT || event.getKeyCode() == KeyEvent.VK_D)
		{
			player.playerRight = false;
			System.out.println("Right Direction Key - Stoped");
		}
		//Left
		else if (event.getKeyCode() == KeyEvent.VK_LEFT || event.getKeyCode() == KeyEvent.VK_A)
		{
			player.playerLeft = false;
			System.out.println("Left Direction Key - Stoped");
		}
		//Up
		if (event.getKeyCode() == KeyEvent.VK_UP || event.getKeyCode() == KeyEvent.VK_W)
		{
			player.playerUp = false;
			System.out.println("Up Direction Key - Stoped");
		}
		//Down
		else if (event.getKeyCode() == KeyEvent.VK_DOWN || event.getKeyCode() == KeyEvent.VK_S)
		{
			player.playerDown = false;
			System.out.println("Down Direction Key - Stoped");
		}
		
	}

    
}