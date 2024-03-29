import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * This class represents a processor.
 *
 * Executes in a separate thread.
 */

public class Processor extends Thread {
    DSM dsm;
    TokenRingAgent tra;
    int processID;
    static int test = 0;
    int N;
    boolean multipleTR;

    /**
     * The constructor of Processor.
     * processID and DSM are set.
     *
     * @param processID       is the assigned processID.
     * @param broadcastSystem is the global instance.
     */
    public Processor(int processID, BroadcastSystem broadcastSystem, ConcurrentLinkedQueue<TokenRing> tokenRings, int numberOfProcessors, boolean multipleTR) {
        this.processID = processID; // process number
        tra = new TokenRingAgent(tokenRings, this);
        dsm = new DSM(broadcastSystem, this, tra);
        this.N = numberOfProcessors;
        this.multipleTR = multipleTR;
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
            dsm.store("flag" + processID, k);
            // tells the current level that it's ProcessorIDs turn
            dsm.store("turn" + k, processID);

            while ((ThereExists(k)) && (dsm.load("turn" + k) == processID)) {
            }
        }
        // END <Entry Section>

        // <Critical Section>
        System.out.println("   ***Process " + processID + " is in the CS***");

        try {
            Thread.sleep(200); // short delay to demonstrate that the algorithm is not perfect
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        System.out.println("   Increment test value by processor " + processID + "  =  " + (++test));

//        try {
//            Thread.sleep(200); // short delay to demonstrate that the algorithm is not perfect
//        } catch (InterruptedException e1) {
//            e1.printStackTrace();
//        }

        System.out.println("Process " + processID + " is leaving the CS");
        // END <Critical Section>

        // <Exit Section>
        dsm.store("flag" + processID, -1);
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