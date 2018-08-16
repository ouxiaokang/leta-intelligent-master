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
@ApiModel(value = "SysRoleResource", description = "角色权限")
@Data
public class SysRoleResourceDTO implements Serializable{

	//columns START
	@ApiModelProperty(value="角色ID")
	private Integer roleId;
	@ApiModelProperty(value="资源ID")
	private Integer resId;
	//columns END
}

