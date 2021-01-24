package cn.xiechong.demo3;

public class Singleton {
    private static final Singleton INSTANCE = new Singleton();

    private Singleton() {
        System.out.println("构造方法");
    }

    public void print() {
        System.out.println("实例化对象输出内容");
    }
}
