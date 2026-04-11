package domain;

/**
 * Abstract class respresents a participant in the system.
 * A participant can be a player or a team.
 */
public abstract class Participant {
    protected String name;
    protected Integer minutes;
    protected char position; //For players [P(Goalkeepers),D(Defenders), M(Midfielders), A(Forwards)] 
                             // For teams [A .. L]
    
    /**
     * Constructs a Participant.
     * @param name the name of the parcitipant.
     * @param minutes the minutes played by the participant.
     * @param positon the position identifier.
     */
    public Participant(String name, Integer minutes, char position){
        this.name=name;
        this.minutes=minutes;
        this.position=position;
    }
    
    /**
     * Get the name's participant.
     * @return name the name of the participant.
     */
    public String name(){
        return name;
    }

    /**
     * Get the total minutes played by the participant.
     * @return the total number of minutes played.
     * @throws Fifa Exception - MINUTES_UNKNOWN If minutes are unknown.
     */
    public int minutes() throws FifaException{
       if (minutes == null) throw new FifaException(FifaException.MINUTES_UNKNOWN);
       return minutes;
    } 
 
    /**
     * Get the participant's position.
     * @return position the position like a character.
     */
    public char position(){
        return position;
    }
    
    /**
     * Calculate the market value of the participant.
     * @return market the Market Value of a player or the Weighted Market Value of a team
     * @throws FifaException- VALUE_UNKNOWN, if any marker value or minutes is unknown
     */
    public abstract int marketValue() throws FifaException;
    
    
    /**
     * Get the representation as string of the participants data.
     * @throws FifaException, if the data has problems (some unknown or erroneous data)
     * @return a string format with participant information.
     */    
    public abstract String data() throws FifaException;

}
