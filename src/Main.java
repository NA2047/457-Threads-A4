/**
 * Created by Kleen star on 2017-03-22.
 */
public class Main {

    public static void main(String[] args){
        String turn = "turn";
        String flag = "flag";
        BroadcastSystem broadcastSystem = new BroadcastSystem();
        // this broadcastSystem needs to be accessible by each broadcastAgent

        // create all of the processors
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

        Processor [] processors = {
                pro0,
                pro1,
                pro2,
                pro3,
                pro4,
                pro5,
                pro6,
                pro7,
                pro8,
                pro9
        };

        // The array of all broadcast agents to set in the broadcast system
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

        // set all of the agents in the broadcast system
        broadcastSystem.setAgents(broadcastAgents);

        // run the processors
        for (Processor p : processors){
            p.start();
//            p.dsm.store("turn", p.processID);
//            for (Processor pr : processors){
//                System.out.print("pro" + pr.processID + " --- ");
//                System.out.println(pr.dsm.load(turn));
//            }
        }
    }
}
