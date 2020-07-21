package com.marc.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Entity
{
	private int entityX;
	private int entityY;
	private int entityWidth;
	private int entityHeight;
	
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
		return this.entityX;
	}
	
	public int getEntityY()
	{
		return this.entityY;
	}
	
	public int getEntityWidth()
	{
		return this.entityWidth;
	}
	
	public int getEntityHeight()
	{
		return this.entityHeight;
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
