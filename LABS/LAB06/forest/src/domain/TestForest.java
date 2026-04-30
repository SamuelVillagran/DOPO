package domain;


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
public class TestForest {
    
    @Test
    public void shouldCreate() {
        Forest forest1 = new Forest(); 
        int TIMESTICTAC = 7;
        for (int i = 0; i < TIMESTICTAC; i++) {
            forest1.ticTac();
        }
    }
    
    @Test
    public void shoudCreateShadow() {
        Forest forest2 = new Forest();
        Shadow shadow = new Shadow(forest2, 2, 1);
        assertNotNull(shadow);
    }
    
    @Test
    public void shouldMoveLastPosition() {
        Forest forest3 = new Forest();
        Shadow shadow = new Shadow(forest3, 0, 0);
        forest3.ticTac();
        assertEquals(24, shadow.getRow());
    }
    
}