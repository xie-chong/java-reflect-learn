package cn.xiechong.demo04.part01;

/**
 * 三.单级属性配置
 */
public class ReflectAndJavaClassDemo {
    public static void main(String[] args) {
        // 在Emp类中,存在两个String类型的成员变量ename,job,以及其相关的getter,setter
        String value = "ename:Mike|job:code java";
        Emp emp = ClassInstanceFactory.create(Emp.class, value);// 产生实例化对象
        System.out.println("姓名: " + emp.getEname() + " 职位: " + emp.getJob());
    }
}
