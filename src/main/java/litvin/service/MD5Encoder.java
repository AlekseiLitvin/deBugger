package litvin.service;



import litvin.constants.ConstError;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Encoder {

    public static String getEncodedString(String pass){
        final int MD5_RESULT_LENGTH = 32;
        final String ENCTYPE_ALGORITHM = "MD5";
        try {
            MessageDigest md = MessageDigest.getInstance(ENCTYPE_ALGORITHM);
            md.reset();
            md.update(pass.getBytes());
            byte[] digest = md.digest();

            StringBuilder sb = new StringBuilder(MD5_RESULT_LENGTH);
            for (byte b : digest){
                sb.append(String.format("%02x", b & 0xff));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println(ConstError.MD5_ERROR + e);
            throw new RuntimeException(e);
        }
    }
}
