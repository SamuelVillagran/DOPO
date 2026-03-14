public class Sailor implements Prepared {

    private String name;
    private int rank;

    public void autodestruct(String instruction) {
        if (instruction.equals("auto-destruct")) {
            System.out.println("Destroying...");
            System.out.println("Destroyed");
            System.out.println("Auto-destruct was a instruction given by this, this machine was destroyed.");
        }
    }
    
}
