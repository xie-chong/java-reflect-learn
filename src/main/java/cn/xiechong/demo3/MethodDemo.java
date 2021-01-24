package cn.xiechong.demo3;

import cn.xiechong.vo.Person;

import java.lang.reflect.Method;

/**
 * 反射调用普通方法
 */
public class MethodDemo {
    public static void main(String[] args) {
        Class<Person> personClass = Person.class;// 获取指定类的class对象
        {// 获取全部方法
            Method[] methods = personClass.getMethods();
            for (Method method : methods) {
                System.out.println(method);
            }
        }

        System.out.println("=========================");

        {
            // 获取全部方法
            Method[] methods = personClass.getDeclaredMethods();
            for (Method method : methods) {
                System.out.println(method);
            }
        }
    }
}
