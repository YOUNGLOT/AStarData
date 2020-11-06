package dao.base;

import help.Helper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class EntityDao<E> {

    //  Helper 커넥션 함수 호출
    protected final Connection getConnection() throws SQLException {
        return new Helper().getConnection();
    }

    //  하위 클래스에서 구현 해야 할 추상 함수 들
    protected abstract E readEntity(ResultSet resultSet) throws SQLException;

    public abstract boolean update(E entity) throws SQLException;

    public abstract boolean insert(E entity) throws SQLException;

    public abstract int getCount() throws SQLException;

    public abstract ArrayList<E> getAll() throws SQLException;

    protected final E getOne(String query, ParameterSetter parameterSetter) throws SQLException {
        ArrayList<E> entities = getMany(query, parameterSetter);
        return (entities.size() == 0) ? null : entities.get(0);
    }

    protected final ArrayList<E> getMany(String query, ParameterSetter parameterSetter) throws SQLException {
        Connection connection = getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        if (parameterSetter != null) {
            parameterSetter.setValue(preparedStatement);
        }

        ResultSet resultSet = preparedStatement.executeQuery();

        ArrayList<E> entities = new ArrayList<>();
        while (resultSet.next()) {
            E entity = readEntity(resultSet);
            entities.add(entity);
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();

        return entities;
    }

    protected final int getInt(String query, ParameterSetter parameterSetter) throws SQLException {
        Connection connection = getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        if (parameterSetter != null) {
            parameterSetter.setValue(preparedStatement);
        }

        ResultSet resultSet = preparedStatement.executeQuery();

        int count = 0;
        while (resultSet.next()) {
            count = resultSet.getInt(1);
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();

        return count;
    }

    protected final boolean execute(String query, ParameterSetter parameterSetter) throws SQLException {
        Connection connection = getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        if (parameterSetter != null)
            parameterSetter.setValue(preparedStatement);

        int rowCount = preparedStatement.executeUpdate();

        preparedStatement.getConnection().close();
        preparedStatement.close();
        connection.close();

        return rowCount >= 1;
    }


}
