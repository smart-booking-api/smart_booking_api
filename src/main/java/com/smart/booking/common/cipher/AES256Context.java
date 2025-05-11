package com.smart.booking.common.cipher;


import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Base64;
import java.util.Set;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import lombok.NonNull;

public class AES256Context {

    private static final String PADDING = "AES/CBC/PKCS5Padding";
    private static final String CHARSET = "UTF-8";
    private static final String ALGORITHM = "AES";
    private static final int AES_BLOCK_SIZE = 16;
    private static final Set<Integer> AES_KEYSIZES = Set.of(32 /*, 24, 16*/); // 256-bit only
    private static Key key;
    private static AlgorithmParameterSpec iv;

    public static void setKey(@NonNull String keyPart, @NonNull String ivPart) {
        if (!AES_KEYSIZES.contains(keyPart.length())) {
            throw new RuntimeException("AES config key length not valid! (" + keyPart.length() +
                " != " + AES_KEYSIZES + ")");
        }

        if (ivPart.length() != AES_BLOCK_SIZE) {
            throw new RuntimeException("AES config iv length not valid! (" + ivPart.length() + " != " + AES_BLOCK_SIZE + ")");
        }

        key = new SecretKeySpec(keyPart.getBytes(StandardCharsets.UTF_8), ALGORITHM);
        iv = new IvParameterSpec(ivPart.getBytes(StandardCharsets.UTF_8));
    }

    public static String encrypt(@NonNull String plainText) throws Exception {
        return encode(plainText);
    }

    public static String decrypt(String encryptedText) throws Exception {
        return decode(encryptedText);
    }

    private static Cipher getCipher(int operationMode) throws Exception {
        Cipher cipher = Cipher.getInstance(PADDING);
        cipher.init(operationMode, key, iv);
        return cipher;
    }

    private static String encode(String plainText) throws Exception {
        Cipher cipher = getCipher(Cipher.ENCRYPT_MODE);
        byte[] encrypted = cipher.doFinal(plainText.getBytes(CHARSET));
        return Base64.getEncoder().encodeToString(encrypted);
    }

    private static String decode(String encodedText) throws Exception {
        Cipher cipher = getCipher(Cipher.DECRYPT_MODE);
        byte[] decodedBytes = Base64.getDecoder().decode(encodedText);
        byte[] decrypted = cipher.doFinal(decodedBytes);
        return new String(decrypted, CHARSET);
    }
}
