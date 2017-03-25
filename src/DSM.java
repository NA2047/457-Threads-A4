import java.util.concurrent.ConcurrentHashMap;

/**
 * This class represents the DSM layer.
 *
 * DSM executes in a separate thread.
 */
public class DSM {
    LocalMemory localMemory;
    BroadcastAgent broadcastAgent;

    ConcurrentHashMap<String, Integer> mainMemory = new ConcurrentHashMap(20);

    public DSM(){
        mainMemory.put("flag_1",-2);
        mainMemory.put("flag_2",-2);
        mainMemory.put("flag_3",-2);
        mainMemory.put("flag_4",-2);
        mainMemory.put("flag_5",-2);
        mainMemory.put("flag_6",-2);
        mainMemory.put("flag_7",-2);
        mainMemory.put("flag_8",-2);
        mainMemory.put("flag_9",-2);
        mainMemory.put("turn_1",0);
        mainMemory.put("turn_2",0);
        mainMemory.put("turn_3",0);
        mainMemory.put("turn_4",0);
        mainMemory.put("turn_5",0);
        mainMemory.put("turn_6",0);
        mainMemory.put("turn_7",0);
        mainMemory.put("turn_8",0);
        mainMemory.put("turn_9",0);
        mainMemory.put("turn_10",0);

    }


    public int load(String x){

        if (mainMemory.contains(x)) {
            return mainMemory.get(x);

        }
        else{
            System.out.println("cannot find "+ x +" ");
            return -5;
        }

    }

    public void store(String x, Integer v){
        // TODO: write v into x in local memory
        // TODO: broadcast a message to all other DSMs to apply the write locally in their replicas
        if(mainMemory.contains(x)){
            mainMemory.replace(x,v);
        }else{
            mainMemory.put(x,v);
        }
    }
}
