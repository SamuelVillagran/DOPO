package domain;



import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class BearTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class BearTest {
    
    @Test
    public void shouldMakeDamage() {
        Forest forest = new Forest();
        Bear bear = (Bear) forest.getThing(5, 4);
        for (int i = 0; i < 20; i++) {
            bear.attack();
        }
        Squirrel sql = (Squirrel) forest.getThing(5, 5);
        assertEquals(40, sql.getEnergy());
    }
    
    @Test
    public void shouldMakeDamageTree() {
        Forest forest = new Forest();
        Bear bear = new Bear(forest, 15, 16);
        for (int i = 0; i < 20; i++) {
            bear.attack();
        }
        
        Tree tree = (Tree) forest.getThing(15, 15);
        assertEquals(40, tree.getEnergy());
    }
    
    @Test
    public void shouldMoveDifferentPlace() {
        Forest forest = new Forest();
        Bear bear = new Bear(forest, 15, 16);
        bear.move();
        assertNotEquals(15, bear.getRow());
        assertNotEquals(16, bear.getColumn());
    }
}