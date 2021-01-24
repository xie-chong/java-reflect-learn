package cn.xiechong.demo2;



public class JavaAPIDemo2 {
    public static void main(String[] args) throws Exception {
        Class<?> aClass = Class.forName("cn.xiechong.vo.Person");
        Object o = aClass.getDeclaredConstructor().newInstance(); // JDK 1.9 after 实例化对象
        System.out.println(o);
    }
}
