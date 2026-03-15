public class Sailor implements Prepared {

    private String name;
    private int rank;
    private boolean isDestroyed;
    private String cause;

    @Override
    public void autodestruct(String cause) {
        this.cause = cause;
        isDestroyed = true;
    }
    
    @Override
    public boolean isDestroyed(){
        return isDestroyed;
    }
    
    @Override
    public String destructionCause(){
        return cause;
    }
}
