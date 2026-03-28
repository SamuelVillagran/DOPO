
/**
 * Represents a physical product that requires delivering. It extends the Product 
 * class incorporating attributes related to weight and delivery cost.
 */
public class PhysicalProduct extends Product
{
    private double weight;
    private int deliveryPrice;
    
    /**
     * Check if a user can get the product.
     * @return true if user is within 60 km otherwise return false.
     */
    public boolean isAllowedFor(User user){
        //Se omite lógica.
        return true;
    }
}
