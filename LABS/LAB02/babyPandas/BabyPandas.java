import java.util.HashMap;

/** BabyPandas.java
 * 
 * @author ESCUELA 2026-01
 */
    
public class BabyPandas{
    
    private HashMap<String, DataFrame> variables;
    private boolean lastOperOk;
    
    /**
     * Constructor default clase.
     */
    public BabyPandas(){
        variables = new HashMap<>();
        lastOperOk = true;
    }

    /**
     * Define a new variable.
     */
    public void define(String name){
        variables.put(name, null);
        lastOperOk = true;
    }
     
    /**
     * Assign a DataFrame to an existing variable.
     */
    public void assign(String a, String [][] dataFrame){
        String[] columns = {"Nombre", "Edad", "Profesión"};
        
        if(!variables.containsKey(a)){
            lastOperOk = false;
            return;
        }
        
        for(int i = 0; i < dataFrame.length; i++){
            int longData = dataFrame[i].length;
            if(longData != columns.length){
                lastOperOk = false;
                return;
            }
        }
        
        DataFrame df = new DataFrame(dataFrame, columns);
        variables.put(a, df);
        lastOperOk = true;
    }
    
    /**
     * Return DataFrame's shape
     */
    public int[] shape(String a){
        if(!variables.containsKey(a)){
            lastOperOk = false;
            return new int[]{0};
        }
        lastOperOk = true;
        return variables.get(a).shape();
    }
    
    
    //Assigns the value of a unary operation to a variable
    // a = b op parameters
    //The operator characters are: 'r' select rows, 'c' select columns, '?' select condition
    //The parameters for 'r' are [index1, index2, ...]
    //The parameters for 'c' are [column1, column2, ...]
    //The parameters for '?' are [valueColumn1, valueColumn2, ...]

    public void assignUnary(String a, String b, char op, String [] parameters){
        
    }
      
    
    //Assigns the value of a binary operation to a variable
    // a = b op c
    //The operator characters are:  'r' concate by rows, 'c' concate by columns
    public void assignBinary(String a, String b, char op, String c){
        
    }
  
    
    /**
     * Return some rows of the DataFrame
     */
    public String head(String variable, int rows){
        if(!variables.containsKey(variable)){
            lastOperOk = false;
            return "";
        }
        lastOperOk = true;
        return variables.get(variable).head(rows);
    }
    
    
    /**
     * Check if the last operation was successfully completed.
     */
    public boolean ok(){
        return lastOperOk;
    }
}