package dao;

import dao.base.EntityDao;
import dao.base.ParameterSetter;
import entity.AStar;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AStarDao extends EntityDao<AStar> {

    //region singleton
    private AStarDao() {
    }

    private static AStarDao _instance;

    public static AStarDao getInstance() {
        if (_instance == null)
            _instance = new AStarDao();

        return _instance;
    }
    //endregion

    @Override
    protected AStar readEntity(ResultSet resultSet) throws SQLException {
        AStar entity = new AStar();

        entity.setAStarId(resultSet.getInt(1));
        entity.setMixTime(resultSet.getInt(2));
        entity.setOperationCount(resultSet.getInt(3));
        entity.setResultCount(resultSet.getInt(4));

        return entity;
    }

    @Override
    public boolean update(final AStar entity) throws SQLException {
        String query = "update ASTAR set MixTime = ?, OperationCount = ?, ResultCount = ? where AStarId = ?";

        return execute(query, new ParameterSetter() {
            @Override
            public void setValue(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setInt(1, entity.getMixTime());
                preparedStatement.setInt(2, entity.getOperationCount());
                preparedStatement.setInt(3, entity.getResultCount());
                preparedStatement.setInt(4, entity.getAStarId());
            }
        });
    }

    @Override
    public boolean insert(final AStar entity) throws SQLException {
        String query = "insert into AStar values (?, ?, ?)";

        return execute(query, new ParameterSetter() {
            @Override
            public void setValue(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setInt(1, entity.getMixTime());
                preparedStatement.setInt(2, entity.getOperationCount());
                preparedStatement.setInt(3, entity.getResultCount());
            }
        });
    }

    @Override
    public int getCount() throws SQLException {
        String query = "select count(*) from AStar";
        return getInt(query, null);
    }

    @Override
    public ArrayList<AStar> getAll() throws SQLException {
        String query = "select * from AStar";
        return getMany(query, null);
    }
}
