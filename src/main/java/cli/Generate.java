package cli;

import cli.command.Command;
import cli.command.Option;
import rsa.KeyBuilder;
import rsa.KeyPair;
import rsa.KeyWriter;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class Generate extends Command {
    @Option(displayName = "OUTPUT_DIR", flagName = "o")
    private String path;

    public Generate() {
        super();
    }

    @Override
    public void configure() {
        super.setIcon("🔨");
        super.setName("generate");
        super.setDescription("generate a new key pair and store it at the specified location");
    }

    @Override
    public void run(PrintStream output) {
        try {
            output.println("✨\t...generating key pair");
            KeyBuilder builder = new KeyBuilder();
            KeyPair pair = builder.build();

            Path targetFolder = FileSystems.getDefault()
                    .getPath(this.path)
                    .normalize()
                    .toAbsolutePath();

            output.println("📝\t...writing keys to " + targetFolder);

            FileWriter publicKeyFileWriter = new FileWriter(Path.of(targetFolder.toString(), "pk.txt").toString());
            FileWriter privateKeyFileWriter = new FileWriter(Path.of(targetFolder.toString(), "sk.txt").toString());

            KeyWriter publicKeyWriter = new KeyWriter(publicKeyFileWriter);
            publicKeyWriter.write(pair.publicKey.getN().toString(), pair.publicKey.getE().toString());
            publicKeyWriter.close();

            KeyWriter privateKeyWriter = new KeyWriter(privateKeyFileWriter);
            privateKeyWriter.write(pair.publicKey.getN().toString(), pair.privateKey.getD().toString());
            privateKeyWriter.close();

            output.println("✔︎\t...done");
        } catch (Exception e) {
            return;
        }

        output.println("\nℹ️ \tNotice: use the encrypt and decrypt commands to work encrypt and decrypt a message");
    }
}
