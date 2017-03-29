import java.util.concurrent.ConcurrentLinkedQueue;

public class TokenRingAgent {
    int tokenID = -1;
    boolean Active = true;
    int processorID;
    int ringPredecessorID;
    int ringSuccessorID;
    ConcurrentLinkedQueue<TokenRing> tokenRings;
    Processor proc;
    boolean needToken = false;

    /**
     * Default constructor assigns current token ring to local copy and the processor ID of for this token ring
     *
     * @param tokenRings
     * @param proc
     */
    TokenRingAgent(ConcurrentLinkedQueue<TokenRing> tokenRings, Processor proc) {
        this.tokenRings = tokenRings;
        this.processorID = proc.processID;
        this.proc = proc;

        // for all token rings, add this token ring agent
        for (TokenRing tr : tokenRings) {
            tr.addAgent(this);
        }
    }

    /**
     * tell the ring Run method that the tokenAgent wants the Token
     */
    public void requestToken() {
        needToken = true;
    }

    /**
     * This method returns the unique identifier for the token
     * received from the predecessor.
     */
    public void receiveToken(Token t) {
        if (proc.multipleTR) {
            proc.dsm.tokens.add(t);
        } else {
            tokenID = t.tokenID;
            proc.dsm.tokenValue = t.tokenID;
        }
    }

    /**
     * This method sends the token to the successor.
     */
    public void sendToken(Token t) {
        if (proc.multipleTR) {
            proc.dsm.tokens.remove(t);
        } else {
            tokenID = -1;
            proc.dsm.tokenValue = -1;
        }
    }

    /**
     * setter for ringPredecessor
     *
     * @param ringPredecessorID sets the ring successor to this value
     */
    public void setRingPredecessor(int ringPredecessorID) {
        this.ringPredecessorID = ringPredecessorID;
    }

    /**
     * setter for ringSuccessor
     *
     * @param ringSuccessorID sets the ring successor to this value
     */
    public void setRingSuccessor(int ringSuccessorID) {
        this.ringSuccessorID = ringSuccessorID;
    }

    /**
     * getter for processor ID
     *
     * @return return processor ID
     */
    public int getProcessorID() {
        return this.processorID;
    }

    /**
     * disables token agents
     */
    public void disable() {
        Active = false;
    }

    /**
     * setter for Token
     *
     * @param t token to be set
     */
    public void setTokenID(Token t) {
        this.tokenID = t.tokenID;
    }

    /**
     * getter for active variable
     *
     * @return return the current value of Active
     */
    public Boolean getActive() {
        return Active;
    }

    public int getToken() {
        return tokenID;
    }


}
