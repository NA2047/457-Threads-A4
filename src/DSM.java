/**
 * This class represents the DSM layer.
 */

public class DSM {
    LocalMemory localMemory;
    BroadcastAgent broadcastAgent;
    TokenRingAgent tokenRingAgent;
    Processor proc;
    int tokenValue = -1;

    /**
     * The constructor of DSM.
     * LocalMemory and BroadcastAgent are instantiated.
     * @param broadcastSystem is the global instance.
     */
    public DSM(BroadcastSystem broadcastSystem, Processor proc, TokenRingAgent tokenRingAgent) {
        this.proc = proc;
        localMemory = new LocalMemory(proc.N);
        broadcastAgent = new BroadcastAgent(broadcastSystem, this);
        this.tokenRingAgent = tokenRingAgent;
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
    public void store(String x, int v) /*throws InterruptedException*/ {
        if (tokenRingAgent.getActive()){
            if (v == -1){
                tokenRingAgent.tokenRings.get(0).removeAgent(tokenRingAgent);
            }
            while ((tokenValue == -1)){
                tokenRingAgent.requestToken();
                try {
                    proc.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                System.out.println(proc.processID + " has token ID: " + tokenRingAgent.tokenID);
//            may want to sleep
            }
        }

        // write v into x in local memory
        localMemory.store(x, v);
        // broadcast a message to all other DSMs to apply the write locally in their replicas
        broadcastAgent.broadcast(x, v);

        if (tokenRingAgent.getActive()){
            tokenRingAgent.sendToken();
//            System.out.println(proc.processID + " has token ID: " + tokenRingAgent.tokenID);
        }
    }
}
