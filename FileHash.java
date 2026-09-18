import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;

public class FileHash {
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("Usage: java FileHash <filename>");
            return;
        }

        MessageDigest digest = MessageDigest.getInstance("SHA-256");

        try (InputStream input = new FileInputStream(args[0])) {
            byte[] buffer = new byte[8192];
            int bytesRead;

            while ((bytesRead = input.read(buffer)) != -1) {
                digest.update(buffer, 0, bytesRead);
            }
        }

        byte[] hash = digest.digest();

        for (byte value : hash) {
            System.out.printf("%02x", value & 0xff);
        }

        System.out.println();
    }
}
