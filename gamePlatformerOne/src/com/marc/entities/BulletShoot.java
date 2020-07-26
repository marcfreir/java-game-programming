package com.marc.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.marc.world.Camera;

public class BulletShoot extends Entity
{
	
	private int shootDirectionX;
	private int shootDirectionY;
	private double shootSpeed;

	public BulletShoot(int entityX, int entityY, int entityWidth, int entityHeight, BufferedImage sprite)
	{
		super(entityX, entityY, entityWidth, entityHeight, sprite);
	}
	
	@Override
	public void updateEntity()
	{
		entityX += shootDirectionX * shootSpeed;
		entityY += shootDirectionY * shootSpeed;
	}
	
	public void renderEntity(Graphics entityGraphics)
	{
		entityGraphics.setColor(Color.yellow);
		entityGraphics.fillOval((this.getEntityX() - Camera.cameraX), (this.getEntityY() - Camera.cameraY), 3, 3);
	}
	
}
