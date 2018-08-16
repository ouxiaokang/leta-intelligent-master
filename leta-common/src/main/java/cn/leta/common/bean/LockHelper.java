package cn.leta.common.bean;

import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 加锁帮助类
 * Created by <a href="mailto:xiegengcai@foxmail.com">xiegengcai</a> on 2018/7/19.
 *
 * @author Xie Gengcai
 */
@Slf4j
@Component
public class LockHelper {
    public static final String LOCKED = "L";
    public static final int DEFAULT_LOCK_SEC=100;//缺省锁定时间100s
    public static final int DEFAULT_LOCK_RETRY=3;//缺省重试次数
    public static final int DEFAULT_INTERVAL = 1000; //缺省重试间隔毫秒

    /**
     * 尝试锁定并执行任务
     * @param executor 任务
     * @param <T>      返回的结果类型
     * @return 返回结果
     */
    public <T> T tryLockExecute(LockExecutor<T> executor) {

        int count = 0;
        while (true) {

            if (executor.redisTemplate().opsForValue().setIfAbsent(executor.lockKey(), LOCKED)) {
                if (executor.lockSec() > 0) {
                    executor.redisTemplate().expire(executor.lockKey(), executor.lockSec(), TimeUnit.SECONDS);
                }
                try {
                    return executor.execute();
                } finally {
                    executor.redisTemplate().delete(executor.lockKey());
                }
            }
            count++;
            if (count >= executor.retyCount()) {
                throw new LockingFailureException(String.format("get lock of key[%s] failure", executor.lockKey()));
            }
            if (executor.interval()>0){
                try {
                    Thread.sleep(executor.interval());
                } catch (InterruptedException e) {
                    log.warn(Throwables.getStackTraceAsString(e));
                }
            }
        }

    }


    /**
     * 防止重复提交的任务
     * @param <T>
     */
    public interface LockExecutor<T> {
        RedisTemplate redisTemplate();
        /**
         * 锁定成功后要执行的业务逻辑
         * @return  返回结果
         */
        T execute();

        /**
         * 锁定Key,唯一的,保证自己业务Key与其他业务区分, 格式如 order:update:123 表示要更新订单123
         * @return 锁定Key,唯一的
         */
        String lockKey();

        /**
         * 锁定时间 秒
         */
        default int lockSec() {
            return DEFAULT_LOCK_SEC;
        }

        /**
         * 重试次数
         * @return
         */
        default int retyCount() {
            return DEFAULT_LOCK_RETRY;
        }

        /**
         * 重试间隔时间
         * @return
         */
        default int interval(){
            return DEFAULT_INTERVAL;
        }
    }

    public final class LockingFailureException extends RuntimeException{

        public LockingFailureException(String message) {
            super(message);
        }

    }
}
