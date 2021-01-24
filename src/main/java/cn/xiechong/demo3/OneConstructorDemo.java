package cn.xiechong.demo3;

import java.lang.reflect.Constructor;

/**
 * 调用有参构造方法
 */
public class OneConstructorDemo {
    public static void main(String[] args) throws Exception {
        Class<ClassHierarchyInfo> classHierarchyInfoClass = ClassHierarchyInfo.class;// 获取指定类的class对象
        Constructor<ClassHierarchyInfo> constructor = classHierarchyInfoClass.getConstructor(String.class, String.class);
        ClassHierarchyInfo classHierarchyInfo = constructor.newInstance("张三", "优秀");
        System.out.println(classHierarchyInfo);
    }

}
