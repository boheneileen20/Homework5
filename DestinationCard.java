import java.awt.*;
/**
 * Encapsulates information for transportation cards.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class DestinationCard
{
    //Starter file
    private int num;
    private String dest1;
    private String dest2;
    private Image picture;
    
    public DestinationCard(int num, String dest1, String dest2, Image picture){
        this.num = num;
        this.dest1 = dest1;
        this.dest2 = dest2;
        this.picture = picture;
    }
    
    /**
     * Returns the number of the card
     * 
     * @return the number on the card
     */
    public int getNum(){
        return num;
    }
    
    /**
     * Returns the first destination
     * 
     * @return the first destination
     */
    public String getDest1(){
        return dest1;
    }
    
    /**
     * Returns the second destination
     * 
     * @return the second destination
     */
    public String getDest2(){
        return dest2;
    }
    
    /**
     * Returns the card image
     * 
     * @return the card image
     */
    public Image getPicture(){
        return picture;
    }
    
}
