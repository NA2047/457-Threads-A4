import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
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
//    BlockingQueue<TokenRingAgent> agentArray;
    int tokenRingID;
    Token token;
    int i = 0;
    boolean flag;

    /**
     * constructor for token ring, set instance variables
     * @param tokenRingID
     */
    public TokenRing(int tokenRingID) {
        this.tokenRingID = tokenRingID;
        token = new Token(tokenRingID);
//        agentArray = new ArrayBlockingQueue<TokenRingAgent>(10);
        agentArray = new ConcurrentLinkedQueue<>();

    }

    /**
     * run method for TokenRing listens for to
     */
    @Override
    public void run() {
        // give the token to the first proc (i)
        // once the proc stores, give up the token
        // give the token to the next proc (i++)
        while (flag) {

            if (agentArray.size() == 0){
//                System.out.println("PID id "+agentArray.peek().processorID + " with token" + agentArray.peek().tokenID);
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
//    public void run(){
//
////        while(flag){
//            System.out.println("PID id "+agentArray.peek().processorID + " with token" + agentArray.peek().tokenID);
//            while (!agentArray.isEmpty()){
//                if (agentArray.size() == 0){
//                flag = false;
//                System.out.println("token ring empty");
//                break;
//            }
//            if (i >= agentArray.size() - 1) {
//                i = 0;
//            }
//            if (agentArray.peek().needToken) {
//                agentArray.peek().receiveToken(token);
//            }
//            try {
//                Thread.sleep(200); // may need to play around with time
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//
//
//
//        }
//    }



//    public void addItems(TokenRingAgent tokenRingAgent){
//        agentArray.add(tokenRingAgent);
//    }

    /**
     * add each agent upon creation to agentArray
     * @param tokenRingAgent agent of Processor thread
     */
    public void addItems(TokenRingAgent tokenRingAgent){
        agentArray.add(tokenRingAgent);
    }

    /**
     *  this method removes agent from the Q after they have stored -1
     * @param tra agent to be removed from Q
     */
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
//    public void setAgents(ArrayBlockingQueue<TokenRingAgent> agentArray) {
//        this.agentArray = agentArray;
//    }

}


