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
@TableName("sys_user")
public class SysUser implements Serializable{

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
	 * 密码
	 */
    @TableField("password")
	private String password;
	/**
	 * 盐
	 */
    @TableField("salt")
	private String salt;
	/**
	 * 姓名
	 */
    @TableField("real_name")
	private String realName;
	/**
	 * 头像
	 */
    @TableField("avatar")
	private String avatar;
	/**
	 * 电话号码
	 */
    @TableField("phone")
	private String phone;
	/**
	 * 邮箱
	 */
    @TableField("email")
	private String email;
	/**
	 * 性别：0 女 1 男 2 保密
	 */
    @TableField("gender")
	private Byte gender;
	/**
	 * 状态：0正常 1锁定
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

