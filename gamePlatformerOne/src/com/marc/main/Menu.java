package com.marc.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Menu
{
	
	public String[] options = {"new game", "load game", "exit"};
	
	public int currentOption = 0;
	public int maxOption = (options.length - 1);
	
	public boolean menuUp;
	public boolean menuDown;
	
	
	public void updateMenu()
	{
		if (menuUp)
		{
			menuUp = false;
			
			currentOption--;
			
			if (currentOption < 0)
			{
				currentOption = maxOption;
			}
		}
		
		if (menuDown)
		{
			menuDown = false;
			
			currentOption++;
			
			if (currentOption > maxOption)
			{
				currentOption = 0;
			}
		}
	}
	
	public void renderMenu(Graphics menuGraphics)
	{
		//Background
		menuGraphics.setColor(Color.black);
		menuGraphics.fillRect(0, 0, (Game.WIDTH * Game.SCALE), (Game.HEIGHT * Game.SCALE));
		
		//Game Info
		menuGraphics.setColor(Color.white);
		menuGraphics.setFont(new Font("Arial", Font.BOLD, 32));
		menuGraphics.drawString("PLATFORMER ONE", (((Game.WIDTH * Game.SCALE) / 2) - 150), (((Game.HEIGHT * Game.SCALE) / 2) - 130));
		menuGraphics.setFont(new Font("Arial", Font.BOLD, 24));
		menuGraphics.drawString("(aka Timmy Nee)", (((Game.WIDTH * Game.SCALE) / 2) - 98), (((Game.HEIGHT * Game.SCALE) / 2) - 100));
		menuGraphics.setFont(new Font("Arial", Font.PLAIN, 12));
		menuGraphics.drawString("(Version 0.1)", (((Game.WIDTH * Game.SCALE) / 2) - 38), (((Game.HEIGHT * Game.SCALE) / 2) - 75));
		
		//Menu Options
		menuGraphics.setColor(Color.white);
		menuGraphics.setFont(new Font("Arial", Font.BOLD, 18));
		menuGraphics.drawString("New Game", (((Game.WIDTH * Game.SCALE) / 2) - 50), (((Game.HEIGHT * Game.SCALE)) - 260));
		menuGraphics.drawString("Load Game", (((Game.WIDTH * Game.SCALE) / 2) - 52), (((Game.HEIGHT * Game.SCALE)) - 220));
		menuGraphics.drawString("Exit", (((Game.WIDTH * Game.SCALE) / 2) - 18), (((Game.HEIGHT * Game.SCALE)) - 180));
		
		//Signature
		menuGraphics.setColor(Color.yellow);
		menuGraphics.setFont(new Font("Arial", Font.ITALIC, 14));
		menuGraphics.drawString("Developed by Marc Freir (2020)", (((Game.WIDTH * Game.SCALE) / 2) - 120), (((Game.HEIGHT * Game.SCALE)) - 40));
		menuGraphics.setFont(new Font("Arial", Font.PLAIN, 10));
		menuGraphics.drawString("Licence: MIT", (((Game.WIDTH * Game.SCALE)) - 410), (((Game.HEIGHT * Game.SCALE)) - 25));
		menuGraphics.drawString("For Download: https://github.com/marcfreir/java-game-programming/tree/master/gamePlatformerOne", (((Game.WIDTH * Game.SCALE)) - 600), (((Game.HEIGHT * Game.SCALE)) - 10));
		
		pointMenuOption(menuGraphics);
	}
	
	public void pointMenuOption(Graphics menuGraphics)
	{
		if (options[currentOption] == "new game")
		{
			menuGraphics.setColor(Color.blue);
			menuGraphics.setFont(new Font("Arial", Font.BOLD, 22));
			menuGraphics.drawString("[", (((Game.WIDTH * Game.SCALE) / 2) - 65), (((Game.HEIGHT * Game.SCALE)) - 260));
			menuGraphics.drawString("]", (((Game.WIDTH * Game.SCALE) / 2) + 52), (((Game.HEIGHT * Game.SCALE)) - 260));
		}
		else if (options[currentOption] == "load game")
		{
			menuGraphics.setColor(Color.yellow);
			menuGraphics.setFont(new Font("Arial", Font.BOLD, 22));
			menuGraphics.drawString("[", (((Game.WIDTH * Game.SCALE) / 2) - 70), (((Game.HEIGHT * Game.SCALE)) - 220));
			menuGraphics.drawString("]", (((Game.WIDTH * Game.SCALE) / 2) + 57), (((Game.HEIGHT * Game.SCALE)) - 220));
		}
		else if (options[currentOption] == "exit")
		{
			menuGraphics.setColor(Color.red);
			menuGraphics.setFont(new Font("Arial", Font.BOLD, 22));
			menuGraphics.drawString("[", (((Game.WIDTH * Game.SCALE) / 2) - 33), (((Game.HEIGHT * Game.SCALE)) - 180));
			menuGraphics.drawString("]", (((Game.WIDTH * Game.SCALE) / 2) + 25), (((Game.HEIGHT * Game.SCALE)) - 180));
		}
	}
	
}
