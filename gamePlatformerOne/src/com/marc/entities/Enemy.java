package com.marc.entities;

import java.awt.image.BufferedImage;

import com.marc.main.Game;
import com.marc.world.World;

public class Enemy extends Entity
{
	private double enemySpeed = 1.0;

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
		if ((int)entityX < Game.player.getEntityX() && World.placeIsFree((int)(entityX + enemySpeed), this.getEntityY()))
		{
			entityX += enemySpeed;
		}
		else if ((int)entityX > Game.player.getEntityX() && World.placeIsFree((int)(entityX - enemySpeed), this.getEntityY()))
		{
			entityX -= enemySpeed;
		}
		
		else if ((int)entityY < Game.player.getEntityY() && World.placeIsFree(this.getEntityX(), (int)(entityY + enemySpeed)))
		{
			entityY += enemySpeed;
		}
		else if ((int)entityY > Game.player.getEntityY() && World.placeIsFree(this.getEntityX(), (int)(entityY - enemySpeed)))
		{
			entityY -= enemySpeed;
		}
	}
	
}
