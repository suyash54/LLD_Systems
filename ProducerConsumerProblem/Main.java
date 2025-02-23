package ProducerConsumerProblem;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Main {


    public static void main(String[] args)  {

        ProducerConsumer producerConsumer = new ProducerConsumer();

        CompletableFuture<Void> producerFuture = CompletableFuture.runAsync(
                producerConsumer::producerCall
        );

        CompletableFuture<Void> consumerFuture = CompletableFuture.runAsync(
                producerConsumer::consumerCall
        );

        try {
            CompletableFuture.allOf(producerFuture, consumerFuture).join();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
