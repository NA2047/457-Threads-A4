/**
 * Created by Sharon on 3/24/2017.
 */

public class LocalMemory {
    private int turn = -1;
    private int flag = -1;

    // return the value of x read from memory
    public int load(String x){
        if (x.equals("turn")) {
            return turn;
        }
        else if (x.equals("flag")){
            return flag;
        }
        else {
            System.out.println("Error: not stored in memory.");
            return -2;
        }
    }

    // stores the value v of x to memory
    public void store(String x, int v){
        if (x.equals("turn")) {
            turn = v;
        }
        else if (x.equals("flag")){
            flag = v;
        }
        else {
            System.out.println("Error: cannot store in memory.");
        }
    }
}
