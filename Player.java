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
    public int taxis;
    public ArrayList <Card> hand;
    public ArrayList <Destinations> claimed;

    public Player(String in) {
        name = in;
        taxis = 15;
        //Initialize hand to empty
        hand = new ArrayList<Card>();
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

    public void addToHand(Card c) {
        hand.add(c);
    }
}
