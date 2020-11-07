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

        entity.setMIX_COUNT(1);
        entity.setOPERATION_COUNT(1);
        entity.setRESULT_COUNT(1);
        entity.setHEURISTIC_WEIGHT(1);

        AStarDao.getInstance().insert(entity);
    }
}
