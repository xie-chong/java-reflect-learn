package cn.xiechong.demo1;

public class JavaAPIDemo {
    public static void main(String[] args) throws ClassNotFoundException {
        // 此时Person类位于cn.xiechong.vo包中; 该方式不需要导包
        Class<?> aClass = Class.forName("反射.认识反射机制.Person");
        System.out.println(aClass);
    }
}
