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
@ApiModel(value = "SysDepartment", description = "部门")
@Data
public class SysDepartmentDTO implements Serializable{

	//columns START
	@ApiModelProperty(value="部门ID")
	private Integer id;
	@ApiModelProperty(value="上级部门ID")
	private Integer parentId;
	@ApiModelProperty(value="名称")
	private String name;
	@ApiModelProperty(value="描述")
	private String description;
	@ApiModelProperty(value="状态：0无效 1有效")
	private Integer status;
	@ApiModelProperty(value="创建时间")
	private Date createTime;
	@ApiModelProperty(value="部门层级")
	private Integer level;
	@ApiModelProperty(value="排序")
	private Integer sortOrder;
	@ApiModelProperty(value="更新时间")
	private Date updateTime;
	//columns END
}

