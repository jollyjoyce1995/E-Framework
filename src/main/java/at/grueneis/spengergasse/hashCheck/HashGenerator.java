package at.grueneis.spengergasse.hashCheck;

import at.grueneis.spengergasse.lesson_plan.domain.BasePersistable;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Felix on 19.03.2014.
 */
public class HashGenerator{

    public String generateMd5Hash(BasePersistable basePersistable){
        try {
            MessageDigest md5Generator = MessageDigest.getInstance("MD5");
            String str = "";
            for(String s : basePersistable.getAllAttributesAsString()){
                str += s;
            }
            byte[] md5bytes = md5Generator.digest(str.getBytes());
            return new String(md5bytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed to generate md5Hash of basePersistable with id " + basePersistable.getId(),e);
        }
    }
}
