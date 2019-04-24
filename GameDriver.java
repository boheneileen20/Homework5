import javax.swing.*;
import java.util.*;
/**
 * Class which runs the game with no graphics implementation
 *
 * @author Eileen Bohen
 * @version Spring 2019
 */
public class GameDriver
{
     //boolean to check for last turns
    public boolean lessThanTwo = false;
    //boolean to make setup simpler
    public boolean init = false;
    //list of player objects
    public ArrayList <Player> players;
    
    /**
     * Constructor of Game Driver. Asks the user for player names and ages and creates player objects.
     * 
     * @param playerNum, the number of players in the game. This is entered by the user and can be between 2 and 4,
     * inclusive.
     * 
     */
    public GameDriver(int playerNum){
        JOptionPane jPane = new JOptionPane(); 
        players = new ArrayList<Player>();
        for (int i = 1; i <= playerNum; i++) {
            String name = jPane.showInputDialog("Please enter the player " + i + "'s name");
            int age = Integer.parseInt(jPane.showInputDialog("Please enter the player " + i + "'s age"));
            players.add(new Player(name, age));
        }
        init = true;
    }

    /**
     * Method to draw transportation cards
     */
    public void drawTrans() {
        //full implementation to be done later

        boolean blind = true; //drawing a card from the pile is a blind draw 
        for (int i = 0; i < 2; i++){
            if (!init) return; //draw two blind cards
            else {
                //implement mouse listening for coordinates to click on cards
                //on board or the trans deck
                if (!blind) {
                    //if the card is a rainbow card
                    break;
                }
            }
        }
    }
    
    
    /**
     * Method to draw destination cards
     */
    public void drawDest() {
        //full implementation to be done later
        
        //Draw two cards, then allow user to either keep both cards or discard one of them
    }
    
    public static void main(String[] args){
        
    }
}
