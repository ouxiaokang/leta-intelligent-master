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
@TableName("sys_module")
public class SysModule implements Serializable{

	//columns START
	/**
	 * ID
	 */
	@TableId(type = IdType.AUTO)
	private Integer id;
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
	 * 根路径
	 */
    @TableField("base_path")
	private String basePath;
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
	//columns END
}

