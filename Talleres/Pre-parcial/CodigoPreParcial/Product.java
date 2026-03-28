
/**
 * Write a description of class Product here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Product {

    private int stock;
    private String category;
    
    /**
     * Comprove if product can be purchased
     * @param productQuantity productQuantity is quantity that wants to purchase
     * @return true if there is suficient stock to purchase quantity that wants to purchase
     *          false otherwise
     */
    public boolean canBePurchased(int productQuantity) {
        return stock >= productQuantity;
    }
    
    public String getCategory() {
        return category;
    }
    
    public int getStock() {
        return stock;
    }

}