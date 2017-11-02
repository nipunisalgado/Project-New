package test.samples;

public class GetDataType {
    public static void main(String[] args) {
        String str = "test";
        String type = str.getClass().getName();

        System.out.println(type);
        System.out.println(str.getClass().getSimpleName());

        Object o = 1;
        System.out.println(o.getClass().getSimpleName());
    }
}
