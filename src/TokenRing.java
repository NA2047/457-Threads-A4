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
    int tokenRingID;
    Token token;
    int i = 0;
    boolean flag = true;
//    boolean passToken = true;


    public TokenRing(int tokenRingID) {
        this.tokenRingID = tokenRingID;
        token = new Token(tokenRingID);
        agentArray = new ArrayList<>();
    }

    @Override
    public void run() {
        // give the token to the first proc (i)
        // once the proc stores, give up the token
        // give the token to the next proc (i++)
        while (flag) {
//            if (passToken){
            if (agentArray.size() == 0){
                flag = false;
                break;
            }
            if (i >= agentArray.size() - 1) {
                i = 0;
            }
            if (agentArray.get(i).needToken) {
//                if (agentArray.get(i).needToken){
                agentArray.get(i).receiveToken(token);
                i++;
//                }
            }
//            passToken = false;
            try {
                Thread.sleep(100); // may need to play around with time
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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

    public void removeAgent(TokenRingAgent tra) {
        if (agentArray.contains(tra)){
            agentArray.remove(tra);
        }
    }

    public void addAgent(TokenRingAgent tra) {
        agentArray.add(tra);
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


