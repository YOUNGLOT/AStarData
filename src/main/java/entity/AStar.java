package entity;

import lombok.Data;

@Data
public class AStar {
    private int aStarId;
    private int mixTime;
    private int operationCount;
    private int resultCount;

    public AStar(){}
    //  필요해서 만든 일부분의 값만 넣은 생성자
    public AStar(int operationCount, int resultCount) {
        this.operationCount = operationCount;
        this.resultCount = resultCount;
    }
}
