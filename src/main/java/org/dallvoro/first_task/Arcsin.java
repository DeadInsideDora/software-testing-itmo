package org.dallvoro.first_task;

public class Arcsin {
    private final double[] factorials;
    private final int steps;

    public Arcsin(int n) {
        steps = n;
        factorials = new double[2 * (n + 1)];
        factorials[0] = factorials[1] = 1;
        for (int i = 2; i <= 2 * n + 1; ++i) {
            factorials[i] = factorials[i - 2] * i;
        }
    }

    public double calculate(double x) {
        if (Double.isNaN(x) || x < -1 || x > 1) {
            throw new IllegalArgumentException(String.format("Expected x for arcsin(x) in [-1; 1], found: %f", x));
        }
        double result = 0;
        double currentPow = x;
        for (int i = 0; i < steps; ++i) {
            double square = (2 * i + 1) * (2 * i + 1);
            result += (factorials[2 * i + 1] / factorials[2 * i]) * (currentPow / square);
            currentPow *= x * x;
        }
        return result;
    }
}
