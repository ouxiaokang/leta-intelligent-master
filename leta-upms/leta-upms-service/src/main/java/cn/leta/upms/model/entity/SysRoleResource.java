package cn.leta.upms.model.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;
import java.util.Date;
/**
 * Template Created By Xie Gengcai
 * Auto Generate By Code-Generator
 */
@Data
@TableName("sys_role_resource")
public class SysRoleResource implements Serializable{

	//columns START
	/**
	 * 角色ID
	 */
    @TableField("role_id")
	private Integer roleId;
	/**
	 * 资源ID
	 */
    @TableField("res_id")
	private Integer resId;
	//columns END
}

