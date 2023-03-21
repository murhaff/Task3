import java.util.HashMap;
import java.util.concurrent.RecursiveTask;

public class Fibonacci extends RecursiveTask<Integer> {
    private static final int CUTOFF = 20;
    private final int n;
    private static final HashMap<Integer, Integer> cache = new HashMap<>();

    public Fibonacci(int n) {
        this.n = n;
    }

    @Override
    protected Integer compute() {
        if (n <= CUTOFF) {
            return computeSeq();
        }
        Integer result = cache.get(n);
        if (result != null) {
            return result;
        }
        Fibonacci f1 = new Fibonacci(n - 1);
        f1.fork();
        Fibonacci f2 = new Fibonacci(n - 2);
        int res = f2.compute() + f1.join();
        cache.put(n, res);
        return res;
    }

    public Integer computeSeq() {
        if (n <= 1) {
            return n;
        }
        Integer result = cache.get(n);
        if (result != null) {
            return result;
        }
        Fibonacci f1 = new Fibonacci(n - 1);
        Fibonacci f2 = new Fibonacci(n - 2);
        int res = f2.computeSeq() + f1.computeSeq();
        cache.put(n, res);
        return res;
    }
}
