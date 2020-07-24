package com.marc.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.marc.main.Game;
import com.marc.world.Camera;
import com.marc.world.World;

public class Enemy extends Entity
{
	private double enemySpeed = 1.0;
	
	private int maskX = 8;
	private int maskY = 8;
	private int maskWidth = 10;
	private int maskHeight = 10;

	public Enemy(int entityX, int entityY, int entityWidth, int entityHeight, BufferedImage sprite)
	{
		super(entityX, entityY, entityWidth, entityHeight, sprite);
	}
	
	/**
	 * Explanation - The class Enemy extends Entity
	 * then, since the Enemy is a kind of Entity,
	 * the methods of Entity are called and Overridden
	 */
	@Override
	public void updateEntity()
	{
		maskX = 8;
		maskY = 8;
		maskWidth = 5;
		maskHeight = 5;
		
		//Wrap all the IF/ELSE IF statements bellow this first IF to disperse the enemies randomly
		//if (Game.random.nextInt(100) < 50)
		
		if (((int)entityX < Game.player.getEntityX()) && (World.placeIsFree((int)(entityX + enemySpeed), this.getEntityY()))
				&& !(isColliding((int)(entityX + enemySpeed), this.getEntityY())))
		{
			entityX += enemySpeed;
		}
		else if ((int)entityX > Game.player.getEntityX() && World.placeIsFree((int)(entityX - enemySpeed), this.getEntityY())
				&& !(isColliding((int)(entityX - enemySpeed), this.getEntityY())))
		{
			entityX -= enemySpeed;
		}
		
		else if ((int)entityY < Game.player.getEntityY() && World.placeIsFree(this.getEntityX(), (int)(entityY + enemySpeed))
				&& !(isColliding(this.getEntityX(), (int)(entityY + enemySpeed))))
		{
			entityY += enemySpeed;
		}
		else if ((int)entityY > Game.player.getEntityY() && World.placeIsFree(this.getEntityX(), (int)(entityY - enemySpeed))
				&& !(isColliding(this.getEntityX(), (int)(entityY - enemySpeed))))
		{
			entityY -= enemySpeed;
		}
		
	}
	
	public boolean isColliding(int nextX, int nextY)
	{
		Rectangle currentEnemy = new Rectangle((nextX + maskX), (nextY + maskY), maskWidth, maskHeight);
		
		for (int index = 0; index < Game.enemies.size(); index++)
		{
			Enemy enemy = Game.enemies.get(index);
			
			if (enemy == this)
			{
				continue;
			}
			
			Rectangle targetEnemy = new Rectangle((enemy.getEntityX() + maskX), (enemy.getEntityY() + maskY), maskWidth, maskHeight);
			
			if (currentEnemy.intersects(targetEnemy))
			{
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void renderEntity(Graphics entityGraphics)
	{
		super.renderEntity(entityGraphics);
		entityGraphics.setColor(Color.magenta);
		entityGraphics.fillRect(((this.getEntityX() + maskX) - Camera.cameraX), ((this.getEntityY() + maskY) - Camera.cameraY), maskWidth, maskHeight);
	}
	
}
