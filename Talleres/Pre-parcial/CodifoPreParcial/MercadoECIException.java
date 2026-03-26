
/**
 * MercadoECI class
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class MercadoECIException extends Exception {
    
    public static final String USER_NOT_FOUND = "User isn't MercadoECI";
    public static final String NO_CART = "Don't exists cart assigned";
    public static final String INVALID_CART = "Invaid cart, Cart contains at least a product with quantity over stock";
    public static final String INSUFICIENT_BALANCE = "User doesn't enought money";
    public static final String NOT_ALLOWED_CATEGORY_ITEM = "Category isn't found at categories allowed";
    public static final String EXPIRED_BALANCE = "Have expired balance to pay";
    
    
    public MercadoECIException(String message) {
            super(message);
    }

}