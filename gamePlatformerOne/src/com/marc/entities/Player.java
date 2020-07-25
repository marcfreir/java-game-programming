package com.marc.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.marc.main.Game;
import com.marc.world.Camera;
import com.marc.world.World;

public class Player extends Entity
{
	
	public boolean playerRight;
	public boolean playerLeft;
	public boolean playerUp;
	public boolean playerDown;
	
	public int rightDirection = 0;
	public int leftDirection = 1;
	public int forwardDirection = rightDirection;
	
	public double playerSpeed = 1.2;
	
	private int frames = 0;
	private int maxFrames = 5;
	private int indexFrames = 0;
	private int maxIndexFrames = 3;
	
	private boolean playerMoved = false;
	
	private BufferedImage[] rightPlayerOrientation;
	private BufferedImage[] leftPlayerOrientation;
	
	//Ammo and Arrows
	public int ammo = 0;
	public int arrows = 0;
	
	
	public static double playerLife = 100;
	public static double playerMaxLife = 100;

	public Player(int entityX, int entityY, int entityWidth, int entityHeight, BufferedImage sprite)
	{
		super(entityX, entityY, entityWidth, entityHeight, sprite);
		
		rightPlayerOrientation = new BufferedImage[4];
		leftPlayerOrientation = new BufferedImage[4];
		
		int amountOfImagesPerOrientation = 4;
		int spriteSheetWidth = 16;
		int spriteSheetHeight = 16;
		//For the Sprite List to the Right in the spriteSheet.png
		for (int index = 0; index < amountOfImagesPerOrientation; index++)
		{
			rightPlayerOrientation[index] = Game.spritesheet.getSprite((32 + (index * 16)), 0, spriteSheetWidth, spriteSheetHeight);
		}
		
		//For the Sprite List to the Left in the spriteSheet.png
		for (int index = 0; index < amountOfImagesPerOrientation; index++)
		{
			leftPlayerOrientation[index] = Game.spritesheet.getSprite((32 + (index * 16)), 16, spriteSheetWidth, spriteSheetHeight);
		}
	}
	
	//public void updatePlayer() #changed to updateEntity()
	/**
	 * Explanation - The class Player extends Entity
	 * then, since the Player is a kind of Entity,
	 * the methods of Entity are called and Overridden
	 */
	@Override
	public void updateEntity()
	{
		playerMoved = false;
		if (playerRight && World.placeIsFree((int)(entityX + playerSpeed), this.getEntityY()))
		{
			playerMoved = true;
			forwardDirection = rightDirection;
			entityX += playerSpeed;
		}
		else if (playerLeft && World.placeIsFree((int)(entityX - playerSpeed), this.getEntityY()))
		{
			playerMoved = true;
			forwardDirection = leftDirection;
			entityX -= playerSpeed;
		}
		
		if (playerUp && World.placeIsFree(this.getEntityX(), (int)(entityY - playerSpeed)))
		{
			playerMoved = true;
			entityY -= playerSpeed;
		}
		else if (playerDown && World.placeIsFree(this.getEntityX(), (int)(entityY + playerSpeed)))
		{
			playerMoved = true;
			entityY += playerSpeed;
		}
		
		//For the animation
		if (playerMoved)
		{
			frames++;
			
			if (frames == maxFrames)
			{
				frames = 0;
				indexFrames++;
				
				if (indexFrames > maxIndexFrames)
				{
					indexFrames = 0;
				}
			}
		}
		
		checkCollisionLifePack();
		checkCollisionAmmo();
		checkCollisionArrow();
		
		Camera.cameraX = Camera.clamp((this.getEntityX() - (Game.WIDTH / 2)), 0, (World.WORLD_WIDTH * 16 - Game.WIDTH));
		Camera.cameraY = Camera.clamp((this.getEntityY() - (Game.HEIGHT / 2)), 0, (World.WORLD_HEIGHT * 16 - Game.HEIGHT));
	}
	
	public void checkCollisionAmmo()
	{
		for (int index = 0; index < Game.entities.size(); index++)
		{
			Entity currentAmmo = Game.entities.get(index);
			
			if (currentAmmo instanceof Ammo)
			{
				if (Entity.isCollidingEntity(this, currentAmmo))
				{
					ammo +=10;
					//System.out.println("Current Ammo: " + ammo); <-//Just for debugging
					Game.entities.remove(currentAmmo);
				}
			}
		}
	}
	
	public void checkCollisionArrow()
	{
		for (int index = 0; index < Game.entities.size(); index++)
		{
			Entity currentArrows = Game.entities.get(index);
			
			if (currentArrows instanceof Arrow)
			{
				if (Entity.isCollidingEntity(this, currentArrows))
				{
					arrows +=10;
					//System.out.println("Current Arrows: " + arrows); <-//Just for debugging
					Game.entities.remove(currentArrows);
				}
			}
		}
	}
	
	public void checkCollisionLifePack()
	{
		for (int index = 0; index < Game.entities.size(); index++)
		{
			Entity currentLifePack = Game.entities.get(index);
			
			if (currentLifePack instanceof LifePack)
			{
				if (Entity.isCollidingEntity(this, currentLifePack))
				{
					playerLife += 10;
					
					if (playerLife >= 100)
					{
						playerLife = 100;
					}
					Game.entities.remove(currentLifePack);
					//Game.entities.remove(index);
					//return;
				}
			}
		}
	}
	
	@Override
	public void renderEntity(Graphics entityGraphics)
	{
		if (forwardDirection == rightDirection)
		{
			entityGraphics.drawImage(rightPlayerOrientation[indexFrames], (this.getEntityX() - Camera.cameraX), (this.getEntityY() - Camera.cameraY), null);
		}
		else if (forwardDirection == leftDirection)
		{
			entityGraphics.drawImage(leftPlayerOrientation[indexFrames], (this.getEntityX() - Camera.cameraX), (this.getEntityY() - Camera.cameraY), null);
		}
	}
	
}
