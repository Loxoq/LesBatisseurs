package test;

import org.junit.*;
import static org.junit.Assert.*;
import org.junit.rules.*;
import model.*;


/**
 * This class' test the Game class' operations.
 * @author T. Le Berre
 */
public class TestGame {

    Game game;
    Game game2;

    /**
     * Setting of the exceptions that will be trown
     */

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * Set up the attributes Game needed for the tests.
     */

    @Before()
    public void setUp(){

        Batisseurs lesBat = new Batisseurs(Mode.HH, 2, 2);
        
        this.game = new Game(Mode.HH, 2, 2, lesBat);

        thrown.expect(IllegalArgumentException.class);
        this.game2 = new Game(null, -1, -1, lesBat);
    }

    /**
     * Tests on different integers
     */
    @Test()
    public void testQuantities(){

        assertTrue(this.game.getCurrentPlayer() == null);
        assertTrue(this.game.getPlayers().size() == 2); //Check if the length of the ArrayList of players is equal to the number of players wanted.
        assertTrue(this.game.getReserve() == 80);       //Check if the coins stock is correct after the player's initialization.
        assertTrue(this.game.getCards().size() == 84);  //Check if the length of the ArrayList of Card is equal to 84, the numver of cards a game is supposed to have.

        this.game.setReserve(10);
        assertTrue(this.game.getReserve() == 10);     //Check if the stock's value is equals to the value we set it to.
    }

    /**
     * Test on the Player objects initialization.
     */
    @Test()
    public void testPlayersInitialization(){

        Player play1 = new HumanPlayer("1", 0, game);
        Player play2 = new HumanPlayer("2", 1, game);

        //Check if the Players objects in the game are well initialized.
        assertEquals(play1, this.game.getPlayer(0));    
        assertEquals(play2, this.game.getPlayer(1));
    }

    /**
     * Test the Game's constructor.
     */

    @Test
    public void testConstructor(){

        //Check if the attributes are set to their default value in case of a wrong parameter.
        assertNull(this.game2.getCards());
        assertNull(this.game2.getPlayers());
        assertNull(this.game2.getCurrentPlayer());
        assertTrue(this.game2.getReserve() == 0);
        assertTrue(this.game2.getTurn() == 0);
    }

    /**
     * Test the batch of 5 cards initialization.
     */

    @Test
    public void testInitializeCardsWorkers(){

        this.game.intializeStackWorkers();
        this.game.intializeStackBuilding();

        assertTrue(this.game.getStackWorkers().size() == 5);
        assertTrue(this.game.getStackBuilding().size() == 5);

    }

    /**
     * Last step of the test.
     * Useless variables are set to null.
     */

    @After
    public void tearDown(){

        this.game = null;
        this.game2 = null;
    }




}