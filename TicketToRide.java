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
public class TicketToRide
{
    private int width;
    private int height;
    private Image boardImage;

    /**
     * Constructor for objects of class TextTwist
     */
    public TicketToRide() {
        //sets size of window
        setPreferredSize(new Dimension(600, 500));
        toolkit = Toolkit.getDefaultToolkit();
        //sets graphics of window
        boardImage = toolkit.getImage("BG.jpg");
        setBackground(Color.WHITE);
        width = getPreferredSize().width;
        height = getPreferredSize().height;
        //adds functionality of mouse
        addMouseListener( this );
        //givenWord.add("DARKEN");

    }
}
