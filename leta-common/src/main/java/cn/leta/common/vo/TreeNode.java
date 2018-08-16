package cn.leta.common.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiegengcai on 2018/7/3.
 *
 * @author Xie Gengcai
 */
@Data
public class TreeNode <ID extends Serializable> {
    protected ID id;
    protected ID parentId;
    protected List<TreeNode<ID>> children = new ArrayList<>();

    public void add(TreeNode<ID> node) {
        this.children.add(node);
    }
}
