package Test;

import dao.AStarDao;
import entity.AStar;

import java.sql.SQLException;
//  그냥 insert 1,1,1,1 해보는 테스트에용
public class DataBaseTest {
    public static void main(String[] args) throws SQLException {
        DataBaseTest dataBaseTest = new DataBaseTest();
        dataBaseTest.insertTest();
    }

    private void insertTest() throws SQLException {
        AStar entity = new AStar();

        entity.setMixTime(1);
        entity.setOperationCount(1);
        entity.setResultCount(1);

        AStarDao.getInstance().insert(entity);
    }
}
