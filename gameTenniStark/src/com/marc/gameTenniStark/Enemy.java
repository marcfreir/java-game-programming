package com.marc.gameTenniStark;

import java.awt.Color;
import java.awt.Graphics;

public class Enemy
{
	public double axisX;
	public double axisY;
	public int enemyWidth;
	public int enemyHeight;
	
	public double enemyStrength = 0.07;
	
	//Enemy Constructor
	public Enemy(int axisX, int axisY)
	{
		this.axisX = axisX;
		this.axisY = axisY;
		this.enemyWidth = 40;
		this.enemyHeight = 10;
	}
	
	public void update()
	{
		
		axisX += (Game.ball.axisX - axisX - 7) * enemyStrength;
	}
	
	public void render(Graphics graphic)
	{
		graphic.setColor(Color.red);
		graphic.fillRect((int)axisX, (int)axisY, enemyWidth, enemyHeight);
	}

}
