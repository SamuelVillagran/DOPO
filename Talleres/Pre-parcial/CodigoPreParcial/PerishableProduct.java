import java.time.LocalDateTime;

/**
 * Represents a perishable physical product that requires special storage conditions.
 */
public class PerishableProduct extends PhysicalProduct
{
    private LocalDateTime expirationDate;
    private LocalDateTime startDateRefrigeration;
    
    /**
     * Check if the PerishableProduct can be sold.
     * return True if it has kept in a fridge at least 5 days and doesn't expired,
     * otherwise return False.
     */
    public boolean canBeSold(){
        //Se omite logica.
        return true;
    }
}
