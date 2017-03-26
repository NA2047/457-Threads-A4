import java.util.ArrayList;

/**
 * This is the Main class where the processors are created
 * and the BroadcastSystem is created.
 */

public class Main {
    public static void main(String[] args) {
        BroadcastSystem broadcastSystem = new BroadcastSystem();
        TokenRing tokenRing = new TokenRing();
        broadcastSystem.start();
        int numberOfProcessors = 10;
        ArrayList<Processor> listProcessor = new ArrayList<>();
        ArrayList<BroadcastAgent> broadcastAgents = new ArrayList<>();

        // create all of the processors
        for (int i = 0; i < numberOfProcessors; i++) {
            listProcessor.add(new Processor(i, broadcastSystem));
            broadcastAgents.add(listProcessor.get(i).dsm.broadcastAgent);
        }

        // set all of the agents in the broadcast system
        broadcastSystem.setAgents(broadcastAgents);

        // start each thread
        for (Processor processor : listProcessor) {
            processor.start();
        }

    }
}
