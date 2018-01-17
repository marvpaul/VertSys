import java.util.ArrayList;
import java.util.concurrent.*;

public class Prim {

    public boolean isPrim(int prim, int tasksToSplitIn){
        //https://stackoverflow.com/questions/20221408/how-to-pass-a-parameter-to-a-thread-and-get-a-return-value
        ExecutorService executorService = Executors.newFixedThreadPool(tasksToSplitIn);



        int analyzeUpTo = prim;
        int part_size = analyzeUpTo / tasksToSplitIn;



        try {
            executorService.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        boolean isPrim = true;
        ArrayList<Future<Boolean>> tasks = new ArrayList<Future<Boolean>>();
        for(int i = 0; (i+1) * part_size <= analyzeUpTo; i++){
            int start;
            int end = part_size*(i+1);
            if(i == 0){
                start = part_size*i+2;
            } else{
                start = part_size*i+1;
            }
            //Start a new thread and add to thread array
            tasks.add(executorService.submit(new ExecutablePrimFinder(start, end, prim)));

        }
        executorService.shutdown();
        //Try to shutdown and wait for all threads to be ready
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            System.err.println("Task runs to long!");
        }
        for(Future<Boolean> task : tasks){
            try {
                boolean partial_result = task.get(10, TimeUnit.MILLISECONDS);
                System.out.println("Woho, received partial result: " + partial_result);
                isPrim &= partial_result;
            } catch (Exception e) {
                // interrupts if there is any possible error
                task.cancel(true);
            }
        }

        return isPrim;
    }
}
