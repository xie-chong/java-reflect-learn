package cn.xiechong.demo3;

public class GettingClassHierarchyDemo {
    public static void main(String[] args) {
        Class<ClassHierarchyInfo> gettingClassHierarchyClass = ClassHierarchyInfo.class;// 获取指定类的class对象
        Package aPackage = gettingClassHierarchyClass.getPackage();// 获取指定类的包定义
        System.out.println("获取包名称: " + aPackage.getName());
        Class<? super ClassHierarchyInfo> superclass = gettingClassHierarchyClass.getSuperclass();// 获取父类
        System.out.println("ClassHierarchyInfo获取父类: " + superclass.getName());
        System.out.println("superclass获取父类: " + superclass.getSuperclass().getName());
        Class<?>[] interfaces = gettingClassHierarchyClass.getInterfaces();// 获取父接口
        for (int i = 0; i < interfaces.length; i++) {
            System.out.println("获取父接口" + (i + 1) + ": " + interfaces[i].getName());
        }
    }

}
