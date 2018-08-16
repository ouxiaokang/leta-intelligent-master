package cn.leta.common.vo;

import cn.leta.common.constant.CommonConstant;
import cn.leta.common.utils.BeanUtil;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分页查询请求及结果封装
 * Created by xiegengcai on 2018/7/5.
 *
 * @author Xie Gengcai
 */
@Slf4j
@Data
@ApiModel(value = "LetaPage", description = "分页查询请求及结果封装对象")
public class LetaPage<T> implements Serializable {
    /**
     * 页码
     */
    @ApiModelProperty(value = "页码")
    private int pageNum = 1;
    /**
     * 每页大小
     */
    @ApiModelProperty("每页大小")
    private int pageSize = CommonConstant.PAGE_SIZE;
    @ApiModelProperty(value = "总页数", readOnly = true)
    private long totalPage;
    @ApiModelProperty(value = "总记录数", readOnly = true)
    private long totalRecords;
    /**
     * 结果集
     */
    @ApiModelProperty(value = "结果集", readOnly = true)
    private List<T> records;
    @ApiModelProperty(value = "查询条件")
    private Map<String, Object> condition;
    @ApiModelProperty("排序")
    private Sort sort;

    public LetaPage() {
    }

    public LetaPage(int pageNum, int pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public LetaPage(int pageNum, int pageSize, long totalPage, long totalRecords, List<T> records, Map<String, Object> condition, Sort sort) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.totalPage = totalPage;
        this.totalRecords = totalRecords;
        this.records = records;
        this.condition = condition;
        this.sort = sort;
    }

    /**
     * 转为mybatisplus page
     * @return
     */
    public Page<T> toPage() {
        Page<T> page = new Page<>(this.pageNum, this.pageSize);
        // 重新构建条件
        if (this.condition == null) {
            this.condition = new HashMap<>();
        }
        if (!this.condition.isEmpty()) {
            // 驼峰转下划线
            Map<String, Object> camelToUnderlineMap = new HashMap<>(this.condition.size(), 1.0f);
            this.condition.entrySet().forEach(entry -> camelToUnderlineMap.put(StringUtils.camelToUnderline(entry.getKey()), entry.getValue()));
            this.condition = camelToUnderlineMap;
        }
        page.setCondition(this.condition);
        List<String> ascs = Lists.newArrayList();
        List<String> descs = Lists.newArrayList();
        if (this.sort != null) {
            sort.forEach(order -> {
                if (order.getDirection() == Sort.Direction.ASC) {
                    ascs.add(order.getProperty());
                } else {
                    descs.add(order.getProperty());
                }
            });
            if (ascs.size() != 0) {
                page.setAsc(true);
                page.setAscs(ascs);
            }
            if (descs.size() != 0) {
                page.setDescs(ascs);
            }
        }
        return page;
    }

}
