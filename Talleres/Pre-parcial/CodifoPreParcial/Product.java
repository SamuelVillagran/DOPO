
/**
 * Write a description of class Product here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Product {

    private int stock;
    private String category;
    
    public boolean canBePurchased(int productQuantity) {
        return stock >= productQuantity;
    }
    
    public String getCategory() {
        return category;
    }

}