package cn.leta.common.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by <a href="mailto:xiegengcai@foxmail.com">Xie Gengcai</a> on 2018/8/14.
 *
 * @author Xie Gengcai
 */
@Setter
@Getter
@AllArgsConstructor
public class StaffVO implements Serializable {

    private String groupCode;
    private Long id;
    private String name;
    private String phone;
}
