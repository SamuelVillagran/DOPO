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
    
    public boolean canCheckout(int userId, String walletId) throws MercadoECIException {
        User u = loadUser(userId);
        if (u == null) throw new MercadoECIException(MercadoECIException.USER_NOT_FOUND);
        return u.canCheckoutCart(walletId);
    }
    
    public User loadUser(int userId) {
        return users.get(userId);
    }
}