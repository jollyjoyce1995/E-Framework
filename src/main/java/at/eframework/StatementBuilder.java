package at.eframework;

/**
 * Created by Michi on 12.03.14.
 */
public interface StatementBuilder {
    public String create(TableMapper dao) throws QueryBuilderException;
}
