package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import junit.framework.*;

@RunWith(Suite.class)
@SuiteClasses(value = {TestBatiment.class/*, TestBatisseurs.class*/, TestGame.class,
    TestPlayer.class, TestMachine.class, TestOuvrier.class})

public class AllTests extends TestCase {

    public static Test suite(){

        return new JUnit4TestAdapter(AllTests.class);
    }

    public static void main(String[] args) {
        
        junit.textui.TestRunner.run(suite());
    }
}