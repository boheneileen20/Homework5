import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;

/**
 * Class to create and access panels needed for interface
 */
public class GUI extends JPanel implements MouseListener {
    /* Create and set up the window.*/
    private static JFrame frame =  new  JFrame("Ticket To Ride NY");
    //board panel
    private static JPanel gameBoardPanel;

    /* width and height of display*/
    private int width;
    private int height;
    /* Images of the board,box cover,and back of the transport card*/
    private Image coverImage;
    private Image destinationCardBack;
    /* Toolkit used for grabbing Images*/
    private static Toolkit toolkit = Toolkit.getDefaultToolkit();;
    private static TTRDriver driver;
    //private static Image boardImage;

    public GUI(){

        driver = new TTRDriver(2);
        /* sets size of window*/
        frame.setPreferredSize( new  Dimension(1000, 800));

        frame.setBackground(Color.WHITE);
       // width = getPreferredSize().width;
        //height = getPreferredSize().height;
        /* adds functionality of mouse*/
        addMouseListener(this);

        //get board panel
        gameBoardPanel = boardPanel();


    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    /**
     * Creates the window and GUI with a label
     */
    static public void createAndShowGUI()
    {
        GUI thegui = new GUI();
        JFrame frame = new JFrame("GUITest");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //background panel
        JPanel panel = new JPanel();
        panel.setBackground(Color.white);
        panel.setPreferredSize(new Dimension(1000, 800));
        panel.setLayout(new BorderLayout());

        //center board panel
        JPanel game = boardPanel();
        //left side panel
        JPanel left = leftPanel();
        //top panel
        JPanel top = topPanel();
        //bottom panel
        JPanel bottom = bottomPanel();
        //right panel
        JPanel right = rightPanel();

        panel.add(game, BorderLayout.CENTER);
        panel.add(right, BorderLayout.EAST);
        panel.add(bottom, BorderLayout.SOUTH);
        panel.add(top, BorderLayout.NORTH);
        panel.add(left, BorderLayout.WEST);

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    //creates right panel, hold face up transport cards and blind draw pile.
    public static JPanel rightPanel(){
        JPanel p = new JPanel();
        p.setSize(new Dimension(100, 800));
        p.setBackground(Color.BLUE);
        p.setLayout(new GridLayout(6,1));

        Image tCard = driver.getDisplayTransCards().get(0).getPicture();
        tCard = tCard.getScaledInstance(200,125, 0);
        JLabel picLabel = new JLabel(new ImageIcon(tCard));

        Image tCard2= driver.getDisplayTransCards().get(1).getPicture();
        tCard2 = tCard2.getScaledInstance(200,125, 0);
        JLabel picLabel2 = new JLabel(new ImageIcon(tCard2));

        Image tCard3= driver.getDisplayTransCards().get(2).getPicture();
        tCard3 = tCard3.getScaledInstance(200,125, 0);
        JLabel picLabel3 = new JLabel(new ImageIcon(tCard3));

        Image tCard4= driver.getDisplayTransCards().get(3).getPicture();
        tCard4 = tCard4.getScaledInstance(200,125, 0);
        JLabel picLabel4 = new JLabel(new ImageIcon(tCard4));

        Image tCard5= driver.getDisplayTransCards().get(4).getPicture();
        tCard5 = tCard5.getScaledInstance(200,125, 0);
        JLabel picLabel5 = new JLabel(new ImageIcon(tCard5));


        Image blindPile = toolkit.getImage("C:/Users/patri/Documents/eileen/ttrrestored/src/fwdpieces/transportation_back.jpg");
        blindPile = blindPile.getScaledInstance(200,125, 0);
        JLabel blindPileLabel = new JLabel(new ImageIcon(blindPile));

        p.add(picLabel);
        p.add(picLabel2);
        p.add(picLabel3);
        p.add(picLabel4);
        p.add(picLabel5);
        p.add(blindPileLabel);

        return p;
    }
    //creates bottom panel
    public static JPanel bottomPanel(){
        JPanel p = new JPanel();
        p.setSize(new Dimension(1000, 100));
        p.setBackground(Color.RED);
        JPanel panel = new JPanel();
        JLabel jLabel = new JLabel("This will give show score components (taxis, routes claimed)");
        jLabel.setFont(new Font("Calibri",1, 15));
        panel.add(jLabel);
        panel.setBorder(new LineBorder(Color.BLACK));

        p.add(panel);
        return p;
    }

    //creates top panel
    public static JPanel topPanel(){
        JPanel p = new JPanel();
        p.setSize(new Dimension(1000, 100));
        p.setBackground(Color.RED);
        JPanel panel = new JPanel();
        JLabel jLabel = new JLabel("This will give some instruction on who's turn it is");
        jLabel.setFont(new Font("Calibri",1, 15));
        panel.add(jLabel);
        panel.setBorder(new LineBorder(Color.BLACK));

        p.add(panel);
        return p;
    }
    /*creates left side panel for player info,
            1)num taxis
            2)transportation hand
            3)destination hand
        also pile of destination cards
     */
    public static JPanel leftPanel(){
        JPanel p = new JPanel();
        p.setSize(new Dimension(200, 800));
        p.setBackground(Color.BLUE);
        p.setLayout(new GridLayout(3,1));

        JPanel transCardHandPanel = new JPanel();
        transCardHandPanel.setSize(new Dimension(200, 500));
        transCardHandPanel.setBackground(Color.WHITE);
        transCardHandPanel.setLayout(new GridLayout(4,2));

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setFont(new Font("Calibri",1, 15));

        Image blue = driver.getUprightTrans().get(0).getPicture();
        blue = blue.getScaledInstance(100,100, 0);
        JLabel bluePile = new JLabel(new ImageIcon(blue));

        Image gray = driver.getUprightTrans().get(1).getPicture();
        gray = gray.getScaledInstance(100,100, 0);
        JLabel grayPile = new JLabel(new ImageIcon(gray));

        Image green = driver.getUprightTrans().get(2).getPicture();
        green = green.getScaledInstance(100,100, 0);
        JLabel greenPile = new JLabel(new ImageIcon(green));

        Image orange = driver.getUprightTrans().get(3).getPicture();
        orange = orange.getScaledInstance(100,100, 0);
        JLabel orangePile = new JLabel(new ImageIcon(orange));

        Image pink = driver.getUprightTrans().get(4).getPicture();
        pink = pink.getScaledInstance(100,100, 0);
        JLabel pinkPile = new JLabel(new ImageIcon(pink));

        Image red = driver.getUprightTrans().get(5).getPicture();
        red = red.getScaledInstance(100,100, 0);
        JLabel redPile = new JLabel(new ImageIcon(red));

        Image rainbow = driver.getUprightTrans().get(6).getPicture();
        rainbow = rainbow.getScaledInstance(100,100, 0);
        JLabel rainbowPile = new JLabel(new ImageIcon(rainbow));

        Image destDraw = toolkit.getImage("C:/Users/patri/Documents/eileen/ttrrestored/src/fwdboardandtransport/destination_card_back.jpg");
        destDraw = destDraw.getScaledInstance(200,100, 0);
        JLabel destDrawPile = new JLabel(new ImageIcon(destDraw));

        //score panel
        JPanel scorePanel = new JPanel();
        scorePanel.setSize(new Dimension(200, 100));
        scorePanel.setBackground(Color.WHITE);
        scorePanel.setLayout(new GridLayout(3,1));

        JLabel taxiLabel = new JLabel("Taxis: #");
        taxiLabel.setFont(new Font("Calibri",1, 15));
        scorePanel.add(taxiLabel);
        scorePanel.setBorder(new LineBorder(Color.BLACK));

        JLabel destinationLabel = new JLabel("Destination Cards: ");
        destinationLabel.setFont(new Font("Calibri",1, 15));
        scorePanel.add(destinationLabel);
        scorePanel.setBorder(new LineBorder(Color.BLACK));

        JLabel touristLabel = new JLabel("Tourist Attractions: ");
        touristLabel.setFont(new Font("Calibri",1, 15));
        scorePanel.add(touristLabel);
        scorePanel.setBorder(new LineBorder(Color.BLACK));


        transCardHandPanel.add(nameLabel);
        transCardHandPanel.add(bluePile);
        transCardHandPanel.add(grayPile);
        transCardHandPanel.add(greenPile);
        transCardHandPanel.add(orangePile);
        transCardHandPanel.add(pinkPile);
        transCardHandPanel.add(redPile);
        transCardHandPanel.add(rainbowPile);
        p.add(transCardHandPanel);
        p.add(scorePanel);
        p.add(destDrawPile);


        return p;
    }
    /*creates panel in center for board*/
    public static JPanel boardPanel(){

        JPanel p = new JPanel();
        p.setSize(new Dimension(600,600));
        p.setBackground(Color.GRAY);
        JPanel panel = new JPanel();
        panel.setBorder(new LineBorder(Color.BLACK)); // make it easy to see

        //add the board image to the panel
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image boardImage = toolkit.getImage("C:/Users/patri/Documents/eileen/ttrrestored/src/fwdboardandtransport/game_board.jpg");

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
        /* Schedule a job for the event-dispatching thread: creating and showing this application's GUI. Unsupported feature in Stride : anonymous class*/
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

}
