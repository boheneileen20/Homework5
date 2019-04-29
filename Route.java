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
    /**
     * The First Location.
     */
    Location loc1;
    /**
     * The Second Location.
     */
    Location loc2;
    /**
     * Info about route is formatted as "color number".
     */
    String requirement;
    
    /**
     * Constructor for Route objects, initializes instance variables.
     * 
     * @param loc1 First location.
     * @param loc2 Second location.
     * @param requirement The color and number of the card.
     */
    public Route(Location loc1, Location loc2, String requirement){
        this.loc1 = loc1;
        this.loc2 = loc2;
        this.requirement = requirement;
    }
    

    /**
     * Returns the requirements ArrayList for this route.
     * 
     * @return String representing the card in theformat "color number".
     */
    public String getRequirement(){
        return requirement;
    }
    
    /**
     * Returns the locations, color, and number of the card.
     * 
     * @return String representing the card info.
     */
    public String toString(){
        String result = loc1.getName() + " to " + loc2.getName();
        result += " for " + requirement;
        return result;
    }
    
    /**
     * Returns the first location of the card.
     * 
     * @return First location.
     */
    public Location getLoc1(){
        return loc1;
    }

    /**
     * Returns the second location of the card.
     * 
     * @return Second location.
     */
    public Location getLoc2(){
        return loc2;
    }
}
