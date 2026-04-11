package domain;


import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Collections;

/**
 * Fifa class
 * @author DOPO
 * @version ECI 2026
 */

public class Fifa {
    private ArrayList<Participant> participants;
    private TreeMap<String, Player> players;

    /**
     * Create a Fifa
     */
    public Fifa(){
        participants = new ArrayList<Participant>();
        players = new TreeMap<String,Player>();
        addSome();
    }

    private void addSome(){
        String [][] players= {{"L.DIAZ", "690","A","760000000","Bayer"},
                              {"JAMES", "516","M","2200000","Minnesota"},
                              {"BORRE", "445","A","4400000","Sport Club"},
                              {"LUCUMI", "1250","D","125000000","Bologna"},
                              {"VARGAS", "1160","P","540000","Atlas"}};
        for (String [] p: players){
            try
            {
                addPlayer(p[0],p[1],p[2],p[3],p[4]);
            }
            catch (FifaException fe)
            {
                fe.printStackTrace();
            }
        }
        
        String [][] teams = {{"COLOMBIA","1620", "K", "Lorenzo", "Amarill-Rojo-Azul", "L.DIAZ\nJAMES\nBORRE\nLUCUMI\nVARGAS"}};
        for (String [] t: teams){
            try
            {
                addTeam(t[0],t[1],t[2],t[3],t[4],t[5]);
            }
            catch (FifaException fe)
            {
                fe.printStackTrace();
            }
        }
    }


    /**
     * Consult a participant
     */
    public Participant consult(String name){
        Participant c=null;
        for(int i=0;i<participants.size() && c == null;i++){
            if (participants.get(i).name().compareToIgnoreCase(name)==0) 
               c=participants.get(i);
        }
        return c;
    }

    
    /**
     * Add a new player
    */
    public void addPlayer(String name, String minutes, String position, String value, String club) throws FifaException{ 
        int mins = 0, val = 0;
        try { // El try fue ayudado a hacer por Gemini IA 2026
            mins = Integer.parseInt(minutes);
            val = Integer.parseInt(value);
            
        } catch (NumberFormatException e) {
            throw new FifaException(FifaException.ATTRIBUTE_INTEGER_SETTING_INCORRECTLY);
        }
        
        char pos = 0;
        try {
            pos = position.charAt(0);
        } catch (StringIndexOutOfBoundsException e) {
            throw new FifaException(FifaException.ATTRIBUTE_STRING_CHAR_SETTING_INCORRECTLY);
        }
        
        Player np=new Player(name,mins, position.charAt(0), val, club);
        participants.add(np);
        players.put(name.toUpperCase(), np); 
    }
    
    /**
     * Add a new team
    */
    public void addTeam(String name, String minutes, String position, String manager, String uniform, String thePlayers) throws FifaException{ 
        Integer minutesInt = 0;
        try {// El try fue ayudado a hacer por Claude Sonnet 4.6 IA 2026
            minutesInt = Integer.parseInt(minutes);
            
        } catch (NumberFormatException e) {
            throw new FifaException(FifaException.ATTRIBUTE_INTEGER_SETTING_INCORRECTLY);
        }
        char positionChar = ' ';
        try {
            positionChar = position.charAt(0);
        } catch (StringIndexOutOfBoundsException e) {
            throw new FifaException(FifaException.ATTRIBUTE_STRING_CHAR_SETTING_INCORRECTLY);
        }
    
        Team c = new Team(name, minutesInt, positionChar, manager, uniform);
        String[] aPlayers = thePlayers.split("\n");
        for (String b : aPlayers) {
            Player p = players.get(b.trim().toUpperCase());
        if (p == null) throw new FifaException(FifaException.PARTICIPANT_WITH_SAME_NAME); // o crea una constante nueva
            c.addPlayer(p);
        }
        participants.add(c);
    }

    /**
     * Consults the participants that start with a prefix
     * @param  
     * @return 
     */
    public ArrayList<Participant> select(String prefix){
        ArrayList <Participant> answers=new ArrayList<Participant>();
        prefix=prefix.toUpperCase();
        for(int i=0;i<=participants.size();i++){
            if(participants.get(i).name().toUpperCase().startsWith(prefix)){
                answers.add(participants.get(i));
            }   
        }
        return answers;
    }


    
    /**
     * Consult selected participants
     * @param selected
     * @return  
     */
    public String data(ArrayList<Participant> selected){
        StringBuffer answer=new StringBuffer();
        answer.append(participants.size() + " elementos\n");
        int repeticions = 0;
        ArrayList<String> nameParticipants = new ArrayList<>();
        for (Participant p : participants) {
            nameParticipants.add(p.getName());
        }
        
        for(Participant p : selected) {
            try{
                repeticions = Collections.frequency(nameParticipants, p.getName());
                if (repeticions > 1) throw new FifaException(FifaException.PARTICIPANT_WITH_SAME_NAME);
                answer.append('>' + p.data());
                answer.append("\n");
            }catch(FifaException e){
                answer.append("**** "+e.getMessage());
            }
        }    
        return answer.toString();
    }
    
    
     /**
     * Return the data of participants with a prefix
     * @param prefix
     * @return  
     */ 
    public String search(String prefix){
        return data(select(prefix));
    }
    
    
    /**
     * Return the data of all participants
     * @return  
     */    
    public String toString(){
        return data(participants);
    }
    
    /**
     * Consult the number of participants
     * @return 
     */
    public int numberParticipants(){
        return participants.size();
    }
    
    public TreeMap<String, Player> getPlayers() {
        return players;
    }
    
    public ArrayList<Participant> getParticipants() {
        return participants;
    }

}
