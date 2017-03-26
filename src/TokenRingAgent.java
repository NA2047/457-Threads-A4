import java.util.ArrayList;

/**
 * Created by Sharon on 3/24/2017.
 */
public class TokenRingAgent {
    int tokenID;
    boolean Active;
    int processorID;
    int ringPredecessorID;
    int ringSuccessorID;
    ArrayList<TokenRing> tokenRings;

    public TokenRingAgent(ArrayList<TokenRing> tokenRings,int processorID  ){
        this.tokenRings = tokenRings;
        this.processorID = processorID;
        Active = false;
    }

    /**
     * This method returns the unique identifier for the token
     * received from the predecessor.
     *
     * @return the ID for the token received.
     * */
    public int receiveToken(){
        tokenRings.get(0).agentArray.(tokenID);

        return 1;
    }

    /**
     * This method sends the token to the successor.
     *
     * @param t is the Token to send.
     * */
    public void sendToken(Token t){

    }


    public void setPred(int predID){

    }

    public void setSucd(int sucID){

    }

    public int getProcID(){
        return this.processorID;
    }


}
}
