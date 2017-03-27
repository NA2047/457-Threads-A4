import java.util.ArrayList;

/**
 * This class is the arrangement of the token ring.
 * It consists of individual TokenRingAgents.
 * If active, this class creates the necessary token
 * and passes it to an initially designated TokenRingAgent.
 * There can be more than one TokenRing instances,
 * with  different token messages.
 */
public class TokenRing extends Thread {
    ArrayList<TokenRingAgent> agentArray;
    int tokenRingID = 1;
    Token token;
    boolean enabled = true;
    int currentTokenAgentRingIndex;
    Boolean startUp;


    public TokenRing(int tokenRingID) {
        this.tokenRingID = tokenRingID;
        token = new Token(tokenRingID);
        currentTokenAgentRingIndex = 0;
        this.startUp = true;
    }

    @Override
    public void run() {
        agentArray.get(0).receiveToken(tokenRingID);
        while (interrupted()) {
//            int toSend = agentArray.get(findToken()).ringSuccessorID;
            if (currentTokenAgentRingIndex == 0 && startUp) {

                agentArray.get(currentTokenAgentRingIndex).setRingPredecessor(0);
                int nextSuccesorIndex = currentTokenAgentRingIndex + 1;
                agentArray.get(currentTokenAgentRingIndex).setRingSuccessor(nextSuccesorIndex);
                startUp = false;

            } else if (currentTokenAgentRingIndex == 0) {
                agentArray.get(currentTokenAgentRingIndex).setRingPredecessor(agentArray.size() - 1);
                int nextSuccesorIndex = currentTokenAgentRingIndex + 1;
                agentArray.get(currentTokenAgentRingIndex).setRingSuccessor(nextSuccesorIndex);
            } else if (currentTokenAgentRingIndex == (agentArray.size() - 1)) {

                agentArray.get(currentTokenAgentRingIndex).setRingPredecessor(agentArray.size() - 2);
                agentArray.get(currentTokenAgentRingIndex).setRingSuccessor(0);

            } else {
                agentArray.get(currentTokenAgentRingIndex).setRingPredecessor((currentTokenAgentRingIndex - 1));
                agentArray.get(currentTokenAgentRingIndex).setRingSuccessor((currentTokenAgentRingIndex + 1));
            }

            agentArray.get(currentTokenAgentRingIndex).sendToken(token, agentArray);

            if (currentTokenAgentRingIndex >= agentArray.size()) {
                currentTokenAgentRingIndex = 0;
            }
            try {
                Thread.sleep(100); // may need to play around with time
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //maybe sleep somehting here

        }
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


    /**
     * disable token rings
     */
    public void disable() {
        enabled = false;
    }

    /**
     * This method sets the local array of broadcastAgents.
     *
     * @param agentArray The array of all broadcastAgents.
     */
    public void setAgents(ArrayList<TokenRingAgent> agentArray) {
        this.agentArray = agentArray;
    }

}


