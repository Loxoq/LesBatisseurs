package test;

import org.junit.*;
import static org.junit.Assert.*;
import model.*;

/**
 * This class' test the Batisseurs class' operations.
 * @author T. Le Berre
 */

public class TestBatisseurs {

    String path;
    Batisseurs lesBat;
    Batisseurs lesBat2;

    /**
     * Set up the attributes needed for the test.
     */

    @Before
    public void setUp(){

        this.path = "../data/save.txt";
        this.lesBat = new Batisseurs(Mode.HH, 2, 2);
        this.lesBat2 = new Batisseurs(null, -1, -1);
    }

    /**
     * Test the save/load mecanism.
     */

    @Test
    public void TestLoad(){

        this.lesBat.save(this.path);

        Batisseurs lesBat3 = new Batisseurs(Mode.HH, 2, 2);
        lesBat3.load(this.path);
        assertEquals(lesBat.getGame(), lesBat3.getGame());
    }

    /**
     * Last step of the step.
     * Useless variables are set to null.
     */

    @After
    public void tearDown(){

        this.lesBat = null;
        this.lesBat2 = null;
        this.path = null;
    }
}