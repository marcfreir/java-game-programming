package com.marc.logic;

import java.util.ArrayList;

/**
 * Game
 */
public class Game implements Runnable
{
    private boolean isRunning;
    private Thread thread;

    public ArrayList<Entity> entities = new ArrayList<>();

    //Constructor
    public Game()
    {
        entities.add(new Entity());
        entities.add(new Entity());
        entities.add(new Entity());
        entities.add(new Entity());

        for (int index = 0; index < entities.size(); index++)
        {
            Entity ent = entities.get(0);
        }
    }

    public static void main(String[] args)
    {
        Game game = new Game();
        game.start();
    }

    public synchronized void start()
    {
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    public void update()
    {
        // Update game
        System.out.println("Updating...");
    }

    public void render() {
        // Render game
        System.out.println("Rendering...");
    }

    @Override
    public void run()
    {
        while (isRunning) {
            update();
            render();
            /**
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            */
        }

    }
}