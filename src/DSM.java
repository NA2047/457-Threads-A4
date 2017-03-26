/**
 * This class represents the DSM layer.
 *
 * DSM executes in a separate thread.
 */
public class DSM {
    LocalMemory localMemory;
    BroadcastAgent broadcastAgent;

    public DSM(BroadcastSystem broadcastSystem){
        localMemory = new LocalMemory();
        broadcastAgent = new BroadcastAgent(broadcastSystem, this);
    }

    // returns value of x read from local memory
    public int load(String x){
        return localMemory.load(x);
    }

    public void store(String x, int v){
        // write v into x in local memory
        localMemory.store(x,v);
        // broadcast a message to all other DSMs to apply the write locally in their replicas
        broadcastAgent.broadcast(x,v);
    }
}
