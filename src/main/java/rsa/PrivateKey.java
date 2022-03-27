package rsa;

import java.math.BigInteger;

public class PrivateKey extends Key {
    private final BigInteger d;

    public PrivateKey(BigInteger n, BigInteger d) {
        super(n);
        this.d = d;
    }

    public BigInteger getD() {
        return d;
    }
}
