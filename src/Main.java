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

        // create token rings
        for (int i = 0; i < numberOfTokenRings; i++){
            tokenRings.add(new TokenRing(i));
        }

        // create all of the processors
        for (int i = 0; i < numberOfProcessors; i++) {
            processors.add(new Processor(i, broadcastSystem, tokenRings, numberOfProcessors));
//            broadcastAgents.add(processors.get(i).dsm.broadcastAgent);
//            tokenRingAgents.add(processors.get(i).tokenRingAgent);
        }

        // set all of the agents in the broadcast system
//        broadcastSystem.setAgents(broadcastAgents);

        // set all of the agents in each token ring
        for (TokenRing tr : tokenRings){
            if (enableTokenRing){
                tr.setAgents(tokenRingAgents);
                tr.start();
            }
            else {
                tr.disable();
                for(TokenRingAgent tokenRingAgent1: tokenRingAgents){
                    tokenRingAgent1.disable();
                }
                // for each token ring agent, call the disable method
            }

        }

        // start each thread
        for (Processor p : processors) {
            p.start();
        }
    }
}
