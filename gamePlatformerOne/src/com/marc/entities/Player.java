package com.marc.entities;

import java.awt.image.BufferedImage;

public class Player extends Entity
{
	
	public boolean playerRight;
	public boolean playerLeft;
	public boolean playerUp;
	public boolean playerDown;
	
	public double playerSpeed = 1.2;

	public Player(int entityX, int entityY, int entityWidth, int entityHeight, BufferedImage sprite)
	{
		super(entityX, entityY, entityWidth, entityHeight, sprite);
	}
	
	//public void updatePlayer()
	public void updateEntity()
	{
		if (playerRight)
		{
			entityX += playerSpeed;
		}
		else if (playerLeft)
		{
			entityX -= playerSpeed;
		}
		
		if (playerUp)
		{
			entityY -= playerSpeed;
		}
		else if (playerDown)
		{
			entityY += playerSpeed;
		}
	}
	
}
