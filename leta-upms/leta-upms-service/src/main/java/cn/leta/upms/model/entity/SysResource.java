package cn.leta.upms.model.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
/**
 * Template Created By Xie Gengcai
 * Auto Generate By Code-Generator
 */
@Data
@TableName("sys_resource")
public class SysResource implements Serializable{

	//columns START
	/**
	 * 资源ID
	 */
	@TableId(type = IdType.AUTO)
	private Integer id;
	/**
	 * 上级ID
	 */
    @TableField("parent_id")
	private Integer parentId;
	/**
	 * 系统ID
	 */
    @TableField("system_id")
	private Integer systemId;
	/**
	 * 名称
	 */
    @TableField("name")
	private String name;
	/**
	 * 图标
	 */
    @TableField("icon")
	private String icon;
	/**
	 * 资源值
	 */
    @TableField("val")
	private String val;
	/**
	 * 路径
	 */
    @TableField("uri")
	private String uri;
	/**
	 * 描述
	 */
    @TableField("description")
	private String description;
	/**
	 * 排序
	 */
    @TableField("order")
	private Integer order;
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
	 * 资源类型
	 */
    @TableField("res_type")
	private Integer resType;
	//columns END
}

