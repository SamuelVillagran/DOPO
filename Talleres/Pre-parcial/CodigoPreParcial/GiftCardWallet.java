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
     
    /**
     * Verify if satisfy requirements to cart will be paid
     * @param cart cart is cart that it wants to paid
     * @return true if wallet has sufficient balance to pay and
     *              this wallet hasn't expired and
     *              every categories that user choose belong to products choosed at the cart
     *          false otherwise
     */
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