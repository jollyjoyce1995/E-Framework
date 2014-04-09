package at.grueneis.spengergasse.hashCheck;

/**
 * Created by Felix on 02.04.2014.
 */
abstract class Hashable implements BasePersistable{
    private String md5Hash;
    private long id;
    /**
     * -) If the attribute is an object reference, the value in the string should be the ID of the referenced object.
     * -) If the attribute is a list containing object references , the value should be the IDs of all elements in the list
     *
     * @return String[]: Returns a Stringarray containing the values of all attributes. (1 element per attribute).
     */
    abstract String[] getAllAttributesAsString();

    String getMd5Hash(){
        return md5Hash;
    }


    public final void updateMd5Hash(){
        md5Hash = HashGenerator.generateMd5Hash(this);
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }
}
