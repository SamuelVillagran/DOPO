import java.util.Set;

/**
 * Write a description of class PurchaseDetail here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PurchaseDetail {

    private Product product;
    private int productQuantity;

    /**
     * Verify if the product can be purchased
     * @return true if the product of this purchase detail can be purchased with their specifications
     *         false otherwise
     */
    public boolean isAValidPurchaseDetail() {
        return product.canBePurchased(productQuantity);
    } 
    
    /**
     * Verify if the product of this purchase detail belong to categories allowed
     * @param categoriesAllowed categoriesAllowed is categories given to verify if this product belong 
     * @return true if product's category of this purchase detail allowed to categories given 
     */
    public boolean belongAllowedCategories(Set<String> categoriesAllowed) {
        return categoriesAllowed.contains(product.getCategory());
    }
}