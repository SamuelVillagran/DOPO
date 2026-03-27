
/**
 * Write a description of class Wallet here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ECIWallet {
    protected String id;
    protected double balance;
    
    public boolean canPaidCart(Cart cart) throws MercadoECIException {
        boolean sufficientBalance = balance >= cart.getTotalPrice();
        if (!sufficientBalance) throw new MercadoECIException(MercadoECIException.INSUFICIENT_BALANCE);
        return sufficientBalance;
    }
    
    public String getID() {
        return id;
    }
}