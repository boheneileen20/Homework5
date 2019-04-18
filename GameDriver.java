
/**
 * Write a description of class GameDriver here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GameDriver
{
    //Starter file
    
    //Driver class which runs the game, synthesizing the parts into a whole
    boolean init = false;
    
    /**
     * Method which draws transportation cards
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
}
