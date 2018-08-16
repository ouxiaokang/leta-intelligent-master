package cn.leta.upms.controller;

import cn.leta.common.Errors;
import cn.leta.common.exception.BizException;
import cn.leta.common.utils.BeanUtil;
import cn.leta.common.vo.LetaPage;
import cn.leta.common.web.BaseController;
import cn.leta.upms.model.entity.SysRoleResource;
import cn.leta.upms.model.dto.SysRoleResourceDTO;
import cn.leta.upms.service.SysRoleResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
/**
 * Template Created By Xie Gengcai
 * Auto Generate By Code-Generator
 */
@Api(tags = "角色权限操作接口")
@RestController
public class SysRoleResourceController extends BaseController<SysRoleResource, SysRoleResourceService>{
	
	@ApiOperation("按ID查询角色权限")
    @GetMapping("/sysRoleResource/{id}")
    public SysRoleResourceDTO get(@PathVariable @ApiParam(value = "ID", required = true) int id) {
        SysRoleResource  sysRoleResource = baseService.selectById(id);
        if (sysRoleResource == null) {
            throw new BizException(Errors.GLOBAL.DATA_NOT_EXISTED.getCode(), String.format("不存在id=%s的%s记录", id, SysRoleResource.class));
        }
        return BeanUtil.copyProperties(sysRoleResource, new SysRoleResourceDTO());
    }

    @ApiOperation("增加角色权限")
    @PostMapping("/sysRoleResource")
    public void add(@RequestBody @Valid SysRoleResourceDTO sysRoleResource) {
        baseService.insert(BeanUtil.copyProperties(sysRoleResource, new SysRoleResource()));
    }

    @ApiOperation("按ID删除角色权限")
    @DeleteMapping("/sysRoleResource/{id}")
    public void delete(@PathVariable @ApiParam(value = "ID", required = true) int id) {
        baseService.deleteById(id);
    }

    @ApiOperation("更新角色权限")
    @PutMapping("/sysRoleResource")
    public void edit(@RequestBody  @Valid SysRoleResourceDTO sysRoleResource) {
        baseService.updateById(BeanUtil.copyProperties(sysRoleResource, new SysRoleResource()));
    }
    // 方法参数LetaPage<SysRoleResource>中的泛型仅做toPage()转换用
    @ApiOperation("分页查询")
    @GetMapping("/sysRoleResource/query")
    public LetaPage<SysRoleResourceDTO> query(@RequestBody LetaPage<SysRoleResource> letaPage) {
        return BeanUtil.toLetaPage(baseService.selectPage(letaPage.toPage()), SysRoleResourceDTO.class);
    }
}
