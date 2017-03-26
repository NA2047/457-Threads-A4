/**
 * Created by Sharon on 3/24/2017.
 */

import java.util.ArrayList;

/**
 * This class is the arrangement of the token ring.
 * It consists of individual TokenRingAgents.
 *
 * If active, this class creates the necessary token
 * and passes it to an initially designated TokenRingAgent.
 *
 * There can be more than one TokenRing instances,
 * with  different token messages.
 */
public class TokenRing extends Thread {
    ArrayList<TokenRingAgent> agentArray;

    /**
     * This method sets the local array of broadcastAgents.
     *
     * @param agentArray The array of all broadcastAgents.
     */
    public void setAgents(ArrayList<TokenRingAgent> agentArray) {
        this.agentArray = agentArray;
    }
}
