package presentation;

import domain.Forest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * The test class AceptanceTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class AceptanceTest {
    
    private ForestGUI gui;
    /**
     * Default constructor for test class AceptanceTest
     */
    public AceptanceTest()
    {
    }
    

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp() {
        gui = ForestGUI.getGUI();
    }
    
    @Test
    public void shouldCreate() {
        Forest forest1 = gui.getForest(); 
        int TIMESTICTAC = 7;
        for (int i = 0; i < TIMESTICTAC; i++) {
            forest1.ticTac();
        }
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