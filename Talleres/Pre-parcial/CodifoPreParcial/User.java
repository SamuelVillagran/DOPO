import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Write a description of class User here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class User {
    
    private int id;
    private String name;
    private String email;
    private LocalDate birthDay;
    private String shippingAdress;
    private Cart cart;
    private ArrayList<Post> posts;
    private ArrayList<ECIWallet> wallets;
    
    public boolean canCheckoutCart(String walletId) throws MercadoECIException {
        if (!cart.isValid()) throw new MercadoECIException(MercadoECIException.INVALID_CART);
        if (cart == null) throw new MercadoECIException(MercadoECIException.NO_CART);
        
        ECIWallet wallet = loadWallet(walletId);
        boolean canPay = wallet.canPaidCart(cart);
        return canPay;
    }
    
    public ECIWallet loadWallet(String walletId) {
        for (ECIWallet w : wallets) {
            if (w.getID().equals(walletId)) return w;
        }
        return null;
    }
}