
/**
 * Defines the behavior to be autodestructed.
 */

public interface Prepared
{
    /**
     * Allows to the object autodestroys.
     * @param  cause cause is the reason that machine recibes to autodestruct.
     */
    void autodestruct(String cause);
    
    /**
     * Reports if the object has been auto-destroyed.
     */
    boolean isDestroyed();
    
    /**
     * Report the cause if the autodestruction.
     */
    String destructionCause();
}