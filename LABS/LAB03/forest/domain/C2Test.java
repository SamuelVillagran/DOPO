package domain;



import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.Color;

/**
 * The test class C2Test.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class C2Test
{
    
    private Forest forest;
    /**
     * Creat the Forest instance before each test.
     */
    @BeforeEach
    public void setUp()
    {
        forest = new Forest();
    }

    /**
     * Tests a Squirre will be on Places Matrix.
     */
    @Test
    public void shouldAssignSquirrelOnPlaces(){
        Thing squirrelJoan = new Squirrel(forest, 10,10);
        assertFalse(forest.isEmpty(10, 10));
        assertEquals(squirrelJoan, forest.getThing(10, 10));
    }
    
    /**
     * Tests a Squireel must grow old each 4 tictacs.
     */
    @Test
    public void shouldSquirrelGrowOld(){
        Thing squirrelJoan = new Squirrel(forest, 10,10);
        
        forest.ticTac();
        forest.ticTac();
        forest.ticTac();
        forest.ticTac();
        
        int wishedAge = 1;
        int realAge = ((Squirrel) squirrelJoan).getYears(); 
        
        assertEquals(wishedAge, realAge);
    }
    
    /**
     * 
     */
    @Test
    public void shoudlChangeColorEachTwoYears(){
        Thing squirrelJoan = new Squirrel(forest, 10,10);
        
        tictacsByYear(forest);
        tictacsByYear(forest);
        assertEquals(new Color(184, 83, 0), squirrelJoan.getColor());
        
        tictacsByYear(forest);
        tictacsByYear(forest);
        assertEquals(new Color(165, 84, 0), squirrelJoan.getColor());
        
        tictacsByYear(forest);
        tictacsByYear(forest);
        assertEquals(new Color(130, 66, 0), squirrelJoan.getColor());
        
        tictacsByYear(forest);
        tictacsByYear(forest);
        assertEquals(new Color(99, 51, 0), squirrelJoan.getColor());
    }
    
    @Test
    public void shouldDieAfterTenYears(){
        Thing squirrelJoan = new Squirrel(forest, 10,10);
        
        tictacsByYear(forest);
        tictacsByYear(forest);
        tictacsByYear(forest);
        tictacsByYear(forest);
        tictacsByYear(forest);
        tictacsByYear(forest);
        tictacsByYear(forest);
        tictacsByYear(forest);
        tictacsByYear(forest);
        tictacsByYear(forest);
        
        int realAge = ((Squirrel) squirrelJoan).getYears(); 
        
        assertEquals(10, realAge);
        
        
        int row = ((Squirrel) squirrelJoan).getRow();
        int col = ((Squirrel) squirrelJoan).getColumn();
        assertNull(forest.getThing(row, col));
    }
    
    @Test
    public void shouldReproduce(){
        Thing squirrelJoan = new Squirrel(forest, 5,5);
        Thing squirrePaul = new Squirrel(forest, 7,7);
        
        forest.ticTac();
        
        assertFalse(forest.isEmpty(6,6));
        assertTrue(forest.getThing(6,6) instanceof Squirrel);
    }
    
    private void tictacsByYear(Forest forest){
        forest.ticTac();
        forest.ticTac();
        forest.ticTac();
        forest.ticTac();
    }
    
}