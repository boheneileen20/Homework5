import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class TTRDriver {
    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<Location> locations = new ArrayList<>();
    /* array of destination cards that make up the deck*/
    private ArrayList<DestinationCard> destCards =  new  ArrayList <  > ();
    /* array of transportation cards with horizontal images*/
    private ArrayList<TransportationCard> transCards =  new  ArrayList <  > ();
    /* array of transportation cards with vertical images, to be used when displaying a player's hand*/
    private ArrayList<TransportationCard> transCardsUpright =  new  ArrayList <  > ();
    /* array of transportation cards that make up the deck*/
    private ArrayList<TransportationCard> displayTransCards =  new  ArrayList <  > ();
    /* Toolkit used for grabbing Images*/
    private Toolkit toolkit;
    private int numPlayers;
    private int playerTurn;


    public TTRDriver(int numPlayers){
        playerTurn = 0;
        this.numPlayers = numPlayers;

        toolkit = Toolkit.getDefaultToolkit();
        //initialize locations
        initLocations();
        //initialize routes

        //initialize card decks
        readCardImages();
        //shuffle transportation cards
        shuffleTransportCards();
        //set up players
        setUpPlayers();
        //deal initial transportation cards
        dealInitialTransCards();
        //place 5 transportation cards face up
        pickDisplayTransCards();
        //shuffle destination cards
        shuffleDestCards();
        //deal initial destination cards to players
        dealInitialDestCards();


    }

    public void setUpPlayers(){
        Scanner s = new Scanner(System.in);
        for(int i = 1; i<=numPlayers; i++){
            System.out.println("Enter player " + i + "'s name: ");
            String name = s.next();
            System.out.println("Enter player " + i + "'s age: ");
            int age = s.nextInt();
            Player p = new Player(name, age);
            players.add(p);
        }
    }

    public void readCardImages(){
        /* read in destination card images and save them in an arraylist*/
        DestinationCard centralChelsea5 =  new  DestinationCard(5, "CENTRAL_PARK", "CHELSEA", toolkit.getImage("C:/Users/patri/Documents/eileen/ttrrestored/src/fwdboardandtransport/central_chelsea_5.jpg"));
        DestinationCard centralChina8 =  new  DestinationCard(8, "CENTRAL_PARK", "CHINATOWN", toolkit.getImage("C:/Users/patri/Documents/eileen/ttrrestored/src/fwdboardandtransport/central_china_8.jpg"));
        DestinationCard centralGramercy4 =  new  DestinationCard(4, "CENTRAL_PARK", "GRAMERCY_PARK", toolkit.getImage("C:/Users/patri/Documents/eileen/ttrrestored/src/fwdboardandtransport/central_gramercy_4.jpg"));
        DestinationCard centralMidtown3 =  new  DestinationCard(3, "CENTRAL_PARK", "MIDTOWN_WEST", toolkit.getImage("C:/Users/patri/Documents/eileen/ttrrestored/src/fwdboardandtransport/central_midtown_3.jpg"));
        DestinationCard chelseaBrooklyn8 =  new  DestinationCard(8, "CHELSEA", "BROOKLYN", toolkit.getImage("C:/Users/patri/Documents/eileen/ttrrestored/src/fwdboardandtransport/chelsea_brooklyn_8.jpg"));
        DestinationCard chelseaWall6 =  new  DestinationCard(6, "CHELSEA", "WALL_STREET", toolkit.getImage("C:/Users/patri/Documents/eileen/ttrrestored/src/fwdboardandtransport/chelsea_wall_6.jpg"));
        DestinationCard chinaGramercy4 =  new  DestinationCard(4, "CHINATOWN", "GRAMERCY_PARK", toolkit.getImage("C:/Users/patri/Documents/eileen/ttrrestored/src/fwdboardandtransport/china_gramercy_4.jpg"));
        DestinationCard eastSoho4 =  new  DestinationCard(4, "EAST_VILLAGE", "SOHO", toolkit.getImage("C:/Users/patri/Documents/eileen/ttrrestored/src/fwdboardandtransport/east_soho_4.jpg"));
        DestinationCard empireBrooklyn7 =  new  DestinationCard(7, "EMPIRE_STATE_BUILDING", "BROOKLYN", toolkit.getImage("C:/Users/patri/Documents/eileen/ttrrestored/src/fwdboardandtransport/empire_brooklyn_7.jpg"));
        DestinationCard empireGreen3 =  new  DestinationCard(3, "EMPIRE_STATE_BUILDING", "GREENWICH_VILLAGE", toolkit.getImage("C:/Users/patri/Documents/eileen/ttrrestored/src/fwdboardandtransport/empire_green_3.jpg"));
        DestinationCard lincolnEmpire3 =  new  DestinationCard(3, "LINCON_CENTER", "EMPIRE_STATE_BUILDING", toolkit.getImage("C:/Users/patri/Documents/eileen/ttrrestored/src/fwdboardandtransport/lincoln_empire_3.jpg"));
        DestinationCard lincolnGreen6 =  new  DestinationCard(6, "LINCON_CENTER", "GREENWICH_VILLAGE", toolkit.getImage("C:/Users/patri/Documents/eileen/ttrrestored/src/fwdboardandtransport/lincoln_geen_6.jpg"));
        DestinationCard lowerEastWall2 =  new  DestinationCard(2, "LOWER_EAST_SIDE", "WALL_STREET", toolkit.getImage("C:/Users/patri/Documents/eileen/ttrrestored/src/fwdboardandtransport/lowerEast_wall_2.jpg"));
        DestinationCard midWestUN3 =  new  DestinationCard(3, "MIDTOWN_WEST", "UNITED_NATIONS", toolkit.getImage("C:/Users/patri/Documents/eileen/ttrrestored/src/fwdboardandtransport/midWest_UN_3.jpg"));
        DestinationCard timesBrooklyn8 =  new  DestinationCard(8, "TIMES_SQUARE", "BROOKLYN", toolkit.getImage("C:/Users/patri/Documents/eileen/ttrrestored/src/fwdboardandtransport/times_brooklyn_8.jpg"));
        DestinationCard timesEast4 =  new  DestinationCard(4, "TIMES_SQUARE", "EAST_VILLAGE", toolkit.getImage("C:/Users/patri/Documents/eileen/ttrrestored/src/fwdboardandtransport/times_east_4.jpg"));
        DestinationCard timesSoho6 =  new  DestinationCard(6, "TIMES_SQUARE", "SOHO", toolkit.getImage("C:/Users/patri/Documents/eileen/ttrrestored/src/fwdboardandtransport/times_soho_6.jpg"));
        DestinationCard UNWall8 =  new  DestinationCard(8, "UNITED_NATIONS", "WALL_STREET", toolkit.getImage("C:/Users/patri/Documents/eileen/ttrrestored/src/fwdboardandtransport/UN_wall_8.jpg"));
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

        /* read in transportation card images and save them in an arraylist*/
        TransportationCard blue1 =  new  TransportationCard("BLUE", toolkit.getImage("C:/Users/patri/Documents/eileen/ttrrestored/src/fwdpieces/blue_1.jpg"));
        TransportationCard blue2 =  new  TransportationCard("BLUE", toolkit.getImage("C:/Users/patri/Documents/eileen/ttrrestored/src/fwdpieces/blue_2.jpg"));
        TransportationCard gray1 =  new  TransportationCard("GRAY", toolkit.getImage("C:/Users/patri/Documents/eileen/ttrrestored/src/fwdpieces/gray_1.jpg"));
        TransportationCard gray2 =  new  TransportationCard("GRAY", toolkit.getImage("C:/Users/patri/Documents/eileen/ttrrestored/src/fwdpieces/gray_2.jpg"));
        TransportationCard green1 =  new  TransportationCard("GREEN", toolkit.getImage("C:/Users/patri/Documents/eileen/ttrrestored/src/fwdpieces/green_1.jpg"));
        TransportationCard green2 =  new  TransportationCard("GREEN", toolkit.getImage("C:/Users/patri/Documents/eileen/ttrrestored/src/fwdpieces/green_2.jpg"));
        TransportationCard orange1 =  new  TransportationCard("ORANGE", toolkit.getImage("C:/Users/patri/Documents/eileen/ttrrestored/src/fwdpieces/orange_1.jpg"));
        TransportationCard orange2 =  new  TransportationCard("ORANGE", toolkit.getImage("C:/Users/patri/Documents/eileen/ttrrestored/src/fwdpieces/orange_2.jpg"));
        TransportationCard pink1 =  new  TransportationCard("ORANGE", toolkit.getImage("C:/Users/patri/Documents/eileen/ttrrestored/src/fwdpieces/orange_1.jpg"));
        TransportationCard pink2 =  new  TransportationCard("ORANGE", toolkit.getImage("C:/Users/patri/Documents/eileen/ttrrestored/src/fwdpieces/orange_2.jpg"));
        TransportationCard rainbow1 =  new  TransportationCard("RAINBOW", toolkit.getImage("C:/Users/patri/Documents/eileen/ttrrestored/src/fwdpieces/rainbow_1.jpg"));
        TransportationCard rainbow2 =  new  TransportationCard("RAINBOW", toolkit.getImage("C:/Users/patri/Documents/eileen/ttrrestored/src/fwdpieces/rainbow_2.jpg"));
        TransportationCard red1 =  new  TransportationCard("RED", toolkit.getImage("C:/Users/patri/Documents/eileen/ttrrestored/src/fwdpieces/red_1.jpg"));
        TransportationCard red2 =  new  TransportationCard("RED", toolkit.getImage("C:/Users/patri/Documents/eileen/ttrrestored/src/fwdpieces/red_2.jpg"));
        /* this arraylist holds the horizontal images*/
        //there are 8 of each of the color cards
        for(int i = 0; i<6; i++) {
            transCards.add(blue1);
            transCards.add(gray1);
            transCards.add(green1);
            transCards.add(orange1);
            transCards.add(pink1);
            transCards.add(red1);
        }
        //add the 6 rainbow cards
        for(int i = 0; i<8; i++) {
            transCards.add(rainbow1);
        }
        /* this arraylist holds the vertical images*/

        transCardsUpright.add(blue2);
        transCardsUpright.add(gray2);
        transCardsUpright.add(green2);
        transCardsUpright.add(orange2);
        transCardsUpright.add(pink2);
        transCardsUpright.add(red2);
        transCardsUpright.add(rainbow2);


    }

    public void shuffleTransportCards(){
        Collections.shuffle(transCards);
    }

    public void dealInitialTransCards(){
        for(Player p: players){
            p.addToTransHand(transCards.get(0));
            p.addToTransHand(transCards.get(1));
            transCards.remove(0);
            transCards.remove(1);
        }
    }

    public ArrayList<Player> getPlayers(){
        return players;
    }

    public ArrayList<TransportationCard> getTransDeck(){
        return transCards;
    }

    public void pickDisplayTransCards(){
        //add any current display cards back into array
        for(int i = 0; i<displayTransCards.size(); i++){
            transCards.add(displayTransCards.get(i));
            displayTransCards.remove(i);
        }
        //keep picking trans cards to display until a valid set is found
        boolean foundSet = false;
        while(!foundSet){
            Collections.shuffle(transCards);
            for(int i = 0; i<5; i++){
                TransportationCard add = transCards.get(i);
                displayTransCards.add(add);
            }
            if(validVisibleTrans()){
                foundSet = true;
                for(int i = 0; i<5; i++){
                    transCards.remove(i);
                }
            }
            else{
                //remove all from display and try again
                //add any current display cards back into array
                for(int i = 0; i<displayTransCards.size(); i++){
                    transCards.add(displayTransCards.get(i));
                    displayTransCards.remove(i);
                }
            }
        }
    }

    public boolean validVisibleTrans(){
        int count = 0;
        for(TransportationCard t: displayTransCards){
            if(t.getColor().equals("RAINBOW")){
                count++;
            }
        }
        if(count<3){
            return true;
        }
        return false;
    }

    public ArrayList<TransportationCard> getDisplayTransCards(){
        return displayTransCards;
    }

    public void shuffleDestCards(){
        Collections.shuffle(destCards);
    }

    public void dealInitialDestCards(){
        Scanner s = new Scanner(System.in);
        for(Player p: players){
            System.out.println("You may pick one or both of these cards: ");
            DestinationCard choice1 = destCards.get(0);
            DestinationCard choice2 = destCards.get(1);
            System.out.println("Choice 1: " + choice1.toString());
            System.out.println("Choice 2: " + choice2.toString());
            System.out.println("Enter \"1\" for choice 1, \"2\" for choice 2, or \"both\" for both");
            String playersChoice = s.next();
            if(playersChoice.equals("1")){
                //add card to hand, remove that card from deck
                p.addToDestHand(choice1);
                //remove then re-add discarded card so that it is at the bottom of the deck
                destCards.remove(1);
                destCards.add(choice2);
                //remove chosen card from deck
                destCards.remove(0);
            }
            else if(playersChoice.equals("2")){
                p.addToDestHand(choice2);
                //remove chosen card from deck
                destCards.remove(1);
                //remove the re-add discarded card
                destCards.remove(0);
                destCards.add(choice1);
            }
            else if(playersChoice.equals("both")){
                p.addToDestHand(choice1);
                p.addToDestHand(choice2);
                destCards.remove(0);
                destCards.remove(1);
            }

        }
    }

    public void turn(){
        //players may either draw a transport card, draw dest cards, or claim route
        Scanner s = new Scanner(System.in);
        System.out.println("It is " + players.get(playerTurn).getName() + "'s turn.");
        System.out.println("Enter 1 to draw transportation cards, 2 to draw destination cards, or 3 to claim a route");
        int turnChoice = s.nextInt();
        if(turnChoice == 1){
            drawTransCards();
        }
        else if(turnChoice == 2){
            drawDestCards();
        }

    }

    public void drawTransCards(){
        //player may make a blind draw or draw from the displayed cards
        //if the player takes a face up rainbow taxi card, they may not take another card that turn
        //player may not take a face up taxi card as their second card either
        System.out.println("Enter \"blind\" to draw from the top of the transportation deck");
        System.out.println("Enter \"face\" to draw a face up card. You may only draw once if you take a taxi card");
        Scanner s = new Scanner(System.in);
        String choice = s.next();

        if(choice.equals("blind")){
            TransportationCard t = transCards.get(0);
            System.out.println("You have drawn a " + t.getColor() + " card.");
            players.get(playerTurn).addToTransHand(t);
            transCards.remove(0);
        }
        else if(choice.equals("face")){
            System.out.println("You may choose from these cards: ");
            for(TransportationCard t: getDisplayTransCards()){
                System.out.print(t.getColor() + " ");
            }
            System.out.println("Enter 1,2,3,4, or 5 to indicate your choice: ");
            int faceChoice = s.nextInt() - 1;
            //add choice to player hand and remove from display, replacing with top card on deck
            TransportationCard t = displayTransCards.get(faceChoice);
            players.get(playerTurn).addToTransHand(t);
            //replace taken card with top card in deck, remove top card from deck
            displayTransCards.set(faceChoice, transCards.get(0));
            transCards.remove(0);

            //check that five cards don't contain more than 3 rainbow cards
            while(!validVisibleTrans()){
                pickDisplayTransCards();
            }

            //if player chose a taxi, return
            if(t.getColor().equals("RAINBOW")){
                System.out.println("Your turn is over");
                return;
            }
        }

    }

    public void drawDestCards(){
        //player chooses 2 destination cards and may keep one or both of them
        //if there is only one card left, they must take that card
        if(destCards.size()>1){
            DestinationCard d = destCards.get(0);
            DestinationCard d2 = destCards.get(1);
            System.out.println("You have drawn these cards: " + d.toString() + " and " + d2.toString());
            System.out.println("Enter 1 to keep the first card, 2 to keep the second card, or \"both\" to keep both cards");
            Scanner s = new Scanner(System.in);
            String choice = s.next();
            if(choice.equals("1")){
                //add to player hand
                players.get(playerTurn).addToDestHand(d);
                //put the discarded card at the bottom of the deck
                destCards.remove(1);
                destCards.add(d2);
                //add the chosen card to the player's hand and remove from deck
                destCards.remove(0);
            }
            else if(choice.equals("2")){
                //add to player hand
                players.get(playerTurn).addToDestHand(d2);
                //remove that card from the deck
                destCards.remove(1);
                //put the discarded card at the bottom of the deck
                destCards.remove(0);
                destCards.add(d);
            }
            else if(choice.equals("both")){
                //add cards to player hand
                players.get(playerTurn).addToDestHand(d);
                players.get(playerTurn).addToDestHand(d2);
                //remove both cards from deck
                destCards.remove(0);
                destCards.remove(1);

            }
        }
        else if(destCards.size() ==1){
            DestinationCard d = destCards.get(0);
            System.out.println("You have drawn this card: " + d.toString());
            //add to player hand and remove from deck
            players.get(playerTurn).addToDestHand(d);
            destCards.remove(0);

        }


    }

    public void initLocations(){
        Location lincolnCenter = new Location("LINCOLN_CENTER", 308, 31);
        Location centralPark = new Location("CENTRAL_PARK", 456, 21);
        Location timesSquare = new Location("TIMES_SQUARE", 392, 159);
        Location midtownWest = new Location("MIDTOWN_WEST", 283,184);
        Location unitedNations = new Location("UNITED_NATIONS", 589, 152);
        Location empireStateBldg = new Location("EMPIRE_STATE_BUILDING", 451, 251);
        Location chelsea = new Location("CHELSEA", 517, 328);
        Location gramercyPark = new Location("GRAMERCY_PARK", 517, 328);
        Location greenwichVillage = new Location("GREENWICH_VILLAGE", 485, 464);
        Location eastVillage = new Location("EAST_VILLAGE", 642, 459);
        Location soho = new Location("SOHO", 392, 594);
        Location lowerEastSide = new Location("LOWER_EAST_SIDE", 623,557);
        Location chinatown = new Location("CHINATOWN", 526, 616);
        Location wallStreet = new Location("WALL_STREET", 480, 723);
        Location brooklyn = new Location("BROOKLYN", 689, 741);

        locations.add(lincolnCenter);
        locations.add(centralPark);
        locations.add(timesSquare);
        locations.add(midtownWest);
        locations.add(unitedNations);
        locations.add(empireStateBldg);
        locations.add(chelsea);
        locations.add(gramercyPark);
        locations.add(greenwichVillage);
        locations.add(eastVillage);
        locations.add(soho);
        locations.add(lowerEastSide);
        locations.add(chinatown);
        locations.add(wallStreet);
        locations.add(brooklyn);

    }

    public void initRoutes(){
        Route lincolnCentral = new Route(locations.get(0), locations.get(1), "orange 2");
        Route lincolnMidtown = new Route(locations.get(0), locations.get(3), "red 2");
        Route lincolnTimes = new Route(locations.get(0), locations.get(2), "green 2");
        Route lincolnTimes2 = new Route(locations.get(0), locations.get(2), "blue 2");
        Route centralTimes = new Route(locations.get(1), locations.get(2), "black 2");
        Route centralTimes2 = new Route(locations.get(1), locations.get(2), "red 2");
        Route centralUN = new Route(locations.get(1), locations.get(4), "pink 3");
        Route midtownChelsea = new Route(locations.get(3), locations.get(6), "blue 2");
        Route midtownEmpire = new Route(locations.get(3), locations.get(5), "black 2");
        Route midtownTimes = new Route(locations.get(3), locations.get(2), "white 1");
        Route timesUN = new Route(locations.get(2), locations.get(4), "white 2");
        Route timesEmpire = new Route(locations.get(1), locations.get(5), "orange 1");
        Route timesEmpire2 = new Route(locations.get(1), locations.get(5), "pink 1");
        Route unEmpire = new Route(locations.get(4), locations.get(5), "black 2");
        Route unGramercy = new Route(locations.get(4), locations.get(7), "green 3");
        Route chelseaEmpire = new Route(locations.get(6), locations.get(5), "white 2");
        Route chelseaEmpire2 = new Route(locations.get(6), locations.get(5), "white 2");
        Route chelseaGramercy = new Route(locations.get(6), locations.get(7), "orange 2");
        Route chelseaGreenwhich = new Route(locations.get(6), locations.get(8), "green 3");




    }

    public static void main(String[] args) {
        System.out.println("Enter the number of players");
        Scanner s = new Scanner(System.in);
        int numPlayers = s.nextInt();
        TTRDriver ttr = new TTRDriver(numPlayers);

        //TEST: is trans card deck set up? YES
        for(TransportationCard t: ttr.getTransDeck()){
            System.out.print(t.getColor() + " ");
        }
        System.out.println();

        //TEST: are trans cards added to hands? YES
        for(Player p: ttr.getPlayers()){
            p.printTransHand();
        }
        System.out.println();

        //TEST: are display trans cards correct? YES
        System.out.println("Face up transportation cards: ");
        for(TransportationCard t: ttr.getDisplayTransCards()){
            System.out.print(t.getColor() + " ");
        }

        System.out.println();

        //TEST: are dest cards added to player's hands?
        for(Player p : ttr.getPlayers()){
            p.printDestHand();
            System.out.println();
        }

    }

}
