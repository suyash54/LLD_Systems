package ProducerConsumerProblem;


import java.util.ArrayList;
import java.util.List;

public class ProducerConsumer {

   List<Integer> messageQueue = new ArrayList<>();

   int bufferSize = 2;

   public void producerCall(){
       int producedData = 0;

       try{
           while(true){
               synchronized (this){

                   while(messageQueue.size() == bufferSize) {
                       wait();
                   }

                   messageQueue.add(producedData++);
                   System.out.println("Producer has produced data "+ producedData);
                   Thread.sleep(3000);

                   notify();
               }
           }
       }
       catch (InterruptedException e){
           e.printStackTrace();
       }
   }

   public void consumerCall(){

       try{
           while(true){
               synchronized (this){

                   while(messageQueue.isEmpty()){
                       wait();
                   }

                   int removedData = messageQueue.removeFirst();
                   System.out.println("Consumer has consumed data "+ removedData);
                   Thread.sleep(3000);

                   notify();
               }
           }
       }
       catch(InterruptedException e){
           e.printStackTrace();
       }
   }
}
