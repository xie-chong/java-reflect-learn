package cn.xiechong.demo2;

public class FactoryDemo {
    public static void main(String[] args) throws Exception {
        IMessage message = Factory.getInstance("cn.xiechong.demo2.NetMessage", IMessage.class);
        message.send();
        IService instance = Factory.getInstance("cn.xiechong.demo2.HouseService", IService.class);
        instance.service();
    }
}

class Factory {
    // 没有产生实例化对象的意义，所以构造方法私有化
    private Factory() {
    }

    /**
     * 获取接口实例化对象
     *
     * @param className 接口的子类
     * @param tClass    描述的是一个接口的类型
     * @return 如果子类存在则返回指定接口实例化对象
     * @throws Exception
     */
    public static <T> T getInstance(String className, Class<T> tClass) throws Exception {
        return tClass.cast(Class.forName(className).getDeclaredConstructor().newInstance());
    }
}


interface IMessage {
    void send();// 消息发送
}

/**
 * 网络消息实现类
 */
class NetMessage implements IMessage {
    @Override
    public void send() {
        System.out.println("发送网络消息");
    }
}

/**
 * 服务实现类
 */
interface IService {
    void service();
}

class HouseService implements IService {
    @Override
    public void service() {
        System.out.println("为您的住房提供服务.");
    }
}
