import java.util.ArrayList;
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

        ArrayList<Processor> processors = new ArrayList<>();
        ArrayList<BroadcastAgent> broadcastAgents = new ArrayList<>();
        ConcurrentLinkedQueue<TokenRing> tokenRings = new ConcurrentLinkedQueue<>();
        ConcurrentLinkedQueue<TokenRingAgent> tokenRingAgents = new ConcurrentLinkedQueue<>();

        // create token rings
        for (int i = 0; i < numberOfTokenRings; i++) {
            TokenRing tr = new TokenRing(i);
            tokenRings.add(tr);
        }

        // create all of the processors
        for (int i = 0; i < numberOfProcessors; i++) {
            processors.add(new Processor(i, broadcastSystem, tokenRings, numberOfProcessors));
            broadcastAgents.add(processors.get(i).dsm.broadcastAgent);
            tokenRingAgents.add(processors.get(i).tra);
        }

        // set all of the agents in the broadcast system
        broadcastSystem.setAgents(broadcastAgents);

        if (enableTokenRing) {
            // set tokenRingAgents and start the token ring(s)
            for (TokenRing tr : tokenRings) {
                tr.setAgents(tokenRingAgents);
                tr.start();
            }
        } else {
            // for each token ring agent, call the disable method
            for (TokenRingAgent tra : tokenRingAgents) {
                tra.disable();
            }
        }

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
