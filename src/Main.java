/**
 * Created by Kleen star on 2017-03-22.
 */
public class Main {

    public static void main(String[] args){
        MainMemory mainMemory = new MainMemory();
        Processes pro1 = new Processes(mainMemory,0);
        Processes pro2 = new Processes(mainMemory,0);
        Processes pro3 = new Processes(mainMemory,0);
        Processes pro4 = new Processes(mainMemory,0);
        Processes pro5 = new Processes(mainMemory,0);
        Processes pro6 = new Processes(mainMemory,0);
        Processes pro7 = new Processes(mainMemory,0);
        Processes pro8 = new Processes(mainMemory,0);
        Processes pro9 = new Processes(mainMemory,0);
        Processes pro10 = new Processes(mainMemory,0);



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
