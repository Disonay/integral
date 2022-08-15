package integral;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Integral {
    private final double a;
    private final double b;
    private final int n;

    private final double eps;

    static double f(double x) {
        return x;
    }

    public Integral(double a, double b, int n, double eps) {
        this.a = a;
        this.b = b;
        this.n = n;
        this.eps = eps;
    }

    public Double solve() {
        ExecutorService executor = Executors.newFixedThreadPool(n);
        List<Future<Double>> futures = new ArrayList<>();

        double step = (b-a) / n;
        for (int i = 0; i < n; i++) {
            futures.add(executor.submit(new SmallIntegral(a + i*step, a + (i+1)*step, eps)));
        }

        Double result = futures.stream().map(x -> {
            try {
                return x.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }).mapToDouble(Double::doubleValue).sum();

        executor.shutdown();

        return result;
    }
}
