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
@TableName("sys_user_resource")
public class SysUserResource implements Serializable{

	//columns START
	@TableId(type = IdType.AUTO)
	private Integer id;
	/**
	 * 资源ID
	 */
    @TableField("res_id")
	private Integer resId;
	/**
	 * 用户ID
	 */
    @TableField("user_id")
	private Integer userId;
	/**
	 * 类型：-1 减 +1加
	 */
    @TableField("type")
	private Byte type;
	//columns END
}

