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
        try
        {
            fifa.addPlayer("MESSI", "1420", "D", "15000000" , "Inter");
        }
        catch (FifaException fe)
        {
            fe.printStackTrace();
        }
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
        try {   
        // Primero hay que agregar los jugadores que Argentina va a referenciar
        fifa.addPlayer("MESSI",    "900", "A", "1000000000", "Inter");
        fifa.addPlayer("Maradona", "850", "A", "500000000",  "Napoli");
        fifa.addPlayer("Hernan",   "700", "D", "300000000",  "Milan");

        // addSome() ya agregó 5 players (0-4) + COLOMBIA (5)
        // Los 3 nuevos quedan en índices 6, 7, 8
        // Argentina quedará en el índice 9
        fifa.addTeam("Argentina", "1620", "J", "Scaloni", "Azul-blanco", "MESSI\nMaradona\nHernan");
        } catch (FifaException fe) {
            fe.printStackTrace();
        }
        Team argentina = (Team) fifa.getParticipants().get(9); // ← índice corregido
        assertEquals(argentina.getName(), "Argentina");
        assertEquals(argentina.getMinutes(), 1620);
        assertEquals(argentina.getPosition(), 'J');
        assertEquals(argentina.getManager(), "Scaloni");
        assertEquals(argentina.getUniform(), "Azul-blanco");
        assertEquals(argentina.getPlayers().size(), 3); 
    }
    
    @Test
    public void shouldMakeListAtTeam() { // Test ayudado a hacer por Claude Sonnet 4.6 IA 2026 (El String)
        Fifa fifa = new Fifa();

        String expected =
            "6 elementos\n" +
            ">L.DIAZ.    Rol: A Valor:760000000    Minutos:690\n" +
            ">JAMES.     Rol: M Valor:2200000      Minutos:516\n" +
            ">BORRE.     Rol: A Valor:4400000      Minutos:445\n" +
            ">LUCUMI.    Rol: D Valor:125000000    Minutos:1250\n" +
            ">VARGAS.    Rol: P Valor:540000       Minutos:1160\n" +
            ">COLOMBIA.\t Grupo: K.\t Valor Promedio:168522432\n" +
            "\tL.DIAZ.    Rol: A Valor:760000000    Minutos:690\n" +
            "\tJAMES.     Rol: M Valor:2200000      Minutos:516\n" +
            "\tBORRE.     Rol: A Valor:4400000      Minutos:445\n" +
            "\tLUCUMI.    Rol: D Valor:125000000    Minutos:1250\n" +
            "\tVARGAS.    Rol: P Valor:540000       Minutos:1160\n";

        assertEquals(expected, fifa.toString());
    }

    @Test
    public void shouldThrowFifaExceptionDataisNoAInteger() {
        Fifa fifa = new Fifa();
        
        try {
            fifa.addPlayer("Hernan", "Samuel", "D", "1234", "Colombia"); // No debería ir Samuel sino un número entero
            
        } catch (FifaException fe) {
            assertEquals(FifaException.ATTRIBUTE_INTEGER_SETTING_INCORRECTLY, fe.getMessage());
        }
        
    }
    
    @Test
    public void shouldThrowFifaExceptionStringOrCharIsIncorrect() {
        Fifa fifa = new Fifa();
        
        try {
            fifa.addPlayer("Hernan", "50", "1230", "1234", "Colombia"); // No debería ir 1230 sino un char
            
        } catch (FifaException fe) {
            assertEquals(FifaException.ATTRIBUTE_STRING_CHAR_SETTING_INCORRECTLY, fe.getMessage());
        }
        
    }
    
    @Test
    public void shouldThrowFifaExceptionIncorrectMarketValue() {
        Fifa fifa = new Fifa();
        
        try {
            fifa.addPlayer("Hernan", "150", "D", "-1234", "Colombia"); // No debería ir un -1234, numero negativo sino un numero entero positivo
            
        } catch (FifaException fe) {
            assertEquals(FifaException.ATTRIBUTE_INTEGER_SETTING_INCORRECTLY, fe.getMessage());
        }
        
    }
    
    @Test
    public void shouldThrowFifaExceptionParticipantWithSameNameValueAddTeam() {
        Fifa fifa = new Fifa();
        
        try {
            fifa.addTeam("Colombia", "150", "D", "Samuel", "Amarillo-azul-rojo", "L.DIAZ\nFALCABO\nCUADRADO\nFALCABO"); // No se deberia repetir el nombre FALCABO
            
        } catch (FifaException fe) {
            assertEquals(FifaException.PARTICIPANT_WITH_SAME_NAME, fe.getMessage());
        }
        
    }
    
    @Test
    public void shouldThrowFifaExceptionStringCharSettingIncorrectlyAddTeam() {
        Fifa fifa = new Fifa();
        
        try {
            fifa.addTeam("Colombia", "150", "1350", "Samuel", "Amarillo-azul-rojo", "JAMES"); // En el apartado de position no debe ir un numero
            
        } catch (FifaException fe) {
            assertEquals(FifaException.ATTRIBUTE_STRING_CHAR_SETTING_INCORRECTLY, fe.getMessage());
        }
        
    }
    
    @Test
    public void shouldThrowFifaExceptionAttributeIntegerSettingIncorrectlyAddTeam() {
        Fifa fifa = new Fifa();
        
        try {
            fifa.addTeam("Colombia", "Samuel", "D", "Samuel", "Amarillo-azul-rojo", "L.DIAZ\nFALCABO\nCUADRADO"); // En el apartado de minutos no debe ir un String (letras)
            
        } catch (FifaException fe) {
            assertEquals(FifaException.ATTRIBUTE_INTEGER_SETTING_INCORRECTLY, fe.getMessage());
        }
        
    }
}