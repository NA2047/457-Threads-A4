import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * This class is the implementation of the broadcasting
 * mechanism between processors.
 * One BroadcastSystem object is shared between all BroadcastAgents.
 * Executes in a separate thread.
 */

public class BroadcastSystem extends Thread {
    private ArrayList<BroadcastAgent> agentArray;
    private ConcurrentLinkedQueue<Message> messages = new ConcurrentLinkedQueue<>();
    boolean broadcast;
    String x;
    int v;
    int time = 300;

    /**
     * The overridden run method.
     * Broadcasts when necessary.
     * Delays are added to simulate sending/receiving messages.
     */
    @Override
    public void run() {
        while (!interrupted()) {
            if (broadcast) {
                delay();
                messages.add(new Message(x,v));
                while (!messages.isEmpty()){
                    broadcast(messages.peek());
                    messages.poll();
                    delay();
                }
                broadcast = false;
            }
        }
    }

    /**
     * This method broadcasts the message to
     * store to each broadcastAgent.
     * @param msg The message to broadcast.
     */
    public void broadcast(Message msg) {
        for (BroadcastAgent agent : agentArray) {
            agent.receive(msg.x, msg.v);
        }
    }

    /**
     * This method sleeps the class for the amount specified
     * in the time variable.
     */
    public void delay() {
        try {
            sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method sets the local array of broadcastAgents.
     * @param agentArray The array of all broadcastAgents.
     */
    public void setAgents(ArrayList<BroadcastAgent> agentArray) {
        this.agentArray = agentArray;
    }

    public class Message {
        String x;
        int v;

        public Message(String x, int v){
            this.x = x;
            this.v = v;
        }
    }
}


