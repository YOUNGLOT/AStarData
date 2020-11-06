package dao.base;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface ParameterSetter {
    void setValue(PreparedStatement preparedStatement) throws SQLException;
}
