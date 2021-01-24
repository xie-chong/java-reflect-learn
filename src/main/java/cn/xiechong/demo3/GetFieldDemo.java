package cn.xiechong.demo3;

import java.lang.reflect.Field;

public class GetFieldDemo {
    public static void main(String[] args) throws Exception {
        Class<?> aClass = Class.forName("cn.xiechong.vo.Person");// 获取指定类的class对象
        Field[] fields = aClass.getFields();
        for (Field field : fields) {
            System.out.println(field);
        }
        System.out.println("======================");
        fields = aClass.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field);
        }
    }
}
