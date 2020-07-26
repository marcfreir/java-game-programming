package com.marc.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.marc.main.Game;
import com.marc.world.Camera;

public class BulletShoot extends Entity
{
	
	private int shootDirectionX;
	private int shootDirectionY;
	private double shootSpeed = 4;
	
	private int bulletLength = 30;
	private int currentBulletLength = 0;

	public BulletShoot(int entityX, int entityY, int entityWidth, int entityHeight, BufferedImage sprite, int shootDirectionX, int shootDirectionY)
	{
		super(entityX, entityY, entityWidth, entityHeight, sprite);
		this.shootDirectionX = shootDirectionX;
		this.shootDirectionY = shootDirectionY;
	}
	
	@Override
	public void updateEntity()
	{
		entityX += shootDirectionX * shootSpeed;
		entityY += shootDirectionY * shootSpeed;
		
		currentBulletLength++;
		
		if (currentBulletLength == bulletLength)
		{
			Game.bullets.remove(this);
			return;
		}
	}
	
	public void renderEntity(Graphics entityGraphics)
	{
		entityGraphics.setColor(Color.yellow);
		entityGraphics.fillOval((this.getEntityX() - Camera.cameraX), (this.getEntityY() - Camera.cameraY), entityWidth, entityHeight);
	}
	
}
