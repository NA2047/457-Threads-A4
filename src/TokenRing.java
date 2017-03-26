import java.util.ArrayList;

/**
 * This class is the arrangement of the token ring.
 * It consists of individual TokenRingAgents.
 * <p>
 * If active, this class creates the necessary token
 * and passes it to an initially designated TokenRingAgent.
 * <p>
 * There can be more than one TokenRing instances,
 * with  different token messages.
 */
public class TokenRing extends Thread {
    ArrayList<TokenRingAgent> agentArray;
    int tokenRingID;
    Token token;
    boolean enabled = true;

    public TokenRing(int tokenRingID) {
        this.tokenRingID = tokenRingID;
        token = new Token(tokenRingID);
    }

    @Override
    public void run() {
        while (!interrupted()) {
            // pass token ring
        }
    }

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
