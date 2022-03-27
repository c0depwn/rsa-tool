package rsa;

public class KeyPair {
    public PrivateKey privateKey;
    public PublicKey publicKey;

    public KeyPair(PrivateKey privateKey, PublicKey publicKey) {
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }
}
