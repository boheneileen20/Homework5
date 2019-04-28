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
     * the value on the card
     */
    private int num;
    /**
     * one destination on the card
     */
    private String d1;
    /**
     * the other destination on the card
     */
    private String d2;
    /**
     * the picture of the card
     */
    private Image picture;
    /**
     * Constructor for initializing instance variables.
     * 
     * @param num The value on the card.
     * @param d1 The first destination.
     * @param d2 The second destination.
     * @param picture The image of the card.
     */
    public DestinationCard(int num, String d1, String d2, Image pic){
        this.num = num;
        this.d1 = d1;
        this.d2 = d2;
        this.picture = pic;
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
        return d1;
    }

    /**
     * Returns the second destination.
     * 
     * @return The second destination.
     */
    public String getDest2(){
        return d2;
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
     * String of the desination card.
     * 
     * @return Information on the destination card.
     */
    public String toString(){
        String message = d1 + " to " + d2 + 
        " for " + num + " points.";
        return message;

    }
}
