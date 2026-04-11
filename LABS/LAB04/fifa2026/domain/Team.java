package domain;  
 
import java.util.ArrayList;

public class Team extends Participant{
    
    private String manager;
    private String uniform;
    
    private ArrayList<Player> players;
    
    /**
     * Constructs a new Team
     * @param name
     * @param type
     */
    public Team(String name, int minutes, char position, String manager, String uniform){
        super(name, minutes, position);
        this.manager=manager;
        this.uniform=uniform;
        players= new ArrayList<Player>();
    }


     /**
     * Add a new Player
     * @param c
     */   
    public void addPlayer(Player c){
        players.add(c);
    }
    
    
   /**
    * Calculates the weighted average of the market values of players.
    * It takes minutes played as weights.
    * @return the team's weighted average marked value. 
    * @Trows FifaException - IMPOSSIBLE If players doesn't have minutes or if there's no players.
    */
   public int marketValue() throws FifaException{
       double value = 0;
       double totalMinutes = 0;
       
       if(players.isEmpty()) throw new FifaException(FifaException.IMPOSSIBLE);
       
       for(Player p : players){
           totalMinutes += p.minutes();
       }
       
       if(totalMinutes == 0) throw new FifaException(FifaException.IMPOSSIBLE);
       
        for(Player p: players){
            value += p.marketValue() * (p.minutes() / totalMinutes);
        }
        
        return (int)value;
    }


   /**
     * Returns the expectet Market Value 
     * @return
     * @throws FifaException, if any marker value or minutes is unknown
     */
    //If more than half of the players have no recorded minutes, the total number of players is used to average. 
    //Otherwise, the average minutes played by known players is used for those whose minutes are unknown.
    
    public int expectedMarketValue() throws FifaException{
        double expectedValue = 0.0;
        int contPlayersWithoutMinutes = 0;
        double currentExpectedValue = 0.0;
        try {
            for (Player p : players) {
                p.minutes(); // Verificar cuantos jugadores tienen los minutos registrados
            }    
            if (contPlayersWithoutMinutes == 0) return marketValue();
            if (contPlayersWithoutMinutes > players.size()/2) {
                for (Player p : players) {
                    expectedValue += p.marketValue();
                }
                expectedValue /= players.size();
            } else {
                for (Player p : players) {
                    if ((Integer) p.minutes() != null) currentExpectedValue += p.minutes(); // Cuenta tiempo total jugadores con valor de minutos
                }   
                currentExpectedValue /= players.size() - contPlayersWithoutMinutes; // Cuenta ese tiempo total y lo divide en los jugadores que si tienen
            }
        } catch (FifaException fe) {
            String errorMessage = fe.getMessage();
            if (errorMessage.equals(FifaException.MINUTES_UNKNOWN)) contPlayersWithoutMinutes++;
            if (errorMessage.equals(FifaException.VALUE_UNKNOWN)) throw new FifaException(errorMessage);
        }
        return (int) expectedValue;
    }
    
    
    /**
     * If a player's market value or minutes played are unknown, default values are used.
     * @param defaultMarketValue the value used when marketvalue player is unknown.
     * @param defaultMinutes the value used when minute player is unknown.
     * @return the Marked Value using default values.
     * @throws FifaException if minutes team or players ar unknown.
     */
    public int defaultMarkedValue(int defaultMarketValue, int defaultMinutes) throws FifaException{
        if (players.isEmpty() || players == null) throw new FifaException(FifaException.IMPOSSIBLE);
        double totalMin = 0;
        double sum = 0;
        double marketValue = 0;
        double minutes = 0;
    
        for(Player p: players){
            try{
                totalMin += p.minutes();
            } catch (FifaException f) {
                if (FifaException.MINUTES_UNKNOWN.equals(f.getMessage())) totalMin += defaultMinutes;
            }
        }
        
        if (totalMin == 0) throw new FifaException(FifaException.IMPOSSIBLE);
    
        for (Player p : players) {
            try{
                minutes = p.minutes();
            } catch(FifaException f){
                if (FifaException.MINUTES_UNKNOWN.equals(f.getMessage())) minutes = defaultMinutes;
            }
            
            try{
                marketValue = p.marketValue();
            } catch (FifaException f) {
                if (FifaException.VALUE_UNKNOWN.equals(f.getMessage())) marketValue = defaultMarketValue;
            }
            sum += marketValue *(minutes / totalMin);
        }
        return (int) sum;
    }
    
    
    @Override
    public String data() throws FifaException {
        StringBuffer answer=new StringBuffer();
        answer.append(name+".\t Grupo: "+position+".\t Valor Promedio:" +marketValue());
        for(Player p: players) {
            answer.append("\n\t"+p.data());
        }
        return answer.toString();
    } 
    

}
