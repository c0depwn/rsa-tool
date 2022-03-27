package rsa;

import math.GreatestCommonDivisor;

import java.math.BigInteger;
import java.security.SecureRandom;

public class KeyBuilder {
    private SecureRandom secRand;
    private KeySize size = KeySize.DEFAULT;
    private BigInteger phiOfN;
    private BigInteger n;
    private BigInteger d;
    private final BigInteger e = BigInteger.valueOf(65537);

    public KeyBuilder() {
        this.secRand = new SecureRandom();
    }

    public void setSize(KeySize size) {
        this.size = size;
    }

    private void initPAndQ() {
        BigInteger p, q;
        do {
            p = BigInteger.probablePrime(this.size.size(), this.secRand);
            q = BigInteger.probablePrime(this.size.size(), this.secRand);

            // check that e is relatively prime to phi(n)
            this.phiOfN = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        } while (p.equals(q) || this.phiOfN.mod(this.e).equals(BigInteger.ZERO));

        // n = p * q where p != q
        this.n = p.multiply(q);
    }

    private void initD() throws Exception {
        GreatestCommonDivisor gcd = new GreatestCommonDivisor(this.phiOfN, this.e);
        gcd.calculate();

        this.d = gcd.getBezoutIdentity(this.e);

        if (this.d.signum() < 0) {
            this.d = this.phiOfN.add(this.d);
        }

        if (!this.e.multiply(this.d).mod(this.phiOfN).equals(BigInteger.ONE)) {
            throw new Exception("e * d mod phiOfN != 1");
        }
    }

    /**
     * Create a new key pair by generating a new private and public key
     * @return
     * @throws Exception
     */
    public KeyPair build() throws Exception {
        this.initPAndQ();
        this.initD();

        return new KeyPair(
                new PrivateKey(this.n, this.d),
                new PublicKey(this.n, this.e)
        );
    }
}
