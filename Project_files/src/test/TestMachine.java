package test;

import org.junit.*;
import static org.junit.Assert.*;
import org.junit.rules.*;

import model.*;

/**
 * This class' test the Machine class' operations.
 * @author T. Le Berre
 */

public class TestMachine {

    Machine m;
    Machine m2;

    /**
     * Setting of the exceptions that will be trown
     */

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * Set up the attributes Machine needed for the tests.
     */

    @Before()
    public void setUp(){

        thrown.expect(IllegalArgumentException.class);
        this.m = new Machine("1",1,1,1,1,1,0,0);
        this.m2 = new Machine("1",-1,-1,-1,-1,-1,0,0);
    }

    /**
     * Test the Machine's constructor.
     */

    @Test
    public void testConstructor(){

        assertFalse(this.m.getState());

        //Check if the attributes are set to their default value in case of a wrong parameter.
        assertTrue(this.m2.getProdWood() == 0);
        assertTrue(this.m2.getProdStone() == 0);
        assertTrue(this.m2.getProdIntell() == 0);
        assertTrue(this.m2.getProdTile() == 0);

    }

    /**
     * Test the getter & setter methods.
     */

    @Test
    public void testGandS(){

        assertTrue(this.m.getProdWood() == 0);
        assertTrue(this.m.getProdStone() == 0);
        assertTrue(this.m.getProdIntell() == 0);
        assertTrue(this.m.getProdTile() == 0);
        
    }

    /**
     * Test the workingStatus attribute modification.
     */

    @Test
    public void testSetWorkingStatus(){

        this.m.setWorkingStatus(true);
        assertTrue(this.m.getWorkingStatus());
    }

    /**
     * Last step of the step.
     * Useless variables are set to null.
     */

    @After
    public void tearDown(){

        this.m = null;
        this.m2 = null;
    }
}