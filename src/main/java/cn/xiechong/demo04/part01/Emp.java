package cn.xiechong.demo04.part01;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Emp {
    private String ename;
    private String job;

    public void setEname(String ename) {
        this.ename = ename;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getEname() {
        return ename;
    }

    public String getJob() {
        return job;
    }
}

class ClassInstanceFactory {
    private ClassInstanceFactory() {
    }        //构造方法私有化

    /**
     * 实例化对象创建的方法,该对象可以根据传入的字符串的结构"内容|属性:内容|"进行处理
     *
     * @param tClass 要进行反射实例化的Class类对象,有Class就可以反射实例化对象
     * @param value  要设置给对象的属性内容
     * @return 一个已经配置完内容的简单java类对象
     */
    public static <T> T create(Class<T> tClass, String value) {
        //如果想采用反射进行简单Java类对象的属性设置的时候,类中必须要有无参构造
        try {
            Object o = tClass.getDeclaredConstructor().newInstance();
            BeanUtils.setValue(o, value);        //通过反射设置属性
            return tClass.cast(o);    //获取对象
        } catch (Exception e) {
            e.printStackTrace();        //此时如果出现异常,将异常抛出也没有多大作用
            return null;
        }


    }

}

class BeanUtils {    //进行Bean处理的工具类
    private BeanUtils() {
    }

    /**
     * 实现指定对象的属性设置
     *
     * @param obj   要进行反射操作的实例化对象
     * @param value 包含有指定内容的字符串
     */
    public static void setValue(Object obj, String value) {
        String results[] = value.split("\\|");//按照竖线进行每一组属性的拆分
        for (int i = 0; i < results.length; i++) {  //循环设置属性内容
            String[] attval = results[i].split(":");   //获取属性名称及内容
            try {
                Field field = obj.getClass().getDeclaredField(attval[0]);
                Method setMethod = obj.getClass()
                        .getDeclaredMethod("set" + StringUtils.initcap(attval[0]), field.getType());
                setMethod.invoke(obj, attval[1]);        //使用setter方法进行内容的赋值
            } catch (Exception e) { //捕获异常,否则的话一个属性不存在将会导致所有的属性都无法正常赋值
            }

        }
    }
}

class StringUtils {
    private StringUtils() {
    }

    public static String initcap(String str) {
        if (str == null || str.equals("")) {
            return str;
        }
        if (str.length() == 1) {
            return str.toUpperCase();
        } else {
            return str.substring(0, 1).toUpperCase() + str.substring(1);
        }
    }

}
