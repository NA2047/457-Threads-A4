/**
 * This class provides the implementation of the broadcast
 * mechanism needed by DSM.
 */

public class BroadcastAgent {
    BroadcastSystem broadcastSystem;
    DSM dsm;

    /**
     * The constructor of BroadcastAgent.
     * <p>
     * BroadcastSystem and DSM are set.
     *
     * @param broadcastSystem is the global instance.
     * @param dsm             is the corresponding DSM for the BroadcastAgent.
     */
    public BroadcastAgent(BroadcastSystem broadcastSystem, DSM dsm) {
        this.broadcastSystem = broadcastSystem;
        broadcastSystem.addAgents(this);
        this.dsm = dsm;
    }

    /**
     * This method sends a store.
     *
     * @param x The item to send.
     * @param v The value to send.
     */
    public void broadcast(String x, int v) {
        broadcastSystem.x = x;
        broadcastSystem.v = v;
        broadcastSystem.broadcast = true;
    }

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

