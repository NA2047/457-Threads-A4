/**
 * This class represents the DSM layer.
 *
 * DSM executes in a separate thread.
 */
public class DSM {
    private LocalMemory localMemory;
    private BroadcastAgent broadcastAgent;
    private Processor ourProcessor;


    public DSM(BroadcastSystem broadcastSystem,Processor ourProcessor){

        localMemory = new LocalMemory();
        broadcastAgent = new BroadcastAgent(broadcastSystem, localMemory);
        this.ourProcessor = ourProcessor;
    }

    // returns value of x read from local memory
    public int load(String x){
        return localMemory.load(x);
    }

    public void store(String x, int v) throws InterruptedException{

        // write v into x in local memory
        localMemory.store(x,v);
        // broadcast a message to all other DSMs to apply the write locally in their replicas
        broadcastAgent.broadcast(x,v);
    }
}
