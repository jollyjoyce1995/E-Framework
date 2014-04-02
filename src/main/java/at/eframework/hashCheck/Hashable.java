package at.eframework.hashCheck;

/**
 * Created by Felix on 02.04.2014.
 */
abstract class Hashable implements BasePersistable{

    /**
     * -) If the attribute is an object reference, the value in the string should be the ID of the referenced object.
     * -) If the attribute is a list containing object references , the value should be the IDs of all elements in the list
     *
     * @return String[]: Returns a Stringarray containing the values of all attributes. (1 element per attribute).
     */
    abstract String[] getAllAttributesAsString();
    abstract String getMd5Hash();
    public abstract long getId();
    public abstract void setId(long id);
    abstract void setMd5Hash(String md5Hash);

    public final void updateMd5Hash(){
        setMd5Hash(HashGenerator.generateMd5Hash(this));
    }
}
