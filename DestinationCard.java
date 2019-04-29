import java.awt.*;
/**
 * Encapsulates information for transportation cards.
 *
 * @author Eileen Bohen
 * @version Spring 2019
 */
public class DestinationCard
{
    /**
     * The value on the card.
     */
    private int num;
    /**
     * One destination on the card.
     */
    private String dest1;
    /**
     * The other destination on the card.
     */
    private String dest2;
    /**
     * The picture of the card.
     */
    private Image picture;
    
    /**
     * Constructor initializes instance variables.
     * 
     * @param num The value on the card.
     * @param dest1 The first destination.
     * @param dest2 The second destination.
     * @param picture The image of the card.
     */
    public DestinationCard(int num, String dest1, String dest2, Image picture){
        this.num = num;
        this.dest1 = dest1;
        this.dest2 = dest2;
        this.picture = picture;
    }
    
    /**
     * Returns the number of the card.
     * 
     * @return The number on the card.
     */
    public int getNum(){
        return num;
    }
    
    /**
     * Returns the first destination.
     * 
     * @return The first destination.
     */
    public String getDest1(){
        return dest1;
    }
    
    /**
     * Returns the second destination.
     * 
     * @return The second destination.
     */
    public String getDest2(){
        return dest2;
    }
    
    /**
     * Returns the card image.
     * 
     * @return The card image.
     */
    public Image getPicture(){
        return picture;
    }

    /**
     * Returns a sting of card info.
     * 
     * @return The card destinations and points
     */
    public String toString(){
        String message = dest1 + " to " + dest2;
        message += " for " + num + " points.";
        return message;

    }
    
}
