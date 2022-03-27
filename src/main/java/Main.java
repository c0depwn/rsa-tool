import cli.Decrypt;
import cli.Encrypt;
import cli.EntryPoint;
import cli.Generate;

public class Main {
    public static void main(String[] args) {
        EntryPoint entryPoint = new EntryPoint(System.out);
        entryPoint.register(new Generate(), new Encrypt(), new Decrypt());
        entryPoint.run(args.length > 0 ? args[0] : "", args);
    }
}
