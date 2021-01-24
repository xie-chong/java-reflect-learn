package cn.xiechong.vo;

import cn.xiechong.demo3.AbstractBase;
import cn.xiechong.service.IChannelService;
import cn.xiechong.service.IMessageService;

public class Person extends AbstractBase implements IMessageService, IChannelService {
    public static final String NAME = "小张三";
    private String name;
    private int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean connect() {
        return true;
    }

    @Override
    public void send() {
        if (this.connect()) {
            System.out.println("消息发送");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
