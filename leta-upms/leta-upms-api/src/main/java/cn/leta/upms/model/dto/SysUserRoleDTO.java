package cn.leta.upms.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
/**
 * Template Created By Xie Gengcai
 * Auto Generate By Code-Generator
 */
@ApiModel(value = "SysUserRole", description = "用户角色")
@Data
public class SysUserRoleDTO implements Serializable{

	//columns START
	@ApiModelProperty(value="ID")
	private Integer id;
	@ApiModelProperty(value="角色ID")
	private Integer roleId;
	@ApiModelProperty(value="用户ID")
	private Integer userId;
	//columns END
}

