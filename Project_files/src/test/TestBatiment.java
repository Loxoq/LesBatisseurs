package test;

import org.junit.*;
import static org.junit.Assert.*;
import org.junit.rules.*;


import model.*;

/**
 * This class' test the Batiment class' operations.
 * @author T. Le Berre
 */

public class TestBatiment{

    Batiment bat;
    Batiment bat2;

    /**
     * Setting of the exceptions that will be trown
     */

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
    /**
     * Set up the attributes Batiment needed for the tests.
     */

    @Before()
    public void setUp(){

        this.bat = new Batiment("1", 1,1,1,1,1,1);

        thrown.expect(IllegalArgumentException.class);
        this.bat2 = new Batiment("1", -1,-1,-1,-1,-1,-1);
    }

    /**
     * Test the Batiment's constructor.
     */

    @Test
    public void testConstructor(){

        assertFalse(this.bat.getState());

        //Check if the attributes are set to their default value in case of a wrong parameter.
        assertTrue(this.bat.getWood() == 1);
        assertTrue(this.bat.getStone() == 1);
        assertTrue(this.bat.getIntell() == 1);
        assertTrue(this.bat.getTile() == 1);
        assertTrue(this.bat.getCost() == 1);
        assertTrue(this.bat.getName().equals("1"));

    }

    /**
     * Last step of the step.
     * Useless variables are set to null.
     */

    @After
    public void tearDown(){

        this.bat = null;
    }
}