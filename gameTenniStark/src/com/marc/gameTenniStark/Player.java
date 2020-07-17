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
	
	public Player(int x, int y)
	{
		this.x = x;
		this.y = y;
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
	}
	
	public void render(Graphics graphic)
	{
		Color electricPurple = new Color(98, 0, 225);
		graphic.setColor(electricPurple);
		graphic.fillRect(x, y, 40, 10);
	}

}
