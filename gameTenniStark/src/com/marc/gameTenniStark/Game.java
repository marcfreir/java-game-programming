package com.marc.gameTenniStark;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;


public class Game extends Canvas implements Runnable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 240;
	public static final int HEIGHT = 120;
	public static final int SCALE = 4;
	
	//Game Constructor
	public Game()
	{
		this.setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
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
	}

	@Override
	public void run()
	{
		// TODO Auto-generated method stub
		
	}
	

}
