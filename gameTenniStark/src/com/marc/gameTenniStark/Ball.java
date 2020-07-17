package com.marc.gameTenniStark;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Ball
{
	public double axisX;
	public double axisY;
	public int width;
	public int height;
	
	//Ball directions
	public double directionX;
	public double directionY;
	
	//Ball speed
	public double speed = 0.5;
	
	
	//Enemy Constructor
	public Ball(int axisX, int axisY)
	{
		this.axisX = axisX;
		this.axisY = axisY;
		this.width = 5;
		this.height = 5;
		
		directionX = new Random().nextGaussian();
		directionY = new Random().nextGaussian();
	}
	
	public void update()
	{
		axisX += directionX * speed;
		axisY += directionY * speed;
	}
	
	public void render(Graphics graphic)
	{
		graphic.setColor(Color.lightGray);
		graphic.fillRect((int)axisX, (int)axisY, width, height);
	}

}
