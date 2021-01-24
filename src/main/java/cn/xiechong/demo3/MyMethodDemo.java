package cn.xiechong.demo3;

import cn.xiechong.vo.Person;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 这种代码你只需要清楚可以根据反射获取方法的结构即可，不需要做过多的深入理解。
 */
public class MyMethodDemo {
    public static void main(String[] args) {
        Class<Person> personClass = Person.class;// 获取指定类的class对象
        Method[] methods = personClass.getMethods();
        for (Method method : methods) {
            String modifier = Modifier.toString(method.getModifiers());// 获取访问修饰符
            String returnType = method.getReturnType().getName();// 获取方法的返回类型
            Class<?>[] parameterTypes = method.getParameterTypes();// 获取参数类型
            StringBuffer buffer = new StringBuffer();
            buffer.append("(");
            for (int i = 0; i < parameterTypes.length; i++) {
                buffer.append(parameterTypes[i].getName());
                buffer.append(" arg").append(i);
                if (i < parameterTypes.length - 1) {
                    buffer.append(",");
                }
            }
            buffer.append(")");
            String parameterType = buffer.toString();
            Class<?>[] exceptionTypes = method.getExceptionTypes();// 获取异常类型
            buffer = new StringBuffer();
            if (exceptionTypes.length > 0) {
                buffer.append(" throws ");
                for (int i = 0; i < exceptionTypes.length; i++) {
                    buffer.append(exceptionTypes[i].getName());
                    buffer.append(" e").append(i);
                    if (i < exceptionTypes.length - 1) {
                        buffer.append(",");
                    }
                }
            }
            String exceptionType = buffer.toString();
            System.out.println(modifier + " " + returnType + " " + method.getName() + parameterType + "" + exceptionType);
        }
    }
}
