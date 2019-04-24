import java.util.ArrayList;

/**
 * Class that holds the necessary data for a player in the game.
 *
 * @author Greg MacGown
 * @version 1.0
 */
public class Player
{
    //the player's name
    public String name;
    //the player's age. the oldest player goes first
    public int age;
    //the number of taxis that the player has (15 to begin with)
    public int taxis;
    //the array of transportation cards that the user has
    private int gray = 0;
    private int blue = 0;
    private int orange = 0;
    private int red = 0;
    private int green = 0;
    private int pink = 0;
    private int rainbow = 0;
    public ArrayList <TransportationCard> transHand;
    //the array of destination cards that the user has
    public ArrayList <DestinationCard> destHand;
    //the array of destinations that the player has successfully claimed
    public ArrayList <Destinations> claimed;
    //score keeper
    public int score = 0;

    /**
     * Constructor for player objects. Instantiates name and age to the user inputs, taxis to 15, their hands to empty, 
     * and claimed routes to none.
     * 
     * @param name, the player's name. age, the player's age.
     */
    public Player(String name, int age) {
        this.name = name;
        this.age = age;
        taxis = 15;
        //Initialize hand to empty
        transHand = new ArrayList<TransportationCard>();
        destHand = new ArrayList<DestinationCard>();
        //set claimed routes to none
        claimed = new ArrayList<Destinations>();
    }

    /**
     * Getter method used to check number of taxis/score
     * @return int number of taxis player has/current score
     */

    public int getTaxis(){return taxis;}
    
    public int getScore(){return score;}
    
    /**
     * Methods used to add drawn cards to the player's hand
     * @param TransportationCard c to add to the player's hand
     */
    public void addToTransHand(TransportationCard c) {
        String color = c.getColor();
        if (color.equals("GRAY"))gray++;
        if (color.equals("ORANGE"))orange++;
        if (color.equals("GREEN"))green++;
        if (color.equals("BLUE"))blue++;
        if (color.equals("PINK"))pink++;
        if (color.equals("RED"))red++;
        if (color.equals("RAINBOW"))rainbow++;
        transHand.add(c);
    }
    
    /**
     * Adds a destination card to the player's hand
     * 
     * @param DestinationCard c, the card to add to the player's hand
     */
    public void addToDestHand(DestinationCard c) {
        destHand.add(c);
    }
