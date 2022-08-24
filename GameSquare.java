import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class GameSquare here.
 * 
 * @author Alec Tang 
 * @version 16 Mar 2022
 */
public class GameSquare extends Actor
{
    //Instance Constants
    
    /* Note: 
     * Since these variables are definied at STATIC they will never change (aka are constant).
     * Normally when we have constant variables like this we name them in ALL CAPS
    */
    //Define some directions to make it easier to reference which way the blocks are moving
    private static final int UP = 0 ;
    private static final int RIGHT = 1;
    private static final int DOWN = 2;
    private static final int LEFT = 3;
    public static boolean spawn;
    public static int points = 0;
    
    //Define a debugging variable (See video linked in assignment outline)
    private final boolean debug = false;
    //Instance Variables    
    private int value;
    
    //Constructor
    /*
     * Constructor for objects of class GameSquare
    */
    public GameSquare()
    {
        this(2);//sends a default value of 2
    }
    
    /*
     * Constructor for objects of the GameSquare class
     * @param The value of the GameSquare
     */
    public GameSquare(int valueIn)
    {
        //put and display a value in the gamesquare
        setValue(valueIn);
        displayValue();
    }
    
    /**
     * Tell the block to move in the given direction until it hits an obstacle
     * 
     * @param The direction in which the block is to move (UP = 0; RIGHT = 1; DOWN = 2; LEFT = 3;
     */
    public void move(int direction) 
    {
        //check if can move
        int movable = canMove(direction);

        //if moveable, start a loop
        while (movable > 0)
        {
            //Get current coordinates
            //ADD CODE
            int x = getX();
            int y = getY();
            
            //Change x and y values to the "new" location based on direction
            //ADD CODE
            if(direction == 1) x += 1;
            else if(direction == 3) x -= 1;
            else if(direction == 0) y -= 1;
            else if(direction == 2) y += 1;
            
            //If Nothing in the way - move the block and allow spawning to occur
            if (movable == 1) {
                setLocation(x,y);
                movable = canMove(direction);
                spawn = true;
                continue;
            }
            
            //Find which block we need to merge with
            //Tell the other block object we wish to merge with it.  If successful, delete this block from the game.
            else {
                //create a list to get objects at a specific location
                List mergeCheck = getWorld().getObjectsAt(x,y,GameSquare.class);
                //get the specific object in the list
                GameSquare block = (GameSquare)mergeCheck.get(0);
                //see if it can merge
                if(merge(block.getValue())) {
                    
                    //increase points on screen
                    points += block.getValue()*2;
                    //remove the extra block after merging
                    getWorld().removeObject(block);    
                    setLocation(x,y);
                    movable = 0;
                    spawn = true;
                    continue;
                    
                } 
                else {
                    movable = 0;
                    continue;
                }
            }
        }
        //can't move, so don't move.
        return;
    }
    
    /**
     * Sets the value of the game square to the value of the input parameter.
     * Will only work if the value is a factor of 2
     * 
     * @param The number to use as the new value
     * @return If the number was set correctly return true, otherwise return false
     */
    
    public boolean setValue(int valueIn)
    {
        if(value%2==0) {
            this.value = valueIn; 
            return true;
        }
        
        else return false;
    }
    
    /**
     * Merge with another block and absorb it's value.
     * Will only work if the two blocks are of the same value
     * 
     * @param The value of the block to be added
     * 
     * @return Return true if the merge is successful.
     */
    public boolean merge(int valueIn)
    {
        int var = getValue();
        //if you merge, double the value of the merged blocks
        if(valueIn == var){
            setValue(value*2);
            displayValue();
            return true;
        }
        else return false;
    }
    
    /**
     * Returns the current value of the gameSquare
     * 
     * @return The current value (int) of the game square
     */
    
    public int getValue()
    {
        return this.value;
    }

    /**
     * Checks to see if the block can move
     * 
     * @return int value representing what is in the space to be moved to.  0 -> Path Blocked, 1 -> Empty Space, int>1 value of block in the space to be moved to.
     */
    private int canMove(int direction)
    {
        //Get World
        GameBoard currentWorld = (GameBoard)getWorld();
        
        //Get x and y values of current object
        int x = getX();
        int y = getY();
        
        //Change x and y values to the "new" location based on direction
        if(direction == 1) x += 1;
        if(direction == 3) x -= 1;
        if(direction == 0) y -= 1;
        if(direction == 2) y += 1;
        //Test for outside border
        if(x<0||x>3||y<0||y>3)return 0;
        
        //Check to see if there is a block in the way
        //create a list that gets objects at a specific location
        List ifEmpty = currentWorld.getObjectsAt(x, y, GameSquare.class);
        //if the spot is not empty, return the value at that position
        if(!ifEmpty.isEmpty()) {
            GameSquare tester = (GameSquare)currentWorld.getObjectsAt(x,y,GameSquare.class).get(0);
            return tester.getValue();
        }
        return 1;
    }
    
    /**
     * displayValue - Displays the current value of a block in an image, then sets that image to the block display image
     */    
    private void displayValue() 
    {
        //Create an image consisting of the display value using built in greenfoot commands
        GreenfootImage displayImage;
        displayImage = new GreenfootImage( Integer.toString(value), 30, Color.BLUE, Color.WHITE);
        //Add the image as the new image for this object
        setImage(displayImage);
        
    }

}