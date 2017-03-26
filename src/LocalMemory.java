/**
 * This class represents the LocalMemory
 * where the flag and turn values are stored.
 */

import java.util.concurrent.ConcurrentHashMap;

public class LocalMemory {
    ConcurrentHashMap<String, Integer> memory = new ConcurrentHashMap(19);

    /**
     * The constructor for LocalMemory.
     *
     * The flag values are initialized to -1
     * and the turn values are initialized to 0.
     */
    public LocalMemory() {
        memory.put("flag0", -1);
        memory.put("flag1", -1);
        memory.put("flag2", -1);
        memory.put("flag3", -1);
        memory.put("flag4", -1);
        memory.put("flag5", -1);
        memory.put("flag6", -1);
        memory.put("flag7", -1);
        memory.put("flag8", -1);
        memory.put("flag9", -1);
        memory.put("turn0", 0);
        memory.put("turn1", 0);
        memory.put("turn2", 0);
        memory.put("turn3", 0);
        memory.put("turn4", 0);
        memory.put("turn5", 0);
        memory.put("turn6", 0);
        memory.put("turn7", 0);
        memory.put("turn8", 0);
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

