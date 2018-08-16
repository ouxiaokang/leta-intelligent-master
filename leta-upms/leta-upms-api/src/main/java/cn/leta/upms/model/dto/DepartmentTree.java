package cn.leta.upms.model.dto;

import cn.leta.common.vo.TreeNode;
import lombok.Data;

/**
 * Created by xiegengcai on 2018/7/3.
 *
 * @author Xie Gengcai
 */
@Data
public class DepartmentTree extends TreeNode<Integer> {
    private String name;
//    private Integer level;
}
