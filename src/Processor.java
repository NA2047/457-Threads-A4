import org.omg.CORBA.INTERNAL;

/**
 * Created by Kleen star on 2017-03-22.
 */
public class Processor extends Thread {
    DSM dsm;
    int processID;


    public Processor(int processID, BroadcastSystem broadcastSystem){
        //process number
        this.processID = processID;
        dsm = new DSM(broadcastSystem);
    }

    public void run(){
        // setups processes and calls pertersons algo
        // need to set up agents

        while (!interrupted()){
            petersonsN();
        }


    }
//    flag = ;
//    turn ends up being the late comer

    public void petersonsN(){
        //<Entry Section>
        for(int k =0; k < 8; k++){
            dsm.store("flag"+this.processID,k);
            dsm.store("turn"+k,this.processID);
            for (int i =0;i<10;i++){

                while ((i!=k)&& ((setFlag(i)>= k) && ))
            }


        }



        //<Critical Section>
        System.out.println(processor);



        //<Exit Section>


        //<Remainder Section>

    }

    //Critical section. takes 2  Integers and returns their multiplication.
    public Integer setFlag(int flag){

        try {
            flag =
        }


    }

    public Integer setTurn(int turn){
        try {

        }

    }


    public void criticalSection(int processer) {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
        System.out.println();


    }





}
