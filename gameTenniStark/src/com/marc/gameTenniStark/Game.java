package com.marc.gameTenniStark;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;


public class Game extends Canvas implements Runnable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//For the size of the canvas
	public static final int WIDTH = 240;
	public static final int HEIGHT = 120;
	public static final int SCALE = 4;
	
	//Declaring new object as Player type
	public Player player;
	
	//Game Constructor
	public Game()
	{
		this.setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		//Instantiating new Player
		player = new Player();
	}
	
	public static void main(String[] args)
	{
		Game game = new Game();
		JFrame frame = new JFrame("TenniStark v1.0");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		frame.add(game);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		new Thread(game).start();
	}
	
	public void update()
	{
		//to do
	}
	
	public void render()
	{
		BufferStrategy bs = this.getBufferStrategy();
		
		if (bs == null)
		{
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics graphic = bs.getDrawGraphics();
		player.render(graphic);
		
		//Display the Player
		bs.show();
	}

	@Override
	public void run()
	{
		//Game Loop
		while(true)
		{
			update();
			render();
			try
			{
				Thread.sleep(1000/60);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		
	}
	

}
