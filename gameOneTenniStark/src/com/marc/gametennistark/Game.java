package com.marc.gametennistark;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;

/**import javax.swing.ImageIcon;*/
import javax.swing.JFrame;

public class Game extends Canvas implements Runnable
{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public static final int WIDTH = 240;
    public static final int HEIGHT = 120;
    public static final int SCALE = 3;

    public Player player;

    //Game Constructor
    public Game()
    {
        this.setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
    }

    public static void main(String[] args)
    {
        Game game = new Game();

        

        /**String icon = "./img/tenniStarkLogo.png";
        ImageIcon img = new ImageIcon(icon);*/
        JFrame frame = new JFrame("Game version 1.0");
        /**frame.setIconImage(img.getImage());*/
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(game);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    public void updateBeat()
    {
        //smt
    }

    public void render(Graphics graphic)
    {
        player.render(graphic);
    }

    @Override
    public void run()
    {
        //ds

    }

}
