import java.util.ArrayList;

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
    int N ;

    /**
     * The constructor of Processor.
     * processID and DSM are set.
     *
     * @param processID       is the assigned processID.
     * @param broadcastSystem is the global instance.
     */
    public Processor(int processID, BroadcastSystem broadcastSystem, ArrayList<TokenRing> tokenRings, int numberOfProcessors) {
        this.processID = processID; // process number
        tra = new TokenRingAgent(tokenRings,this.processID);
        dsm = new DSM(broadcastSystem, numberOfProcessors,tra);
        this.N = numberOfProcessors;
    }

    /**
     * The overridden run method.
     * Calls Peterson's algorithm.
     */
    @Override
    public void run() {
        petersonsN();
    }

    /**
     * This method represents Peterson's algorithm.
     */
    public void petersonsN() {
        // <Entry Section>
        for (int k = 0; k < N - 2; k++) {
            try {
                // processor that is competing at level k
                dsm.store("flag" + processID, k);
                // tells the current level that it's ProcessorIDs turn
                dsm.store("turn" + k, processID);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //the while loop from peterson's algorithm
            while ((ThereExists(k)) && (dsm.load("turn" + k) == processID)) ;
        }
        // END <Entry Section>

        // <Critical Section>
        System.out.println("Process " + processID + " is in the CS");

        try {
            System.out.println("   Increment test value by processor " + processID + "  =  " + (++test));
            Thread.sleep(50); // short delay to demonstrate that the algorithm is not perfect

        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        System.out.println("Process " + processID + " is leaving the CS");
        // END <Critical Section>

        // <Exit Section>
        try {
            dsm.store("flag" + processID, -1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // END <Exit Section>
    }

    /**
     * This method checks if there exists a k
     * such that flag[k] >= j, as per Peterson's
     * Algorithm.
     *
     * @param k The k value to check.
     * @return Whether or not there exists such a k.
     */
    private Boolean ThereExists(int k) {
        for (int j = 0; j < 10; j++) {
            if (j != this.processID) {
                if (dsm.load("flag" + j) >= k) return true;
            }
        }
        return false;
    }
}







