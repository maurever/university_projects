package org.wpa.provider;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.springframework.stereotype.Component;

@Component("hashProvider")
public class SHA1Provider implements HashProvider {

    private static String convertToHex(byte[] data) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            int halfbyte = (data[i] >>> 4) & 0x0F;
            int two_halfs = 0;
            do {
                if ((0 <= halfbyte) && (halfbyte <= 9)) {
                    builder.append((char) ('0' + halfbyte));
                } else {
                    builder.append((char) ('a' + (halfbyte - 10)));
                }
                halfbyte = data[i] & 0x0F;
            } while (two_halfs++ < 1);
        }
        return builder.toString();
    }

    public static String computeHash(String s) {
        MessageDigest md = null;
        byte[] sha1hash;
        try {
            md = MessageDigest.getInstance("SHA-1");
            md.update(s.getBytes("utf8"), 0, s.length());
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
        sha1hash = md.digest();
        return convertToHex(sha1hash);
    }
}
