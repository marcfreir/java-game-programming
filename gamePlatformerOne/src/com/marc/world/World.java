package com.marc.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

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
					
					if (currentPixel == 0xFF000000)
					{
						//Floor
						tiles[indexWidth + (indexHeight * WORLD_WIDTH)] = new FloorTile(indexWidth * 16, indexHeight * 16, Tile.TILE_FLOOR);
					}
					else if (currentPixel == 0xFFFFFFFF)
					{
						//Wall
						tiles[indexWidth + (indexHeight * WORLD_WIDTH)] = new FloorTile(indexWidth * 16, indexHeight * 16, Tile.TILE_WALL);
					}
					else if (currentPixel == 0xFF4800FF)
					{
						//Player
						tiles[indexWidth + (indexHeight * WORLD_WIDTH)] = new FloorTile(indexWidth * 16, indexHeight * 16, Tile.TILE_FLOOR);
					}
					else
					{
						//Floor
						tiles[indexWidth + (indexHeight * WORLD_WIDTH)] = new FloorTile(indexWidth * 16, indexHeight * 16, Tile.TILE_FLOOR);
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
