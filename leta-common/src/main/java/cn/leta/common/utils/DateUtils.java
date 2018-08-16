package cn.leta.common.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Java8 版本日期帮助类
 * Created by xiegengcai on 2018/7/4.
 *
 * @author Xie Gengcai
 */
public final class DateUtils {

    private DateUtils(){}

    /**
     * 获取当前日期
     * @return
     */
    public static Date now() {
        return Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获取当前时间
     * @return
     */
    public static Date nowWithTime() {
        return Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
    }
}
