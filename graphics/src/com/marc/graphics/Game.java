package com.marc.graphics;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;
/**
 * Graphics
 */
public class Game extends Canvas implements Runnable
{

    public static JFrame frame;

    private Thread thread;

    private boolean isRunning = true;

    private final int WIDTH = 160;
    private final int HEIGHT = 120;
    private final int SCALE = 3;
    
    //Constructor
    public Game()
    {
        setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
        initFrame();
    }

    public void initFrame()
    {
        frame = new JFrame("Game #01");
        frame.add(this);
        frame.setResizable(false);
        frame.pack();
        //Centered window
        frame.setLocationRelativeTo(null);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public synchronized void start()
    {
        thread = new Thread(this);
        isRunning = true;
        thread.start();
    }

    public synchronized void stop()
    {

    }

    public static void main(String[] args)
    {
        Game game = new Game();
        game.start();
    }

    @Override
    public void run()
    {
        while (isRunning)
        {
            System.out.println("My game is running!...");
        }

    }

    
}