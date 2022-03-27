package rsa;

import java.math.BigInteger;

public class Key {
    private KeySize size = KeySize.DEFAULT;
    private BigInteger n;

    public Key(BigInteger n) {
        this.n = n;
    }

    public KeySize getSize() {
        return size;
    }

    public BigInteger getN() {
        return n;
    }
}
