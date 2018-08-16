package cn.leta.common.handler;

/**
 * 事件驱动器接口
 * Created by <a href="mailto:xiegengcai@foxmail.com">xiegengcai</a> on 2018/7/23.
 *
 * @author Xie Gengcai
 */
public interface EventHanlder<T> {

    /**
     * 事件名称
     * @return
     */
    String eventName();

    /**
     * 是否支持
     * @param event
     * @return
     */
    boolean supports(EventDTO<T> event);

    /**
     * 处理事件
     * @param event
     */
    void handle(EventDTO<T> event);


}
