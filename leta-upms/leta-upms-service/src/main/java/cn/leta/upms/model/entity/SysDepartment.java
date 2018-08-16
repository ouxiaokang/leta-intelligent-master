package cn.leta.upms.model.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;
/**
 * Template Created By Xie Gengcai
 * Auto Generate By Code-Generator
 */
@Data
@TableName("sys_department")
public class SysDepartment implements Serializable{

	//columns START
	/**
	 * 部门ID
	 */
	@TableId(type = IdType.AUTO)
	private Integer id;
	/**
	 * 上级部门ID
	 */
    @TableField("parent_id")
	private Integer parentId;
	/**
	 * 名称
	 */
    @TableField("name")
	private String name;
	/**
	 * 描述
	 */
    @TableField("description")
	private String description;
	/**
	 * 状态：0无效 1有效
	 */
    @TableField("status")
	private Integer status;
	/**
	 * 创建时间
	 */
    @TableField("create_time")
	private Date createTime;
	/**
	 * 部门层级
	 */
    @TableField("level")
	private Integer level;
	/**
	 * 排序
	 */
    @TableField("sort_order")
	private Integer sortOrder;
	/**
	 * 更新时间
	 */
    @TableField("update_time")
	private Date updateTime;
	//columns END
}

