import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Sharon on 3/24/2017.
 */

public class LocalMemory {
        ConcurrentHashMap<String, Integer> memory = new ConcurrentHashMap(19);

        public LocalMemory(){
            memory.put("flag1",-1);
            memory.put("flag2",-1);
            memory.put("flag3",-1);
            memory.put("flag4",-1);
            memory.put("flag5",-1);
            memory.put("flag6",-1);
            memory.put("flag7",-1);
            memory.put("flag8",-1);
            memory.put("flag9",-1);
            memory.put("flag0", -1);
            memory.put("turn1",0);
            memory.put("turn2",0);
            memory.put("turn3",0);
            memory.put("turn4",0);
            memory.put("turn5",0);
            memory.put("turn6",0);
            memory.put("turn7",0);
            memory.put("turn8",0);
            memory.put("turn0",0);
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

