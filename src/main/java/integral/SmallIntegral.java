package integral;

import java.util.concurrent.Callable;

public class SmallIntegral implements Callable<Double> {
    private final double a;
    private final double b;

    private final double eps;

    public SmallIntegral(double a, double b, double eps) {
        this.a = a;
        this.b = b;
        this.eps = eps;
    }

    @Override
    public Double call()  {
        double x = a;
        double sum = 0;
        int n = (int) Math.round(((b - a) / eps));
        for (int i = 0; i < n; i++) {
            sum += Integral.f(x) *eps;
            x += eps;
        }

        return sum;
    }
}
