package aStar;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class SampleMaker {
    private int[][] array1; //  inputArray가 될 array
    private int[][] array2; //  goalArray가 될 array
    private final int exBehaveNum = 4;   //  중복제거를 위해 이전 mix의 방향을 갖고 있을 변수

    private final Set<int[][]> arraySet = new HashSet<>();  //  중복제거 이전 기록을 갖고있는 Set

    public static void main(String[] args) {
        int arraySize = 4;          //  축의 크기가 4인 배열
        int mixCount = 10000;     //  섞을 횟수
        SampleMaker sampleMaker = new SampleMaker(arraySize, mixCount);
        sampleMaker.getSample();    //  섞고
        sampleMaker.printArrays();  //  복사 붙여넣기 할 수 있게 출력
    }

    //  복사 붙여넣기 할 수 있게 출력
    private void printArrays() {
        printArray_CtrlCV(array1, "inputArray");
        printArray_CtrlCV(array2, "goalArray");
    }

    //  필드에 2개의 Array를 생성하고 섞어주는 메소드
    public SampleMaker(int arraySize, int mixCount) {
        array1 = new int[arraySize][arraySize];

        int arrayValue = 0;
        for (int i = 0; i < arraySize; i++) {
            for (int j = 0; j < arraySize; j++) {
                array1[j][i] = arrayValue++;
            }
        }

        array1 = mixArray(array1);
        array2 = cloneArray(array1);

        for (int i = 0; i < mixCount; i++) {
            array2 = mixArray(array2);
        }
    }

    //  Array DeepClone Method
    private int[][] cloneArray(int[][] array) {
        int[][] newArray = new int[array.length][array.length];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                newArray[i][j] = array[i][j];
            }
        }
        return newArray;
    }

    //  array를 한번 랜덤한 방향으로 mix하는 함수
    private int[][] mixArray(int[][] array) {
        int direction = new Random().nextInt(4);
        int x = -1, y = -1;
        for (int k = 0; k < array.length; k++) {
            for (int j = 0; j < array[k].length; j++) {
                if (array[k][j] == 0) {
                    x = j;
                    y = k;
                }
            }
        }

        //  오른쪽
        if (direction == 0 && exBehaveNum != 1) {
            if (x + 1 < array.length) {
                int value = array[y][x + 1];
                array[y][x] = value;
                array[y][x + 1] = 0;
                if (!arraySet.contains(array)) {
                    arraySet.add(array);
                    return array;
                }
            }
        }

        //  왼쪽
        if (direction == 1 && exBehaveNum != 0) {
            if (x - 1 >= 0) {
                int value = array[y][x - 1];
                array[y][x] = value;
                array[y][x - 1] = 0;
                if (!arraySet.contains(array)) {
                    arraySet.add(array);
                    return array;
                }
            }
        }

        //  아래
        if (direction == 2 && exBehaveNum != 3) {
            if (y + 1 < array.length) {
                int value = array[y + 1][x];
                array[y][x] = value;
                array[y + 1][x] = 0;
                if (!arraySet.contains(array)) {
                    arraySet.add(array);
                    return array;
                }
            }
        }

        //  위
        if (direction == 3 && exBehaveNum != 2) {
            if (y - 1 >= 0) {
                int value = array[y - 1][x];
                array[y][x] = value;
                array[y - 1][x] = 0;
                if (!arraySet.contains(array)) {
                    arraySet.add(array);
                    return array;
                }
            }
        }

        return array;
    }

    //  array 2개를 쌍으로 return해 주는 함수
    public Array_Array getSample() {
        return new Array_Array(array1, array2);
    }

    //  int[][] arrayName = {{1,2,3},{4,5,6}..........식으로 출력하는 함수!
    private void printArray_CtrlCV(int[][] array, String arrayName) {
        System.out.printf("int[][] %s = {{ ", arrayName);
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (j == 0) {
                    System.out.print(array[i][j]);
                } else {
                    System.out.printf(", %d", array[i][j]);
                }
            }
            if (i != array.length - 1) {
                System.out.printf("}, {");
            }
        }
        System.out.println("}};");
    }


}

//  Array 2개를 쌍으로 전달 할 목적으로 만든 DataClass

class Array_Array {
    private int[][] array1;
    private int[][] array2;

    public Array_Array(int[][] array1, int[][] array2) {
        this.array1 = array1;
        this.array2 = array2;
    }

    public int[][] getArray1() {
        return array1;
    }

    public int[][] getArray2() {
        return array2;
    }

}
