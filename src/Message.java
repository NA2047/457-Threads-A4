/**
 * Created by lambda on 2017-03-28.
 */

/**
 * Message class is ued to store turn and flag
 */
public class Message {
    String x;
    int v;

    /**
     * default constructor for Message
     * @param x String either turn or flag
     * @param v Integer to store
     */
    public Message(String x, int v){
        this.x = x;
        this.v = v;
    }
}