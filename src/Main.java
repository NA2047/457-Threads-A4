import java.util.ArrayList;

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
        boolean enableTokenRing = false;

        ArrayList<Processor> processors = new ArrayList<>();
        ArrayList<BroadcastAgent> broadcastAgents = new ArrayList<>();
        ArrayList<TokenRing> tokenRings = new ArrayList<>();
        ArrayList<TokenRingAgent> tokenRingAgents = new ArrayList<>();

        // create all of the processors
        for (int i = 0; i < numberOfProcessors; i++) {
            processors.add(new Processor(i, broadcastSystem, tokenRings, numberOfProcessors));
            broadcastAgents.add(processors.get(i).dsm.broadcastAgent);
            tokenRingAgents.add(processors.get(i).tra);
        }

        // set all of the agents in the broadcast system
        broadcastSystem.setAgents(broadcastAgents);

        // set all of the agents in each token ring
        if (enableTokenRing) {
            // create token rings, set tokenRingAgents and start the token ring
            for (int i = 0; i < numberOfTokenRings; i++) {
                TokenRing tr = new TokenRing(i);
                tokenRings.add(tr);
                tr.setAgents(tokenRingAgents);

            }
        } else {
            // for each token ring agent, call the disable method
            for (TokenRingAgent tra : tokenRingAgents) {
                tra.disable();
            }
        }

        // start each thread
        for (Processor p : processors) {
            p.start();
        }
        for (TokenRing t : tokenRings){
            t.start();
        }
    }
}
