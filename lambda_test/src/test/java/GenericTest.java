//泛型基本测试
public class GenericTest<T> {
    private T t;

    //泛型方法，
    public <E, F> F getNow(E e,F f) {
        return f;
    }

    //确定返回类泛型参数的类型，T此时为创建对象传入的具体类型
    public GenericTest<T> getInstance() {
        return this;
    }

    //无法确定返回类泛型参数的类型，T此时默认为Object
    public GenericTest getInstance1() {
        return this;
    }

    public T getValue() {
        return t;
    }
}
