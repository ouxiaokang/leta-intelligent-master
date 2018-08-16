package cn.leta.upms.controller;

import cn.leta.common.Errors;
import cn.leta.common.exception.BizException;
import cn.leta.common.utils.BeanUtil;
import cn.leta.common.vo.LetaPage;
import cn.leta.common.web.BaseController;
import cn.leta.upms.model.entity.SysRole;
import cn.leta.upms.model.dto.SysRoleDTO;
import cn.leta.upms.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
/**
 * Template Created By Xie Gengcai
 * Auto Generate By Code-Generator
 */
@Api(tags = "角色操作接口")
@RestController
public class SysRoleController extends BaseController<SysRole, SysRoleService>{
	
	@ApiOperation("按ID查询角色")
    @GetMapping("/sysRole/{id}")
    public SysRoleDTO get(@PathVariable @ApiParam(value = "ID", required = true) int id) {
        SysRole sysRole = baseService.selectById(id);
        if (sysRole == null) {
            throw new BizException(Errors.GLOBAL.DATA_NOT_EXISTED.getCode(), String.format("不存在id=%s的%s记录", id, SysRole.class));
        }
        return BeanUtil.copyProperties(sysRole, new SysRoleDTO());
    }

    @ApiOperation("增加角色")
    @PostMapping("/sysRole")
    public void add(@RequestBody @Valid SysRoleDTO sysRole) {
        baseService.insert(BeanUtil.copyProperties(sysRole, new SysRole()));
    }

    @ApiOperation("按ID删除角色")
    @DeleteMapping("/sysRole/{id}")
    public void delete(@PathVariable @ApiParam(value = "ID", required = true) int id) {
        baseService.deleteById(id);
    }

    @ApiOperation("更新角色")
    @PutMapping("/sysRole")
    public void edit(@RequestBody  @Valid SysRoleDTO sysRole) {
        baseService.updateById(BeanUtil.copyProperties(sysRole, new SysRole()));
    }
    // 方法参数LetaPage<SysRole>中的泛型仅做toPage()转换用
    @ApiOperation("分页查询")
    @GetMapping("/sysRole/query")
    public LetaPage<SysRoleDTO> query(@RequestBody LetaPage<SysRole> letaPage) {
        return BeanUtil.toLetaPage(baseService.selectPage(letaPage.toPage()), SysRoleDTO.class);
    }
}
