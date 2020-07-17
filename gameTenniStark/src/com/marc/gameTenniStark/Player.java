package com.marc.gameTenniStark;

import java.awt.Color;
import java.awt.Graphics;

public class Player
{
	public boolean right;
	public boolean left;
	
	//For the axis
	public int axisX;
	public int axisY;
	
	//For the player
	public int playerWidth;
	public int playerHeight;
	
	//Player Constructor
	public Player(int axisX, int axisY)
	{
		this.axisX = axisX;
		this.axisY = axisY;
		this.playerWidth = 40;
		this.playerHeight = 10;
	}
	
	public void update()
	{
		if (right)
		{
			axisX++;
		}
		else if (left)
		{
			axisX--;
		}
		
		//Adding collision detection
		if ((axisX + playerWidth) > Game.WIDTH)
		{
			axisX = Game.WIDTH - playerWidth;
		}
		else if (axisX < 0)
		{
			axisX = 0;
		}
	}
	
	public void render(Graphics graphic)
	{
		Color electricPurple = new Color(98, 0, 225);
		graphic.setColor(electricPurple);
		graphic.fillRect(axisX, axisY, playerWidth, playerHeight);
	}

}
