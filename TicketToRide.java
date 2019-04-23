import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.event.*;
import java.util.*;
/**
 * Write a description of class TicketToRide here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class TicketToRide extends JPanel implements MouseListener
{
    private int width;
    private int height;
    private Image boardImage;
    private Image transportationCardBack;
    private Toolkit toolkit;
    private Rectangle transportationCardPile = new Rectangle(100,100,100,100);
    private ArrayList<DestinationCard> destCards = new ArrayList<>();
    /**
     * Constructor for objects of class TextTwist
     */
    public TicketToRide() {
        //sets size of window
        setPreferredSize(new Dimension(800, 800));
        toolkit = Toolkit.getDefaultToolkit();
        //sets graphics of window
        boardImage = toolkit.getImage("fwdboardandtransport/game_board.jpg");
        setBackground(Color.WHITE);
        width = getPreferredSize().width;
        height = getPreferredSize().height;
        //adds functionality of mouse
        addMouseListener( this );

        //instantiate instance variable images
        transportationCardBack = toolkit.getImage("fwdboardandtransport/transportation_card_back.jpg");
        
        //read in transportation card images and save them in an arraylist
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
    }

    /**
     * PaintComponent method for JPanel.
     *
     * @param  g   the Graphics object for this applet
     */
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        //draw board such that there is white space on the right side
        g.drawImage(boardImage, 0, 0, 550, 800, 0,0, 3337, 5048,this);
        //add pile of transportation cards
        g.setColor(Color.YELLOW);
        g.fillRect(550, 0, 250, 150);
        g.drawImage(transportationCardBack, 550, 0, 800, 150, 0,0, 769, 504, this);
        
        
        //add pile of destination cards
        
        //add taxis
        
        //add player scores
        
        
    }

    /**
     * Creates the window and GUI
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
     * This method makes sure the mouse does nothing when the mouse enters the panel.
     * 
     * @param e   the event the mouse triggers
     * 
     */
    public void mouseEntered( MouseEvent e ) { }

    /**
     * This method makes sure the mouse does nothing when the mouse exits the panel.
     * 
     * @param e   the event the mouse triggers
     * 
     */
    public void mouseExited( MouseEvent e ) { }

    /**
     * This method makes sure the mouse does nothing when the mouse is pressed down.
     * 
     * @param e   the event the mouse triggers
     * 
     */
    public void mousePressed( MouseEvent e ) { }

    /**
     * This method makes sure the mouse does nothing when the mouse button is released.
     * 
     * @param e   the event the mouse triggers
     * 
     */
    public void mouseReleased( MouseEvent e ) { }

    /**
     * This method makes sure the mouse does nothing when the mouse is moved.
     * 
     * @param e   the event the mouse triggers
     * 
     */
    public void mouseWheelMoved( MouseWheelEvent e ){ }

    /**
     * 
     * 
     * @param e   the event the mouse triggers
     * 
     */
    public void mouseClicked( MouseEvent e ) {

    }

    /**
     * Main method to run program
     *
     * @param args command line arguments. 
     */
    public static void main(String[] args) {
        
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    createAndShowGUI();
                }
            });
    }
}
