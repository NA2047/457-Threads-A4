/**
 * Created by Sharon on 3/24/2017.
 */

import java.util.ArrayList;
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
public class BroadcastSystem extends Thread{

    private ConcurrentHashMap<String, Integer> mainMemory; //hash tablet to store flags and turns
    private BlockingQueue<String[]> blockingQueue;  //BlockingQueue to solve producer-consumer problem
    private ArrayList<BroadcastAgent> agentArray;//array list of all the BA
    // TODO: implement some delays to simulate sending/receiving messages.


    BroadcastSystem(){
        blockingQueue = new ArrayBlockingQueue<>(10); // an array blocking Q of size 10
        agentArray = new ArrayList<>();	//initializes the bca ArrayList
        mainMemory = new ConcurrentHashMap<>(); //initalizes the Hashtable
        //load with starting values
        mainMemory.put("flag1",-1);
        mainMemory.put("flag2",-1);
        mainMemory.put("flag3",-1);
        mainMemory.put("flag4",-1);
        mainMemory.put("flag5",-1);
        mainMemory.put("flag6",-1);
        mainMemory.put("flag7",-1);
        mainMemory.put("flag8",-1);
        mainMemory.put("flag9",-1);
        mainMemory.put("flag0", -1);
        mainMemory.put("turn1",0);
        mainMemory.put("turn2",0);
        mainMemory.put("turn3",0);
        mainMemory.put("turn4",0);
        mainMemory.put("turn5",0);
        mainMemory.put("turn6",0);
        mainMemory.put("turn7",0);
        mainMemory.put("turn8",0);
        mainMemory.put("turn0",0);

    }

    //this run method will continuosly check the queue to see if it needs to broadcast
    //anything to the other local memorys
    public void run(){
        while(true){
            if(!bcQ.isEmpty()){  //If there is something in the queue
                String[] tempArray = bcQ.remove();  //remove the array and store it
                String key = tempArray[0];			//stores the key
                int value = Integer.parseInt(tempArray[1]); //stores the value
                mainMemory.put(key, value);			//puts it into main memory
                //Iterate through all of the BA and notify them to change thier localmem to the
                //key and value
                for(int i = 0; i < bcaList.size(); i++){
                    bcaList.get(i).receive(key,value);
                }
            }

        }

    }

    //This is the function that adds BA to the BCA arrayList
    public void addBA(BroadcastAgent ourBA){
        bcaList.add(ourBA);
        //System.out.println(bcaList.size());
    }

    //This function returns the address of the BCS queue
    public BlockingQueue<String[]> getBlockingQueue(){
        return blockingQueue;
    }




    // for each process, store the message
    public void broadcast(String x, int v){
        for (BroadcastAgent agent : agentArray){
            agent.receive(x,v);
        }
    }

    public void setAgents(BroadcastAgent [] agentArray){
        this.agentArray = agentArray;
    }
}
