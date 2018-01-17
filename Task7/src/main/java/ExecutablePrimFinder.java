import java.util.concurrent.Callable;

public class ExecutablePrimFinder implements Callable<Boolean> {

    private int from, to, prim;

    public ExecutablePrimFinder(int from, int to, int prim) {
        this.from = from;
        this.prim = prim;
        this.to = to;
    }

    public Boolean call() throws Exception {
        System.out.println("Thread number: " + Thread.currentThread().getId() + " begins to work!");
        for(int i = from; i <= to; i++){
            if(prim % i == 0){
                return false;
            }
        }
        return true;
    }
}
