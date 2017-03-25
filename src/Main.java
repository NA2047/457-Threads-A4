/**
 * Created by Kleen star on 2017-03-22.
 */
public class Main {

    public static void main(String[] args){
        BroadcastSystem broadcastSystem = new BroadcastSystem();
        // this broadcastSystem needs to be accessible by each broadcastAgent

        Processor pro0 = new Processor(0, broadcastSystem);
        Processor pro1 = new Processor(1, broadcastSystem);
        Processor pro2 = new Processor(2, broadcastSystem);
        Processor pro3 = new Processor(3, broadcastSystem);
        Processor pro4 = new Processor(4, broadcastSystem);
        Processor pro5 = new Processor(5, broadcastSystem);
        Processor pro6 = new Processor(6, broadcastSystem);
        Processor pro7 = new Processor(7, broadcastSystem);
        Processor pro8 = new Processor(8, broadcastSystem);
        Processor pro9 = new Processor(9, broadcastSystem);

        BroadcastAgent [] broadcastAgents = {
            pro0.dsm.broadcastAgent,
            pro1.dsm.broadcastAgent,
            pro2.dsm.broadcastAgent,
            pro3.dsm.broadcastAgent,
            pro4.dsm.broadcastAgent,
            pro5.dsm.broadcastAgent,
            pro6.dsm.broadcastAgent,
            pro7.dsm.broadcastAgent,
            pro8.dsm.broadcastAgent,
            pro9.dsm.broadcastAgent
        };

        broadcastSystem.setAgents(broadcastAgents);

        pro0.start();
        pro1.start();
        pro2.start();
        pro3.start();
        pro4.start();
        pro5.start();
        pro6.start();
        pro7.start();
        pro8.start();
        pro9.start();
    }
}
