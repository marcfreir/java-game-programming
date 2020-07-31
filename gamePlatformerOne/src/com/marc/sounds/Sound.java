package com.marc.sounds;

import java.applet.Applet;
import java.applet.AudioClip;

@SuppressWarnings("deprecation")
public class Sound
{
	private AudioClip audioClip;
	
	public static final Sound musicBackground = new Sound("/levelOne.wav");
	public static final Sound hurtEffect = new Sound("/damage.wav");
	
	private Sound(String soundName)
	{
		try
		{
			audioClip = Applet.newAudioClip(Sound.class.getResource(soundName));
		}
		catch (Throwable exception)
		{
			
		}
	}
	
	public void play()
	{
		try
		{
			new Thread()
			{
				public void run()
				{
					audioClip.play();
				}
			}.start();
		}
		catch (Throwable exception)
		{
			
		}
	}
	
	public void loop()
	{
		try
		{
			new Thread()
			{
				public void run()
				{
					audioClip.loop();
				}
			}.start();
		}
		catch (Throwable exception)
		{
			
		}
	}
}
