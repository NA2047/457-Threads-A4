import java.util.ArrayList;

/**
 * Created by Sharon on 3/24/2017.
 */
public class TokenRingAgent {
    int tokenID;
    boolean Active = true;
    int processorID;
    int ringPredecessorID;
    int ringSuccessorID;
    ArrayList<TokenRing> tokenRings;

    public TokenRingAgent(ArrayList<TokenRing> tokenRings,int processorID ){
        this.tokenRings = tokenRings;
        this.processorID = processorID;
    }

    /**
     * This method returns the unique identifier for the token
     * received from the predecessor.
     *
     * @return the ID for the token received.
     * */
    public int receiveToken(int TokenID){
//        tokenRings.get(0).agentArray.(tokenID);

        this.tokenID = TokenID;

        return tokenID;
    }

    /**
     * This method sends the token to the successor.
     *
     * @param t is the Token to send.
     * */
    public void sendToken(Token t, ArrayList<TokenRingAgent> AgentList){
//        System.out.println("TokenAgent "+this.tokenID+" has Successor "+ this.ringSuccessorID);
        AgentList.get(ringSuccessorID).receiveToken(t.tokenID);
        tokenID = -1;

    }


    public void setPredecessor(int ringPredecessorID){
//        System.out.println("Setting Ring Predecessor for "+this.processorID+ " from "+ this.ringPredecessorID );
        this.ringPredecessorID = ringPredecessorID;
//        System.out.print(" to "+ringPredecessorID);
    }

    public void setRingSuccessor(int ringSuccessorID){
//        System.out.println("Setting Ring Successor for "+this.processorID+ " from "+ this.ringSuccessorID);
        this.ringSuccessorID = ringSuccessorID;
//        System.out.println(" to "+ ringPredecessorID);

    }

    public int getProcID(){

        return this.processorID;
    }


    public void disable (){
        Active = false;
    }

    public void setTokenID(Token t){
//        System.out.println("setting Token for "+ this.processorID);
        this.tokenID = t.tokenID;

    }

    public Boolean checkIfActive(){
//        System.out.println("checking active");
        return Active;
    }


}
