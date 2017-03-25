/**
 * Created by Kleen star on 2017-03-22.
 */
public class Main {

    public static void main(String[] args){
        DSM dsm = new DSM();
        Processor pro1 = new Processor(dsm,0);
        Processor pro2 = new Processor(dsm,0);
        Processor pro3 = new Processor(dsm,0);
        Processor pro4 = new Processor(dsm,0);
        Processor pro5 = new Processor(dsm,0);
        Processor pro6 = new Processor(dsm,0);
        Processor pro7 = new Processor(dsm,0);
        Processor pro8 = new Processor(dsm,0);
        Processor pro9 = new Processor(dsm,0);
        Processor pro10 = new Processor(dsm,0);



        pro1.start();
        pro2.start();
        pro3.start();
        pro4.start();
        pro5.start();
        pro6.start();
        pro7.start();
        pro8.start();
        pro9.start();
        pro10.start();

    }
}
