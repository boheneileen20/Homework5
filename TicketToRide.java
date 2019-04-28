import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.event.*;
import java.util.*;
import java.awt.geom.Ellipse2D;

/**
 * Graphic version of the New York Ticket to Ride Game and contains the main method to start.
 * 
 * <!--
 * NOTES:
 * 15 taxis per user (60 total, 15 of each color)
 * 44 transport cards (8 rainbow taxi)
 * 18 destination cards
 * if a player has less than 3 taxis, other players get one more turn, and game is over
 * score 
 * -->
 * @author Eileen Bohen
 * @version Spring 2019
 */
public class TicketToRide extends JPanel implements MouseListener
{ 
    /**
     * width of display
     */
    private int width;
    /**
     * height of display
     */
    private int height;
    /**
     * Image of front of transport card
     */
    private Image coverImage;
    /**
     * Image of game board
     */
    private Image boardImage;
    /**
     * Image of back of transport card
     */
    private Image destinationCardBack;
    /**
     * Toolkit used for grabbing Images
     */
    private Toolkit toolkit;
    /**
     * Rectangle that transportation card pile will be placed on
     */
    private Rectangle transportationCardPile = new Rectangle(100,100,100,100);
    /**
     * Rectangle that the words "play game" will be printed on
     */
    private Rectangle playGame;
    /**
     * array of destination cards that make up the deck
     */
    private ArrayList<DestinationCard> destCards = new ArrayList<>();
    /**
     * array of transportation cards with horizontal images
     */
    private ArrayList<TransportationCard> transCards = new ArrayList<>();
    /**
     * array of transportation cards with vertical images, to be used when displaying a player's hand
     */
    private ArrayList<TransportationCard> transCardsUpright = new ArrayList<>();
    /**
     * array of transportation cards that make up the deck
     */
    private ArrayList<TransportationCard> displayTransCards = new ArrayList<>();
    /**
     * variable to keep track of whether the game has started
     */
    private boolean inGame;
    /**
     * the number of players in the game
     */
    private int numPlayers;
    /**
     * the game driver object
     */
    private GameDriver gd;
    /**
     * list of players in the game
     */
    private ArrayList<Player> players = new ArrayList<>();
    /**
     * rectangles for desination cards
     */
    Rectangle destPile = new Rectangle(0, 630, 200, 90);
    /**
     * rectangles for transporation cards
     */
    Rectangle drawPile = new Rectangle(750, 665, 250, 133);
    /**
     * rectangles for first transporation card on table
     */
    Rectangle card1 = new Rectangle(750, 0, 250, 133);
    /**
     * rectangles for second transporation card on table
     */
    Rectangle card2 = new Rectangle(750, 133, 250, 133);
    /**
     * rectangles for third transporation card on table
     */
    Rectangle card3 = new Rectangle(750, 266, 250, 133);
    /**
     * rectangles for fourth transporation card on table
     */
    Rectangle card4 = new Rectangle(750, 399, 250, 133);
    /**
     * rectangles for fifth transporation card on table
     */
    Rectangle card5 = new Rectangle(750, 532, 250, 133);
    /**
     * Constructor for objects of class TicketToRide to set up the viewing window and instantiates images and array lists.
     * 
     */
    public TicketToRide() {
        inGame = false;
        //sets size of window
        setPreferredSize(new Dimension(1000, 800));
        toolkit = Toolkit.getDefaultToolkit();
        //sets graphics of window
        boardImage = toolkit.getImage("fwdboardandtransport/game_board.jpg");
        setBackground(Color.WHITE);
        width = getPreferredSize().width;
        height = getPreferredSize().height;
        //adds functionality of mouse
        addMouseListener( this );

        //instantiate instance variable images
        destinationCardBack = toolkit.getImage("fwdboardandtransport/destination_card_back.jpg");

        //get board cover image
        coverImage = toolkit.getImage("fwdpieces/box_cover.jpg");
        playGame = new Rectangle(400, 375, 200,50);

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
        TransportationCard pink1 = new TransportationCard("ORANGE", toolkit.getImage("fwdpieces/orange_1.jpg"));
        TransportationCard pink2 = new TransportationCard("ORANGE", toolkit.getImage("fwdpieces/orange_2.jpg"));
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

        //shuffle transportation cards and pick five to display
        //if there are three of more rainbow taxi cards in these five,
        //re-shuffle
        Collections.shuffle(transCards);
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

        for(int i = 0; i<6; i++){
            displayTransCards.add(transCards.get(i));
        }

    }

    /**
     * The cover image is displayed with a button "play game" to begin, otherwise the game has started.
     *
     * @param  g,  the Graphics object for this applet
     */
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if(inGame == false){
            //display cover
            g.drawImage(coverImage, 0,0, width, height, this);
            //add "PLAY GAME" button
            ((Graphics2D)g).fill(playGame);
            //add "PLAY GAME" text
            g.setColor(Color.YELLOW);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 32));
            g.drawString("PLAY GAME", 410, 408);

        }

        else{
            //draw board such that there is white space on both sides
            g.drawImage(boardImage, 200, 0, 750, 800, 0,0, 3337, 5048,this);
            //add pile of transportation cards
            g.setColor(Color.BLACK);
            g.fillRect(750, 0, 250, 133);
            g.fillRect(750, 133, 250, 133);
            g.fillRect(750, 266, 250, 133);
            g.fillRect(750, 399, 250, 133);
            g.fillRect(750, 532, 250, 133);
            g.fillRect(750, 665, 250, 133);

            //draw 5 displayed transport cards
            g.drawImage(displayTransCards.get(0).getPicture(), 750, 0, 1000, 133, 0,0, 769, 504, this);
            g.drawImage(displayTransCards.get(1).getPicture(), 750, 133, 1000, 266, 0,0, 769, 504, this);
            g.drawImage(displayTransCards.get(2).getPicture(), 750, 266, 1000, 399, 0,0, 769, 504, this);
            g.drawImage(displayTransCards.get(3).getPicture(), 750, 399, 1000, 532, 0,0, 769, 504, this);
            g.drawImage(displayTransCards.get(4).getPicture(), 750, 532, 1000, 665, 0,0, 769, 504, this);

            //add pile of destination cards
            g.fillRect(0, 630, 200, 90);
            g.drawImage(destinationCardBack, 750, 665, 1000, 798, 0, 0, 769, 504, this);

            //add taxis
            //g.drawString("Taxis: " + String.valueOf(getCurrentPlayer().getTaxis()), 20, 730);
            //add player scores
            //g.drawString("Score: " + String.valueOf(getCurrentPlayer().getScore()), 20, 760);

            //add player hand
            g.drawImage(toolkit.getImage("fwdpieces/blue_1.jpg"), 0, 0, 200, 90, 0,0, 769, 504, this);
            g.drawImage(toolkit.getImage("fwdpieces/gray_1.jpg"), 0, 90, 200, 180, 0,0, 769, 504, this);
            g.drawImage(toolkit.getImage("fwdpieces/green_1.jpg"), 0, 180, 200, 270, 0,0, 769, 504, this);
            g.drawImage(toolkit.getImage("fwdpieces/orange_1.jpg"), 0, 270, 200, 360, 0,0, 769, 504, this);
            g.drawImage(toolkit.getImage("fwdpieces/pink_1.jpg"), 0, 360, 200, 450, 0,0, 769, 504, this);
            g.drawImage(toolkit.getImage("fwdpieces/rainbow_1.jpg"), 0, 450, 200, 540, 0,0, 769, 504, this);
            g.drawImage(toolkit.getImage("fwdpieces/red_1.jpg"), 0, 540, 200, 630, 0,0, 769, 504, this);
            g.drawImage(destinationCardBack, 0, 630, 200, 720, 0, 0, 769, 504, this);

            //draw amount of card for each type
            Ellipse2D.Double circ1 = new Ellipse2D.Double(170, 30, 30, 30);
            ((Graphics2D)g).fill(circ1);
            Ellipse2D.Double circ2 = new Ellipse2D.Double(170, 120, 30, 30);
            ((Graphics2D)g).fill(circ2);
            Ellipse2D.Double circ3 = new Ellipse2D.Double(170, 210, 30, 30);
            ((Graphics2D)g).fill(circ3);
            Ellipse2D.Double circ4 = new Ellipse2D.Double(170, 300, 30, 30);
            ((Graphics2D)g).fill(circ4);
            Ellipse2D.Double circ5 = new Ellipse2D.Double(170, 390, 30, 30);
            ((Graphics2D)g).fill(circ5);
            Ellipse2D.Double circ6 = new Ellipse2D.Double(170, 480, 30, 30);
            ((Graphics2D)g).fill(circ6);
            Ellipse2D.Double circ7 = new Ellipse2D.Double(170, 570, 30, 30);
            ((Graphics2D)g).fill(circ7);
            //g.fillCircle(0,0,0,0);
        }
    }

    /**
     * Creates the window and GUI with a label
     *
     */
    public static void createAndShowGUI(){
        //Create and set up the window.
        JFrame frame = new JFrame("Ticket To Ride NY");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add label
        TicketToRide panel = new TicketToRide();
        frame.getContentPane().add(panel);

        //Display the window.
        frame.pack();
        frame.setVisible(true);

    }

    /**
     * Deals the beginning 2 transportation cards to each player. Called once at the beginning of the game. 
     * 
     */
    public void dealDest(){
        //loop over each player, giving them two cards
        for(Player p : players){
            //deal index 0 and 1 to player by adding to their hand
            TransportationCard first = transCards.get(0);
            TransportationCard second = transCards.get(1);
            //add to hand
            p.addToTransHand(first);
            p.addToTransHand(second);
            //remove from deck
            transCards.remove(0);
            transCards.remove(1);
        }
    }

    /**
     * This method makes sure the mouse does nothing when the mouse enters the panel.
     * 
     * @param  MouseEvent e, the event the mouse triggers
     * 
     */
    public void mouseEntered( MouseEvent e ) { }

    /**
     * This method makes sure the mouse does nothing when the mouse exits the panel.
     * 
     * @param MouseEvent e, the event the mouse triggers
     * 
     */
    public void mouseExited( MouseEvent e ) { }

    /**
     * This method makes sure the mouse does nothing when the mouse is pressed down.
     * 
     * @param MouseEvent e, the event the mouse triggers
     * 
     */
    public void mousePressed( MouseEvent e ) { }

    /**
     * This method makes sure the mouse does nothing when the mouse button is released.
     * 
     * @param MouseEvent e, the event the mouse triggers
     * 
     */
    public void mouseReleased( MouseEvent e ) { }

    /**
     * This method makes sure the mouse does nothing when the mouse is moved.
     * 
     * @param MouseEvent e, the event the mouse triggers
     * 
     */
    public void mouseWheelMoved( MouseWheelEvent e ){ }

    /**
     * This method handles clicking "play game" to start the game, and interacting with the board.
     * 
     * @param MouseEvent e, the event the mouse triggers
     * 
     */
    public void mouseClicked( MouseEvent e ) {
        //handle clicking on opening screen
        if(inGame == false){
            if(playGame.contains(e.getPoint())){
                //ask user to enter the number of players
                JOptionPane jPane = new JOptionPane("");
                numPlayers = Integer.parseInt(jPane.showInputDialog("Enter number of players (2, 3, or 4)"));
                if(numPlayers >1 && numPlayers<5){
                    //since a valid number of players has been entered, the game can start
                    inGame = true;
                    gd = new GameDriver(numPlayers);
                    players = gd.getPlayers();
                    repaint();
                }
                else{
                    JOptionPane.showMessageDialog(null, "You have entered an invalid number of players.");
                }
            }
        }
        else{
            if (destPile.contains(e.getPoint())) {
                System.out.println("Destination pile clicked");
            }
            if (drawPile.contains(e.getPoint())) {
                System.out.println("Draw pile clicked");
            }
            if (card1.contains(e.getPoint())) {
                System.out.println("Card 1 clicked");
            }
            if (card2.contains(e.getPoint())) {
                System.out.println("Card 2 clicked");
            }
            if (card3.contains(e.getPoint())) {
                System.out.println("Card 3 clicked");
            }
            if (card4.contains(e.getPoint())) {
                System.out.println("Card 4 clicked");
            }
            if (card5.contains(e.getPoint())) {
                System.out.println("Card 5 clicked");
            }
        }

    }

    /**
     * Main method to run program
     *
     * @param String[] args, command line arguments. 
     */
    public static void main(String[] args) {

        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
                /**
                 * creates the user interface
                 */
                public void run() {
                    createAndShowGUI();
                }
            });
    }

}
