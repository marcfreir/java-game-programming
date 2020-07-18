package com.marc.gameTenniStark;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;


public class Game extends Canvas implements Runnable, KeyListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//For the size of the canvas
	public static final int WIDTH = 240;
	public static final int HEIGHT = 120;
	public static final int SCALE = 4;
	
	public BufferedImage layer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	
	//Declaring new object as Player, Enemy and Ball type
	public static Player player;
	public static Enemy enemy;
	public static Ball ball;
	
	//Game Constructor
	public Game()
	{
		this.setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		this.addKeyListener(this);
		//Instantiating new Player
		player = new Player(100, (HEIGHT - 10));
		enemy = new Enemy(100, 0);
		ball = new Ball(100, ((HEIGHT / 2) - 1));
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
		player.update();
		enemy.update();
		ball.update();
	}
	
	public void render()
	{
		BufferStrategy bs = this.getBufferStrategy();
		
		if (bs == null)
		{
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics graphic = layer.getGraphics();
		graphic.setColor(Color.black);
		graphic.fillRect(0, 0, WIDTH, HEIGHT);
		player.render(graphic);
		enemy.render(graphic);
		ball.render(graphic);
		
		//Fix the canvas flickering by adding a layer on the canvas
		graphic = bs.getDrawGraphics();
		graphic.drawImage(layer, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
		
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
				Thread.sleep(1000 / 60);
			}
			catch (InterruptedException exception)
			{
				exception.printStackTrace();
			}
		}
		
	}

	@Override
	public void keyTyped(KeyEvent event)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent event)
	{
		if (event.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			System.out.println("Right Key pressed");
			player.right = true;
		}
		else if (event.getKeyCode() == KeyEvent.VK_LEFT)
		{
			System.out.println("Left Key pressed");
			player.left = true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent event)
	{
		if (event.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			System.out.println("Right Key pressed");
			player.right = false;
		}
		else if (event.getKeyCode() == KeyEvent.VK_LEFT)
		{
			System.out.println("Left Key pressed");
			player.left = false;
		}
		
	}
	

}
