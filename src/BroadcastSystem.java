/**
 * Created by Sharon on 3/24/2017.
 */

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This class is the implementation of the broadcasting
 * mechanism between processors.
 *
 * One BroadcastSystem object is shared between all BroadcastAgents.
 * Executes in a separate thread.
 */
public class BroadcastSystem extends Thread {
     ConcurrentHashMap<String, Integer> memory;
     BlockingQueue<String[]> blockingQueue;
     List<BroadcastAgent> agentArray;


    BroadcastSystem(){
        blockingQueue = new ArrayBlockingQueue<>(10); //intialzes an array blocking
        agentArray = new ArrayList<>();
        memory = new ConcurrentHashMap<>();

        memory.put("flag0",-1);
        memory.put("flag1",-1);
        memory.put("flag2",-1);
        memory.put("flag3",-1);
        memory.put("flag4",-1);
        memory.put("flag5",-1);
        memory.put("flag6",-1);
        memory.put("flag7",-1);
        memory.put("flag8",-1);
        memory.put("flag9",-1);
        memory.put("turn0",0);
        memory.put("turn1",0);
        memory.put("turn2",0);
        memory.put("turn3",0);
        memory.put("turn4",0);
        memory.put("turn5",0);
        memory.put("turn6",0);
        memory.put("turn7",0);
        memory.put("turn8",0);
    }
//    // for each process, store the message
//    public void broadcast(String x, int v){
//        for (BroadcastAgent agent : agentArray){
//            agent.receive(x,v);
//        }
//    }
//
//    public void setAgents(BroadcastAgent [] agentArray){
//        List<BroadcastAgent> l1 = Collections.unmodifiableList(Arrays.asList(agentArray));
//        this.agentArray = l1;
//    }

//
//    public void addBA(BroadcastAgent ourBA){
//        agentArray.add(ourBA);
//        //System.out.println(bcaList.size());
//    }
public void run(){
    while(true){
        if(!blockingQueue.isEmpty()){  //If there is something in the queue
            String[] tempArray = blockingQueue.remove();  //remove the array and store it
            String key = tempArray[0];			//stores the key
            int value = Integer.parseInt(tempArray[1]); //stores the value
            memory.put(key, value);			//puts it into main memory

            for(int i = 0; i < agentArray.size(); i++){
                agentArray.get(i).receive(key,value);
            }
        }

    }

}

    public void addBA(BroadcastAgent broadcastAgent){
        agentArray.add(broadcastAgent);
    }


    public BlockingQueue<String[]> getBQ(){
        return blockingQueue;
    }
}
