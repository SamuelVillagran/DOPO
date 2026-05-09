package test;



import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.BaobabPrince;
import domain.DefaultTree;
import domain.Forest;

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
    
    @Test
    public void sohuldNeighborsTreesGetDamageAfterTenYears(){
        DefaultTree bob = new DefaultTree(forest, 14,14); //Arbol vecino
        
        //Se llama al método de calcular 2 años, 5 veces.
        tictacsByTwoYears();
        assertEquals(98, bob.getEnergy());
        tictacsByTwoYears();
        assertEquals(96, bob.getEnergy());
        tictacsByTwoYears();
        assertEquals(94, bob.getEnergy());
        tictacsByTwoYears();
        assertEquals(92, bob.getEnergy());
        tictacsByTwoYears();
        
        //Bob deberia tener 70 de daño ya que por pasados 10 años los baobabs empiezan a afectar
        //otras plantas y quita -5 de daño por cada temporada del año
        assertEquals(70, bob.getEnergy());
    
    }
    
    @Test
    public void soulgNeighborsTreesDie(){
        
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