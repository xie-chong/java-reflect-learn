package cn.xiechong.demo04.part02;

/**
 * 设置多种数据类型
 */
public class ReflectAndJavaClassDemo {
    public static void main(String[] args) {
        // 在Emp类中,存在两个String类型的成员变量ename,job,以及其相关的getter,setter
        String value = "ename:Mike|job:code java|empo:1258|salary:750|hireDate:1911-12-12";
        Emp emp = ClassInstanceFactory.create(Emp.class, value);// 产生实例化对象
        System.out.println(emp);
    }
}
