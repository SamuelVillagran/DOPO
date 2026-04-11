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
    
    @Test
    public void shouldMakeListAtTeam() { // Test ayudado a hacer por Claude Sonnet 4.6 IA 2026
        Fifa fifa = new Fifa();

        String expected =
        "6 elementos\n" +
        ">L.DIAZ.\t Rol: A. \t Valor:760000000\t Minutos:690\n" +
        ">JAMES.\t Rol: M. \t Valor:2200000\t Minutos:516\n" +
        ">BORRE.\t Rol: A. \t Valor:4400000\t Minutos:445\n" +
        ">LUCUMI.\t Rol: D. \t Valor:125000000\t Minutos:1250\n" +
        ">VARGAS.\t Rol: P. \t Valor:540000\t Minutos:1160\n" +
        ">COLOMBIA.\t Grupo: K.\t Valor Promedio:168522432\n" +
        "\tL.DIAZ.\t Rol: A. \t Valor:760000000\t Minutos:690\n" +
        "\tJAMES.\t Rol: M. \t Valor:2200000\t Minutos:516\n" +
        "\tBORRE.\t Rol: A. \t Valor:4400000\t Minutos:445\n" +
        "\tLUCUMI.\t Rol: D. \t Valor:125000000\t Minutos:1250\n" +
        "\tVARGAS.\t Rol: P. \t Valor:540000\t Minutos:1160\n";

        assertEquals(expected, fifa.toString());
    }


}