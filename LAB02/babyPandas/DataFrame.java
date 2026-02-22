import java.util.Arrays;

public class DataFrame {
    
    private String[][] data;
    private String[] columns;
    private int[] shape;
    
    public DataFrame(String [][] data, String [] columns){
        this.data = data;
        this.columns = columns;
        shape = new int[] {data.length, columns.length};
    }
    
    /**
     * 
     */
    public DataFrame loc(int [] rows, String columns){
        return null;
    }    
    
    public DataFrame select(String [] values){
        return null;
    }      

    public DataFrame concat(DataFrame [] dfs, byte axis){
        return null;
    }

    public int [] shape(){
        return shape;
    }    
    
   
    /**
     * Find the rows of the DataFrame given a number.
     * @param rows Number of rows to see, if is positive takes the first
     * if it takes negative vaules take all less the last n rows.
     * @return result String with information dataframe.
     */
    public String head(int rows) {
      if(data == null){
          return "";
      }
      String result = "";
      
      result += "   ";
      for(int i = 0; i < columns.length; i++){
          result += columns[i];
          if(i < columns.length - 1){
              result += "   ";
          }
      }
      result += "\n";
      
      int limit;
      if(rows >= 0){
          limit = Math.min(rows, columns.length);
      }else{
            limit = data.length + rows;
            if(limit < 0){
                limit = 0;
            }
        }
      
      for(int j = 0; j < limit; j++){
          result += j + "   ";
          for(int i = 0; i < columns.length; i++){
              result += data[j][i];
              if(i < columns.length -1){
                  result += "   ";
              }
          }
          result += "\n";
      }
      return result;
    }
    
    public boolean equals(DataFrame df) {
        
        if (df == null)  
        
        if (!(Arrays.equals(df.getShape(), this.shape))) return false;
        
        for (int i = 0; i < columns.length; i++) {
            if (!(this.columns[i].equals(df.getColumns()[i]))) return false;
        }
        
        for (int j = 0; j < data.length; j++) {
            for (int k = 0; k < data[j].length; k++) {
                if (!(this.data[j][k].equals(df.getData()[j][k]))) return false;
            }
        }
        
        return true;
    }
    
    public boolean equals(Object o) {
        if (this == o) return true;
        
        if (o == null) return false;
        
        if (!(o instanceof DataFrame)) return false;
        
        if (o instanceof DataFrame) return this.equals((DataFrame) o);
        
        if (o instanceof int[]) return this.shape.equals((int[]) o);
        
        return false;
    }
    
    public String[] getColumns() {
        return columns;
    }
    
    public int[] getShape() {
        return shape;
    }
    
    public String[][] getData() {
        return data;
    }
}
