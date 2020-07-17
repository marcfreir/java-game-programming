package com.marc.gameTenniStark;

import java.awt.Color;
import java.awt.Graphics;

public class Enemy
{
	public double axisX;
	public double axisY;
	public int width;
	public int height;
	
	//Enemy Constructor
	public Enemy(int axisX, int axisY)
	{
		this.axisX = axisX;
		this.axisY = axisY;
		this.width = 40;
		this.height = 10;
	}
	
	public void update()
	{
		axisX += (Game.ball.axisX - axisX - 7);
	}
	
	public void render(Graphics graphic)
	{
		graphic.setColor(Color.red);
		graphic.fillRect((int)axisX, (int)axisY, width, height);
	}

}
