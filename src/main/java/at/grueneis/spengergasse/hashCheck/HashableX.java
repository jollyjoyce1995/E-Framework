package at.grueneis.spengergasse.hashCheck;

/**
 * Created by Felix on 02.04.2014.
 */
public abstract class HashableX {
    private String md5Hash;

/**
 * -) If the attribute is an object reference, the value in the string should be the ID of the referenced object.
 * -) If the attribute is a list containing object references , the value should be the IDs of all elements in the list
 * @return String[]: Returns a Stringarray containing the values of all attributes. (1 element per attribute).
 */
    public abstract String[] getAllAttributesAsString();

    public String getMd5Hash() {
        return md5Hash;
    }

    public void setMd5Hash(String md5Hash) {
        this.md5Hash = md5Hash;
    }
}
