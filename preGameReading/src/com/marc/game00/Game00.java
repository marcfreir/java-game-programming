package com.marc.game00;

import java.util.Random;
import java.util.Scanner;

public class Game00 {

    public static void main(String[] args)
    {
        
        Scanner input = new Scanner(System.in);

        String name;
        String command;

        Random randNum = new Random();
        //Step 01 - Ask for the user name
        System.out.println("Welcome to the Game00 - Enter your name:");
        //Store the answer in the variable name
        name = input.nextLine();
        System.out.println("Greetings, " + name);

        //Step 02 - Ask the direction
        System.out.println("[w] -> Up | [s] -> Down | [a] -> Left | [d] -> Right");
        System.out.println("Which direction do you want to move to? (w, s, a, d)");
        //Store the answer in the variable command
        command = input.nextLine();

        //Validation | Logic
        try
        {
            if (command.equals("w"))
            {
                System.out.println("You are going foward.");
                System.out.println("The enemy appeared. What do you want to do?");
                System.out.println("[a] -> Attack | [r] -> Run Away");
                
                command = input.nextLine();

                if (command.equals("a"))
                {
                    if (randNum.nextInt(100) < 75)
                    {
                        System.out.println("You won the game!");
                    } else
                    {
                        System.out.println("You lost the game!");
                    }
                }
                else if (command.equals("r"))
                {
                    System.out.println("You ran away! The game is over!");
                }
                else
                {
                    System.out.println("Wrong key! - Restart again!");
                }
            }
            else
            {
                if (command.equals("s"))
                {
                    System.out.println("You are going to another map...");
                }
            }
        }
        
        catch (Exception exc)
        {
            System.out.println("Wrong option!");
            exc.printStackTrace();
        }

        input.close();
    }
}