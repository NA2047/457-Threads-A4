/**
 * This class represents the DSM layer.
 *
 * DSM executes in a separate thread.
 */
public class DSM {
    LocalMemory localMemory;
    BroadcastAgent broadcastAgent;

    public String load(String x){
        return x;
    }

    public void store(String x, String v){
        // TODO: write v into x in local memory
        // TODO: broadcast a message to all other DSMs to apply the write locally in their replicas
    }
}
