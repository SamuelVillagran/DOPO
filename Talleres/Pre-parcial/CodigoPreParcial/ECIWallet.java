
/**
 * Write a description of class Wallet here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ECIWallet {
    protected String id;
    protected double balance;
    
    /**
     * Verify if satisfy requirements to cart will be paid
     * @param cart cart is cart that it wants to paid
     * @return true if wallet has sufficient balance to pay
     *          false otherwise
     */
    public boolean canPaidCart(Cart cart) throws MercadoECIException {
        boolean sufficientBalance = balance >= cart.getTotalPrice();
        if (!sufficientBalance) throw new MercadoECIException(MercadoECIException.INSUFICIENT_BALANCE);
        return sufficientBalance;
    }
    
    public String getID() {
        return id;
    }
}