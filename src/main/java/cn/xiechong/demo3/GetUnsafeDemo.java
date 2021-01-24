package cn.xiechong.demo3;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class GetUnsafeDemo {
    public static void main(String[] args) throws Exception {
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);  //解除封装处理
        Unsafe unsafe = (Unsafe) theUnsafe.get(null);// static属性不需要传递实例化对象
        Singleton singleton = (Singleton) unsafe.allocateInstance(Singleton.class);
        singleton.print();
    }
}
