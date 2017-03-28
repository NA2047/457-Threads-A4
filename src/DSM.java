import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * This class represents the DSM layer.
 */

public class DSM {
    LocalMemory localMemory;
    BroadcastAgent broadcastAgent;
    TokenRingAgent tokenRingAgent;
    Processor proc;
    int tokenValue = -1;
    ConcurrentLinkedQueue<Token> tokens;
    Token token;

    /**
     * The constructor of DSM.
     * LocalMemory and BroadcastAgent are instantiated.
     * @param broadcastSystem is the global instance.
     */
    public DSM(BroadcastSystem broadcastSystem, Processor proc, TokenRingAgent tokenRingAgent) {
        this.proc = proc;
        localMemory = new LocalMemory(proc.N);
        broadcastAgent = new BroadcastAgent(broadcastSystem, this);
        this.tokenRingAgent = tokenRingAgent;
        tokens = new ConcurrentLinkedQueue<>();
    }

    /**
     * This method loads the value of x from local memory.
     * @param x The item to load a value from.
     * @return The loaded value from memory.
     */
    public int load(String x) {
        return localMemory.load(x);
    }


//    public void store(String x, int v){
//
//        TokenRingAgent LocalTRA = proc.tra;
//        int tok = LocalTRA.getToken();
//
////        while (LocalTRA.getActive() && tok ==-1){
////            try {
////                Thread.sleep(0);
////            }
////            catch (InterruptedException e){
////                e.printStackTrace();
////            }
////        }
//
//        localMemory.store(x, v);
//        // broadcast a message to all other DSMs to apply the write locally in their replicas
//        try {
//            broadcastAgent.broadcast(x, v);
//
//        }catch (InterruptedException e){
//            e.printStackTrace();
//        }
//    }

    /**
     * This method stores to local memory and broadcasts
     * that store to all other DSMs.
     * @param x The item to store.
     * @param v The value to store.
     */
    public void store(String x, int v) {
        if (tokenRingAgent.getActive()){
            if (v == -1){
                // need to change peek() to allow for picking the necessary token ring
                // that corresponds to the turn that you want to write to
                tokenRingAgent.tokenRings.peek().removeAgent(tokenRingAgent);
                return;
            }
            // if there is > 1 token ring, flag storage does not require a token
            else if (x.contains("flag") && proc.multipleTR){}
            else {
//                System.out.println("pid: " + proc.processID + " & token: " + tokenValue);
                if (proc.multipleTR){
                    // the token it needs to acquire needs to be the same number as the turn
                    String temp = x;
                    while (Character.isLetter(temp.charAt(0))){
                        temp = temp.substring(1);
                    }
                    int turn = Integer.parseInt(temp);
                    while (tokens.isEmpty()){
                        tokenRingAgent.requestToken();
                    }

                    try {
                        proc.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    while (!tokens.isEmpty() && tokens.peek().tokenID != turn){
//                        try {
//                            proc.sleep(0);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
                        tokenRingAgent.sendToken(tokens.poll());
                        tokenRingAgent.requestToken();
//                        try {
//                            proc.sleep(0);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
                    }
                }
                while (!proc.multipleTR && tokenValue == -1){
//                System.out.println("stuck");
//                    try {
//                        proc.sleep(0);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                    tokenRingAgent.requestToken();
//                    try {
//                        proc.sleep(0);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                System.out.println(proc.processID + " has token ID: " + tokenRingAgent.tokenID);
                }
            }
        }

        // write v into x in local memory
        localMemory.store(x, v);
        // broadcast a message to all other DSMs to apply the write locally in their replicas

        broadcastAgent.dsm.localMemory.store(x, v);
//        broadcastAgent.broadcast(x,v);

        if (x.contains("flag") && proc.multipleTR){
            // don't need to send token if multiple token rings and storing flag
        }
        else if (v != -1 && !proc.multipleTR && tokenRingAgent.getActive()){
            tokenRingAgent.sendToken(null);
//            System.out.println(proc.processID + " has token ID: " + tokenRingAgent.tokenID);
        }
        else {
            return;
        }
    }
}
