public class Tetst1 {
    public static void main(String[] args) {
        GenericTest<Integer> integerGenericTest = new GenericTest<>();
        //泛型方法测试
        String now = integerGenericTest.getNow(1, "2");
        System.out.println(now);
        //返回具体泛型参数的测试
        GenericTest<Integer> instance = integerGenericTest.getInstance();
        Integer value = instance.getValue();
        //返回泛型参数未确认的测试.
        GenericTest instance1 = integerGenericTest.getInstance1();
        Object value1 = instance1.getValue();
        //判断两个类是否是同一个，结果是true
        System.out.println(instance.getClass().equals(instance1.getClass()));
    }
}
