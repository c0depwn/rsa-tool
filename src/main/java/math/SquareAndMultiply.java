package math;

import java.math.BigInteger;

public class SquareAndMultiply {
    /**
     * Square and multiply algorithm for efficient x^e mod m.
     * @param x base
     * @param e exponent
     * @param m modulo
     * @return
     */
    public static BigInteger squareAndMultiply(BigInteger x, BigInteger e, BigInteger m) {
        String[] bits = e.toString(2).split("");
        int idx = bits.length - 1;
        BigInteger h = BigInteger.valueOf(1);
        BigInteger k = x;

        while (idx >= 0) {
            if (bits[idx].equals("1")) {
                h = h.multiply(k).mod(m);
            }
            k = k.pow(2).mod(m);
            idx -= 1;
        }

        return h;
    }
}
