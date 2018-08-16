package cn.leta.common.handler;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by <a href="mailto:xiegengcai@foxmail.com">xiegengcai</a> on 2018/7/23.
 *
 * @author Xie Gengcai
 */
public class EventHanlderManager<T> {
    private Map<String, EventHanlder<T>> hanlderMap;

    public EventHanlderManager() {
        this.hanlderMap = new HashMap<>();
    }

    public EventHanlderManager(EventHanlder<T> ... hanlders) {
        this();
        for (EventHanlder hanlder : hanlders) {
            this.hanlderMap.put(hanlder.eventName(), hanlder);
        }
    }

    /**
     * 增加事件处理器
     * @param hanlder
     */
    public void addEventHanlder(EventHanlder<T> hanlder) {
        this.hanlderMap.put(hanlder.eventName(), hanlder);
    }

    /**
     * 触发单个事件
     * @param events
     */
    public void fireEvent(EventDTO<T> ... events) {
        for (EventDTO<T> event : events) {
            EventHanlder<T> hanlder = this.hanlderMap.get(event.getEventName());
            if (hanlder.supports(event)) {
                hanlder.handle(event);
            }
        }
    }
}
