package com.marc.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Entity
{
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
