/**
 * Created by Sharon on 3/24/2017.
 */

import java.util.HashMap;
import java.util.concurrent.BlockingQueue;

/**
 * This class provides the implementation of the broadcast
 * mechanism needed by DSM.
 *
 * Each BroadcastAgent executes in a separate thread.
 */
public class BroadcastAgent {
    BroadcastSystem broadcastSystem;
     BlockingQueue<String[]> blockingQueue;
     LocalMemory localMemory;		//the localMem

    public BroadcastAgent(BroadcastSystem broadcastSystem, LocalMemory localMem){
        this.broadcastSystem = broadcastSystem;
        this.localMemory = localMem;
    }

    /**
     * This method sends a store.
     *
     * @param x The item to send.
     * @param v The value to send.
     * */
//    public void broadcast(String x, int v) throws InterruptedException{
//        broadcastSystem.broadcast(x,v);
//    }


    public void broadcast(String x, int v) throws InterruptedException{
//        HashMap<String, Integer> temp = new HashMap<>();
//        temp.put(x,v);
        String[] tempArray = new String[2];
        tempArray[0] = x;
        tempArray[1] = Integer.toString(v);
        blockingQueue.put(tempArray);
    }

    /**
     * This method receives a store.
     *
     * @param x The item to store.
     * @param v The value to store.
     * */
    public void receive(String x, int v){
        localMemory.store(x, v);

        }
    }

