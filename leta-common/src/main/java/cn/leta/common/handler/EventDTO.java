package cn.leta.common.handler;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 事件定义类
 * Created by <a href="mailto:xiegengcai@foxmail.com">xiegengcai</a> on 2018/7/23.
 *
 * @author Xie Gengcai
 */
@Data
@AllArgsConstructor
public class EventDTO<T> {

    /**
     * 事件名称
     */
    private String eventName;
    /**
     * 触发事件点前的值
     */
    private T preVal;

    /**
     * 触发事件点后的值
     */
    private T postVal;
}
