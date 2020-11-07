package entity;

import lombok.Data;

@Data
public class AStar {
    private int ASTAR_ID;
    private int MIX_COUNT;
    private int OPERATION_COUNT;
    private int RESULT_COUNT;
    private int HEURISTIC_WEIGHT;

    public AStar(){}
    //  필요해서 만든 일부분의 값만 넣은 생성자
    public AStar(int operation_Count, int result_Count) {
        this.OPERATION_COUNT = operation_Count;
        this.RESULT_COUNT = result_Count;
    }
}
