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

    public boolean isAValidPurchaseDetail() {
        return product.canBePurchased(productQuantity);
    } 
    
    public boolean belongAllowedCategories(Set<String> categoriesAllowed) {
        return categoriesAllowed.contains(product.getCategory());
    }
}