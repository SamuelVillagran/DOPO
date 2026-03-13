

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class FleetTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class FleetTest {

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
    }
    
    @Test
    public void shouldMoveEveryMachines() {
        Fleet proofFleet = new Fleet();
        proofFleet.addMachine(3, -70);
        proofFleet.addMachine(-25, 40);
        proofFleet.addMachine(20, 20);
        proofFleet.advance(10, -5);
        
        Machine currentMachine = proofFleet.getASpeciefiedMachine(0);
        assertEquals(13, currentMachine.getLongitudeMachine());
        assertEquals(-75, currentMachine.getLatitudeMachine());
        
        currentMachine = proofFleet.getASpeciefiedMachine(1);
        assertEquals(-15, currentMachine.getLongitudeMachine());
        assertEquals(35, currentMachine.getLatitudeMachine());
        
        currentMachine = proofFleet.getASpeciefiedMachine(2);
        assertEquals(30, currentMachine.getLongitudeMachine());
        assertEquals(15, currentMachine.getLatitudeMachine());
        
        // Deberia de no moversen las maquinas 
        proofFleet.advance(1800, 900);
        /*
        currentMachine = proofFleet.getASpeciefiedMachine(0);
        assertEquals(13, currentMachine.getLongitudeMachine());
        assertEquals(-75, currentMachine.getLatitudeMachine());
        
        currentMachine = proofFleet.getASpeciefiedMachine(1);
        assertEquals(-15, currentMachine.getLongitudeMachine());
        assertEquals(35, currentMachine.getLatitudeMachine());
        
        currentMachine = proofFleet.getASpeciefiedMachine(2);
        assertEquals(30, currentMachine.getLongitudeMachine());
        assertEquals(15, currentMachine.getLatitudeMachine());
        */
    }
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
    }
}