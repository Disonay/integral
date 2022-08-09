import integral.Integral;

public class Main {
    public static void main(String[] args) {
        Integral integral = new Integral(1, 10, 5, 0.0001);
        System.out.println(integral.solve());
    }
}
