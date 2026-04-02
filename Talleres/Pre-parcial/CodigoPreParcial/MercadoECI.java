import java.util.ArrayList;
import java.util.HashMap;

/**
 * Write a description of class MercadoECI here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class MercadoECI {
    private ArrayList<Purchase> purchases;
    private HashMap<Integer, User> users;
    private HashMap<String, Post> posts;
    private HashMap<String, Product> products;
    
    /**
     * Verify if it can do the payment complete 
     * @param userId userId is the identifier of the user whose cart is to be validated for the checkout process.
     * @param walletId walletId is the identifier of the wallet to be used for the cart payment.
     * @throws MercadoECIException - USER_NOT_FOUND: The user does not exist in the system.
     *           MercadoECIException - NO_CART: The user does not have a shopping cart that can be validated.
     *           MercadoECIException - INVALID_CART: The cart contains at least one product with a requested quantity exceeding the available stock.
     */
    public boolean canCheckout(int userId, String walletId) throws MercadoECIException {
        User u = loadUser(userId);
        return u.canCheckoutCart(walletId);
    }
    
    /**
     * Search the specific user with his specific id
     * @param userId userId is the id of user that wants to found at the users
     * @return User User that wants to found at users
     */
    public User loadUser(int userId) throws MercadoECIException {
        if (users.get(userId) == null) throw new MercadoECIException(MercadoECIException.USER_NOT_FOUND);
        return users.get(userId);
    }
}