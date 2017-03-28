import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * This class represents a processor.
 * <p>
 * Executes in a separate thread.
 */

public class Processor extends Thread {
    DSM dsm;
    TokenRingAgent tra;
    int processID;
    static int test = 0;
    int N;

    /**
     * The constructor of Processor.
     * processID and DSM are set.
     *
     * @param processID       is the assigned processID.
     * @param broadcastSystem is the global instance.
     */
    public Processor(int processID, BroadcastSystem broadcastSystem, ConcurrentLinkedQueue<TokenRing> tokenRings, int numberOfProcessors) {
        this.processID = processID; // process number
        tra = new TokenRingAgent(tokenRings, this);
        dsm = new DSM(broadcastSystem, this, tra);
        this.N = numberOfProcessors;
    }

    /**
     * The overridden run method.
     * Calls Peterson's algorithm.
     */
    @Override
    public void run() {
        petersonsN();
        System.out.println(processID + " is done");
    }

    /**
     * This method represents Peterson's algorithm.
     */
    public void petersonsN() {
        // <Entry Section>
        for (int k = 0; k < N - 1; k++) {
//            System.out.println(".");
//            System.out.println(processID + " went into loop to store flag and turn --- iteration: " + k);
            // processor that is competing at level k
            dsm.store("flag" + processID, k);
//            System.out.println("     " + processID + " stored flag");
            // tells the current level that it's ProcessorIDs turn
            dsm.store("turn" + k, processID);
//            System.out.println("     " + processID + " stored turn");
//                Thread.sleep(100);

            //the while loop from peterson's algorithm
            while ((ThereExists(k)) && (dsm.load("turn" + k) == processID)) {
//                System.out.println(processID + " is in while");
            }
        }
        // END <Entry Section>

        // <Critical Section>
//        System.out.println("   ***Process " + processID + " is in the CS***");
        System.out.println("   Increment test value by processor " + processID + "  =  " + (++test));

//        try {
//            System.out.println("   Increment test value by processor " + processID + "  =  " + (++test));
//            Thread.sleep(50); // short delay to demonstrate that the algorithm is not perfect
//
//        } catch (InterruptedException e1) {
//            e1.printStackTrace();
//        }

//        System.out.println("Process " + processID + " is leaving the CS");
        // END <Critical Section>

        // <Exit Section>
//        try {
        dsm.store("flag" + processID, -1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        // END <Exit Section>
    }

    /**
     * This method checks if there exists a k
     * such that flag[j] >= k, as per Peterson's
     * Algorithm.
     *
     * @param k The k value to check.
     * @return Whether or not there exists such a k.
     */
    private Boolean ThereExists(int k) {
        for (int j = 0; j < N; j++) {
            if (j != this.processID) {
                if (dsm.load("flag" + j) >= k) return true;
            }
        }
        return false;
    }
}







