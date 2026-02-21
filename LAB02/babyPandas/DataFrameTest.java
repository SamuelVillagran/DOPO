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
        assertEquals(shape, df.shape());     
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
        assertEquals(shape, df.shape());   
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
        assertEquals(shape, df.shape());   
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
            String[][] data = {{"NO", "Yes"}, {0, 1}, {false, true}};
            DataFrame df = new DataFrame(data, columns);
        } catch (Exception e) {
            fail("This test should fail");
        }
    }
    
    @Test
    public void shouldError() {
        String[] columns = {"Car", "Bycicle"};
        String[][] data = {{"YES", "Yes"}, {1, 1}, {true, true}};
        DataFrame df = new DataFrame(data, columns);
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
}
