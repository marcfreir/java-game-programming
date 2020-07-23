package com.marc.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.marc.main.Game;

public class Entity
{
	public static int lifePackPosition = 7;
	public static int gunPosition = 8;
	public static int enemyPosition = 9;
	public static int gunMagazinePosition = 7;
	public static int arrowPosition = 8;
	public static int bowPosition = 9;
	public static int spriteSheetWidth = 16;
	public static int spriteSheetHeight = 16;
	public static int firstLineAxisY = 0;
	public static int secondLineAxisY = 16;
	
	public static BufferedImage LIFEPACK_ENTITY = Game.spritesheet.getSprite((lifePackPosition * spriteSheetWidth), firstLineAxisY, spriteSheetWidth, spriteSheetHeight);
	public static BufferedImage GUN_ENTITY = Game.spritesheet.getSprite((gunPosition * spriteSheetWidth), firstLineAxisY, spriteSheetWidth, spriteSheetHeight);
	public static BufferedImage ENEMY_ENTITY = Game.spritesheet.getSprite((enemyPosition * spriteSheetWidth), firstLineAxisY, spriteSheetWidth, spriteSheetHeight);
	public static BufferedImage GUN_MAGAZINE_ENTITY = Game.spritesheet.getSprite((gunMagazinePosition * spriteSheetWidth), secondLineAxisY, spriteSheetWidth, spriteSheetHeight);
	public static BufferedImage ARROW_ENTITY = Game.spritesheet.getSprite((arrowPosition * spriteSheetWidth), secondLineAxisY, spriteSheetWidth, spriteSheetHeight);
	public static BufferedImage BOW_ENTITY = Game.spritesheet.getSprite((bowPosition * spriteSheetWidth), secondLineAxisY, spriteSheetWidth, spriteSheetHeight);
	
	protected double entityX;
	protected double entityY;
	protected int entityWidth;
	protected int entityHeight;
	
	//
	private BufferedImage sprite;
	
	//Constructor
	public Entity(int entityX, int entityY, int entityWidth, int entityHeight, BufferedImage sprite)
	{
		this.entityX = entityX;
		this.entityY = entityY;
		this.entityWidth = entityWidth;
		this.entityHeight = entityHeight;
		this.sprite = sprite;
	}
	
	//Getters
	public int getEntityX()
	{
		return (int)this.entityX;
	}
	
	public int getEntityY()
	{
		return (int)this.entityY;
	}
	
	public int getEntityWidth()
	{
		return this.entityWidth;
	}
	
	public int getEntityHeight()
	{
		return this.entityHeight;
	}
	
	//Setters
	public void setEntityX(int newEntityX)
	{
		this.entityX = newEntityX;
	}
	
	public void setEntityY(int newEntityY)
	{
		this.entityY = newEntityY;
	}
	
	public void updateEntity()
	{
		//To do
	}
	
	//Render
	public void renderEntity(Graphics entityGraphics)
	{
		entityGraphics.drawImage(sprite, this.getEntityX(), this.getEntityY(), null);
	}
}
