import java.lang.reflect.Array;
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
    public ArrayList <TransportationCard> transHand;
    //the array of destination cards that the user has
    public ArrayList <DestinationCard> destHand;
    //routes the player has claimed
    public ArrayList<Route>  claimed;

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
        claimed = new ArrayList<Route>();
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
     * @param TransportationCard c to add to the player's hand
     */
    public void addToTransHand(TransportationCard c) {
        transHand.add(c);
    }

    public void removeFromTransHand(String color){
        boolean found = false;
        TransportationCard t = null;
        //loop through until card is found and remove
        while(!found) {
            for (int i = 0; i < transHand.size(); i++) {
                if (transHand.get(i).getColor().equalsIgnoreCase(color)) {
                    transHand.remove(i);
                    found = true;
                }
            }
        }
    }
    
    /**
     * Adds a destination card to the player's hand
     * 
     * @param DestinationCard c, the card to add to the player's hand
     */
    public void addToDestHand(DestinationCard c) {
        destHand.add(c);
    }

    public ArrayList<TransportationCard> getTransHand(){
        return transHand;
    }

    public void printTransHand(){
        System.out.print(name + "'s transportation hand: ");
        for(TransportationCard t: transHand){
            System.out.print(t.getColor() + " ");
        }
    }

    public void printDestHand(){
        System.out.print(name + "'s destination hand: ");
        for(DestinationCard t: destHand){
            System.out.print(t.toString() + " ");
        }


    }
    public String getName(){
        return name;
    }

    public int getNumColor(String color){
        int result = 0;
        for(TransportationCard t: transHand){
            if(t.getColor().equalsIgnoreCase(color)){
                result++;
            }
        }
        return result;

    }

    public void addRoute(Route r){
        claimed.add(r);
    }

    public ArrayList<Route> getRoutes(){
        return claimed;
    }

    public void printRoutes(){
        System.out.print(name + "'s claimed routes: ");
        for(Route r: claimed){
            System.out.print(r.toString() + " ");
        }
    }

    public void printStats(){
        System.out.println(name +"'s stats: ");
        System.out.println("Taxis: " + taxis);
        printTransHand();
        System.out.println();
        printDestHand();
        System.out.println();
        printRoutes();
        System.out.println();
    }

    public void deductTaxis(int num){
        taxis = taxis - num; }


        public ArrayList<DestinationCard> getDestHand(){
            return destHand;
        }
    
}
