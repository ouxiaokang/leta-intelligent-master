package cn.leta.upms.controller;

import cn.leta.common.Errors;
import cn.leta.common.exception.BizException;
import cn.leta.common.utils.BeanUtil;
import cn.leta.common.vo.LetaPage;
import cn.leta.common.web.BaseController;
import cn.leta.upms.model.entity.SysUserResource;
import cn.leta.upms.model.dto.SysUserResourceDTO;
import cn.leta.upms.service.SysUserResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
/**
 * Template Created By Xie Gengcai
 * Auto Generate By Code-Generator
 */
@Api(tags = "用户权限操作接口")
@RestController
public class SysUserResourceController extends BaseController<SysUserResource, SysUserResourceService>{
	
	@ApiOperation("按ID查询用户权限")
    @GetMapping("/sysUserResource/{id}")
    public SysUserResourceDTO get(@PathVariable @ApiParam(value = "ID", required = true) int id) {
        SysUserResource sysUserResource = baseService.selectById(id);
        if (sysUserResource == null) {
        throw new BizException(Errors.GLOBAL.DATA_NOT_EXISTED.getCode(), String.format("不存在id=%s的%s记录", id, SysUserResource.class));
        }
        return BeanUtil.copyProperties(sysUserResource, new SysUserResourceDTO());
    }

    @ApiOperation("增加用户权限")
    @PostMapping("/sysUserResource")
    public void add(@RequestBody @Valid SysUserResourceDTO sysUserResource) {
        baseService.insert(BeanUtil.copyProperties(sysUserResource, new SysUserResource()));
    }

    @ApiOperation("按ID删除用户权限")
    @DeleteMapping("/sysUserResource/{id}")
    public void delete(@PathVariable @ApiParam(value = "ID", required = true) int id) {
        baseService.deleteById(id);
    }

    @ApiOperation("更新用户权限")
    @PutMapping("/sysUserResource")
    public void edit(@RequestBody  @Valid SysUserResourceDTO sysUserResource) {
        baseService.updateById(BeanUtil.copyProperties(sysUserResource, new SysUserResource()));
    }
    // 方法参数LetaPage<SysUserResource>中的泛型仅做toPage()转换用
    @ApiOperation("分页查询")
    @GetMapping("/sysUserResource/query")
    public LetaPage<SysUserResourceDTO> query(@RequestBody LetaPage<SysUserResource> letaPage) {
        return BeanUtil.toLetaPage(baseService.selectPage(letaPage.toPage()), SysUserResourceDTO.class);
    }
}
