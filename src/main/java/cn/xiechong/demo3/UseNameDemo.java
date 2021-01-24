package cn.xiechong.demo3;

import java.lang.reflect.Field;

public class UseNameDemo {
    public static void main(String[] args) throws Exception {
        Class<?> aClass = Class.forName("cn.xiechong.vo.Person");// 获取指定类的class对象
        Object obj = aClass.getConstructor().newInstance();
        Field name = aClass.getDeclaredField("name");
        System.out.println(name.getType().getName());// 获取完整类名称
        System.out.println(name.getType().getSimpleName());
    }
}
