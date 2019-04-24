import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.event.*;
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
    //number of players in simple variable
    public int numPlayers;
    //number used to track current player's turn
    public int currentTurn;
    //Toolkit used for grabbing Images
    private Toolkit toolkit;
    //array of destination cards that make up the deck
    private ArrayList<DestinationCard> destCards = new ArrayList<>();
    //array of transportation cards with horizontal images
    private ArrayList<TransportationCard> transCards = new ArrayList<>();
    //array of transportation cards with vertical images, to be used when displaying a player's hand
    private ArrayList<TransportationCard> transCardsUpright = new ArrayList<>();
    //array of transportation cards that make up the deck
    private ArrayList<TransportationCard> displayTransCards = new ArrayList<>();
    //array of transportation cards in the discard pile
    private ArrayList<TransportationCard> transDisc = new ArrayList<>();
    //array of destination cards in the discard pile
    private ArrayList<DestinationCard> destDisc = new ArrayList<>();

    /**
     * Constructor of Game Driver. Asks the user for player names and ages and creates player objects.
     * 
     * @param playerNum, the number of players in the game. This is entered by the user and can be between 2 and 4,
     * inclusive.
     * 
     */
    public GameDriver(int playerNum){
        //read in destination card images and save them in an arraylist
        DestinationCard centralChelsea5 = new DestinationCard(5, "CENTRAL_PARK", "CHELSEA", toolkit.getImage("fwdboardandtransport/central_chelsea_5.jpg"));
        DestinationCard centralChina8 = new DestinationCard(8, "CENTRAL_PARK", "CHINATOWN", toolkit.getImage("fwdboardandtransport/central_china_8.jpg"));
        DestinationCard centralGramercy4 = new DestinationCard(4, "CENTRAL_PARK", "GRAMERCY_PARK", toolkit.getImage("fwdboardandtransport/central_gramercy_4.jpg"));
        DestinationCard centralMidtown3 = new DestinationCard(3, "CENTRAL_PARK", "MIDTOWN_WEST", toolkit.getImage("fwdboardandtransport/central_midtown_3.jpg"));  
        DestinationCard chelseaBrooklyn8 = new DestinationCard(8, "CHELSEA", "BROOKLYN", toolkit.getImage("fwdboardandtransport/chelsea_brooklyn_8.jpg"));          
        DestinationCard chelseaWall6 = new DestinationCard(6, "CHELSEA", "WALL_STREET", toolkit.getImage("fwdboardandtransport/chelsea_wall_6.jpg"));
        DestinationCard chinaGramercy4 = new DestinationCard(4, "CHINATOWN", "GRAMERCY_PARK", toolkit.getImage("fwdboardandtransport/china_gramercy_4.jpg")); 
        DestinationCard eastSoho4 = new DestinationCard(4, "EAST_VILLAGE", "SOHO", toolkit.getImage("fwdboardandtransport/east_soho_4.jpg"));    
        DestinationCard empireBrooklyn7 = new DestinationCard(7, "EMPIRE_STATE_BUILDING", "BROOKLYN", toolkit.getImage("fwdboardandtransport/empire_brooklyn_7.jpg"));    
        DestinationCard empireGreen3 = new DestinationCard(3, "EMPIRE_STATE_BUILDING", "GREENWICH_VILLAGE", toolkit.getImage("fwdboardandtransport/empire_green_3.jpg"));   
        DestinationCard lincolnEmpire3 = new DestinationCard(3, "LINCON_CENTER", "EMPIRE_STATE_BUILDING", toolkit.getImage("fwdboardandtransport/lincoln_empire_3.jpg"));   
        DestinationCard lincolnGreen6 = new DestinationCard(6, "LINCON_CENTER", "GREENWICH_VILLAGE", toolkit.getImage("fwdboardandtransport/lincoln_geen_6.jpg"));  
        DestinationCard lowerEastWall2 = new DestinationCard(2, "LOWER_EAST_SIDE", "WALL_STREET", toolkit.getImage("fwdboardandtransport/lowerEast_wall_2.jpg"));   
        DestinationCard midWestUN3 = new DestinationCard(3, "MIDTOWN_WEST", "UNITED_NATIONS", toolkit.getImage("fwdboardandtransport/midWest_UN_3.jpg"));  
        DestinationCard timesBrooklyn8 = new DestinationCard(8, "TIMES_SQUARE", "BROOKLYN", toolkit.getImage("fwdboardandtransport/times_brooklyn_8.jpg"));     
        DestinationCard timesEast4 = new DestinationCard(4, "TIMES_SQUARE", "EAST_VILLAGE", toolkit.getImage("fwdboardandtransport/times_east_4.jpg")); 
        DestinationCard timesSoho6 = new DestinationCard(6, "TIMES_SQUARE", "SOHO", toolkit.getImage("fwdboardandtransport/times_soho_6.jpg")); 
        DestinationCard UNWall8 = new DestinationCard(8, "UNITED_NATIONS", "WALL_STREET", toolkit.getImage("fwdboardandtransport/UN_wall_8.jpg"));  

        destCards.add(centralChelsea5);
        destCards.add(centralChina8);
        destCards.add(centralGramercy4);
        destCards.add(centralMidtown3);
        destCards.add(chelseaBrooklyn8);
        destCards.add(chelseaWall6);
        destCards.add(chinaGramercy4);
        destCards.add(eastSoho4);
        destCards.add(empireBrooklyn7);
        destCards.add(empireGreen3);
        destCards.add(lincolnEmpire3);
        destCards.add(lincolnGreen6);
        destCards.add(lowerEastWall2);
        destCards.add(midWestUN3);
        destCards.add(timesBrooklyn8);
        destCards.add(timesEast4);
        destCards.add(timesSoho6);
        destCards.add(UNWall8);

        //read in transportation card images and save them in an arraylist
        TransportationCard blue1 = new TransportationCard("BLUE", toolkit.getImage("fwdpieces/blue_1.jpg"));
        TransportationCard blue2 = new TransportationCard("BLUE", toolkit.getImage("fwdpieces/blue_2.jpg"));
        TransportationCard gray1 = new TransportationCard("GRAY", toolkit.getImage("fwdpieces/gray_1.jpg"));
        TransportationCard gray2 = new TransportationCard("GRAY", toolkit.getImage("fwdpieces/gray_2.jpg"));
        TransportationCard green1 = new TransportationCard("GREEN", toolkit.getImage("fwdpieces/green_1.jpg"));
        TransportationCard green2 = new TransportationCard("GREEN", toolkit.getImage("fwdpieces/green_2.jpg"));
        TransportationCard orange1 = new TransportationCard("ORANGE", toolkit.getImage("fwdpieces/orange_1.jpg"));
        TransportationCard orange2 = new TransportationCard("ORANGE", toolkit.getImage("fwdpieces/orange_2.jpg"));
        TransportationCard pink1 = new TransportationCard("PINK", toolkit.getImage("fwdpieces/orange_1.jpg"));
        TransportationCard pink2 = new TransportationCard("PINK", toolkit.getImage("fwdpieces/orange_2.jpg"));
        TransportationCard rainbow1 = new TransportationCard("RAINBOW", toolkit.getImage("fwdpieces/rainbow_1.jpg"));
        TransportationCard rainbow2 = new TransportationCard("RAINBOW", toolkit.getImage("fwdpieces/rainbow_2.jpg"));
        TransportationCard red1 = new TransportationCard("RED", toolkit.getImage("fwdpieces/red_1.jpg"));
        TransportationCard red2 = new TransportationCard("RED", toolkit.getImage("fwdpieces/red_2.jpg"));

        //this arraylist holds the horizontal images
        transCards.add(blue1);
        transCards.add(gray1);
        transCards.add(green1);
        transCards.add(orange1);
        transCards.add(pink1);
        transCards.add(rainbow1);
        transCards.add(red1);

        //this arraylist holds the vertical images
        transCardsUpright.add(blue2);
        transCardsUpright.add(gray2);
        transCardsUpright.add(green2);
        transCardsUpright.add(orange2);
        transCardsUpright.add(pink2);
        transCardsUpright.add(rainbow2);
        transCardsUpright.add(red2);

        //initial shuffle
        Collections.shuffle(transCards);
        isShuffledWell();

        for(int i = 0; i<6; i++){
            displayTransCards.add(transCards.get(i));
        }

        numPlayers = playerNum;

        JOptionPane jPane = new JOptionPane(); 
        players = new ArrayList<Player>();
        for (int i = 1; i <= playerNum; i++) {
            String name = jPane.showInputDialog("Please enter the player " + i + "'s name");
            int age = Integer.parseInt(jPane.showInputDialog("Please enter the player " + i + "'s age"));
            players.add(new Player(name, age));
        }

        //youngest player goes first
        int min = players.get(0).age;
        for (int i = 1; i < playerNum; i++) {
            if (players.get(i).age < players.get(min).age){
                min = i;
            }
        }
        for (int i = 0; i < min; i++) {
            Player x = players.remove(0);
            players.add(x);
        }

        for (Player p:players) {
            dealDest(p);
        }
        currentTurn = 0;
        init = true;
        play();
    }

    /**
     * Getter methods used to return arraylists for decks
     * @return list representing the appropriate deck
     */
    public ArrayList<DestinationCard> getDestDeck() {
        return destCards;
    }

    public ArrayList<TransportationCard> getTransDeck() {
        return transCards;
    }

    /**
     * Method to draw transportation cards
     */
    public void drawTrans(Player p) {
        //full implementation to be done later

        boolean blind = true; //drawing a card from the pile is a blind draw 
        for (int i = 0; i < 2; i++){
            if (!init) {
                p.addToTransHand(transCards.get(0));
                p.addToTransHand(transCards.get(1));
                //remove from deck
                transCards.remove(0);
                transCards.remove(1);
            }
            else {
                //mouseListener here
                if (!blind) {
                    //if the card is a rainbow card
                    break;
                }
            }
        }
    }

    /**
     * Deals two destination cards to player
     * @param p Player to deal destination cards to
     */
    public void dealDest(Player p){
        //give the player two destination cards
        //deal index 0 and 1 to player by adding to their hand
        DestinationCard first = destCards.get(0);
        DestinationCard second = destCards.get(1);

        boolean firstPick = false;
        boolean secondPick = false;

        if (!firstPick) {
            p.addToDestHand(first);
        }
        else 
        {destDisc.add(first);
        }
        if (!secondPick) {
            p.addToDestHand(second);
        }
        else 
        {destDisc.add(second);
        }
        //remove from deck
        destCards.remove(0);
        destCards.remove(1);

    }

    public void isShuffledWell(){
        boolean shuffledWell = false;
        while(!shuffledWell){
            int taxiCount = 0;
            for(TransportationCard t : transCards){
                if(t.getColor().equals("RAINBOW")){
                    taxiCount++;
                }
            }
            if(taxiCount<3){
                shuffledWell = true;
            }
            else{
                Collections.shuffle(transCards);
            }
        }
    }

    /**
     * Returns the array of players
     * 
     * @return the array of players
     */
    public ArrayList<Player> getPlayers(){
        return players;
    }

    public void play(){
        //begin game loop
        while(!lessThanTwo) {
            //during a player's turn, they choose to either:
            //  1) draw destination cards,
            //  2) draw transportation cards, or
            //  3) claim a route
            //the loop ends when a player has fewer than 3 taxis, and at that point each of the other
            //players has one more turn and the game is over.

            //beginning: pick 3 dest keep 1-3
            //turn: pick 3 more dest cards, or take 2 transport cards, unless you get rainbow face up
            //always 4 transport cards face up, replace every time you take
            //claim route: pay that amount of transport cards

        }
    }
}
