import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/*
* This class contains the main method that must be run to play the game. The class contains an
* instance of TTRDriver, which handles much of the game logic and initializes many aspects of the
* game. This class focuses on creating the graphic user interface, using five main panels to
* display information.
*
* The top panel contains a message stating which player's turn it is, as well as a button that
* the user must press to begin their turn.
*
* The left panel contains three panels: one to display the player's transition card hand, one
* to list player stats (which contains a button panel to view destination cards in a pop up
* window), and one that contains an image of the back of the destination card.
*
* The center panel contains the game board.
*
* The right panel contains 6 image panels, 5 of them for the face up transportation cards,
* and one for the face down cards. There are panels in between for the numbers (which are
* used to select a card).
*
* The final panel is on the bottom. As of now it is not used for anything. One idea is to
* implement a button that will bring up the game directions.
*
* @Author Eileen Bohen
* @Version Spring 2019
* */
public class GUITest extends JPanel implements MouseListener {
    /* Create and set up the window.*/
    private JFrame frame =  new  JFrame("Ticket To Ride NY");

    /* Images of the board,box cover,and back of the transport card*/
    private Image coverImage;
    private Image destinationCardBack;
    /* Toolkit used for grabbing Images*/
    private static Toolkit toolkit = Toolkit.getDefaultToolkit();;
    //driver that handles much of the game logic
    private TTRDriver driver;

    //center board panel
    JPanel game;
    //left side panel
    JPanel left;
    //top panel
    JPanel top;
    //bottom panel
    JPanel bottom;
    //right panel
    JPanel right;

//    true if the current player's turn is over
    boolean turnOver;

    /*
    * Constructor for the GUITest class. Begins interaction with user by
    * asking for number of players, taking player names and ages, and creating
    * the Player objects that are needed to run the driver. Deals starting hand to
    * each player as well.
    *
    * Begins actual graphics by creating the frame that the panels sits upon,
    * and sets MouseListener functionality.
    *
    * */
    public GUITest(){

//       ask user to enter the number of players
        int numPlayers = Integer.parseInt(JOptionPane.showInputDialog("How many players?"));
        driver = new TTRDriver(numPlayers);


//        creates as many Player objects as the user specified.
//        FIX THIS: USER SHOULD ONLY BE ABLE TO ENTER 2-5 PLAYERS
        for(int i = 1; i<=numPlayers; i++){
            String name = JOptionPane.showInputDialog("Enter player " + i + "'s name.");
            int age = Integer.parseInt(JOptionPane.showInputDialog("Enter player " + i + "'s age."));
            driver.makePlayer(name, age);
        }


        //deal initial transportation cards
        driver.dealInitialTransCards();


        /* sets size of window*/
        frame.setPreferredSize( new  Dimension(1000, 800));
        //  sets background of frame to white
        frame.setBackground(Color.WHITE);
        /* adds functionality of mouse*/
        addMouseListener(this);


        //  Deals destination cards by offering the first two cards in the
        //  destination card array and allowing the user to choose between
        //  them (1,2, or both).
        for(Player p: driver.getPlayers()){
            ArrayList<DestinationCard> choices = driver.drawTwoDest();
            String playerChoice = JOptionPane.showInputDialog(p.getName() + ", you have drawn these cards: "+ choices.get(0).toString() + " and " +
                    choices.get(1) + " \n" +
                    "Enter \"both\" to take both, \"1\" for the first card, and \"2\" for the second");
            driver.dealInitialDestCardsGUI(playerChoice, choices, p);
        }

        //  sets frame to exit on close
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

    /*
    * paint component method
    * */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

    }

    /*
    * Creates panels by calling methods that create them, then adds them
    * to the frame.
    * */
    public void createAndShowGUI() {

        //  background panel
        JPanel panel = new JPanel();
        panel.setBackground(Color.white);
        panel.setPreferredSize(new Dimension(1000, 800));
        panel.setLayout(new BorderLayout());

        //  center board panel
        game = boardPanel();
        //  left side panel
        left = leftPanel();
        //  top panel
        top = topPanel();
        //  adds the "Start Turn" button to the top panel
        JButton startTurnButton = new JButton("Start Turn");
        //  positions the button on the panel (theoretically. still moves every once in a while)
        top.add(startTurnButton, BorderLayout.EAST);

        //  action listener for the startTurnButton object. when an action
        //  is performed, the completeTurn method is called,
        //  which initiates the process of recording what a
        //  user would like to do on their turn
        startTurnButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                completeTurn();
            }
        });

        //bottom panel
        bottom = bottomPanel();
        //right panel
        right = rightPanel();

        //  adds the 5 information panels to the large blank panel.
        panel.add(game, BorderLayout.CENTER);
        panel.add(right, BorderLayout.EAST);
        panel.add(bottom, BorderLayout.SOUTH);
        panel.add(top, BorderLayout.NORTH);
        panel.add(left, BorderLayout.WEST);

        //  adds the panel to the frame and makes it visible.
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);

    }

    /*
    * Completes a players turn. User can draw transportation cards,
    * destination cards, or claim a route.
    * */
    public void completeTurn(){
        //  asks user what they would like to do on their turn
        int turnChoice = Integer.parseInt(JOptionPane.showInputDialog(frame, "enter 1 to draw trans cards, 2 for dest, 3 to claim route"));

        //handle drawing trans cards
        if(turnChoice == 1){
            drawTrans();

        }
        //handle drawing dest cards
        else if(turnChoice ==2){

            drawDest();
        }
        //handle claiming routes
        else if(turnChoice ==3){
            claimRoute();
        }

        turnOver = true;

        //  set's the driver to the next player's turn
        driver.nextPlayersTurn();
        //  prints a message saying who's up next
        Player p = driver.getPlayers().get(driver.getPlayerTurn());
        JOptionPane.showMessageDialog(frame, p.getName() + " is up next");

        /*
        * This is how I handled refreshing the panels. I don't think this is the best way
        * to do it, but it's the only way I could figure out that works.
        *
        * Creates the 5 information panels again and places them on the frame.
        * */
        //background panel
        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.white);
        panel2.setPreferredSize(new Dimension(1000, 800));
        //  this allows you to use the border layout with 5 regions
        panel2.setLayout(new BorderLayout());


        //center board panel
        game = boardPanel();
        //left side panel
        left = leftPanel();

        //top panel
        top = topPanel();
        //bottom panel
        bottom = bottomPanel();
        //right panel
        right = rightPanel();

        //  adds panels to the frame
        panel2.add(game, BorderLayout.CENTER);
        panel2.add(right, BorderLayout.EAST);
        panel2.add(bottom, BorderLayout.SOUTH);
        panel2.add(top, BorderLayout.NORTH);
        panel2.add(left, BorderLayout.WEST);

        //  adds big panel to the frame
        frame.getContentPane().add(panel2);
        //  revalidates the panels (I'm not sure if this is necessary)
        frame.validate();

        createAndShowGUI();
    }


    /*
    * Helps the completeRoute method by figuring out which route a player would like to claim
    * by taking in two location strings
    *
    * @param    2 Strings holding the name of the location on the map
    * @return   Route object that is the route the player is referring to, or null if nonexistent.
    * */
    public Route findRoute(String loc1, String loc2){
        for(Route r: driver.getRoutes()){
            if(r.getLoc1().getName().equalsIgnoreCase(loc1) || r.getLoc1().getName().equalsIgnoreCase(loc2)){
                if(r.getLoc2().getName().equalsIgnoreCase(loc1) || r.getLoc2().getName().equalsIgnoreCase(loc2)){
                    return r;
                }
            }
        }
        return null;
    }

    /*
    * Called when the user chooses to claim a route. Asks which route they'd like to claim
    * and checks if they're able to claim it. Awards the route if appropriate.
    *
    * */
    public void claimRoute(){
        //  get the current Player object
        Player p = driver.getPlayers().get(driver.getPlayerTurn());

        //ask user to enter the endpoints of the route they'd like to claim
        String location1 = JOptionPane.showInputDialog(frame, "Enter the first endpoint of the route you'd like to claim");
        String location2 = JOptionPane.showInputDialog(frame, "Enter the second endpoint of the route you'd like to claim");

        //figure out which route they want
        Route desired = findRoute(location1, location2);

        //if they entered an invalid route, return without moving to the next player
        if(desired == null){
            JOptionPane.showMessageDialog(frame, "That's not a route!");
            return;
        }

        //check if they have enough taxis to claim the route, return if necessary
        if(!driver.enoughTaxis(desired, p)){
            JOptionPane.showMessageDialog(frame, "You don't have enough taxis to claim this route!");
            return;
        }

        /*
        * If a player has enough taxis, they must state how they'd like to pay for the route.
        * This is done by making a window pop up that contains text fields for each card
        * color. The user enters the number of each color card they'd like to
        * put toward the purchase. If they enter a valid selection of cards, they are given
        * the route.
        * */

        //  text fields for each color card
        JTextField blueNum = new JTextField("0", 5);
        JTextField grayNum = new JTextField("0",5);
        JTextField greenNum = new JTextField("0",5);
        JTextField orangeNum = new JTextField("0",5);
        JTextField pinkNum = new JTextField("0",5);
        JTextField redNum = new JTextField("0",5);
        JTextField rainbowNum = new JTextField("0",5);

        //  panel to hold text fields
        JPanel enterPayment = new JPanel();

        //  add a label to the panel before each textField
        enterPayment.add(new Label("blue:"));
        enterPayment.add(blueNum);

        enterPayment.add(new Label("gray:"));
        enterPayment.add(grayNum);

        enterPayment.add(new Label("green:"));
        enterPayment.add(greenNum);

        enterPayment.add(new Label("orange:"));
        enterPayment.add(orangeNum);

        enterPayment.add(new Label("pink:"));
        enterPayment.add(pinkNum);

        enterPayment.add(new Label("red:"));
        enterPayment.add(redNum);
        enterPayment.add(new Label("rainbow:"));
        enterPayment.add(rainbowNum);

        //  make dialogue box
        JOptionPane.showMessageDialog(frame, enterPayment);

        //  get each of the numbers entered
        int blue = Integer.parseInt(blueNum.getText());
        int gray = Integer.parseInt(grayNum.getText());
        int green = Integer.parseInt(greenNum.getText());
        int orange = Integer.parseInt(orangeNum.getText());
        int pink = Integer.parseInt(pinkNum.getText());
        int red = Integer.parseInt(redNum.getText());
        int rainbow = Integer.parseInt(rainbowNum.getText());

        //  check if the user can pay for the route with the offered cards
        //  if the user cannot pay, deny the transaction without moving on
        //  to the next player
        //  I got this working for 2 players but I need to figure out a good
        //  way to do it for more than 2
        if(!canPay(desired, blue, gray, green, orange, pink, red, rainbow)){
            JOptionPane.showMessageDialog(frame, "Transaction denied.");
            //skip ahead

            //NEEDS FIXIN'
            int playIndex = driver.getPlayers().indexOf(p);
            System.out.println(playIndex);
            driver.setPlayerTurn(playIndex);
        }

        //  NEEDS FIXING: currently prints that the user can claim the route
        //  but needs to actually call appropriate methods to add the route to
        //  the player's possession
        else{
            JOptionPane.showMessageDialog(frame, "You can claim this route!");
        }


    }

    /*
    * Checks if a Player can pay for a route with their offered amounts of cards. Called by claimRoute()
    *
    * @param    the Route they'd like to claim, int values for each color card (their offer)
    * @return   true if the user's card offer can be used to pay for the route
    * */
    public boolean canPay(Route r, int blue, int gray, int green, int orange, int pink, int red, int rainbow){
        //  gets the requirement string from the Route object
        String[] recs = r.getRequirement().split(" ");
        //  parses out the color
        String color = recs[0];
        //  parses out the number of taxis needed to claim the route
        int num = Integer.parseInt(recs[1]);
        //  totals the number of cards the player has offered
        int totalOffered = blue+gray+green+orange+pink+red+rainbow;

        //  if user offered too many or too few cards, deny transaction
        if(totalOffered!=num){
            return false;
        }
        //  else check that colors meet requirements
        else{
            //if it's blue
            if(color.equalsIgnoreCase("blue")){
                //blue+rainbow must equal num
                if(blue+rainbow==num){
                    return true;
                }
            }
            //if it's gray
            else if(color.equalsIgnoreCase("gray")){
                if(gray+rainbow==num){
                    return true;
                }
            }
            else if(color.equalsIgnoreCase("green")){
                if(green+rainbow==num){
                    return true;
                }
            }
            else if(color.equalsIgnoreCase("orange")){
                if(orange+rainbow==num){
                    return true;
                }
            }
            else if(color.equalsIgnoreCase("pink")){
                if(pink+rainbow==num){
                    return true;
                }
            }
            else if(color.equalsIgnoreCase("red")){
                if(red+rainbow==num){
                    return true;
                }
            }
            //if the route is white, they must pay with matching color cards and rainbow cards
            else if(color.equalsIgnoreCase("white")){
                if(blue+rainbow==num){
                    return true;
                }
                else if(gray+rainbow==num){
                    return true;
                }
                else if(green+rainbow==num){
                    return true;
                }
                else if(orange+rainbow==num){
                    return true;
                }
                else if(pink+rainbow==num){
                    return true;
                }
                else if(red+rainbow==num){
                    return true;
                }
            }
            //  if the method has not returned already, the offer did not meet requirements
            return false;
        }

    }

    /*
    * Called when the user chooses to draw a destination card. Offers two cards
    * and allows the user to pick choice 1, 2, or both. Adds to their hand and
    * discards unwanted cards (if any).
    * */
    public void drawDest(){
        //  get current player
        Player p = driver.getPlayers().get(driver.getPlayerTurn());
        //  uses driver to draw two destination cards and saves in an array
        ArrayList<DestinationCard> choices = driver.drawTwoDest();
        //  asks the user to enter "1" to take the first card, "2" for the second, or "both"
        String playerChoice = JOptionPane.showInputDialog(p.getName() + ", you have drawn these cards: "+ choices.get(0).toString() + " and " +
                choices.get(1) + " \n" +
                "Enter \"both\" to take both, \"1\" for the first card, and \"2\" for the second");
        //  deals the cards using the driver
        driver.dealInitialDestCardsGUI(playerChoice, choices, p);
    }

    /*
    * Called when the user chooses to draw transportation cards. User can draw a
    * face up card or a face down card. If the user picks a face up taxi card, their
    * turn is over. They can't pick a face up taxi on their second choice. Adds the
    * selected cards to their hand.
    * */
    public void drawTrans(){
        //  gets user's decision for first card draw
        String tCardChoice = (JOptionPane.showInputDialog(frame, "Enter \"blind\" to draw from the top of the transportation deck" +
                "Enter \"face\" to draw a face card. You may only draw once if you take a taxi card."));

        //  if the user wants a blind draw
        if(tCardChoice.equals("blind")){
            //  gets transportation from top of deck in driver
            TransportationCard t = driver.getTransDeck().get(0);
            //  tells the user what they drew
            JOptionPane.showMessageDialog(frame, "You have drawn a " + t.getColor() + " card.");
            //  adds to the player's hand
            driver.getPlayers().get(driver.getPlayerTurn()).addToTransHand(t);
            //  removes from the transportation card deck
            driver.removeFromTransDeck(0);

            //repaints so that the user's hand will update
            //background panel
            JPanel panel2 = new JPanel();
            panel2.setBackground(Color.white);
            panel2.setPreferredSize(new Dimension(1000, 800));
            panel2.setLayout(new BorderLayout());


            //center board panel
            game = boardPanel();
            //left side panel
            left = leftPanel();

            //top panel
            top = topPanel();
            //bottom panel
            bottom = bottomPanel();
            //right panel
            right = rightPanel();

            panel2.add(game, BorderLayout.CENTER);
            panel2.add(right, BorderLayout.EAST);
            panel2.add(bottom, BorderLayout.SOUTH);
            panel2.add(top, BorderLayout.NORTH);
            panel2.add(left, BorderLayout.WEST);

            frame.getContentPane().add(panel2);
            frame.validate();

        }

        //  face draw
        else if(tCardChoice.equals("face")){
            //  asks the user for a number (this is the card they want to pick up)
            int faceChoice = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter 1,2,3,4, or 5 to draw that face up card."));

            //  get the card to add to the player's hand
            TransportationCard t = driver.getDisplayTransCards().get(faceChoice-1);
            //  add to the player's hand
            driver.getPlayers().get(driver.getPlayerTurn()).addToTransHand(t);

            //  replace taken card with top card in deck, remove top card from deck
            //  uses setDisplayTransCards, which sets an index in the trans cards
            //  array to a specified trans card from the deck (in this case the top card)
            driver.setDisplayTransCards(faceChoice-1, driver.getTransDeck().get(0));
            //  removes that card from the deck since it is now face up
            driver.removeFromTransDeck(0);

            //  check that five cards don't contain more than 3 rainbow cards
            //  re pick cards while they're invalid
            while(!driver.validVisibleTrans()){
                driver.pickDisplayTransCards();
            }

            //  if player chose a taxi, repaint and return
            if(t.getColor().equals("RAINBOW")){

                //background panel
                JPanel panel2 = new JPanel();
                panel2.setBackground(Color.white);
                panel2.setPreferredSize(new Dimension(1000, 800));
                panel2.setLayout(new BorderLayout());


                //center board panel
                game = boardPanel();
                //left side panel
                left = leftPanel();

                //top panel
                top = topPanel();
                //bottom panel
                bottom = bottomPanel();
                //right panel
                right = rightPanel();

                panel2.add(game, BorderLayout.CENTER);
                panel2.add(right, BorderLayout.EAST);
                panel2.add(bottom, BorderLayout.SOUTH);
                panel2.add(top, BorderLayout.NORTH);
                panel2.add(left, BorderLayout.WEST);

                frame.getContentPane().add(panel2);
                frame.validate();
                //createAndShowGUI();
                return;
            }

            /*
            * Here's that block of code again. I'm sure there's a better way to
            * do this, but when I put it in it's own method it stopped working.
            * */
            //repaint everything
            //background panel
            JPanel panel2 = new JPanel();
            panel2.setBackground(Color.white);
            panel2.setPreferredSize(new Dimension(1000, 800));
            panel2.setLayout(new BorderLayout());


            //center board panel
            game = boardPanel();
            //left side panel
            left = leftPanel();

            //top panel
            top = topPanel();
            //bottom panel
            bottom = bottomPanel();
            //right panel
            right = rightPanel();

            panel2.add(game, BorderLayout.CENTER);
            panel2.add(right, BorderLayout.EAST);
            panel2.add(bottom, BorderLayout.SOUTH);
            panel2.add(top, BorderLayout.NORTH);
            panel2.add(left, BorderLayout.WEST);

            frame.getContentPane().add(panel2);
            frame.validate();


        }

        //  repeat the process for the second card
        tCardChoice = (JOptionPane.showInputDialog(frame, "Enter \"blind\" to draw from the top of the transportation deck" +
                "Enter \"face\" to draw a face card. You may not draw a rainbow taxi card."));

        //  process for blind card (could be put in it's own method)
        if(tCardChoice.equals("blind")){
            TransportationCard t = driver.getTransDeck().get(0);
            JOptionPane.showMessageDialog(frame, "You have drawn a " + t.getColor() + " card. Your turn is now over.");
            driver.getPlayers().get(driver.getPlayerTurn()).addToTransHand(t);
            driver.removeFromTransDeck(0);

            //repaint everything
            //background panel
            JPanel panel2 = new JPanel();
            panel2.setBackground(Color.white);
            panel2.setPreferredSize(new Dimension(1000, 800));
            panel2.setLayout(new BorderLayout());


            //center board panel
            game = boardPanel();
            //left side panel
            left = leftPanel();

            //top panel
            top = topPanel();
            //bottom panel
            bottom = bottomPanel();
            //right panel
            right = rightPanel();

            panel2.add(game, BorderLayout.CENTER);
            panel2.add(right, BorderLayout.EAST);
            panel2.add(bottom, BorderLayout.SOUTH);
            panel2.add(top, BorderLayout.NORTH);
            panel2.add(left, BorderLayout.WEST);

            frame.getContentPane().add(panel2);
            frame.validate();

        }

        //   second face draw
        else if(tCardChoice.equals("face")){
            int faceChoice = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter 1,2,3,4, or 5 to draw that face up card."));
            //add choice to player hand and remove from display, replacing with top card on deck
            TransportationCard t = driver.getDisplayTransCards().get(faceChoice-1);
            driver.getPlayers().get(driver.getPlayerTurn()).addToTransHand(t);

            //replace taken card with top card in deck, remove top card from deck
            driver.setDisplayTransCards(faceChoice-1, driver.getTransDeck().get(0));
            driver.removeFromTransDeck(0);

            //check that five cards don't contain more than 3 rainbow cards
            while(!driver.validVisibleTrans()){
                driver.pickDisplayTransCards();
            }

            //if player chose a taxi, return
            /*
            * NEEDS FIXING: (i think) so that the player can choose again if they pick rainbow on the second draw
            * */
            if(t.getColor().equals("RAINBOW")){

                //background panel
                JPanel panel2 = new JPanel();
                panel2.setBackground(Color.white);
                panel2.setPreferredSize(new Dimension(1000, 800));
                panel2.setLayout(new BorderLayout());


                //center board panel
                game = boardPanel();
                //left side panel
                left = leftPanel();

                //top panel
                top = topPanel();
                //bottom panel
                bottom = bottomPanel();
                //right panel
                right = rightPanel();

                panel2.add(game, BorderLayout.CENTER);
                panel2.add(right, BorderLayout.EAST);
                panel2.add(bottom, BorderLayout.SOUTH);
                panel2.add(top, BorderLayout.NORTH);
                panel2.add(left, BorderLayout.WEST);

                frame.getContentPane().add(panel2);
                frame.validate();
                //createAndShowGUI();
            }

            //repaint everything
            //background panel
            JPanel panel2 = new JPanel();
            panel2.setBackground(Color.white);
            panel2.setPreferredSize(new Dimension(1000, 800));
            panel2.setLayout(new BorderLayout());


            //center board panel
            game = boardPanel();
            //left side panel
            left = leftPanel();

            //top panel
            top = topPanel();
            //bottom panel
            bottom = bottomPanel();
            //right panel
            right = rightPanel();

            panel2.add(game, BorderLayout.CENTER);
            panel2.add(right, BorderLayout.EAST);
            panel2.add(bottom, BorderLayout.SOUTH);
            panel2.add(top, BorderLayout.NORTH);
            panel2.add(left, BorderLayout.WEST);

            frame.getContentPane().add(panel2);
            frame.validate();
            //createAndShowGUI();
            return;
        }
    }


    /*
    * Creates the right panel
    *
    * @return   a Panel that holds the transportation cards and their labels, as well as the blind draw pile
    * */
    public JPanel rightPanel(){
        // base panel
        JPanel p = new JPanel();
        p.setSize(new Dimension(50, 800));
        p.setBackground(Color.WHITE);
        p.setLayout(new GridLayout(12,1));

        //  number label
        JLabel firstLabel = new JLabel("1");
        firstLabel.setFont(new Font("Calibri",1, 15));
        firstLabel.setMaximumSize(new Dimension(100, 10));

        //  label with ImageIcon on it
        Image tCard = driver.getDisplayTransCards().get(0).getPicture();
        tCard = tCard.getScaledInstance(200,125, 0);
        JLabel picLabel = new JLabel(new ImageIcon(tCard));

        //number label
        JLabel secondLabel = new JLabel("2");
        secondLabel.setFont(new Font("Calibri",1, 15));
        secondLabel.setMaximumSize(new Dimension(100, 10));

        //  label with ImageIcon on it
        Image tCard2= driver.getDisplayTransCards().get(1).getPicture();
        tCard2 = tCard2.getScaledInstance(200,125, 0);
        JLabel picLabel2 = new JLabel(new ImageIcon(tCard2));

        //number label
        JLabel thirdLabel = new JLabel("3");
        thirdLabel.setFont(new Font("Calibri",1, 15));
        thirdLabel.setMaximumSize(new Dimension(100, 10));

        //  label with ImageIcon on it
        Image tCard3= driver.getDisplayTransCards().get(2).getPicture();
        tCard3 = tCard3.getScaledInstance(200,125, 0);
        JLabel picLabel3 = new JLabel(new ImageIcon(tCard3));

        //number label
        JLabel fourthLabel = new JLabel("4");
        fourthLabel.setFont(new Font("Calibri",1, 15));
        fourthLabel.setMaximumSize(new Dimension(100, 10));

        //  label with ImageIcon on it
        Image tCard4= driver.getDisplayTransCards().get(3).getPicture();
        tCard4 = tCard4.getScaledInstance(200,125, 0);
        JLabel picLabel4 = new JLabel(new ImageIcon(tCard4));

        //number label
        JLabel fifthLabel = new JLabel("5");
        fifthLabel.setFont(new Font("Calibri",1, 15));
        fifthLabel.setMaximumSize(new Dimension(100, 10));

        //  label with ImageIcon on it
        Image tCard5= driver.getDisplayTransCards().get(4).getPicture();
        tCard5 = tCard5.getScaledInstance(200,125, 0);
        JLabel picLabel5 = new JLabel(new ImageIcon(tCard5));

        //  label with ImageIcon on it
        Image blindPile = toolkit.getImage("C:/Users/patri/Documents/eileen/ttrrestored/src/fwdpieces/transportation_back.jpg");
        blindPile = blindPile.getScaledInstance(200,125, 0);
        JLabel blindPileLabel = new JLabel(new ImageIcon(blindPile));

        //  adds labels to panel
        p.add(picLabel);
        p.add(firstLabel);
        p.add(picLabel2);
        p.add(secondLabel);
        p.add(picLabel3);
        p.add(thirdLabel);
        p.add(picLabel4);
        p.add(fourthLabel);
        p.add(picLabel5);
        p.add(fifthLabel);
        p.add(blindPileLabel);

        return p;
    }

    /*
    * Creates the bottom panel (used only for "Ticket to Ride" label currently)
    *
    * @return   a Panel with a label
    * */
    public JPanel bottomPanel(){
        //  make panel
        JPanel p = new JPanel();
        p.setSize(new Dimension(1000, 100));
        //  set color to red
        p.setBackground(Color.RED);
        //  add a panel to hold a text label
        JPanel panel = new JPanel();
        JLabel jLabel = new JLabel("Ticket To Ride NY!");
        jLabel.setFont(new Font("Calibri",1, 15));
        //  add text label to panel
        panel.add(jLabel);
        panel.setBorder(new LineBorder(Color.BLACK));

        //  add panel with text panel to main panel
        p.add(panel);
        return p;
    }

    /*
    * Creates top panel. Holds text label contianing who' turn it is. Button is added in createAndShowGUi()
    *
    * @return Panel with label
    * */
    public JPanel topPanel(){
        JPanel p = new JPanel();
        p.setSize(new Dimension(1000, 100));
        p.setBackground(Color.RED);
        JPanel panel = new JPanel();
        JLabel jLabel = new JLabel("It is " + driver.getPlayers().get(driver.getPlayerTurn()).getName() + "'s turn.");
        jLabel.setFont(new Font("Calibri",1, 15));
        panel.add(jLabel, BorderLayout.WEST);
        panel.setBorder(new LineBorder(Color.BLACK));



        p.add(panel);
        return p;
    }


    /*
    * Creates left side panel for player info:
    *   1)  transportation hand
    *   2)  taxis
    *   3)  button to see destination hand
    *   4)  picture of destination card deck (fae down pile)
    *
    * */
    public JPanel leftPanel(){
        //  main panel
        JPanel p = new JPanel();
        p.setSize(new Dimension(200, 800));
        p.setBackground(Color.BLUE);
        p.setLayout(new GridLayout(5,1));

        //  panel to hold transportation cards
        JPanel transCardHandPanel = new JPanel();
        transCardHandPanel.setSize(new Dimension(200, 500));
        transCardHandPanel.setBackground(Color.WHITE);
        //  creates and sets a grid layout, which allows you to set columns and rows
        transCardHandPanel.setLayout(new GridLayout(8,2));

        //  label for Player name
        JLabel nameLabel = new JLabel(driver.getPlayers().get(driver.getPlayerTurn()).getName() + "'s");
        nameLabel.setFont(new Font("Calibri",1, 15));

        //  label with ImageIcon for card
        Image blue = driver.getUprightTrans().get(0).getPicture();
        blue = blue.getScaledInstance(100,100, 0);
        JLabel bluePile = new JLabel(new ImageIcon(blue));

        //  text label that says "cards". completes the message "player's cards" with previous panel
        JLabel blankLabel = new JLabel("cards");
        blankLabel.setFont(new Font("Calibri",1, 15));

        //label for num blue cards
        JLabel blueLabel = new JLabel("Blues: " + driver.getPlayers().get(driver.getPlayerTurn()).getNumColor("blue"));
        blueLabel.setFont(new Font("Calibri",1, 15));

        //  label with ImageIcon for card
        Image gray = driver.getUprightTrans().get(1).getPicture();
        gray = gray.getScaledInstance(100,100, 0);
        JLabel grayPile = new JLabel(new ImageIcon(gray));

        //label for num gray cards
        JLabel grayLabel = new JLabel("Grays: " + driver.getPlayers().get(driver.getPlayerTurn()).getNumColor("gray"));
        grayLabel.setFont(new Font("Calibri",1, 15));

        //  label with ImageIcon for card
        Image green = driver.getUprightTrans().get(2).getPicture();
        green = green.getScaledInstance(100,100, 0);
        JLabel greenPile = new JLabel(new ImageIcon(green));

        //label for num green cards
        JLabel greenLabel = new JLabel("Greens: " + driver.getPlayers().get(driver.getPlayerTurn()).getNumColor("green"));
        greenLabel.setFont(new Font("Calibri",1, 15));

        //  label with ImageIcon for card
        Image orange = driver.getUprightTrans().get(3).getPicture();
        orange = orange.getScaledInstance(100,100, 0);
        JLabel orangePile = new JLabel(new ImageIcon(orange));

        //label for num orange cards
        JLabel orangeLabel = new JLabel("Oranges: " + driver.getPlayers().get(driver.getPlayerTurn()).getNumColor("orange"));
        orangeLabel.setFont(new Font("Calibri",1, 15));

        //  label with ImageIcon for card
        Image pink = driver.getUprightTrans().get(4).getPicture();
        pink = pink.getScaledInstance(100,100, 0);
        JLabel pinkPile = new JLabel(new ImageIcon(pink));

        //label for num pink cards
        JLabel pinkLabel = new JLabel("Pinks: " + driver.getPlayers().get(driver.getPlayerTurn()).getNumColor("pink"));
        pinkLabel.setFont(new Font("Calibri",1, 15));

        //  label with ImageIcon for card
        Image red = driver.getUprightTrans().get(5).getPicture();
        red = red.getScaledInstance(100,100, 0);
        JLabel redPile = new JLabel(new ImageIcon(red));

        //label for num red cards
        JLabel redLabel = new JLabel("Reds: " + driver.getPlayers().get(driver.getPlayerTurn()).getNumColor("red"));
        redLabel.setFont(new Font("Calibri",1, 15));

        Image rainbow = driver.getUprightTrans().get(6).getPicture();
        rainbow = rainbow.getScaledInstance(100,100, 0);
        JLabel rainbowPile = new JLabel(new ImageIcon(rainbow));

        //label for num rainbow cards
        JLabel rainbowLabel = new JLabel("Rainbows: " + driver.getPlayers().get(driver.getPlayerTurn()).getNumColor("Rainbow"));
        rainbowLabel.setFont(new Font("Calibri",1, 15));

        //  label with ImageIcon for dest draw pile
        Image destDraw = toolkit.getImage("C:/Users/patri/Documents/eileen/ttrrestored/src/fwdboardandtransport/destination_card_back.jpg");
        destDraw = destDraw.getScaledInstance(200,100, 0);
        JLabel destDrawPile = new JLabel(new ImageIcon(destDraw));

        //score panel, contains num taxis, dest card button, and tourist attractions connected to (NEEDS TO BE ADDED: TOURIST ATTRACTIONS)
        JPanel scorePanel = new JPanel();
        scorePanel.setSize(new Dimension(200, 100));
        scorePanel.setBackground(Color.WHITE);
        //  set grid layout
        scorePanel.setLayout(new GridLayout(3,1));

        //  taxis by player are displayed on this panel
        JLabel taxiLabel = new JLabel("Taxis: " + driver.getPlayers().get(driver.getPlayerTurn()).getTaxis());
        taxiLabel.setFont(new Font("Calibri",1, 15));
        scorePanel.add(taxiLabel);
        scorePanel.setBorder(new LineBorder(Color.BLACK));

        //  button to see the player's destination cards
        JButton seeDestButton = new JButton("See destination cards");
        scorePanel.add(seeDestButton);

        //  action listener for destination card button
        seeDestButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {

                //  creates a pop up window listing which destination cards the player has
                String message = "You have these cards:";
                for(DestinationCard d: driver.getPlayers().get(driver.getPlayerTurn()).getDestHand()){
                    message = message + " " + d.toString();
                }
                JOptionPane.showMessageDialog(frame, message);
            }
        });


        //  label for tourist attractions
        //  NEEDS FIXING: maybe make another button and pop up like above
        JLabel touristLabel = new JLabel("Tourist Attractions: ");
        touristLabel.setFont(new Font("Calibri",1, 15));
        scorePanel.add(touristLabel);
        scorePanel.setBorder(new LineBorder(Color.BLACK));


        //  add panels to top panel
        transCardHandPanel.add(nameLabel);
        transCardHandPanel.add(bluePile);
        transCardHandPanel.add(blankLabel);
        transCardHandPanel.add(blueLabel);
        transCardHandPanel.add(grayPile);
        transCardHandPanel.add(greenPile);
        transCardHandPanel.add(grayLabel);
        transCardHandPanel.add(greenLabel);
        transCardHandPanel.add(orangePile);
        transCardHandPanel.add(pinkPile);
        transCardHandPanel.add(orangeLabel);
        transCardHandPanel.add(pinkLabel);

        transCardHandPanel.add(redPile);
        transCardHandPanel.add(rainbowPile);
        transCardHandPanel.add(redLabel);
        transCardHandPanel.add(rainbowLabel);

        //  add all three panels to main panel
        p.add(transCardHandPanel);
        p.add(scorePanel);
        p.add(destDrawPile);

        //  return main panel
        return p;
    }




    /*
    * Creates center panel. Contains a scaled image of the board.
    *
    * @return panel with board image on it
    * */
    public  JPanel boardPanel(){

        JPanel p = new JPanel();
        p.setSize(new Dimension(600,600));
        p.setBackground(Color.GRAY);
        JPanel panel = new JPanel();
        panel.setBorder(new LineBorder(Color.BLACK)); // make it easy to see

        //  add the board image to the panel
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image boardImage = toolkit.getImage("C:/Users/patri/Documents/eileen/ttrrestored/src/fwdboardandtransport/game_board.jpg");

        //  scales image
        boardImage = boardImage.getScaledInstance(650,700, 0);
        JLabel picLabel = new JLabel(new ImageIcon(boardImage));
        panel.add(picLabel);
        panel.repaint();

        p.add(panel);
        return p;
    }

    /**
     * This method makes sure the mouse does nothing when the mouse enters the panel.
     * @param e   the event the mouse triggers
     */
    public void mouseEntered(MouseEvent e)
    {
    }

    /**
     * This method makes sure the mouse does nothing when the mouse exits the panel.
     * @param e   the event the mouse triggers
     */
    public void mouseExited(MouseEvent e)
    {
    }

    /**
     * This method makes sure the mouse does nothing when the mouse is pressed down.
     * @param e   the event the mouse triggers
     */
    public void mousePressed(MouseEvent e)
    {
    }

    /**
     * This method makes sure the mouse does nothing when the mouse button is released.
     * @param e   the event the mouse triggers
     */
    public void mouseReleased(MouseEvent e)
    {
    }

    /**
     * This method makes sure the mouse does nothing when the mouse is moved.
     * @param e   the event the mouse triggers
     */
    public void mouseWheelMoved(MouseWheelEvent e)
    {
    }

    /**
     * This method handles mouse clicking events. This includes clicking "play game" to start the game, and the functionality to interact with the board.
     * @param e   the event the mouse triggers
     */
    public void mouseClicked(MouseEvent e)
    {

    }

    /**
     * Main method to run program
     * @param args command line arguments.
     */
    static public void main(String[] args)
    {
        GUITest t = new GUITest();
        /* Schedule a job for the event-dispatching thread: creating and showing this application's GUI. Unsupported feature in Stride : anonymous class*/
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                t.createAndShowGUI();
            }
        });
    }

}
