package domain;



import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.Color;

/**
 * The test class C4BaobabPrinceTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class C4BaobabPrinceTest
{
    private Forest forest;
    private BaobabPrince villagran;
    private BaobabPrince sanchez;
    
    @BeforeEach
    public void setUp(){
        forest = new Forest();
        villagran = new BaobabPrince(forest, 15,15);
        sanchez = new BaobabPrince(forest, 17,17);
    }
    
    @Test
    public void shouldAssignBaobabPrinceOnPlaces(){
        assertFalse(forest.isEmpty(15, 15));
        assertEquals(villagran , forest.getThing(15, 15));
        assertFalse(forest.isEmpty(17, 17));
        assertEquals(sanchez , forest.getThing(17, 17));
    }
    
    @Test
    public void shouldStartWithPinkClearColor(){
        assertEquals(new Color(143, 184, 255), sanchez.getColor());
        assertEquals(new Color(143, 184, 255), villagran.getColor());
    }
    
    @Test
    public void shouldGetOldAfterFourTictacs(){
        forest.ticTac();
        forest.ticTac();
        forest.ticTac();
        forest.ticTac();
        
        assertEquals(1, villagran.getYears());
        assertEquals(1, sanchez.getYears());
    }
    
    @Test
    public void shouldntBeAgressiveBeforeTenYears(){
        //Se llama al método de calcular 2 años, 5 veces.
        tictacsByTwoYears();
        tictacsByTwoYears();
        tictacsByTwoYears();
        tictacsByTwoYears();
        tictacsByTwoYears();
        
        assertTrue(villagran.getIsDangerous());
        assertTrue(sanchez.getIsDangerous());
    }
    
    private void tictacsByTwoYears(){
        forest.ticTac();
        forest.ticTac();
        forest.ticTac();
        forest.ticTac();
        forest.ticTac();
        forest.ticTac();
        forest.ticTac();
        forest.ticTac();
    }
}