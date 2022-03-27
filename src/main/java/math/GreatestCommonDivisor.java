package math;

import java.math.BigInteger;
import java.util.HashMap;

public class GreatestCommonDivisor {
    private final BigInteger x;
    private final BigInteger y;
    private final HashMap<BigInteger, BigInteger> coefficients;

    public GreatestCommonDivisor(BigInteger x, BigInteger y) {
        this.x = x;
        this.y = y;
        this.coefficients = new HashMap<>();
    }

    /**
     * Calculate the GCD efficiently using extended euclidean algorithm.
     * @return GCD of x and y
     */
    public BigInteger calculate() {
        BigInteger
                a = x.max(y),
                b = x.min(y),
                x0 = BigInteger.valueOf(1),
                y0 = BigInteger.valueOf(0),
                x1 = BigInteger.valueOf(0),
                y1 = BigInteger.valueOf(1);

        while (!b.equals(BigInteger.ZERO)) {
            BigInteger
                    q = a.divide(b),
                    r = a.mod(b);

            BigInteger tmpX1 = x1;
            BigInteger tmpY1 = y1;
            x1 = x0.subtract(x1.multiply(q));
            y1 = y0.subtract(y1.multiply(q));
            x0 = tmpX1;
            y0 = tmpY1;

            a = b;
            b = r;
        }

        this.coefficients.put(x.max(y), x0);
        this.coefficients.put(x.min(y), y0);
        return a;
    }

    /**
     * Get the bezout identity of initially supplied variable.
     * @param x one of the variables passed to the constructor
     * @return bezout identity of x
     */
    public BigInteger getBezoutIdentity(BigInteger x) {
        return this.coefficients.get(x);
    }
}
