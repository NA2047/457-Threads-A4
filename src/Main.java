import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * This is the Main class where the processors are created
 * and the BroadcastSystem is created.
 */

public class Main {
    public static void main(String[] args) {
        BroadcastSystem broadcastSystem = new BroadcastSystem();
        broadcastSystem.start();

        int numberOfProcessors = 10;
        int numberOfTokenRings = 1;
        boolean enableTokenRing = true;
        boolean multipleTR = true;

        Scanner in = new Scanner(System.in);
        System.out.println("number of processors? default is 10");
        System.out.print("> ");
        String procNum = in.nextLine();
        if (!procNum.equals("")){
            numberOfProcessors = Integer.parseInt(procNum);
        }
        System.out.println("enable token ring? default is true, f to disable");
        System.out.print("> ");
        if (in.nextLine().equals("f")){
            enableTokenRing = false;
            multipleTR = false;
        }
        else{
            System.out.println("multiple token rings? default is true, f to disable");
            System.out.print("> ");
            if (in.nextLine().equals("f")){
                multipleTR = false;
            }
        }

        String type;
        if (!enableTokenRing){
            type = "Peterson's";
        }
        else if (!multipleTR){
            type = "Single TR";
        }
        else {
            type = "Multi TR";
        }

        System.out.println("\n--- Settings ---");
        System.out.println("|   Algo: " + type);
        System.out.println("|   numberOfProcessors: " + numberOfProcessors + "   enableTokenRing: " + enableTokenRing + "   multipleTR: " + multipleTR + "\n");

        ArrayList<Processor> processors = new ArrayList<>();
        ArrayList<BroadcastAgent> broadcastAgents = new ArrayList<>();
        ConcurrentLinkedQueue<TokenRing> tokenRings = new ConcurrentLinkedQueue<>();
        ConcurrentLinkedQueue<TokenRingAgent> tokenRingAgents = new ConcurrentLinkedQueue<>();
//        BlockingQueue<TokenRingAgent> agentArray = new ArrayBlockingQueue<TokenRingAgent>(numberOfProcessors);;

        if (multipleTR){
            numberOfTokenRings = numberOfProcessors-1;
        }

        // create token rings
        for (int i = 0; i < numberOfTokenRings; i++) {
            TokenRing tr = new TokenRing(i);
            tokenRings.add(tr);
        }

        // create all of the processors
        for (int i = 0; i < numberOfProcessors; i++) {
            processors.add(new Processor(i, broadcastSystem, tokenRings, numberOfProcessors, multipleTR));
//            broadcastAgents.add(processors.get(i).dsm.broadcastAgent);
            tokenRingAgents.add(processors.get(i).tra);
//            agentArray.pu
        }

        // set all of the agents in the broadcast system
//        broadcastSystem.setAgents(broadcastAgents);

        if (enableTokenRing) {
            // set tokenRingAgents and start the token ring(s)
            for (TokenRing tr : tokenRings) {
//                tr.setAgents(tokenRingAgents);
                tr.start();
            }
        } else {
            // for each token ring agent, call the disable method
            for (TokenRingAgent tra : tokenRingAgents) {
                tra.disable();
            }
        }

        // TODO: order of token ring and processor starting interfering with first few processes
        // TODO: token ring agent is enabled right away? disable until processor is run?
        // TODO: let all processors get stuck at first store, then allow a token to be passed?

        // start each thread
        for (Processor p : processors) {
            for (TokenRing tr : tokenRings) {
                if (tr.flag == false){
                    tr.flag = true;
                }
            }
            p.start();
        }
    }
}
