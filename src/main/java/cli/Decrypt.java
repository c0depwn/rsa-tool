package cli;

import cli.command.Command;
import cli.command.Option;
import rsa.CharacterTransformer;
import rsa.Crypto;
import rsa.KeyReader;
import rsa.PrivateKey;

import java.io.*;
import java.nio.file.FileSystems;

public class Decrypt extends Command {
    @Option(displayName = "PRIVATE_KEY", flagName = "s")
    private String privateKeyPath;
    @Option(displayName = "INPUT_FILE_PATH", flagName = "i")
    private String messagePath;
    @Option(displayName = "OUTPUT_DIR_PATH", flagName = "o")
    private String targetPath;

    public Decrypt() {
        super();
    }

    @Override
    public void configure() {
        super.setIcon("🔓");
        super.setName("decrypt");
        super.setDescription("decrypt a given file");
    }

    @Override
    public void run(PrintStream output) {
        File privateKeyFile = FileSystems.getDefault()
                .getPath(this.privateKeyPath)
                .normalize()
                .toAbsolutePath()
                .toFile();
        File messageFile = FileSystems.getDefault()
                .getPath(this.messagePath)
                .normalize()
                .toAbsolutePath()
                .toFile();

        try {
            output.println("💭... processing private key " + privateKeyFile.getAbsolutePath());

            FileReader privateKeyReader = new FileReader(privateKeyFile);
            KeyReader<PrivateKey> reader = new KeyReader<>(privateKeyReader);
            PrivateKey privateKey = reader.read(PrivateKey::new);
            reader.close();

            output.println("💭... processing message file " + messageFile.getAbsolutePath());

            File target = new File(this.targetPath + "/text-d.txt");
            FileOutputStream fileOutputStream = new FileOutputStream(target);
            FileInputStream fileInputStream = new FileInputStream(messageFile);

            output.println("🔑... decrypting ");

            Crypto crypto = new Crypto(fileInputStream, fileOutputStream, new CharacterTransformer());
            crypto.decrypt(privateKey);

            fileInputStream.close();
            fileOutputStream.close();

            output.println("🔓... done decrypted file located at " + target.toPath().normalize().toAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
