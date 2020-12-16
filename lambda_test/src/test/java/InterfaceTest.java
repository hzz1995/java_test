import java.util.stream.BaseStream;
import java.util.stream.Stream;

public interface InterfaceTest<T> {
    public InterfaceTest getInstance1();
    public InterfaceTest<T> getInstance();
}
