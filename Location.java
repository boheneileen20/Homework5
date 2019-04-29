/**
 * Determines route name, coordinates, and attraction.
 *
 * @author Eileen Bohen
 * @version Spring 2019
 */
public class Location {
    /**
     * Name of the route
     */
    String name;
    /**
     * X coordinate of route.
     */
    int xCoord;
    /**
     * Y coordinate of route.
     */
    int yCoord;
    /**
     * Whether or not route is attraction.
     */
    boolean attraction;

    /**
     * Constructor for Route objects, initializes instance variables.
     * 
     * @param name Name of route .
     * @param xCoord X coordinate of route.
     * @param yCoord Y coordinate of route.
     * @param isTouristAttraction Whether or not route is tourist attraction.
     */
    public Location(String name, int xCoord, int yCoord,boolean isTouristAttraction){
        this.name = name;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        attraction = isTouristAttraction;
    }

    /**
     * Returns name of Route as String.
     * 
     * @return The name of the route.
     */
    public String getName(){
        return name;
    }
    
    /**
     * Returns whether or not route is tourist attraction
     * 
     * @return True if route is attraction, false otherwise.
     */
    public boolean isAttraction() { 
        return attraction;
    }
}
