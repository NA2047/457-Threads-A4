/**
 * This class represents the DSM layer.
 *
 * DSM executes in a separate thread.
 */
public class DSM {
    LocalMemory localMemory;
    BroadcastAgent broadcastAgent;

    public DSM(){
    }


    public int load(String x){
        return 0;
    }

    public void store(String x, Integer v){
        // TODO: write v into x in local memory
        // TODO: broadcast a message to all other DSMs to apply the write locally in their replicas
//        if(mainMemory.contains(x)){
//            mainMemory.replace(x,v);
//        }else{
//            mainMemory.put(x,v);
//        }
    }
}
