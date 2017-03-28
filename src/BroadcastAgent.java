import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * This class provides the implementation of the broadcast
 * mechanism needed by DSM.
 */

public class BroadcastAgent {
//    BroadcastSystem broadcastSystem;
    DSM dsm;
    private ConcurrentLinkedQueue<Message> messages ;
    private LocalMemory localMemory;

//    /**
//     * The constructor of BroadcastAgent.
//     *
//     * BroadcastSystem and DSM are set.
//     *
//     * @param broadcastSystem is the global instance.
//     * @param dsm is the corresponding DSM for the BroadcastAgent.
//     */
    public BroadcastAgent(BroadcastSystem broadcastSystem, LocalMemory localMemory) {
        broadcastSystem.addAgent(this);
//        this.broadcastSystem = broadcastSystem;
        this.localMemory = localMemory;
        messages =broadcastSystem.getConcurrentlinked();

//        this.dsm = dsm;
    }

//    /**
//     * This method sends a store.
//     *
//     * @param x The item to send.
//     * @param v The value to send.
//     */
//    public void broadcast(String x, int v) throws InterruptedException {
//        broadcastSystem.x = x;
//        broadcastSystem.v = v;
//        broadcastSystem.broadcast = true;
//    }
    public void broadcast(String x, int v) throws InterruptedException {
        Message message = new Message(x,v);
        messages.add(message);
    }

//    /**
//     * This method receives a store.
//     *
//     * @param x The item to store.
//     * @param v The value to store.
//     */
//    public void receive(String x, int v) {
////        TokenRingAgent tra = new TokenRingAgent();
//        if (dsm.load(x) != v) {
//            try {
//                dsm.store(x, v);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
    public void receive (String x, int v) {
        localMemory.store(x,v);
    }


}
