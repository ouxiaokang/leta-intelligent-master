package cn.leta.common.aliyun;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by <a href="mailto:xiegengcai@foxmail.com">Xie Gengcai</a> on 2018/7/30.
 *
 * @author Xie Gengcai
 */
@Setter
@Getter
public abstract class AbstractAliyunConfig {
    protected String accessKeyId;
    protected String accessKeySecret;
}
