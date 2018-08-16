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
@ApiModel(value = "SysResource", description = "系统资源")
@Data
public class SysResourceDTO implements Serializable{

	//columns START
	@ApiModelProperty(value="资源ID")
	private Integer id;
	@ApiModelProperty(value="上级ID")
	private Integer parentId;
	@ApiModelProperty(value="系统ID")
	private Integer systemId;
	@ApiModelProperty(value="名称")
	private String name;
	@ApiModelProperty(value="图标")
	private String icon;
	@ApiModelProperty(value="资源值")
	private String val;
	@ApiModelProperty(value="路径")
	private String uri;
	@ApiModelProperty(value="描述")
	private String description;
	@ApiModelProperty(value="排序")
	private Integer order;
	@ApiModelProperty(value="状态：0无效 1有效")
	private Integer status;
	@ApiModelProperty(value="创建时间")
	private Date createTime;
	@ApiModelProperty(value="资源类型")
	private Integer resType;
	//columns END
}

