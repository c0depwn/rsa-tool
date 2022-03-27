package rsa;

import java.math.BigInteger;

public class PublicKey extends Key {
    private final BigInteger e;

    public PublicKey(BigInteger n, BigInteger e) {
        super(n);
        this.e = e;
    }

    public BigInteger getE() {
        return e;
    }
}
