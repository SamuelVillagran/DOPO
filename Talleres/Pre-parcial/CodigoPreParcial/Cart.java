import java.util.ArrayList;
import java.util.Set;

/**
 * Write a description of class Cart here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Cart {
    private double totalPrice;
    private ArrayList<PurchaseDetail> selectedProducts;
    
    public double getTotalPrice() throws MercadoECIException {
        return totalPrice;
    }
    
    /**
     * Comprobe if cart is valid with its prurchases datails 
     * @return true if every purchase detail is valid
     *          false if some purchase detail isn't valid
     */
    public boolean isValid() {
        for (PurchaseDetail pd : selectedProducts) {
            if (!pd.isAValidPurchaseDetail()) return false;
        }
        return true;
    }
    
    /**
     * Verify if products category given is within categories allowed
     * @param true if every products categories given belong to the categories of products of this cart
     *        false otherwise
     */
    public boolean belongAllowedCategories(Set<String> categoriesAllowed) {
        for (PurchaseDetail pd : selectedProducts) {
            if (!pd.belongAllowedCategories(categoriesAllowed)) return false;
        }
        return true;
    }
}