package domain;  

/**
 * Players of teams of fifa, class's Player
 */
public class Player extends Participant{
    
    private Integer value;    //[0..300000000] 
    private String club;

    public Player(String name, Integer minutes, char position, Integer value, String club){
        super(name, minutes, position);
        this.value=value;
        this.club=club;
    }
    
    /**
     * Give the player's value at the market.
     * @return the value of the player.
     * @throws FifaException - VALUE_UNKNOWN If player doesn't have value
     */
    @Override
    public int marketValue() throws FifaException {
       if (value == null) throw new FifaException(FifaException.VALUE_UNKNOWN);
       return value;
    }    
    
    
    @Override
    public String data() throws domain.FifaException{ // Las versiones .format del String fueron ayudadas a hacer por Claude Sonnet 4.6 IA 2026
        if (!(name instanceof String || (Character) position instanceof Character)) throw new FifaException(FifaException.ATTRIBUTE_STRING_CHAR_SETTING_INCORRECTLY);
        String theData= String.format("%-10s Rol: %c", name+".", position);
        if (!(value instanceof Integer || minutes instanceof Integer)) throw new FifaException(FifaException.ATTRIBUTE_INTEGER_SETTING_INCORRECTLY);
        try{
            int marketValue = marketValue();
            if (marketValue < 0) throw new FifaException(FifaException.INCORRECT_MARKETVALUE);
            theData+= String.format(" Valor:%-12d Minutos:%d", marketValue, minutes());
        } catch (FifaException e) {
            theData += ". *** Datos incompletos";
        }
        return theData;
    }
    
    public Integer getValue() {
        return value;
    }
    
    public String getClub() {
        return club;
    }
}
