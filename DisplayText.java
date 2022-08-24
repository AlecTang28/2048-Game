    import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * An actor subclass which is used to display text on the screen.
 * @param The text wanted, size of the text, colour of the text.
 * @Alec T. 
 * @16 Mar 2022
 */
public class DisplayText extends Actor
{
    public void setText(String text, int size, Color colour)
    {
        GreenfootImage image = null;
        image = new GreenfootImage(text,size,colour,null);
        this.setImage(image);
    }
}