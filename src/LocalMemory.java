/**
 * This class represents the LocalMemory
 * where the flag and turn values are stored.
 */

import java.util.concurrent.ConcurrentHashMap;

public class LocalMemory {
    ConcurrentHashMap<String, Integer> memory = new ConcurrentHashMap();

    /**
     * The constructor for LocalMemory.
     *
     * The flag values are initialized to -1
     * and the turn values are initialized to 0.
     */
    public LocalMemory(int numberOfProcessors) {
        for (int i = 0; i < numberOfProcessors; i++){
            memory.put("flag"+i, -1);
        }
        for (int i = 1; i < numberOfProcessors-1; i++){
            memory.put("turn"+i, 0);
        }
    }

    /**
     * This method loads a value from memory.
     *
     * @param x The item to load the value from.
     * @return The loaded value for item x.
     */
    public int load(String x) {
        if (memory.containsKey(x)) {
            return memory.get(x);
        } else {
            System.out.println("cannot find " + x + " ");
            return -5;
        }
    }

    /**
     * This method stores the value v of x to memory.
     *
     * @param x The item to store to.
     * @param v The value to store.
     */
    public void store(String x, int v) {
        memory.put(x, v);
    }
}

