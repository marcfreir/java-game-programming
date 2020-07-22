package com.marc.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class World
{
	public World(String path)
	{
		try
		{
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			int[] pixels = new int[map.getWidth() * map.getHeight()];
			map.getRGB(0, 0, map.getWidth(), map.getHeight(), pixels, 0, map.getWidth());
			
			for (int indexWidth = 0; indexWidth < map.getWidth(); indexWidth++)
			{
				for (int indexHeight = 0; indexHeight < map.getHeight(); indexHeight++)
				{
					int currentPixel = pixels[indexWidth + (indexHeight * map.getWidth())];
					
					if (currentPixel == 0xFF000000)
					{
						//Floor
					}
					else if (currentPixel == 0xFFFFFFFF)
					{
						//Wall
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
		
	}
}
