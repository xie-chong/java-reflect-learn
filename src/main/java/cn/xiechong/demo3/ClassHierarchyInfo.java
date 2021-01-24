package cn.xiechong.demo3;

import cn.xiechong.service.IChannelService;
import cn.xiechong.service.IMessageService;

/**
 * 类结构信息
 */
public class ClassHierarchyInfo extends AbstractBase implements IMessageService, IChannelService {

    private String name;
    private String msg;

    public ClassHierarchyInfo() {
    }

    public ClassHierarchyInfo(String name, String msg) {
        this.name = name;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ClassHierarchyInfo{" +
                "name='" + name + '\'' +
                ", msg='" + msg + '\'' +
                '}';
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
}
