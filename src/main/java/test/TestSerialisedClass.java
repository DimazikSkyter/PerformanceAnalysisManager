package test;

import java.io.Serializable;
import java.util.Objects;

public class TestSerialisedClass implements Serializable {
    private static final long serialVersionUID = 1L;
    private int x;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestSerialisedClass that = (TestSerialisedClass) o;
        return x == that.x;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x);
    }

    private TestSerialisedClass(int x) {
        this.x = x;
    }

    public static TestSerialisedClass newInstance(int x) {
        return new TestSerialisedClass(x);
    }


}
