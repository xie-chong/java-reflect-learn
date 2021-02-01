package cn.xiechong.demo04.part04;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Emp {
    private long empo;
    private String ename;
    private String job;
    private double salary;
    private Date hireDate;
    private Dept dept;

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

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

    public long getEmpo() {
        return empo;
    }

    public void setEmpo(long empo) {
        this.empo = empo;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "empo=" + empo +
                ", ename='" + ename + '\'' +
                ", job='" + job + '\'' +
                ", salary=" + salary +
                ", hireDate=" + hireDate +
                ", dept=" + dept +
                '}';
    }
}

class Company {
    private String name;
    private Date createDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}

class Dept {
    private String dname;
    private String loc;
    private Company company;

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "dname='" + dname + '\'' +
                ", loc='" + loc + '\'' +
                ", company=" + company +
                '}';
    }
}


class ClassInstanceFactory {
    private ClassInstanceFactory() {
    }// 构造方法私有化

    /**
     * 实例化对象创建的方法,该对象可以根据传入的字符串的结构"内容|属性:内容|"进行处理
     *
     * @param tClass 要进行反射实例化的Class类对象,有Class就可以反射实例化对象
     * @param value  要设置给对象的属性内容
     * @return 一个已经配置完内容的简单java类对象
     */
    public static <T> T create(Class<T> tClass, String value) {
        // 如果想采用反射进行简单Java类对象的属性设置的时候,类中必须要有无参构造
        try {
            Object o = tClass.getDeclaredConstructor().newInstance();
            BeanUtils.setValue(o, value);// 通过反射设置属性
            return tClass.cast(o);// 获取对象
        } catch (Exception e) {
            e.printStackTrace();// 此时如果出现异常,将异常抛出也没有多大作用
            return null;
        }
    }
}

class BeanUtils {// 进行Bean处理的工具类

    private BeanUtils() {
    }

    /**
     * 实现指定对象的属性设置
     *
     * @param obj   要进行反射操作的实例化对象
     * @param value 包含有指定内容的字符串
     */
    public static void setValue(Object obj, String value) {
        String results[] = value.split("\\|");// 按照竖线进行每一组属性的拆分
        for (int i = 0; i < results.length; i++) {// 循环设置属性内容
            String attval[] = results[i].split(":");// 获取属性名称及内容
            try {
                Object currentObject = obj;
                if (attval[0].contains(".")) {// 这是多级配置
                    String temp[] = attval[0].split("\\.");
                    // 最后一位肯定是指定类中的属性名称,因此不在实例化处理的范畴之内
                    for (int j = 0; j < temp.length - 1; j++) {// 实例化
                        // 调用相应的getter方法,如果getter方法返回了空表示该对象为实例化
                        Method getMethod = currentObject.getClass().getDeclaredMethod(
                                "get" + StringUtils.initcap(temp[j]));
                        if (getMethod.invoke(currentObject) == null) {// 该对象现在并没有被实例化
                            Field field = currentObject.getClass().getDeclaredField(temp[j]);
                            Method method = currentObject.getClass().getDeclaredMethod("set" + StringUtils.initcap(temp[j]), field.getType());
                            Object newObject = field.getType().getDeclaredConstructor().newInstance();
                            method.invoke(currentObject, newObject);
                            currentObject = newObject;
                        } else {
                            currentObject = getMethod.invoke(currentObject);
                        }
                    }
                    // 进行属性内容的设置
                    Field field = currentObject.getClass().getDeclaredField(temp[temp.length - 1]);
                    Method setMethod = currentObject.getClass().getDeclaredMethod("set" + StringUtils.initcap(temp[temp.length - 1]), field.getType());
                    Object val = BeanUtils.getAttributeValue(field.getType().getName(), attval[1]);
                    setMethod.invoke(currentObject, val);// 使用setter方法进行内容的赋值
                } else {
                    Field field = obj.getClass().getDeclaredField(attval[0]);
                    Method setMethod = obj.getClass()
                            .getDeclaredMethod("set" + StringUtils.initcap(attval[0]), field.getType());
                    Object val = BeanUtils.getAttributeValue(field.getType().getName(), attval[1]);
                    setMethod.invoke(obj, val);// 使用setter方法进行内容的赋值
                }
            } catch (Exception e) {// 捕获异常,否则的话一个属性不存在将会导致所有的属性都无法正常赋值
                e.printStackTrace();
            }
        }
    }

    /**
     * 实现属性类型转化处理
     *
     * @param type  属性类型,通过Field获取
     * @param value 属性的内容,传入的都是字符串,需要将其转化为指定的类型
     * @return 转化后的数据
     */
    private static Object getAttributeValue(String type, String value) {
        if ("long".equals(type) || "java.lang.Long".equals(type)) {// 长整型
            return Long.parseLong(value);
        } else if ("int".equals(type) || "java.lang.Integer".equals(type)) {
            return Integer.valueOf(value);
        } else if ("double".equals(type) || "java.lang.Double".equals(type)) {
            return Double.valueOf(value);
        } else if ("java.util.Date".equals(type)) {
            SimpleDateFormat dateFormat = null;
            if (value.matches("\\d{4}-\\d{2}-\\d{2}")) {// 日期类型
                dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            } else if (value.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}")) {// 日期时间
                dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            } else {
                return new Date();// 当前日期
            }
            try {
                return dateFormat.parse(value);
            } catch (ParseException e) {
                return new Date();
            }
        } else {
            return value;
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