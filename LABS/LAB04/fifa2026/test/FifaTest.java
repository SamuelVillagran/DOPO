package test;



import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.*;

/**
 * The test class FifaTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class FifaTest {
    
    
    @Test
    public void shouldAddPlayer() {
        Fifa fifa = new Fifa();
        fifa.addPlayer("MESSI", "1420", "D", "15000000" , "Inter");
        Player playerAdded = fifa.getPlayers().get("MESSI");
        assertEquals(playerAdded.getClass(), Player.class);
        assertEquals(playerAdded.getName(), "MESSI");
        assertEquals(playerAdded.getMinutes(), 1420);
        assertEquals(playerAdded.getPosition(), 'D');
        assertEquals(playerAdded.getValue(), 15000000);
        assertEquals(playerAdded.getClub(), "Inter");
    }
    
    @Test
    public void shouldAddTeam() {
        Fifa fifa = new Fifa();
        fifa.addTeam("Argentina", "1620", "J", "Scaloni",  "Azul-blanco", "MESSI\nMaradona\nHernan");
        Team argentina = (Team) fifa.getParticipants().get(6);
        assertEquals(argentina.getName(), "Argentina");
        assertEquals(argentina.getMinutes(), 1620);
        assertEquals(argentina.getPosition(), 'J');
        assertEquals(argentina.getManager(), "Scaloni");
        assertEquals(argentina.getUniform(), "Azul-blanco");
        assertEquals(argentina.getPlayers().size(), 3); 
    }
    
}