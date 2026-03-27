import java.time.LocalDate;
import java.util.Set;

/**
 * Write a description of class GiftCardWallet here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GiftCardWallet extends ECIWallet {
    
    private LocalDate creationDate;
    private LocalDate expirationDate;
    private double initialBalance;
    private Set<String> eligibleCategories;
     
    @Override
    public boolean canPaidCart(Cart cart) throws MercadoECIException {
        boolean sufficientBalance = balance >= cart.getTotalPrice();
        boolean notExpired = expirationDate.isAfter(LocalDate.now());
        boolean allowedCategories = cart.belongAllowedCategories(eligibleCategories);
        if (!sufficientBalance) throw new MercadoECIException(MercadoECIException.INSUFICIENT_BALANCE);
        if (!notExpired)  throw new MercadoECIException(MercadoECIException.EXPIRED_BALANCE);
        if (!allowedCategories) throw new MercadoECIException(MercadoECIException.NOT_ALLOWED_CATEGORY_ITEM);
        return sufficientBalance && notExpired && allowedCategories;
    }
    
    
}