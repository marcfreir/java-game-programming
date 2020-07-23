package com.marc.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.marc.main.Game;
import com.marc.world.Camera;

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
		if (playerRight)
		{
			playerMoved = true;
			forwardDirection = rightDirection;
			entityX += playerSpeed;
		}
		else if (playerLeft)
		{
			playerMoved = true;
			forwardDirection = leftDirection;
			entityX -= playerSpeed;
		}
		
		if (playerUp)
		{
			playerMoved = true;
			entityY -= playerSpeed;
		}
		else if (playerDown)
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
		Camera.cameraX = this.getEntityX() - (Game.WIDTH / 2);
		Camera.cameraY = this.getEntityY() - (Game.HEIGHT / 2);
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
