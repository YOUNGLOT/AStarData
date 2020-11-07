package aStar;

import dao.AStarDao;
import entity.AStar;
import lombok.SneakyThrows;
import parallel.Parallel;

import java.sql.SQLException;
import java.util.Random;

import static java.lang.System.gc;

public class LoadTool {

    /*
    private static final Parallel parallel = new Parallel(new Runnable() {
        @SneakyThrows
        @Override
        public void run() {
            Random r = new Random(System.currentTimeMillis());

            long s = r.nextInt(3000); // 3초내로 끝내자.
            try {
                Thread.sleep(s); // 쓰레드를 잠시 멈춤
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            solve();
        }
    });
    */

    public static void main(String[] args) throws SQLException {
        //parallel.parallel();
        int oufOfMemortCount = 0;
        //  i = mixCount => 10 ~ 80 까지 10씩 증가
        for (int i = 10; i <= 80; i += 10) {
            //  단순히 k 번을 반복
            for (int k = 0; k < 1000; k++) {
                //  heuristicCount 를 1 ~ 50 까지 대입함
                for (int j = 1; j <= 50; j++) {
                    solve(i, j, oufOfMemortCount);
                }
            }
        }
    }

    private static void solve(int mixCount, int heuristicWeight, int oufOfMemortCount) throws SQLException {

        int input = 4;
        try {
            //  inputArray, goalArray 를 받아 온 후
            Array_Array array_array = new SampleMaker(input, mixCount).getSample();
            //  A* 알고리즘 class 객체를 만든다
            AStarAlgorithm aStarAlgorithm = new AStarAlgorithm(array_array.getArray1(), array_array.getArray2(), heuristicWeight);
            //  input 받은 두 Array로 부터 결과를 만든 후 DB에 넣을 결과(operationCount, resultCount ) 를 AStar(entity) 에 넣는다
            AStar aStar = aStarAlgorithm.getOperationCount_ResultCount();
            //  aStar 에 mixCount 값을 넣는다
            aStar.setMIX_COUNT(mixCount);
            aStar.setHEURISTIC_WEIGHT(heuristicWeight);
            //  적재를 시작한다
            AStarDao.getInstance().insert(aStar);

        } catch (OutOfMemoryError outOfMemoryError) {
            System.out.println(oufOfMemortCount++);
            gc();
        }
    }
}