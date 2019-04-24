import java.util.ArrayList;

/**
 * Class that holds the necessary data for a player in the game.
 *
 * @author Greg MacGown
 * @version 1.0
 */
public class Player
{
    public String name;
    public int age;
    public int taxis;
    public ArrayList <TransportationCard> transHand;
    public ArrayList <DestinationCard> destHand;
    public ArrayList <Destinations> claimed;

    public Player(String in, int age) {
        name = in;
        this.age = age;
        taxis = 15;
        //Initialize hand to empty
        transHand = new ArrayList<TransportationCard>();
        destHand = new ArrayList<DestinationCard>();
        //set claimed routes to none
        claimed = new ArrayList<Destinations>();
    }

    /**
     * Getter method used to check number of taxis
     * @return int number of taxis player has
     */

    public int getTaxis(){
        return taxis;
    }
    
    
    /**
     * Methods used to add drawn cards to the player's hand
     * @param c Card to add to player's hand
     */
    public void addToTransHand(TransportationCard c) {
        transHand.add(c);
    }
    
    public void addToDestHand(DestinationCard c) {
        destHand.add(c);
    }
}
