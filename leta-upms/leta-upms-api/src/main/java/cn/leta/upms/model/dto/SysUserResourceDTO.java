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
@ApiModel(value = "SysUserResource", description = "用户权限")
@Data
public class SysUserResourceDTO implements Serializable{

	//columns START
	@ApiModelProperty(value="")
	private Integer id;
	@ApiModelProperty(value="资源ID")
	private Integer resId;
	@ApiModelProperty(value="用户ID")
	private Integer userId;
	@ApiModelProperty(value="类型：-1 减 +1加")
	private Byte type;
	//columns END
}

