package aStar;

import java.util.Arrays;
import java.util.Objects;

public class Data extends Object{
    private String key;
    private int[][] array;

    public Data(String key, int[][] array) {
        this.key = key;
        this.array = array;
    }

    public String getKey() {
        return key;
    }

    public int[][] getArray() {
        return array;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Data data = (Data) o;
        return Objects.equals(key, data.key) &&
                Arrays.equals(array, data.array);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(key);
        result = 31 * result + Arrays.hashCode(array);
        return result;
    }
}
