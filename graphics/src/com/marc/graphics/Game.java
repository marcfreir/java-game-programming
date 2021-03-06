package com.marc.graphics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

/**
 * Graphics
 */
public class Game extends Canvas implements Runnable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public static JFrame frame;

    private transient Thread thread;

    private boolean isRunning = true;

    private static final int WIDTH = 160;
    private static final int HEIGHT = 120;
    private static final int SCALE = 4;

    private transient BufferedImage image;

    // Constructor
    public Game() {
        setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        initFrame();
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    }

    public void initFrame() {
        frame = new JFrame("Game #01");
        frame.add(this);
        frame.setResizable(false);
        frame.pack();
        // Centered window
        frame.setLocationRelativeTo(null);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public synchronized void start() {
        thread = new Thread(this);
        isRunning = true;
        thread.start();
    }

    public synchronized void stop() {
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
        game.start();
    }

    public void beatUpdate()
    {
        //Something
    }

    public void render()
    {
        //To organize complex memory on the Canvas
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null)
        {
            this.createBufferStrategy(3);
            return;
        }
        //To draw onto Canvas
        Graphics graphics = image.getGraphics();
        graphics.setColor(new Color(19,19,19));
        graphics.fillRect(0, 0, WIDTH, HEIGHT);

        /**graphics.setColor(Color.MAGENTA);
        graphics.fillRect(20, 20, 80, 80);*/
        graphics.setColor(Color.WHITE);
        graphics.setFont(new Font("Arial", Font.BOLD, 16));
        graphics.drawString("Hello Friend!", 15, 15);

        graphics = bs.getDrawGraphics();
        graphics.drawImage(image, 0, 0, WIDTH*SCALE, HEIGHT*SCALE,null);
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
                beatUpdate();
                render();
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
        stop();

    }

    
}