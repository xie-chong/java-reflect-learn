package cn.xiechong.demo3;

import java.lang.reflect.Method;

public class GetterAndSetterDemo {
    public static void main(String[] args) throws Exception {
        // 在不导入开发包的情况下实现属性的配置
        Class<?> aClass = Class.forName("cn.xiechong.vo.Person");// 获取指定类的class对象
        String attribute = "name";// 要操作的类属性
        String value = "张三";// 要设置的属性内容
        // 1.任何情况写想要调用类中的属性或者调用类中的方法都必须保证存在有实例化对象
        Object obj = aClass.getDeclaredConstructor().newInstance();// 调用无参构造实例化
        // 2.如果想要进行方法的调用,一定要获取方法的名称
        String setMethodName = "setName";
        Method method = aClass.getDeclaredMethod(setMethodName, String.class);
        method.invoke(obj, value);// 等价于 person.setName("小强");
        System.out.println("toString: " + obj);
        String getMethodName = "getName";
        Method getName = aClass.getDeclaredMethod(getMethodName);
        System.out.println("getName: " + getName.invoke(obj));// 等价于 person.getName();
    }
}
