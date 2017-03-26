/**
 * Created by Sharon on 3/24/2017.
 */

/**
 * This class provides the implementation of the broadcast
 * mechanism needed by DSM.
 *
 * Each BroadcastAgent executes in a separate thread.
 */
public class BroadcastAgent {
    BroadcastSystem broadcastSystem;
    DSM dsm;

    public BroadcastAgent(BroadcastSystem broadcastSystem, DSM dsm){
        this.broadcastSystem = broadcastSystem;
        this.dsm = dsm;
    }

    /**
     * This method sends a store.
     *
     * @param x The item to send.
     * @param v The value to send.
     * */
    public void broadcast(String x, int v) throws InterruptedException{
//        broadcastSystem.broadcast(x,v);
        broadcastSystem.x = x;
        broadcastSystem.v = v;
        broadcastSystem.broadcast = true;
    }

    /**
     * This method receives a store.
     *
     * @param x The item to store.
     * @param v The value to store.
     * */
    public void receive(String x, int v){
        if (dsm.load(x) != v){
            try {
                dsm.store(x,v);

            }catch (InterruptedException e){
                e.printStackTrace();

            }

        }
    }
}
