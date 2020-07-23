package com.marc.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.marc.entities.*;
import com.marc.main.Game;

public class World
{
	private Tile[] tiles;
	
	public static int WORLD_WIDTH;
	public static int WORLD_HEIGHT;
	
	public World(String path)
	{
		try
		{
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			int[] pixels = new int[map.getWidth() * map.getHeight()];
			
			WORLD_WIDTH = map.getWidth();
			WORLD_HEIGHT = map.getHeight();
			
			tiles = new Tile[map.getWidth() * map.getHeight()];
			map.getRGB(0, 0, map.getWidth(), map.getHeight(), pixels, 0, map.getWidth());
			
			for (int indexWidth = 0; indexWidth < map.getWidth(); indexWidth++)
			{
				for (int indexHeight = 0; indexHeight < map.getHeight(); indexHeight++)
				{
					int currentPixel = pixels[indexWidth + (indexHeight * map.getWidth())];
					
					//Floor
					tiles[indexWidth + (indexHeight * WORLD_WIDTH)] = new FloorTile(indexWidth * 16, indexHeight * 16, Tile.TILE_FLOOR);
					
					if (currentPixel == 0xFF000000)
					{
						//Floor - Map Color: Black
						tiles[indexWidth + (indexHeight * WORLD_WIDTH)] = new FloorTile(indexWidth * 16, indexHeight * 16, Tile.TILE_FLOOR);
					}
					else if (currentPixel == 0xFFFFFFFF)
					{
						//Wall - Map Color: White
						tiles[indexWidth + (indexHeight * WORLD_WIDTH)] = new FloorTile(indexWidth * 16, indexHeight * 16, Tile.TILE_WALL);
					}
					else if (currentPixel == 0xFF4800FF)
					{
						//Player - Map Color: Electric Purple
						//tiles[indexWidth + (indexHeight * WORLD_WIDTH)] = new FloorTile(indexWidth * 16, indexHeight * 16, Tile.TILE_FLOOR);
						Game.player.setEntityX(indexWidth * 16);
						Game.player.setEntityY(indexHeight * 16);
					}
					else if (currentPixel == 0xFF7F0000)
					{
						//Enemy - Map Color: Maroon (Dark Red)
						Game.entities.add(new Enemy(indexWidth * 16, indexHeight * 16, 16, 16, Entity.ENEMY_ENTITY));
					}
					else if (currentPixel == 0xFF0026FF)
					{
						//Gun - Map Color: Blue
						Game.entities.add(new Gun(indexWidth * 16, indexHeight * 16, 16, 16, Entity.GUN_ENTITY));
					}
					else if (currentPixel == 0xFFFF0000)
					{
						//Life Pack - Map Color: Red
						Game.entities.add(new LifePack(indexWidth * 16, indexHeight * 16, 16, 16, Entity.LIFEPACK_ENTITY));
					}
					else if (currentPixel == 0xFF303030)
					{
						//Gun Magazines - Map Color: Night Rider (Dark Gray)
						Game.entities.add(new GunMagazine(indexWidth * 16, indexHeight * 16, 16, 16, Entity.GUN_MAGAZINE_ENTITY));
					}
					else if (currentPixel == 0xFF007F0E)
					{
						//Arrows - Map Color: Green
						Game.entities.add(new Arrow(indexWidth * 16, indexHeight * 16, 16, 16, Entity.ARROW_ENTITY));
					}
					else if (currentPixel == 0xFF7F3300)
					{
						//Bow - Map Color: Saddle Brown (Brown)
						Game.entities.add(new Bow(indexWidth * 16, indexHeight * 16, 16, 16, Entity.BOW_ENTITY));
					}
					else
					{
						//to do
					}
				}
			}
		}
		catch (IOException exception)
		{
			exception.printStackTrace();
		}
	}
	
	public void renderWorld(Graphics worldGraphics)
	{
		for (int indexWidth = 0; indexWidth < WORLD_WIDTH; indexWidth++)
		{
			for (int indexHeight = 0; indexHeight < WORLD_HEIGHT; indexHeight++)
			{
				Tile tile = tiles[indexWidth + (indexHeight * WORLD_WIDTH)];
				tile.renderTile(worldGraphics);
			}
		}
	}
}
