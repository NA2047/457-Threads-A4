import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * This class is the arrangement of the token ring.
 * It consists of individual TokenRingAgents.
 * If active, this class creates the necessary token
 * and passes it to an initially designated TokenRingAgent.
 * There can be more than one TokenRing instances,
 * with  different token messages.
 */
public class TokenRing extends Thread {
    ConcurrentLinkedQueue<TokenRingAgent> agentArray;
    int tokenRingID;
    Token token;
    boolean flag = true;

    /**
     * constructor for token ring, set instance variables
     *
     * @param tokenRingID
     */
    public TokenRing(int tokenRingID) {
        this.tokenRingID = tokenRingID;
        token = new Token(tokenRingID);
        agentArray = new ConcurrentLinkedQueue<>();
    }

    /**
     * run method for TokenRing listens for to
     */
    @Override
    public void run() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // give the token to the first proc (i)
        // once the proc stores, give up the token
        // give the token to the next proc (i++)
        while (flag) {

            if (agentArray.size() == 0) {
                flag = false;
                System.out.println("token ring empty");
                break;
            }
            if (agentArray.peek().needToken) {
                agentArray.peek().receiveToken(token);
                agentArray.add(agentArray.poll());
            }
            try {
                Thread.sleep(100); // may need to play around with time
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return;
    }

    /**
     * add each agent upon creation to agentArray
     *
     * @param tokenRingAgent agent of Processor thread
     */
    public void addAgent(TokenRingAgent tokenRingAgent) {
        agentArray.add(tokenRingAgent);
    }

    /**
     * this method removes agent from the Q after they have stored -1
     *
     * @param tra agent to be removed from Q
     */
    public void removeAgent(TokenRingAgent tra) {
        if (agentArray.contains(tra)) {
            agentArray.remove(tra);
        }
    }
}


