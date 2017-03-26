/**
 * Created by Kleen star on 2017-03-22.
 */
public class Processor extends Thread {
    DSM dsm;
    int processID;
    static int test = 1;
    int N = 10;


    public Processor(int processID, BroadcastSystem broadcastSystem){
        //process number
        this.processID = processID;
        dsm = new DSM(broadcastSystem);
    }

    public void run() {


        while (!interrupted()) {
            petersonsN();
        }
    }


    public void petersonsN(){

        //<Entry Section>
        for(int k=0; k<N-2; k++){
            try { // processor that is competing at level k
                dsm.store("flag"+processID, k);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {//tells the current level that its ProcessorIDs turn
                dsm.store("turn"+k, processID);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //the while loop from petersons algo
            while((ThereExists(k)) && (dsm.load("turn"+k) == 1));

        }

        //<Critical Section>
        System.out.println("Process "+ processID+" is in the CS");


            try {
                System.out.println("   Increment test value by processor "+ processID +"  =  "+(++test));
                Thread.sleep(50);

            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        System.out.println("Process "+ processID+" is leaving the CS");
        //<Critical Section>

        //<Exit Section>
            try {
                dsm.store("flag"+processID, -1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

    }

    private Boolean ThereExists(int turn){
        Boolean thereExists;
        thereExists = false;
        for(int j=0; j<10; j++){
            if(j != this.processID){
                if(dsm.load("flag"+j) >= turn){
                    thereExists = true;
                    return thereExists;
                }
            }
        }
        return false;
    }


}







