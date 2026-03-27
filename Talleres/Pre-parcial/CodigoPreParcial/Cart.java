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
    
    public boolean isValid() {
        for (PurchaseDetail pd : selectedProducts) {
            if (!pd.isAValidPurchaseDetail()) return false;
        }
        return true;
    }
    
    public boolean belongAllowedCategories(Set<String> categoriesAllowed) {
        for (PurchaseDetail pd : selectedProducts) {
            if (!pd.belongAllowedCategories(categoriesAllowed)) return false;
        }
        return true;
    }
}