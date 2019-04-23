import java.awt.*;
/**
 * Write a description of class TransportationCard here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class TransportationCard
{
    private String color;
    private Image picture;
    
    public TransportationCard(String color, Image picture){
        this.color = color;
        this.picture = picture;
    }
    
    /**
     * Returns the color of the card
     * 
     * @return String representing the color of the card
     */
    public String getColor(){
        return color;
    }
    
    /**
     * Returns the picture of the card
     * 
     * @return Image that is the picture of the card
     */
    public Image getPicture(){
        return picture;
    }
}
