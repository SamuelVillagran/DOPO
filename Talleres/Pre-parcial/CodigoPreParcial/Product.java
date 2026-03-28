
/**
 * A PhysicalProduct in the MercadoECI system,it contains the
 * necessary information of all products.
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