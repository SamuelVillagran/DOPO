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
    public int marketValue() throws FifaException{
       if (value == null) throw new FifaException(FifaException.VALUE_UNKNOWN);
       return value;
    }    
    
    
    @Override
    public String data(){ // Las versiones .format del String fueron ayudadas a hacer por Claude Sonnet 4.6 IA 2026
        String theData= String.format("%-10s Rol: %c", name+".", position);
        try{
            theData+= String.format(" Valor:%-12d Minutos:%d", marketValue(), minutes());
        } catch (FifaException e){
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
