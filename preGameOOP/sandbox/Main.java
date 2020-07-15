//package com.marc.test;

public class Main //extends TestAbstract
{
    
    public static void main(String[] args)
    {
        
        /**new Main().instanceMain2();*/

        /**new TestAbstract();*/

    }

    /**
    public void startGame()
    {

    }
     */

    public void callAbstractMethod()
    {
        /**this.startGame();*/
    }

    public void instanceMain2()
    {
        new Main2().print1();
    }

    private class Main2
    {
        public void print1()
        {
            System.out.println("Calling my method inside an inner class");
        }
    }
}