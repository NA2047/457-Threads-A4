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

//        while (!interrupted()){
//            petersonsN();
//        }


    }

//    public void petersonsN(){
//        //<Entry Section>
//        for(int i =0; i < 9; i++){
//            dsm.store("flag"+this.processID,i);
//            dsm.store("turn"+i,this.processID);
//            for (int j =0; j<10;j++){
//
//                while ((i!=j)&& ((set)))
//            }
//
//
//        }
//
//
//
//        //<Critical Section>
//        System.out.println(processor);
//
//
//
//        //<Exit Section>
//
//
//        //<Remainder Section>
//
//    }

    //Critical section. takes 2  Integers and returns their multiplication.
    public void setFlag(int flag){
        
    }



}
