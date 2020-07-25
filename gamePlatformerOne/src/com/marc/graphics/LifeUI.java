package com.marc.graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.marc.entities.Player;

public class LifeUI
{
	public void renderLifeUI(Graphics lifeUIGraphics)
	{
		//Losing life
		lifeUIGraphics.setColor(Color.red);
		lifeUIGraphics.fillRect(4, 4, 70, 8);
		//Current life
		lifeUIGraphics.setColor(Color.blue);
		lifeUIGraphics.fillRect(4, 4, (int)((Player.playerLife / Player.playerMaxLife) * 70), 8);
		
		//Life in numbers
		lifeUIGraphics.setColor(Color.white);
		lifeUIGraphics.setFont(new Font("Arial", Font.BOLD, 8));
		
		//Fix the decimal places e.g.: from 1 to 001
		if ((int)Player.playerLife < 100)
		{
			if ((int)Player.playerLife < 10)
			{
				lifeUIGraphics.drawString(("00" + (int)Player.playerLife + "/" + (int)Player.playerMaxLife), 26, 11);
			}
			else
			{
				lifeUIGraphics.drawString(("0" + (int)Player.playerLife + "/" + (int)Player.playerMaxLife), 26, 11);
			}
		}
		else if ((int)Player.playerLife == 100)
		{
			lifeUIGraphics.drawString(((int)Player.playerLife + "/" + (int)Player.playerMaxLife), 26, 11);
		}
		//lifeUIGraphics.drawString(("0" + (int)Player.playerLife + "/" + (int)Player.playerMaxLife), 26, 11);
	}
}
