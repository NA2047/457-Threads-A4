/**
 * Created by Kleen star on 2017-03-22.
 */
public class Processor extends Thread {
    DSM dsm;
    int processID;
    Integer multiplicant1;
    Integer multiplicant2;

    public Processor(int processID){
        //process number
        this.processID = processID;
        dsm = new DSM();
    }

    public void run(){
        // setups processes and calls pertersons algo
        // need to set up agents
    }

    public int petersonsN(){
        //<Entry Section>

        //<Critical Section>
        int c = criticalSection(multiplicant1, multiplicant2);



        //<Exit Section>


        return c;
        //<Remainder Section>

    }

    //Critical section. takes 2  Integers and returns their multiplication.
    public int criticalSection(int x, int y) {


        multiplicant1 = x;
        multiplicant2 = y;
        return (multiplicant1 * multiplicant2);
    }



}
