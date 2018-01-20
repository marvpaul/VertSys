import java.util.ArrayList;
import java.util.concurrent.*;

public class Prim {
    private static ExecutorService executorService;
    private static ArrayList<Future<Boolean>> tasks;

    /**
     * This method is for checking if a certain number is a prim number
     * @param prim the certain number
     * @param tasksToSplitIn
     * @return
     */
    public static boolean isPrim(int prim, int tasksToSplitIn){
        //Create executor service
        executorService = Executors.newFixedThreadPool(tasksToSplitIn);

        int part_size = prim / tasksToSplitIn;

        tasks = split_into_tasks_and_submit_job(part_size, prim);

        wait_for_termination();

        return merge_results();
    }

    /**
     * Wait until all tasks finished
     */
    private static void wait_for_termination(){
        //Try to shutdown and wait for all threads to be ready
        executorService.shutdown();
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            System.err.println("Task runs to long!");
        }
    }

    /**
     * Merge all results
     * @return return true if number is prim
     */
    private static boolean merge_results(){
        boolean isPrim = true;

        //iterate through each task, get result, combine the results
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

    /**
     * Split the job into subtasks and execute these tasks
     * @param part_size the amount of numbers each tasks has to check
     * @param prim the number which could actually be a prim number
     * @return the submitted tasks
     */
    private static ArrayList<Future<Boolean>> split_into_tasks_and_submit_job(int part_size, int prim){
        ArrayList<Future<Boolean>> tasks = new ArrayList<>();

        //Split into several tasks by defining some intervals
        for(int i = 0; (i+1) * part_size <= prim; i++){
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
        return tasks;
    }
}
