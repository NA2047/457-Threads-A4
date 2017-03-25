/**
 * Created by Sharon on 3/24/2017.
 */

/**
 * This class is the implementation of the broadcasting
 * mechanism between processors.
 *
 * One BroadcastSystem object is shared between all BroadcastAgents.
 * Executes in a separate thread.
 */
public class BroadcastSystem {
    private BroadcastAgent [] agentArray;
    // TODO: implement some delays to simulate sending/receiving messages.

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
