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

        entity.setASTAR_ID(resultSet.getInt(1));
        entity.setMIX_COUNT(resultSet.getInt(2));
        entity.setOPERATION_COUNT(resultSet.getInt(3));
        entity.setRESULT_COUNT(resultSet.getInt(4));
        entity.setHEURISTIC_WEIGHT(resultSet.getInt(5));

        return entity;
    }

    @Override
    public boolean update(final AStar entity) throws SQLException {
        String query = "update ASTAR set MIX_COUNT = ?, OPERATION_COUNT = ?, RESULT_COUNT = ?, HEURISTIC_WEIGHT = ? where ASTAR_ID = ?";

        return execute(query, new ParameterSetter() {
            @Override
            public void setValue(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setInt(1, entity.getMIX_COUNT());
                preparedStatement.setInt(2, entity.getOPERATION_COUNT());
                preparedStatement.setInt(3, entity.getRESULT_COUNT());
                preparedStatement.setInt(4, entity.getHEURISTIC_WEIGHT());
                preparedStatement.setInt(5, entity.getASTAR_ID());
            }
        });
    }

    @Override
    public boolean insert(final AStar entity) throws SQLException {
        String query = "insert into ASTAR values (?, ?, ?, ?)";

        return execute(query, new ParameterSetter() {
            @Override
            public void setValue(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setInt(1, entity.getMIX_COUNT());
                preparedStatement.setInt(2, entity.getOPERATION_COUNT());
                preparedStatement.setInt(3, entity.getRESULT_COUNT());
                preparedStatement.setInt(4, entity.getHEURISTIC_WEIGHT());
            }
        });
    }

    @Override
    public int getCount() throws SQLException {
        String query = "select count(*) from ASTAR";
        return getInt(query, null);
    }

    @Override
    public ArrayList<AStar> getAll() throws SQLException {
        String query = "select * from ASTAR";
        return getMany(query, null);
    }
}
