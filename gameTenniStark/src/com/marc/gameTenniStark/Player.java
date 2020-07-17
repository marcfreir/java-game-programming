package com.marc.gameTenniStark;

import java.awt.Color;
import java.awt.Graphics;

public class Player
{
	public boolean right;
	public boolean left;
	
	//For the axis
	public int x;
	public int y;
	
	//For the player
	public int playerWidth;
	public int playerHeight;
	
	public Player(int x, int y)
	{
		this.x = x;
		this.y = y;
		this.playerWidth = 40;
		this.playerHeight = 10;
	}
	
	public void update()
	{
		if (right)
		{
			x++;
		}
		else if (left)
		{
			x--;
		}
		
		//Adding collision detection
		if ((x + playerWidth) > Game.WIDTH)
		{
			x = Game.WIDTH - playerWidth;
		}
		else if (x < 0)
		{
			x = 0;
		}
	}
	
	public void render(Graphics graphic)
	{
		Color electricPurple = new Color(98, 0, 225);
		graphic.setColor(electricPurple);
		graphic.fillRect(x, y, playerWidth, playerHeight);
	}

}
