package rsa;

import java.io.IOException;
import java.io.Writer;

public class KeyWriter {
    private Writer writer;

    public KeyWriter(Writer writer) {
        this.writer = writer;
    }

    public void write(String n, String secondPart) throws IOException {
        String content = "(" + n + "," + secondPart + ")";
        this.writer.write(content);
    }

    public void close() throws IOException {
        this.writer.close();
    }
}
