import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * This is the Main class where the processors are created
 * and the BroadcastSystem is created.
 */

public class Main {
    public static void main(String[] args) {
        BroadcastSystem broadcastSystem = new BroadcastSystem();
        broadcastSystem.start();

        int numberOfProcessors = 10;
        int numberOfTokenRings = 1;
        boolean enableTokenRing = true;
        boolean multipleTR = true;

        Scanner in = new Scanner(System.in);
        System.out.println("number of processors? default is 10");
        System.out.print("> ");
        String procNum = in.nextLine();
        if (!procNum.equals("")) {
            numberOfProcessors = Integer.parseInt(procNum);
        }
        System.out.println("enable token ring? default is true, f to disable");
        System.out.print("> ");
        if (in.nextLine().equals("f")) {
            enableTokenRing = false;
            multipleTR = false;
        } else {
            System.out.println("multiple token rings? default is true, f to disable");
            System.out.print("> ");
            if (in.nextLine().equals("f")) {
                multipleTR = false;
            }
        }

        String type;
        if (!enableTokenRing) {
            type = "Peterson's";
        } else if (!multipleTR) {
            type = "Single TR";
        } else {
            type = "Multi TR";
        }

        System.out.println("\n--- Settings ---");
        System.out.println("|   Algo: " + type);
        System.out.println("|   numberOfProcessors: " + numberOfProcessors + "   enableTokenRing: " + enableTokenRing + "   multipleTR: " + multipleTR + "\n");

        ArrayList<Processor> processors = new ArrayList<>();
        ConcurrentLinkedQueue<TokenRing> tokenRings = new ConcurrentLinkedQueue<>();
        ConcurrentLinkedQueue<TokenRingAgent> tokenRingAgents = new ConcurrentLinkedQueue<>();

        if (multipleTR) {
            numberOfTokenRings = numberOfProcessors - 1;
        }

        /**
         * create token rings
         */
        for (int i = 0; i < numberOfTokenRings; i++) {
            TokenRing tr = new TokenRing(i);
            tokenRings.add(tr);
        }

        /**
         *   create all of the processors
         */
        for (int i = 0; i < numberOfProcessors; i++) {
            processors.add(new Processor(i, broadcastSystem, tokenRings, numberOfProcessors, multipleTR));
            tokenRingAgents.add(processors.get(i).tra);
        }

        /**
         * set all of the agents in the broadcast system
         * broadcastSystem.setAgents(broadcastAgents);
         */
        if (enableTokenRing) {
            // set tokenRingAgents and start the token ring(s)
            for (TokenRing tr : tokenRings) {
                tr.start();
            }
        } else {
            // for each token ring agent, call the disable method
            for (TokenRingAgent tra : tokenRingAgents) {
                tra.disable();
            }
        }

        // start each thread
        for (Processor p : processors) {
            for (TokenRing tr : tokenRings) {
                if (tr.flag == false) {
                    tr.flag = true;
                }
            }
            p.start();
        }
    }
}
