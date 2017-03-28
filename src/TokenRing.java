import java.util.ArrayList;
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
    int i = 0;
    boolean flag;

    public TokenRing(int tokenRingID) {
        this.tokenRingID = tokenRingID;
        token = new Token(tokenRingID);
        agentArray = new ConcurrentLinkedQueue<>();
    }

    @Override
    public void run() {
        // give the token to the first proc (i)
        // once the proc stores, give up the token
        // give the token to the next proc (i++)
        while (flag) {
            if (agentArray.size() == 0){
                flag = false;
                System.out.println("token ring empty");
                break;
            }
//            if (i >= agentArray.size() - 1) {
//                i = 0;
//            }
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

    public int findToken() {
        int index = 0;
        for (TokenRingAgent tokenRingAgent : agentArray) {
            if (tokenRingAgent.getToken() != -1) {
                return index;
            }
            index++;
        }
        return -2;
    }

    public void removeAgent(TokenRingAgent tra) {
        if (agentArray.contains(tra)){
            agentArray.remove(tra);
            System.out.println("after removing, TRA array in order:");
            for (TokenRingAgent t : agentArray){
                System.out.print("  " + t.processorID + "  ");
            }
            System.out.println("");
        }
    }

    /**
     * This method sets the local array of broadcastAgents.
     *
     * @param agentArray The array of all broadcastAgents.
     */
    public void setAgents(ConcurrentLinkedQueue<TokenRingAgent> agentArray) {
        this.agentArray = agentArray;
    }

}


