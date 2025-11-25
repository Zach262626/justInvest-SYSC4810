//Zachary Gallant 101272210
package mylabs.app;
import java.io.*;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

public class Problem2c {
    
    private static String passwordDatabase = "passwords.txt";
    private static final int SALT_BYTES = 16;
    private static final String HASH_ALGORITHM = "SHA-512";
    private static final String DELIMITER = "\\|";
    private static final char FIELD_SEPARATOR = '|';

    public static void setPasswordDatabase(String path) {
        passwordDatabase = path;
    }

    public static void addUser(String username, String password, String role) throws Exception {
        byte[] randomSalt = generateSalt();
        String encodedSalt = Base64.getEncoder().encodeToString(randomSalt);
        String passwordHash = computeHash(encodedSalt, password);

        StringBuilder entry = new StringBuilder();
        entry.append(username).append(FIELD_SEPARATOR);
        entry.append(encodedSalt).append(FIELD_SEPARATOR);
        entry.append(passwordHash).append(FIELD_SEPARATOR);
        entry.append(role);

        try (FileWriter fw = new FileWriter(passwordDatabase, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(entry.toString());
            bw.newLine();
        }
    }

    public static List<String> retrieveUserInfo(String username) throws IOException {
        File database = new File(passwordDatabase);
        if (!database.exists()) {
            return null;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(passwordDatabase))) {
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                String[] fields = currentLine.split(DELIMITER);

                if (fields.length >= 4 && fields[0].equals(username)) {
                    String salt = fields[1];
                    String hash = fields[2];
                    String userRole = fields[3];
                    return Arrays.asList(salt, hash, userRole);
                }
            }
        }

        return null;
    }


    public static boolean verifyUser(String username, String password) throws Exception {
        List<String> storedData = retrieveUserInfo(username);

        if (storedData == null || storedData.size() < 2) {
            return false;
        }

        String storedSalt = storedData.get(0);
        String storedPasswordHash = storedData.get(1);
        String recomputedHash = computeHash(storedSalt, password);

        return storedPasswordHash.equals(recomputedHash);
    }


    private static byte[] generateSalt() {
        byte[] saltBytes = new byte[SALT_BYTES];
        SecureRandom rng = new SecureRandom();
        rng.nextBytes(saltBytes);
        return saltBytes;
    }


    private static String computeHash(String base64Salt, String password) throws Exception {
        MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);

        byte[] decodedSalt = Base64.getDecoder().decode(base64Salt);
        digest.update(decodedSalt);
        digest.update(password.getBytes());

        byte[] hashResult = digest.digest();
        return Base64.getEncoder().encodeToString(hashResult);
    }


    public static String getUserRole(String username) throws IOException {
        List<String> data = retrieveUserInfo(username);
        if (data != null && data.size() >= 3) {
            return data.get(2);
        }
        return null;
    }


    public static void clearPasswordDatabase() throws IOException {
        java.nio.file.Files.deleteIfExists(java.nio.file.Paths.get(passwordDatabase));
    }
}