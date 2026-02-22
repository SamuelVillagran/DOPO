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
     * Given row's number of an array and specific column filter this dataframe
     * and create a new that this just have the column with cells of row where intersects
     * row-column
     * @param rows This is the row's number that this going to compose new dataframe
     * @param columns This is the only column that going to compose new dataframe 
     */
    public DataFrame loc(int[] rows, String columns) throws Exception{
        if (rows == null || columns == null) throw new 
            IllegalArgumentException("Can't be parameters nulls");
            
        int numColumn = searchNumberColumn(columns);
        if (numColumn < 0) throw new Exception("Can't found the column");
        String[][] dataReformed = new String[rows.length][1];
        int numbersFinded = 0;
        for (int i = 0; i < data.length; i++) {
            int minValueRows = searchMinOnArray(rows);
            if (i == minValueRows) {
                deleteIntAtArray(rows, minValueRows);
                dataReformed[numbersFinded][0] = data[i][numColumn];
                numbersFinded++;
            }
        }
        
        return new DataFrame(dataReformed, new String[] {this.columns[numColumn]});
    }    
    
    public DataFrame select(String [] values){
        
        try {
            int [] perFiles = new int[values.length];
            
            for (int i = 0; i < values.length; i++) {
                perFiles[i] = Integer.parseInt(values[i]);
            }
            
            String[][] newData = new String[values.length][columns.length];
            int numbersRowPut = 0;
            int numbersColPut = 0;
            for (int j = 0; j < data.length; j++) {
                int minValue = searchMinOnArray(perFiles);
                if (j == minValue) {
                    deleteIntAtArray(perFiles, minValue);
                    for (int k = 0; k < data[j].length; k++) {
                        newData[numbersRowPut][numbersColPut] = data[j][k]; 
                        numbersColPut++;
                    }
                    numbersColPut = 0;
                    numbersRowPut++;
                }
            }
            
            return new DataFrame(newData, this.columns);
            
        } catch (Exception e) {
            return selectLikeString(values);
        }
        
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
    
    /*
     * Search the column's number with the name column
     * @param nameColumn It's the name column to search on dataframe's columns 
     */
    private int searchNumberColumn(String nameColumn) {
        for (int i = 0; i < columns.length; i++) {
            if (nameColumn.equals(columns[i])) return i;
        }
        return Integer.MIN_VALUE;
    }
    
    /*
     * Count the files with the value given
     * @param valueToCount It's value going to count how many times is in dataframe's data
     */
    private int countFilesWithValue(String valueToCount) {
        int count = 0;
        for (int i = 0; i < data.length ; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (valueToCount.equals(data[i][j])) count ++;
            }
        }
        return count;
    }
    
    /*
     * Give the min of ints Array
     * @param arrayToSearch It's the array where it's going to search the min
     */
    private int searchMinOnArray(int[] arrayToSearch) {
        int minValue = Integer.MAX_VALUE;
        for (int num : arrayToSearch) {
            if (num < minValue) minValue = num;
        }
        return minValue;
    }
    
    /*
     * Delete number given of array 
     * @param array It's the array to search the number's index and delete it 
     * @param number It's the number to search index and number is going eliminated 
     */
    private int[] deleteIntAtArray(int[] array, int number) {
        int[] arrayToGive = new int[array.length-1];
        int indexNew = 0;
        boolean skipped = false;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == number && !skipped) {
                skipped = true;
                continue;
            }
            array[indexNew] = array[i];
            indexNew++;
        }
        return arrayToGive;
    }
    
    /*
     * Delete string given of array 
     * @param array It's the array to search the number's index and delete it 
     * @param cell It's the cell's array to search and data is going eliminated 
     * @return A array string without string given 
     */
    private String[] deleteStringAtArray(String[] array, String cell) {
        String[] arrayToGive = new String[array.length-1];
        int indexNew = 0;
        boolean skipped = false;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == cell && !skipped) {
                skipped = true;
                continue;
            }
             
            array[indexNew] = array[i];
            indexNew++;
        }
        return arrayToGive;
    }
    
    /*
     * Convert column's names in index of columns
     * @param columns This is columns to search the index
     * @return Return an array of index of columns
     */
    private int[] convertColumnToIndex(String[] columns) {
        int[] indexes = new int[columns.length];
        int indexFinded = 0;
        
        for (int i = 0; i < this.columns.length; i++) {
            for (String col: columns) {
                if (col.equals(this.columns[i])) {
                    indexes[indexFinded] = i;
                    indexFinded++;
                }
            }
        }
        return indexes;
    }
    
    private DataFrame selectLikeString(String[] values) {
        String[][] newData = new String[this.data.length][values.length];
        int numbersRowPut = 0;
        int numbersColPut = 0;
        int[] indexOfValues = convertColumnToIndex(values);
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
            
                int minValue = searchMinOnArray(indexOfValues);
                if (j == minValue) {
                    deleteStringAtArray(values, this.columns[minValue]);
                    newData[numbersRowPut][numbersColPut] = data[i][j];
                    numbersColPut++;
                }
            }
            numbersColPut = 0;
            numbersRowPut++;
        }
        
        return new DataFrame(newData, this.columns);
    }
}
