/**
 * This class is the implementation of the broadcasting
 * mechanism between processors.
 *
 * One BroadcastSystem object is shared between all BroadcastAgents.
 * Executes in a separate thread.
 */

public class BroadcastSystem extends Thread {
    private BroadcastAgent[] agentArray;
    boolean broadcast;
    String x;
    int v;
    int time = 500;

    /**
     * The overridden run method.
     * Broadcasts when necessary.
     * Delays are added to simulate sending/receiving messages.
     */
    @Override
    public void run() {
        while (!interrupted()) {
            if (broadcast) {
                delay();
                broadcast(x, v);
                delay();
                broadcast = false;
            }
        }
    }

    /**
     * This method broadcasts the item and value to
     * store to each broadcastAgent.
     *
     * @param x The item to store.
     * @param v The value to store.
     */
    public void broadcast(String x, int v) {
        for (BroadcastAgent agent : agentArray) {
            agent.receive(x, v);
        }
    }

    /**
     * This method sleeps the class for the amount specified
     * in the time variable.
     */
    public void delay() {
        try {
            sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method sets the local array of broadcastAgents.
     *
     * @param agentArray The array of all broadcastAgents.
     */
    public void setAgents(BroadcastAgent[] agentArray) {
        this.agentArray = agentArray;
    }
}
