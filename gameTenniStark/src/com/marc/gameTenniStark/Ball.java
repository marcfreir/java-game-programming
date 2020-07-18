package com.marc.gameTenniStark;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
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
	public double speed = 1.7;
	
	
	//Ball Constructor
	public Ball(int axisX, int axisY)
	{
		this.axisX = axisX;
		this.axisY = axisY;
		this.width = 5;
		this.height = 5;
		
		int angle = new Random().nextInt(120 - 45) + 45 + 1;
		directionX = Math.cos(Math.toRadians(angle));
		directionY = Math.sin(Math.toRadians(angle));
	}
	
	public void update()
	{
		//For Collision Detection
		if (axisX + (directionX * speed) + width >= Game.WIDTH)
		{
			directionX *= -1;
		}
		else if (axisX + (directionX * speed) < 0)
		{
			directionX *= -1;
		}
		//For Scores
		if (axisY >= Game.HEIGHT)
		{
			//Enemy's Score
			System.out.println("Enemy's point!");
			//Restart/Reset the game
			new Game();
			return;
		}
		else if (axisY < 0)
		{
			//Player's Score
			System.out.println("Player's point!");
			//Restart/Reset the game
			new Game();
			return;
		}
		
		Rectangle bounds = new Rectangle((int)(axisX + (directionX * speed)), (int)(axisY + (directionY * speed)), width, height);
		
		Rectangle boundsPlayer = new Rectangle(Game.player.axisX, Game.player.axisY, Game.player.playerWidth, Game.player.playerHeight);
		Rectangle boundsEnemy = new Rectangle(((int)Game.enemy.axisX), ((int)Game.enemy.axisY), Game.enemy.enemyWidth, Game.enemy.enemyHeight);
		
		if (bounds.intersects(boundsPlayer))
		{

			int angle = new Random().nextInt(120 - 45) + 45 + 1;
			directionX = Math.cos(Math.toRadians(angle));
			directionY = Math.sin(Math.toRadians(angle));
			
			if (directionY > 0)
			{
				directionY *= -1;
			}
		}
		else if (bounds.intersects(boundsEnemy))
		{
			int angle = new Random().nextInt(120 - 45) + 45 + 1;
			directionX = Math.cos(Math.toRadians(angle));
			directionY = Math.sin(Math.toRadians(angle));
			
			if (directionY < 0)
			{
				directionY *= -1;
			}
		}
		
		axisX += directionX * speed;
		axisY += directionY * speed;
	}
	
	public void render(Graphics graphic)
	{
		graphic.setColor(Color.lightGray);
		graphic.fillRect((int)axisX, (int)axisY, width, height);
		//graphic.fillOval((int)axisX, (int)axisY, width, height);
	}

}
