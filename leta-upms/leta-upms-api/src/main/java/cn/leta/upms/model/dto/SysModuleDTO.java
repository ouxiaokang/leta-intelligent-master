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
@ApiModel(value = "SysModule", description = "系统模块")
@Data
public class SysModuleDTO implements Serializable{

	//columns START
	@ApiModelProperty(value="ID")
	private Integer id;
	@ApiModelProperty(value="名称")
	private String name;
	@ApiModelProperty(value="图标")
	private String icon;
	@ApiModelProperty(value="根路径")
	private String basePath;
	@ApiModelProperty(value="描述")
	private String description;
	@ApiModelProperty(value="排序")
	private Integer order;
	@ApiModelProperty(value="状态：0无效 1有效")
	private Integer status;
	@ApiModelProperty(value="创建时间")
	private Date createTime;
	//columns END
}

