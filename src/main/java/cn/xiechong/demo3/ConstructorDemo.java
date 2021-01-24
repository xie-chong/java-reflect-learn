package cn.xiechong.demo3;

import java.lang.reflect.Constructor;

public class ConstructorDemo {
    public static void main(String[] args) {
        Class<ClassHierarchyInfo> classHierarchyInfoClass = ClassHierarchyInfo.class;// 获取指定类的class对象
        Constructor<?>[] constructors = classHierarchyInfoClass.getDeclaredConstructors();// 获取全部构造
        for (Constructor<?> constructor : constructors) {
            System.out.println(constructor);
        }
        System.out.println("========================");

        constructors = classHierarchyInfoClass.getConstructors();
        for (Constructor<?> constructor : constructors) {
            System.out.println(constructor);
        }
    }
}
