package com.marc.game01;

public class Main
{
    int[] x = {1,2,3};
    public static void main(String[] args)
    {
        /** 
        Player player = new Player();
        player.beBorn();
        Player playerSecond = new Player();
        playerSecond.beBorn();
        */

        int[] n1 = new int[10];
        n1[0] = 10;

        new Main().methodOne(n1, "Timmy");
    }

    public void methodOne(int[] n1, String name)
    {
        System.out.println(n1[0]);
        System.out.println(name);
    }
}