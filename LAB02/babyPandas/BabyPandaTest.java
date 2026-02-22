import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class BabyPandaTest.
 *
 * @author Sanchez-Villagran
 */
public class BabyPandaTest
{
    @Test
    public void shouldDefineVariable() {
        BabyPandas bbp = new BabyPandas();
        bbp.define("a");
        assertTrue(bbp.ok());
    }
    @Test
    public void shouldAssignADataFrame() {
        BabyPandas bbp = new BabyPandas();
        bbp.define("a");
        
        String[][] data = {{"Carlos", "35", "Porfesor"}, 
        {"Laura", "29", "Desarrolladora"}, {"Fredy", "34","Arquitecto"}};
        bbp.assign("a", data);
        int[] wished = {3,3};
        assertTrue(bbp.ok());
        assertArrayEquals(wished, bbp.shape("a"));
    }
    @Test
    public void shouldNotAssignADataFrameWithUndefinedVariable() {
        BabyPandas bbp = new BabyPandas();
        
        String[][] data = {{"Carlos", "35", "Porfesor"},
        {"Laura", "29", "Desarrolladora"}, {"Fredy", "34","Arquitecto"}};
        bbp.assign("b", data);
        int[] wished = {3,3};
        assertFalse(bbp.ok());
    }
    @Test
    public void shouldNotAssignADataFrameWithInconsistentData(){
        BabyPandas bbp = new BabyPandas();
        String[][] data = {{"Carlos", "35", "Porfesor", "3104415567"},
        {"Laura", "29", "Desarrolladora"}, {"Fredy", "34","Arquitecto"}};
        bbp.define("a");
        bbp.assign("a", data);
        assertFalse(bbp.ok());
    }
    @Test
    public void shouldSearchHead(){
        BabyPandas bbp = new BabyPandas();
        String[][] data = {{"Carlos", "35", "Porfesor"}, {"Laura", "29", "Desarrolladora"},
        {"Fredy", "34","Arquitecto"}};
        bbp.define("a");
        bbp.assign("a", data);
        String head = bbp.head("a", 2);
        assertTrue(bbp.ok());
        assertNotNull(head);
    }
}