
/**
 * Represents a digital product that does not require physical handling.
 */
public class DigitalProduct extends Product
{
    private String url;
    private double sizeFile;

    /**
     * This product always can be purchased.
     */
    @Override
    public boolean canBePurchased(int productQuantity){
        return true;
    }
    
    /**
     * This poduct will have unlimited stock.
     */
    public int getStock(){
        return Integer.MAX_VALUE;
    }
}
