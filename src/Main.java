/**
 * Created by Kleen star on 2017-03-22.
 */
public class Main {

    public static void main(String[] args){
        DSM dsm = new DSM();
        Processor pro1 = new Processor(dsm,1);
        Processor pro2 = new Processor(dsm,2);
        Processor pro3 = new Processor(dsm,3);
        Processor pro4 = new Processor(dsm,4);
        Processor pro5 = new Processor(dsm,5);
        Processor pro6 = new Processor(dsm,6);
        Processor pro7 = new Processor(dsm,7);
        Processor pro8 = new Processor(dsm,8);
        Processor pro9 = new Processor(dsm,9);
        Processor pro10 = new Processor(dsm,10);



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
