package dbService.jdbc.executor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by qwe on 01.06.2016.
 */
public interface ResultHandler<T> {
    T handle(ResultSet resultSet) throws SQLException;
}
