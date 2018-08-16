package cn.leta.upms.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
/**
 * Template Created By Xie Gengcai
 * Auto Generate By Code-Generator
 */
@ApiModel(value = "SysUser", description = "用户")
@Data
public class SysUserDTO implements Serializable{

	//columns START
	@ApiModelProperty(value="ID")
	private Integer id;
	@ApiModelProperty(value="名称")
	private String name;
	@JsonIgnore
	@ApiModelProperty(value="密码")
	private String password;
	@JsonIgnore
	@ApiModelProperty(value="盐")
	private String salt;
	@ApiModelProperty(value="姓名")
	private String realName;
	@ApiModelProperty(value="头像")
	private String avatar;
	@ApiModelProperty(value="电话号码")
	private String phone;
	@ApiModelProperty(value="邮箱")
	private String email;
	@ApiModelProperty(value="性别：0 女 1 男 2 保密")
	private Byte gender;
	@ApiModelProperty(value="状态：0正常 1锁定")
	private Integer status;
	@ApiModelProperty(value="创建时间")
	private Date createTime;
	//columns END
}

