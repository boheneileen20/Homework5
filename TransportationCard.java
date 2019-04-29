import java.awt.*;
/**
 * Encapsulates information for a single transportation card.
 *
 * @author Eileen Bohen
 * @version Spring 2019
 */
public class TransportationCard
{
    /**
     * The color of the transportation card.
     */
    private String color;
    /**
     * The picture of the card.
     */
    private Image picture;
    
    /**
     * Constructor for TransportationCard objects. Initializes instance variables.
     * 
     * @param color The color of the card. 
     * @param picture The Image of the card.
     */
    public TransportationCard(String color, Image picture){
        this.color = color;
        this.picture = picture;
    }
    
    /**
     * Returns the color of the card.
     * 
     * @return String representing the color of the card.
     */
    public String getColor(){
        return color;
    }
    
    /**
     * Returns the picture of the card.
     * 
     * @return Image that is the picture of the card.
     */
    public Image getPicture(){
        return picture;
    }
}
