package blockchain;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

class StringUtil {
    /* Applies Sha256 to a string and returns a hash. */
    public static String applySha256(String input){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            /* Applies sha256 to our input */
            byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte elem: hash) {
                String hex = Integer.toHexString(0xff & elem);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        }
        catch(Exception e) {
            return null;
        }
    }

    public static boolean isValidHash(String hash, int zeros) {
        return hash.substring(0, zeros).equals("0".repeat(zeros));
    }

    public static String encodeToBase64(byte[] bytes) {
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }

    public static byte[] decodeFromBase64(String base64) {
        return Base64.getUrlDecoder().decode(base64);
    }
    public static String extractPubKey(String str) {
        return str.substring(str.length()-64, str.length()-1);
    }
}