package test;

import org.junit.*;
import static org.junit.Assert.*;
import org.junit.rules.*;

import model.*;

/**
 * This class' test the Ouvrier class' operations.
 * @author T. Le Berre
 */

public class TestOuvrier {

    Ouvrier ouvr;
    Ouvrier ouvr2;

    /**
     * Setting of the exceptions that will be trown
     */

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * Set up the attributes Ouvrier needed for the tests.
     */

    @Before()
    public void setUp(){

        this.ouvr = new Ouvrier("1", 1,1,1,1,1);

        thrown.expect(IllegalArgumentException.class);
        this.ouvr2 = new Ouvrier("1", -1,-1,-1,-1,-1);
    }

    /**
     * Test the Ouvrier's constructor.
     */

    @Test
    public void testConstructor(){

        assertFalse(this.ouvr.getState());

        //Check if the attributes are set to their default value in case of a wrong parameter.
        assertTrue(this.ouvr2.getWood() == -1);
        assertTrue(this.ouvr2.getStone() == -1);
        assertTrue(this.ouvr2.getIntell() == -1);
        assertTrue(this.ouvr2.getTile() == -1);
        assertTrue(this.ouvr2.getCost() == -1);
    }

    /**
     * Test the getter & setter methods.
     */

    @Test
    public void testGAndS(){

        this.ouvr.setWood(5);
        assertTrue(this.ouvr.getWood() == 5);

        this.ouvr.setStone(5);
        assertTrue(this.ouvr.getStone() == 5);

        this.ouvr.setIntell(5);
        assertTrue(this.ouvr.getIntell() == 5);

        this.ouvr.setTile(5);
        assertTrue(this.ouvr.getTile() == 5);

        assertTrue(this.ouvr.getCost() == 1);
        assertFalse(this.ouvr.getState());

        this.ouvr.setState(true);
        assertTrue(this.ouvr.getState());
        this.ouvr.setState(false);

        Batiment leBat = new Batiment("1", 1,1,1,1,1,1);

        this.ouvr.setWorkOn(leBat);
        assertEquals(leBat , this.ouvr.getWorkOn());

    }

    /**
     * Test the production mecanism.
     * N.B. : This test is for both the Ouvrier and Machine classes.
     */

    @Test
    public void testProduction(){

        Batiment leBat = new Batiment("1", 1,1,1,1,1,1);
        this.ouvr.setWorkOn(leBat);

        int nbWood = leBat.getWood();
        //this.ouvr.product();

        //Check if the amount of wood product by the Ouvrier has been substracted to
        //the Batiment's amount of wood.
        assertTrue(nbWood - this.ouvr.getWood() == leBat.getWood());

    }

    /**
     * Test the allocation of the workOn variable to null.
     */

    @Test
    public void testSetNullWorkOn(){

        this.ouvr.setNullWorkOn();
        assertNull(this.ouvr.getWorkOn());
    }

    /**
     * Last step of the step.
     * Useless variables are set to null.
     */

    @After
    public void tearDown(){

        this.ouvr = null;
        this.ouvr2 = null;
    }
}