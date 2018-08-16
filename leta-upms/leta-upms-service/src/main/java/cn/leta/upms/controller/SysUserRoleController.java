package cn.leta.upms.controller;

import cn.leta.common.Errors;
import cn.leta.common.exception.BizException;
import cn.leta.common.utils.BeanUtil;
import cn.leta.common.vo.LetaPage;
import cn.leta.common.web.BaseController;
import cn.leta.upms.model.entity.SysUserRole;
import cn.leta.upms.model.dto.SysUserRoleDTO;
import cn.leta.upms.service.SysUserRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
/**
 * Template Created By Xie Gengcai
 * Auto Generate By Code-Generator
 */
@Api(tags = "用户角色操作接口")
@RestController
public class SysUserRoleController extends BaseController<SysUserRole, SysUserRoleService>{
	
	@ApiOperation("按ID查询用户角色")
    @GetMapping("/sysUserRole/{id}")
    public SysUserRoleDTO get(@PathVariable @ApiParam(value = "ID", required = true) int id) {
        SysUserRole sysUserRole = baseService.selectById(id);
        if (sysUserRole == null) {
            throw new BizException(Errors.GLOBAL.DATA_NOT_EXISTED.getCode(), String.format("不存在id=%s的%s记录", id, SysUserRole.class));
        }
        return BeanUtil.copyProperties(sysUserRole, new SysUserRoleDTO());
    }

    @ApiOperation("增加用户角色")
    @PostMapping("/sysUserRole")
    public void add(@RequestBody @Valid SysUserRoleDTO sysUserRole) {
        baseService.insert(BeanUtil.copyProperties(sysUserRole, new SysUserRole()));
    }

    @ApiOperation("按ID删除用户角色")
    @DeleteMapping("/sysUserRole/{id}")
    public void delete(@PathVariable @ApiParam(value = "ID", required = true) int id) {
        baseService.deleteById(id);
    }

    @ApiOperation("更新用户角色")
    @PutMapping("/sysUserRole")
    public void edit(@RequestBody  @Valid SysUserRoleDTO sysUserRole) {
        baseService.updateById(BeanUtil.copyProperties(sysUserRole, new SysUserRole()));
    }
    // 方法参数LetaPage<SysUserRole>中的泛型仅做toPage()转换用
    @ApiOperation("分页查询")
    @GetMapping("/sysUserRole/query")
    public LetaPage<SysUserRoleDTO> query(@RequestBody LetaPage<SysUserRole> letaPage) {
        return BeanUtil.toLetaPage(baseService.selectPage(letaPage.toPage()), SysUserRoleDTO.class);
    }
}
