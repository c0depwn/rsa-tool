package cli;

import cli.command.Command;
import cli.command.Option;
import rsa.CharacterTransformer;
import rsa.Crypto;
import rsa.KeyReader;
import rsa.PublicKey;
import java.io.*;
import java.nio.file.FileSystems;

public class Encrypt extends Command {
    @Option(displayName = "PUBLIC_KEY", flagName = "p")
    private String publicKeyPath;
    @Option(displayName = "INPUT_FILE_PATH", flagName = "i")
    private String messagePath;
    @Option(displayName = "OUTPUT_DIR_PATH", flagName = "o")
    private String targetPath;

    public Encrypt() {
        super();
    }

    @Override
    public void configure() {
        super.setIcon("🔐");
        super.setName("encrypt");
        super.setDescription("encrypt a given file");
    }

    @Override
    public void run(PrintStream output) {
        File publicKeyFile = FileSystems.getDefault()
                .getPath(this.publicKeyPath)
                .normalize()
                .toAbsolutePath()
                .toFile();
        File messageFile = FileSystems.getDefault()
                .getPath(this.messagePath)
                .normalize()
                .toAbsolutePath()
                .toFile();

        try {
            output.println("💭... processing public key " + publicKeyFile.getAbsolutePath());

            FileReader pubKeyReader = new FileReader(publicKeyFile);
            KeyReader<PublicKey> reader = new KeyReader<>(pubKeyReader);
            PublicKey pubKey = reader.read(PublicKey::new);
            reader.close();

            output.println("💭... processing message file " + messageFile.getAbsolutePath());

            File target = new File(this.targetPath + "/chiffre.txt");
            FileOutputStream fileOutputStream = new FileOutputStream(target);
            FileInputStream fileInputStream = new FileInputStream(messageFile);

            output.println("🔑... encrypting ");

            Crypto crypto = new Crypto(fileInputStream, fileOutputStream, new CharacterTransformer());
            crypto.encrypt(pubKey);

            fileInputStream.close();
            fileOutputStream.close();

            output.println("🔒... done encrypted file located at " + target.toPath().normalize().toAbsolutePath());
        } catch (FileNotFoundException e) {
            output.println("⚠️ File not found: " + e.getMessage());
        } catch (IOException e) {
            output.println("⚠️ Could not read file: " + e.getMessage());
        }
    }
}
