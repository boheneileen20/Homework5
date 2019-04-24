import java.util.ArrayList;
/**
 * Class to encapsulate information about the routes between adjacent locations that can be used to build a continuous
 * path between attractions.
 *
 * @author Eileen Bohen
 * @version Spring 2019
 */
public class Route
{
    //location 1
    private String location1;
    //location 2
    private String location2;
    //x coordinate of location 1
    private int loc1x;
    //y coordinate of location 1
    private int loc1y;
    //x coordinate of location 2
    private int loc2x;
    //y coordinate of location 2
    private int loc2y;
    //each string is formatted as "color number"
    //for example, if four pink cards are needed, 
    //the entry is "pink 4"
    String requirement;
    
    /**
     * Constructor for Route objects, initializes instance variables
     * 
     * @param the two locations, their x and y coordinates, and a requirements arraylist of strings representing
     * the cards needed to claim the route.
     */
    public Route(String location1, String location2, int loc1x, int loc1y, int loc2x, int loc2y,String requirements){
        this.location1 = location1;
        this.location2 = location2;
        this.loc1x = loc1x;
        this.loc1y = loc1y;
        this.loc2x = loc2x;
        this.loc2y = loc2y;
        this.requirement = requirement;
    }
    
    /**
     * Returns location 1
     * 
     * @return String location 1
     */
    public String getLocation1(){
        return location1;
    }
    
    /**
     * Returns location 2
     * 
     * @return String location 1
     */
    public String getLocation2(){
        return location2;
    }
    
    /**
     * Returns location 1 x coordinate
     * 
     * @return int location 1 x coordinate
     */
    public int getLocation1x(){
        return loc1x;
    }
    
    /**
     * Returns location 1 y coordinate
     * 
     * @return int location 1 y coordinate
     */
    public int getLocation1y(){
        return loc1y;
    }
    
    /**
     * Returns location 2 x coordinate
     * 
     * @return int location 2 x coordinate
     */
    public int getLocation2x(){
        return loc2x;
    }
    
    /**
     * Returns location 2 y coordinate
     * 
     * @return int location 2 y coordinate
     */
    public int getLocation2y(){
        return loc2y;
    }
    
    /**
     * Returns the requirements ArrayList for this route
     * 
     * @return String representing the requirements
     * Strings have the format "color number" such as "pink 4" if four pink cards are needed
     */
    public String getRequirement(){
        return requirement;
    }
    
    public static void main(String[] args){
        
        Route lincolnCentral = new Route("LINCON_CENTER","CENTRAL_PARK", 0,0,0,0, "2orange 2"); 
        
    }
}
