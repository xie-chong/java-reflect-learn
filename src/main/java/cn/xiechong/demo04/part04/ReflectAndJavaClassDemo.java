package cn.xiechong.demo04.part04;

/**
 * 六.级联属性设置
 */
public class ReflectAndJavaClassDemo {
    public static void main(String[] args) {
        // 在Emp类中,存在两个String类型的成员变量ename,job,以及其相关的getter,setter
        String value =
                "ename:Mike|job:code java|empo:1258|salary:750|hireDate:1911-12-12|dept.dname:财务部" +
                        "|dept.company.name:一个写java的公司";
        Emp emp = ClassInstanceFactory.create(Emp.class, value);// 产生实例化对象
        System.out.println(emp);
    }
}
