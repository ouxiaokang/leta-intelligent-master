package cn.leta.common.bean.interceptor;

import lombok.Data;

import java.util.HashMap;
import java.util.List;

/**
 * 数据权限、参考guns实现
 * Created by xiegengcai on 2018/6/29.
 *
 * @author Xie Gengcai
 */
@Data
public class DataScope extends HashMap {
    /**
     * 限制范围的字段名称
     */
    private String scopeName = "dept_id";

    /**
     * 具体的数据范围
     */
    private List<Integer> deptIds;

    /**
     * 是否只查询本部门
     */
    private Boolean isOnly = false;
}