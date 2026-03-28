import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Write a description of class User here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class User implements Trustable {
    private int id;
    private String name;
    private String email;
    private LocalDate birthDay;
    private String shippingAdress;
    private Cart cart;
    private HashMap<Integer, Post> posts;
    private ArrayList<ECIWallet> wallets;
    private ArrayList<Purchase> purchases;
    
    /**
     * This method verifies if the wallet's cart is valid for payment.
     * @param walletId walletId is the identifier of the wallet to be used for the cart payment.
     * @throws MercadoECIException - NO_CART: The user does not have a shopping cart that can be validated.
     *         MercadoECIException - INVALID_CART: The cart contains at least one product with a requested quantity exceeding the available stock.
     */
    public boolean canCheckoutCart(String walletId) throws MercadoECIException {
        if (!cart.isValid()) throw new MercadoECIException(MercadoECIException.INVALID_CART);
        if (cart == null) throw new MercadoECIException(MercadoECIException.NO_CART);
        ECIWallet wallet = loadWallet(walletId);
        boolean canPay = false;
        try {
            canPay = wallet.canPaidCart(cart);
            
        } catch (MercadoECIException mee) {
            if (!canPay) {
                Notification n = new Notification(mee.getMessage());    
            }
            mee.printStackTrace();
        }
        return canPay;
    }
    
    /**
     * Search the specific wallet with its specific id
     * @param walletId walletId is the id of user that wants to found at the wallets
     * @return Wallet Wallet that wants to found at wallets
     */
    public ECIWallet loadWallet(String walletId) {
        for (ECIWallet w : wallets) {
            if (w.getID().equals(walletId)) return w;
        }
        return null;
    }
    
    /**
     * Verify if this is a reliable user 
     * @return true if this is a reliable user
     *          false if It is considered unreliable if the majority of their purchases have a "denied" status or if any of their posts are unreliable.
     */
    public boolean isReliable() {
        int contPurchaseUnreliable = 0;
        String statePurchase;
        for (Purchase p : purchases) {
            statePurchase = p.getStatus();
            if (statePurchase.equals("denegado")) contPurchaseUnreliable++;
        }
        if (contPurchaseUnreliable > posts.size()) return false; // Si la mayoria de sus compras tiene estado denegado
        for (Post p: posts.values()) {
            if (!isReliable()) return false; // Si alguno de sus posts no es confiable
        }
        return true;
    }
    
    /**
     * Verify the equals between objects
     * @return true if some attributes of both objects are same (id, name, email)
     *      false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User user = (User) obj;
        String userName = user.getName();
        String userEmail = user.getEmail();
        return id == user.getID() && name.equals(userName) && email.equals(userEmail);
    }
    
    public int getID() {
        return id;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getName() {
        return name;
    }
}