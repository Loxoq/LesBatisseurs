package test;

import org.junit.*;
import static org.junit.Assert.*;
import org.junit.rules.*;

import model.*;



/**
 * This class' test the HumanPlayer class' operations.
 * N.B. : There are no tests for AutoPlayer, because it inherits the same functions as HumanPlayer.
 * @author T. Le Berre
 */

public class TestPlayer {

    Player player;
    Player player2;

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
        Game jeu = new Game(Mode.HH, 2, 2, lesBat);
        this.player = new HumanPlayer("1", 0, jeu);

        thrown.expect(IllegalArgumentException.class);
        this.player2 = new HumanPlayer("", -1, jeu);
    }

    /**
     * Test the Player's constructor.
     */

    @Test
    public void testConstructor(){

        //Check if the attributes are set to their default value in case of a wrong parameter.
        assertNull(this.player2.getName());
        assertTrue(this.player2.getId() == -1);
        assertNull(this.player2.getCards());
        assertTrue(this.player2.getCoins() == 0);
        assertTrue(this.player2.getActionsBought() == 0);
        assertTrue(this.player2.getActions() == 0);
        assertNull(this.player2.getWorkers());

        //Test if the Player's attributes are well initialized
        assertTrue(this.player.getName().equals("1"));
        assertTrue(0 == this.player.getId());
        assertTrue(this.player.getActions() == 3);
        assertTrue(this.player.getActionsBought() == 0);
        assertTrue(this.player.getCoins() == 10);
    }

    /**
     * Test the getter & setter methods.
     */

    @Test()
    public void testGAndS(){

        this.player.setCoins(20);
        assertEquals(20, this.player.getCoins());

        this.player.setActions(2);
        assertEquals(2, this.player.getActions());

        this.player.setActionsBought(5);
        assertEquals(5, this.player.getActionsBought());

        /*Test the wrong parameters value verification.
        If the value didn't change after the setter, if means the verification is correctly done */
        this.player.setCoins(-1);
        assertEquals(20, this.player.getCoins());

        this.player.setActions(-1);
        assertEquals(2, this.player.getActions());

        this.player.setActionsBought(-1);
        assertEquals(5, this.player.getActionsBought());


        /*Test that the cards chosen on the board by the player are added
        to his list of owned cards/workers */

        int nbWorkersbefore = this.player.getWorkers().size();

        Ouvrier laCarte = new Ouvrier("1",1,1,1,1,1);
        this.player.recruit(laCarte);

        assertNotEquals(nbWorkersbefore, this.player.getWorkers().size());

        int nbOfCardsBefore = this.player.getCards().size();

        Batiment leBat = new Batiment("1",1,1,1,1,1,1);
        this.player.openBuild(leBat);

        assertNotEquals(nbOfCardsBefore, this.player.getCards().size());

        //Test that getJoueSur returns an empty AL if the player had not send anyone to work
        assertTrue(this.player.getJoueSur().size() == 0);

        assertTrue(this.player.getMachines().size() == 0);
        assertTrue(this.player.getWorkers().size() == 1);
    }

    /**
     * Test the "send to work" mecanism, to link a worker to a building.
     */
    @Test
    public void testSendToWork(){

        Batiment leBat = new Batiment("1",1,1,1,1,1,1);
        Ouvrier ouvr = new Ouvrier("1", 1,1,1,1,1);

        this.player.openBuild(leBat);
        this.player.recruit(ouvr);

        this.player.sendToWork(ouvr, leBat);

        assertTrue(ouvr.getState());
        assertTrue(this.player.getJoueSur().size() == 1);
    }

    /**
     * Test the "openBuild" mecanism.
     */

    @Test
    public void testOpenBuild(){

        Batiment build = new Batiment("1",1,1,1,1,1,1);
        int length = this.player.getCards().size();
        this.player.openBuild(build);

        assertTrue(this.player.getCards().size() == length+1);
    }

    /**
     * Test the "buyAction" mecanism.
     */

    @Test
    public void testBuyAction(){

        int coins = this.player.getCoins();
        this.player.buyAction();

        assertTrue(this.player.getActionsBought() == 1);
        assertTrue(this.player.getCoins() == coins-5);
    }

    /**
     * Test the "takeCoin" mecanism.
     */

    @Test
    public void testTakeCoin(){

        int coins = this.player.getCoins();
        int actions = this.player.getActions();
        this.player.takeCoin(1);

        assertTrue(this.player.getActions() == actions-1);
        assertTrue(this.player.getCoins() == coins+1);
    }



    /**
     * Last step of the step.
     * Useless variables are set to null.
     */

    @After
    public void tearDown(){

        this.player = null;
        this.player2 = null;
    }
}