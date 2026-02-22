import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DataFrameTest{

    
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp(){
        
    }

    
    @Test
    public void shouldCreateSmallestDataFrame(){
        String [] columns = {"Nombre", "Edad", "Profesión"};
        String [][] data={};
        int [] shape={0,3};
        DataFrame df=new DataFrame(data,columns);
        assertArrayEquals(shape, df.shape());     
    }    
   
    @Test
    public void shouldCreateOtherDataFrame(){
        String [] columns = {"Nombre", "Edad", "Profesión"};
        String [][] data = {{"Carlos", "35", "Profesor"}, 
        {"Ana", "42", "Doctor"}, 
        {"Jorge", "30", "Arquitecto"},
        {"Elena", "25", "Diseñador"}};
        int [] shape={4, 3};
        DataFrame df=new DataFrame(data,columns);
        assertArrayEquals(shape, df.shape());   
    }    
    
    @Test
    public void shouldNotCreateBadDataFrame(){
        String [] columns = {"Nombre", "Edad", "Profesión"};
        String [][] data = {{"Carlos", "35"}, 
        {"Ana", "42", "Doctor"}, 
        {"30", "Arquitecto"},
        {"Elena", "25", "Diseñador"}};
        int [] shape={2,3};
        DataFrame df=new DataFrame(data,columns);
        assertNotEquals(shape, df.shape());   
    }      
    
    @Test
    public void shouldPass() {
        DataFrame df1 = new DataFrame(new String[][] {{"AYED", "4"}, {"AYSW", "4"}, {"FUPR", "3"}}, new String [] {"Asignatura", "Créditos"}); 
        DataFrame df2 = new DataFrame(new String[][] {{"AYED", "4"}, {"AYSW", "4"}, {"FUPR", "3"}}, new String [] {"Asignatura", "Créditos"});
        assertEquals(df1, df2);
    }
    
    @Test
    public void shouldFail() {
        try {
            String[] columns = {"Pass", "No Pass"};
            String[][] data = {{"NO", "Yes"}, {"NO", "NO"}, {"NO", "NO"}};
            DataFrame df = new DataFrame(data, columns);
            assertNotNull(df);
            fail("This test should fail");
        } catch (Exception e) {
            fail("This test should fail");
        }
    }
    
    @Test
    public void shouldError() {
        String[] columns = {"Car", "Bycicle"};
        String[][] data = {{"YES", "Yes"}, {"YES", "NO"}, {"HAVE", "HAVE"}};
        DataFrame df = new DataFrame(data, columns);
        throw new IllegalArgumentException("ERROR!");
    }
    
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
    
    @Test
    public void shouldReturnFirstRow(){
        String[] columns = {"Nombre", "Edad", "Profesión"};
        String[][] data = {{"Laura", "29", "Profesora"}, {"Ana", "25", "Doctora"},
            {"Diego", "45", "Ingeniero"}};
        DataFrame df = new DataFrame(data, columns);
        String result = df.head(1);
        assertTrue(result.contains("Laura"));
        assertTrue(result.contains("29"));
        assertTrue(result.contains("Profesora"));
    }
    
    @Test
    public void shouldIgnoreLastRowWithNegativeIndex(){
        String[] columns = {"Nombre", "Edad", "Profesión"};
        String[][] data = {{"Laura", "29", "Profesora"}, {"Ana", "25", "Doctora"},
            {"Diego", "45", "Ingeniero"}};
        DataFrame df = new DataFrame(data, columns);
        String result = df.head(-1);
        assertFalse(result.contains("Diego"));
        assertTrue(result.contains("Ana"));
    }
    
    @Test
    public void shouldReturnColumnsNameWithIndexZero(){
        String[] columns = {"Nombre", "Edad", "Profesión"};
        String[][] data = {{"Laura", "29", "Profesora"}, {"Ana", "25", "Doctora"},
            {"Diego", "45", "Ingeniero"}};
        DataFrame df = new DataFrame(data, columns);
        String result = df.head(0);
        assertFalse(result.contains("Diego"));
    }
}
