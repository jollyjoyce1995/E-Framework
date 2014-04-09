package at.grueneis.spengergasse.hashCheck;

/**
 * Created by Felix on 09.04.2014.
 */
public class HashableImplementation extends Hashable {
    private int integer  = 100;
    private String string = "Name";

    @Override
    String[] getAllAttributesAsString() {
        return new String[]{""+getId(),string, ""+integer};
    }
}
