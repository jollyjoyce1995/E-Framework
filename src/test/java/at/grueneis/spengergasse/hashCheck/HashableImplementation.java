package at.grueneis.spengergasse.hashCheck;

/**
 * Created by Felix on 02.04.2014.
 */
public class HashableImplementation extends Hashable {
    private int integer  = 100;
    private String string = "Name";
    private long id;
    private String md5Hash;

    public String[] getAllAttributesAsString() {
        return new String[]{""+this.getId(), ""+integer, string};
    }

    @Override
    public String getMd5Hash() {
        return md5Hash;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public void setMd5Hash(String md5Hash) {
        this.md5Hash = HashGenerator.generateMd5Hash(this);
    }
}
