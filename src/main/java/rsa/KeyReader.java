package rsa;

import java.io.IOException;
import java.io.Reader;
import java.math.BigInteger;
import java.util.Scanner;
import java.util.function.BiFunction;

public class KeyReader<T> {
    private Reader source;

    public KeyReader(Reader source) {
        this.source = source;
    }

    public T read(BiFunction<BigInteger, BigInteger, T> keyInitializer) {
        Scanner s = new Scanner(this.source);
        String content = s.nextLine();

        String[] parts = extractParts(content);
        return keyInitializer.apply(new BigInteger(parts[0], 10), new BigInteger(parts[1], 10));
    }

    public void close() throws IOException {
        this.source.close();
    }

    private static String[] extractParts(String content) {
        return content.replaceAll("[\\(\\)]", "").split(",");
    }
}
