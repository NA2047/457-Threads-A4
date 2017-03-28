import java.util.ArrayList;

/**
 * Created by Sharon on 3/24/2017.
 */
public class TokenRingAgent{
    int tokenID = -1;
    boolean Active = true;
    int processorID;
    int ringPredecessorID;
    int ringSuccessorID;
    ArrayList<TokenRing> tokenRings;
    Processor proc;
    boolean needToken = false;

    /**
     * Default constructor assigns current token ring to local copy and the processor ID of for this token ring
     * @param tokenRings
     * @param proc
     */
    TokenRingAgent(ArrayList<TokenRing> tokenRings, Processor proc){
        this.tokenRings = tokenRings;
        this.processorID = proc.processID;
        this.proc = proc;
    }

    public void remove(){
        tokenRings.get(0).removeAgent(this);
    }

    public void requestToken(){
        needToken = true;
    }

    /**
     * This method returns the unique identifier for the token
     * received from the predecessor.
     * */
    public void receiveToken(Token t){
//        tokenRings.get(0).passToken = false;
        this.tokenID = t.tokenID;
    }

    /**
     * This method sends the token to the successor.
     * */
    public void sendToken(){
        tokenID = -1;
//        tokenRings.get(0).passToken = true;
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
        this.tokenID = t.tokenID;

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
