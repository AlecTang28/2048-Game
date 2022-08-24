import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * An end screen that is displayed after the game ends. It displays a background, some text, and the player's final score.  
 * 
 * @Alec T.
 * @16 Mar 2022
 */
public class End extends World
{
    DisplayText gameOver = new DisplayText();
    DisplayText finalScore = new DisplayText();
    
    public static int showScore = 0;
    /**
     * Constructor for objects of class End.
     * 
     */
    public End()
    {    
    super(200,200,1); 
    
    //add text to screen
    this.addObject(gameOver,100,110);
    this.addObject(finalScore,95,165);
    }
    
    public void act(){
        //details of the text
        gameOver.setText("GAME OVER", 40,Color.RED);
        finalScore.setText("Your Score was: " + showScore,23, Color.BLACK); 
    }
}
