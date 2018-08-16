package cn.leta.upms.model.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;
/**
 * Template Created By Xie Gengcai
 * Auto Generate By Code-Generator
 */
@Data
@TableName("sys_user_role")
public class SysUserRole implements Serializable{

	//columns START
	/**
	 * ID
	 */
	@TableId(type = IdType.AUTO)
	private Integer id;
	/**
	 * 角色ID
	 */
    @TableField("role_id")
	private Integer roleId;
	/**
	 * 用户ID
	 */
    @TableField("user_id")
	private Integer userId;
	//columns END
}

