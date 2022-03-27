package rsa;

import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

/**
 * Crypto can encrypt and decrypt data from an input stream and write it to an output stream
 * using a provided InputTransformer. It reads until EOF file is hit.
 */
public class Crypto {
    private final InputStream source;
    private final OutputStream destination;
    private final InputTransformer transformer;

    public Crypto(InputStream source, OutputStream destination, InputTransformer transformer) {
        this.source = source;
        this.destination = destination;
        this.transformer = transformer;
    }

    /**
     * Encrypt reads data from the InputStream, encrypts the data and writes it to the OutputStream.
     * @param publicKey PublicKey The public key used to encrypt the data.
     * @throws IOException
     */
    public void encrypt(PublicKey publicKey) throws IOException {
        int next = source.read();
        while (next != -1) {
            // this is ok because read() guarantees range 0 - 255
            byte[] b = new byte[]{(byte) next};
            byte[] encrypted = transformer.transform(publicKey.getE(), publicKey.getN(), b);

            String toWrite = new BigInteger(encrypted).toString(10);
            destination.write(toWrite.getBytes(StandardCharsets.UTF_8));

            next = source.read();
            if (next != -1 ) {
                destination.write(",".getBytes(StandardCharsets.UTF_8));
            }
        }
    }

    /**
     * Decrypt reads data from the InputStream, decrypts the data and writes it to the OutputStream.
     * @param privateKey PrivateKey The private key used to decrypt the data.
     * @throws IOException
     */
    public void decrypt(PrivateKey privateKey) throws IOException {
        int next = source.read();
        while (next != -1) {
            String buffer = "";
            while (next != -1 && (char) next != ',') {
                buffer += (char) next;
                next = source.read();
            }
            next = source.read();

            byte[] decrypted = transformer.transform(
                    privateKey.getD(),
                    privateKey.getN(),
                    (new BigInteger(buffer, 10)).toByteArray()
            );
            destination.write(decrypted);
        }
    }
}
