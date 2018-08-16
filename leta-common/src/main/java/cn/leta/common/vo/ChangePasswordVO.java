package cn.leta.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 修改密码
 * Created by <a href="mailto:xiegengcai@foxmail.com">xiegengcai</a> on 2018/7/17.
 *
 * @author Xie Gengcai
 */
@Data
@ApiModel(value = "ChangePassword", description = "更新密码")
public class ChangePasswordVO<ID extends Serializable> implements Serializable {
    @ApiModelProperty(required = true, value = "唯一标识")
    @NotNull
    private ID id;

    @ApiModelProperty(required = true, value = "原始密码")
    @NotBlank
    @Size(min = 6, max = 15)
    private String oldPwd;

    @ApiModelProperty(required = true, value = "新密码")
    @NotBlank
    @Size(min = 6, max = 15)
    private String password;
}
