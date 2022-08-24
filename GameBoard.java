import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.ArrayList;

/**
 * This is a simple game where you join together boxes of the same value and try to get to 2048
 * 
 * This assignment is to get you familar with workin in an OO environment and 
 * to get you used to accessing and using java documentation.  Below are two
 * useful links that will help you.
 * 
 * Greenfoot Javadoc:  https://www.greenfoot.org/files/javadoc/
 * Java 11 List Javadoc : https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/List.html
 * 
 * I also talk about access control in the video and you might find this page
 * interesting to check out if you want to know more about different java
 * class types : https://docs.oracle.com/javase/tutorial/java/javaOO/accesscontrol.html
 * 
 * Extra Videos: See the assignment for some extra video lessons that might help
 * 
 * @author A. Tang
 * @version v4 - 16 Mar 2022
 */

public class GameBoard extends World
{
    //Instance Constants
    
    /* Note: 
     * Since these variables are definied at STATIC they will never change (aka are constant).
     * Normally when we have constant variables like this we name them in ALL CAPS
    */
    //Define some directions to make it easier to reference which way the blocks are moving
    private static final int UP = 0;
    private static final int RIGHT = 1;
    private static final int DOWN = 2;
    private static final int LEFT = 3;
    public int addBlock = 0;
    private int counter = 0;
    private List blockNums = new ArrayList();
    DisplayText score = new DisplayText();
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public GameBoard()
    {
        super(4, 4, 50); 
        //populate gameboard with some randomly placed objects
        while (addBlock < 1) {
            if(placeRandomBlock()){
               addBlock++; 
            }
            else continue;
        }   
        //display the score in the top right of the screen
        this.addObject(score,2,0);
        //put the scoreboard in front of the gamesquares
        setPaintOrder(DisplayText.class,GameSquare.class);
        //points set back to 0 every time the game is reset
        GameSquare.points = 0; 
        
        //maunually drawing a grid on the screen
        GreenfootImage line = getBackground();
        line.setColor(Color.CYAN);
        line.drawLine(50,0,50,200);//(x1,y1,x2,y2)
        line.drawLine(100,0,100,200);
        line.drawLine(150,0,150,200);
        line.drawLine(0,50,200,50);
        line.drawLine(0,100,200,100);
        line.drawLine(0,150,200,150);
        
    }
    
    /**
     * Place a block on a random location on the board
     * 
     * @return Returns true if successful, false if not successful
     */
    private boolean placeRandomBlock()
    {
        //Generate Random Location (Hint: Is there anything to do with random values in the apis?)
        GameSquare square = new GameSquare();
        int x = Greenfoot.getRandomNumber(4);
        int y = Greenfoot.getRandomNumber(4);
        //if the location is empty, place the square
        if (getObjectsAt(x, y, GameSquare.class).isEmpty()) {
            addObject(square,x,y);
            return true;
        } else {
            return false;
        }
        //Check to ensure random location is not yet taken, if the spot is free add it to the world
        //(Hint: Is there any way to figure out if an object is somewhere in the world in the apis?)
    }
    
    /**
     * Act - Check for key presses and tell each block to move itsself.
     */
    public void act()
    {
        score.setText("Score: " + GameSquare.points,20, Color.RED);
        End.showScore = GameSquare.points; 
        //Add key press actions here
        String key = Greenfoot.getKey();
        //If a key was pressed...do something
        if (key != null) {
            
            switch(key) {
                case "up":
                    //Tell the blocks to move up
                    //Start checking from the top, then move downwards
                    for (int i =0; i< getWidth(); i++) {
                        for (int j=0; j<getHeight(); j++) {
                            //Get a list containing all of the GameSquare objects at position (i,j)
                            List blockList = getObjectsAt(i,j,GameSquare.class);
            
                            //Move the block in the direction needed
                            if (blockList.size() == 1) { //Error checking - Might want to deal with the case where this isn't true (ie, something went very wrong)
                                //Create a temporary holding space for a generic object
                                Object tempObject;
                
                                //Get the first (and only) entry in the list 
                                tempObject = blockList.get(0);
                                //Create a temporary holding space for the gameSquare object
                                GameSquare tempSquare;
                                //Convert it from the generic "Object" to a GameSquare Object
                                tempSquare = (GameSquare)tempObject;  //Error Discussion: Incompatible Types - See video on this
                                //Then move UP.  
                                tempSquare.move(UP);
                            }
                        }
                    }
                    break;
                
                case "right":
                    for (int i =3; i>=0; i--) {
                        for (int j=0; j<getHeight(); j++) {
                            //Get a list containing all of the GameSquare objects at position (i,j)
                            List blockList = getObjectsAt(i,j,GameSquare.class);
            
                            //Move the block in the direction needed
                            if (blockList.size() == 1) { //Error checking - Might want to deal with the case where this isn't true (ie, something went very wrong)
                                ( (GameSquare)( blockList.get(0) )).move(RIGHT);  
                            }
                        }
                    }
                    break;

                case "down":
                    for (int i = 3; i>=0; i--) {
                        for (int j=3; j>=0; j--) {
                            //Get a list containing all of the GameSquare objects at position (i,j)
                            List blockList = getObjectsAt(j,i,GameSquare.class);
            
                            //Move the block in the direction needed
                            if (blockList.size() == 1) { //Error checking - Might want to deal with the case where this isn't true (ie, something went very wrong)
                                ( (GameSquare)( blockList.get(0) )).move(DOWN);  
                            }
                        }
                    }
                    break;

                case "left":
                    for (int i =0; i< getWidth(); i++) {
                        for (int j=0; j<getHeight(); j++) {
                            //Get a list containing all of the GameSquare objects at position (i,j)
                            List blockList = getObjectsAt(j,i,GameSquare.class);
                            
                            //Move the block in the direction needed
                            if (blockList.size() == 1) { //Error checking - Might want to deal with the case where this isn't true (ie, something went very wrong)
                                ( (GameSquare)( blockList.get(0) )).move(LEFT);  
                            }
                        }
                    }
                    break;

                }

            counter = 0;
            //cycle through every square
            for (int i =0; i<4; i++) {
                for (int j=0; j<4; j++) {
                    //Get a list containing all of the GameSquare objects at position (i,j)
                    List blockList = getObjectsAt(j,i,GameSquare.class);
                    
                    //if there is acutally a block (real blocks are all >= 2), increase counter
                    if(blockList.size() > 0){
                        if(((GameSquare)(blockList.get(0))).getValue() >= 2){
                            counter += 1;
                        }
                    }
                    //once the entire board is filled with squares, go to end scnree
                    if (counter == 15) {
                        if(GameSquare.spawn == false){
                            End endScreen = new End();
                            Greenfoot.setWorld(endScreen);
                            GameSquare.points = 0;
                        }
                    }
                }
            }
            //toggle off block spawning after placing a random block
            while(GameSquare.spawn == true) {
                if (placeRandomBlock()) {
                    GameSquare.spawn = false;
                }   
            }
        }
    }
}