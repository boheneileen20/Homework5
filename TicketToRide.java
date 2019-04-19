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
