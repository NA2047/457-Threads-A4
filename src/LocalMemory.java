/**
 * Created by Sharon on 3/24/2017.
 */

import java.util.concurrent.ConcurrentHashMap;

public class LocalMemory {
    ConcurrentHashMap<String, Integer> memory = new ConcurrentHashMap(20);

    public LocalMemory(){
        memory.put("flag_1",-2);
        memory.put("flag_2",-2);
        memory.put("flag_3",-2);
        memory.put("flag_4",-2);
        memory.put("flag_5",-2);
        memory.put("flag_6",-2);
        memory.put("flag_7",-2);
        memory.put("flag_8",-2);
        memory.put("flag_9",-2);
        memory.put("flag_10", -2);
        memory.put("turn_1",0);
        memory.put("turn_2",0);
        memory.put("turn_3",0);
        memory.put("turn_4",0);
        memory.put("turn_5",0);
        memory.put("turn_6",0);
        memory.put("turn_7",0);
        memory.put("turn_8",0);
        memory.put("turn_9",0);
        memory.put("turn_10",0);
    }

    // return the value of x read from memory
    public int load(String x){
        if (memory.contains(x)) {
            return memory.get(x);
        }
        else{
            System.out.println("cannot find "+ x +" ");
            return -5;
        }
    }

    // stores the value v of x to memory
    public void store(String x, int v){
        if(memory.contains(x)){
            memory.replace(x,v);
        }else{
            memory.put(x,v);
        }
    }
}
