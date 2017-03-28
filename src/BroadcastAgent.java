import java.util.concurrent.BlockingQueue;

/**
 * This class provides the implementation of the broadcast
 * mechanism needed by DSM.
 */

public class BroadcastAgent {
    BroadcastSystem broadcastSystem;
    DSM dsm;
    private BlockingQueue<Message> blockingQueue;

    /**
     * The constructor of BroadcastAgent.
     *
     * BroadcastSystem and DSM are set.
     *
     * @param broadcastSystem is the global instance.
     * @param dsm is the corresponding DSM for the BroadcastAgent.
     */
    public BroadcastAgent(BroadcastSystem broadcastSystem, DSM dsm) {

        this.broadcastSystem = broadcastSystem;
        broadcastSystem.addAgents(this);
        this.dsm = dsm;
        this.blockingQueue=broadcastSystem.blockingQueue;
    }

    /**
     * This method sends a store.
     *
     * @param x The item to send.
     * @param v The value to send.
     */
    public void broadcast(String x, int v) /*throws InterruptedException*/ {
        broadcastSystem.x = x;
        broadcastSystem.v = v;
        broadcastSystem.broadcast = true;
    }

//    public void broadcast(String x, int v) throws InterruptedException {
//        Message temp = new Message(x,v);
//        blockingQueue.put(temp);
//;
//    }
    /**
     * This method receives a store.
     *
     * @param x The item to store.
     * @param v The value to store.
     */
    public void receive(String x, int v) {
        dsm.store(x, v);

        }
    }

