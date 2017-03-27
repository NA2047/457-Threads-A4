/**
 * This class represents the DSM layer.
 */

public class DSM {
    LocalMemory localMemory;
    BroadcastAgent broadcastAgent;

    /**
     * The constructor of DSM.
     * LocalMemory and BroadcastAgent are instantiated.
     * @param broadcastSystem is the global instance.
     */
    public DSM(BroadcastSystem broadcastSystem, int numberOfProcessors) {
        localMemory = new LocalMemory(numberOfProcessors);
        broadcastAgent = new BroadcastAgent(broadcastSystem, this);
    }

    /**
     * This method loads the value of x from local memory.
     * @param x The item to load a value from.
     * @return The loaded value from memory.
     */
    public int load(String x) {
        return localMemory.load(x);
    }

    /**
     * This method stores to local memory and broadcasts
     * that store to all other DSMs.
     * @param x The item to store.
     * @param v The value to store.
     * @throws InterruptedException
     */
    public void store(String x, int v,TokenRingAgent tra) throws InterruptedException {
        while ((tra.tokenID ==-1) && (tra.getActive())){
//            may want to sleep
        }


        // write v into x in local memory
        localMemory.store(x, v);
        // broadcast a message to all other DSMs to apply the write locally in their replicas
        broadcastAgent.broadcast(x, v);
    }
}
