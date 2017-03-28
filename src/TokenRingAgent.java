import java.util.ArrayList;

/**
 * Created by Sharon on 3/24/2017.
 */
public class TokenRingAgent {
    int tokenID =-1;
    boolean Active = true;
    int processorID;
    int ringPredecessorID;
    int ringSuccessorID;
//    ArrayList<TokenRing> tokenRings;


    /**
     * Default constructor assigns current token ring to local copy and the processor ID of for this token ring
     * @param tokenRings
     * @param processorID
     */
    TokenRingAgent(ArrayList<TokenRing> tokenRings,int processorID ){
        tokenRings.get(0).addTokenAgents(this);
//        this.tokenRings = tokenRings;
        this.processorID = processorID;
        this.tokenID =-1;
    }

    /**
     * This method returns the unique identifier for the token
     * received from the predecessor.
     * @return the ID for the token received.
     * */
    public int receiveToken(int TokenID){
//        tokenRings.get(0).agentArray.(tokenID);

        this.tokenID = TokenID;

        return tokenID;
    }

    /**
     * This method sends the token to the successor.
     * @param t is the Token to send.
     * */
    public void sendToken(Token t, ArrayList<TokenRingAgent> AgentList){
//        System.out.println("TokenAgent "+this.tokenID+" has Successor "+ this.ringSuccessorID);
        AgentList.get(ringSuccessorID).receiveToken(t.getToken());
        tokenID = -1;

    }

    /**
     * setter for ringPredecessor
     * @param ringPredecessorID
     */
    public void setRingPredecessor(int ringPredecessorID){
//        System.out.println("Setting Ring Predecessor for "+this.processorID+ " from "+ this.ringPredecessorID );
        this.ringPredecessorID = ringPredecessorID;
//        System.out.print(" to "+ringPredecessorID);
    }

    /**
     * setter for ringSuccessor
     * @param ringSuccessorID
     */
    public void setRingSuccessor(int ringSuccessorID){
//        System.out.println("Setting Ring Successor for "+this.processorID+ " from "+ this.ringSuccessorID);
        this.ringSuccessorID = ringSuccessorID;
//        System.out.println(" to "+ ringPredecessorID);

    }

    /**
     * getter for processor ID
     * @return
     */
    public int getProcessorID(){

        return this.processorID;
    }

    /**
     * disables token agents
     */
    public void disable (){
        Active = false;
    }

    /**
     * setter for Token
     * @param t
     */
    public void setTokenID(Token t){
//        System.out.println("setting Token for "+ this.processorID);
        this.tokenID = t.getToken();

    }

    /**
     * getter for avtive variable
     * @return
     */
    public Boolean getActive(){
//        System.out.println("checking active");
            return Active;
    }

    public int getToken(){
        return tokenID;
    }


}
