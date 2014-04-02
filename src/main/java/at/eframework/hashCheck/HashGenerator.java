package at.eframework.hashCheck;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Felix on 19.03.2014.
 */
public class HashGenerator{

    public static String generateMd5Hash(Hashable hashable){
        try {
            MessageDigest md5Generator = MessageDigest.getInstance("MD5");
            String str = "";
            for(String s : hashable.getAllAttributesAsString()){
                str += s;
            }
            byte[] md5bytes = md5Generator.digest(str.getBytes());
            return new String(md5bytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed to generate md5Hash of hashable with id " + hashable.getId(),e);
        }
    }
}
