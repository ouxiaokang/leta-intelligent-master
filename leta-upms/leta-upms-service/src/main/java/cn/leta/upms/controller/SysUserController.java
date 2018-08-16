package cn.leta.upms.controller;

import cn.leta.common.Errors;
import cn.leta.common.exception.BizException;
import cn.leta.common.utils.BeanUtil;
import cn.leta.common.vo.LetaPage;
import cn.leta.common.web.BaseController;
import cn.leta.upms.model.entity.SysUser;
import cn.leta.upms.model.dto.SysUserDTO;
import cn.leta.upms.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
/**
 * Template Created By Xie Gengcai
 * Auto Generate By Code-Generator
 */
@Api(tags = "用户操作接口")
@RestController
public class SysUserController extends BaseController<SysUser, SysUserService>{
	
	@ApiOperation("按ID查询用户")
    @GetMapping("/sysUser/{id}")
    public SysUserDTO get(@PathVariable @ApiParam(value = "ID", required = true) int id) {
        SysUser sysUser = baseService.selectById(id);
        if (sysUser == null) {
            throw new BizException(Errors.GLOBAL.DATA_NOT_EXISTED.getCode(), String.format("不存在id=%s的%s记录", id, SysUser.class.getSimpleName()));
        }
        return BeanUtil.copyProperties(sysUser, new SysUserDTO());
    }

    @ApiOperation("增加用户")
    @PostMapping("/sysUser")
    public void add(@RequestBody @Valid SysUserDTO sysUser) {
        baseService.insert(BeanUtil.copyProperties(sysUser, new SysUser()));
    }

    @ApiOperation("按ID删除用户")
    @DeleteMapping("/sysUser/{id}")
    public void delete(@PathVariable @ApiParam(value = "ID", required = true) int id) {
        baseService.deleteById(id);
    }

    @ApiOperation("更新用户")
    @PutMapping("/sysUser")
    public void edit(@RequestBody  @Valid SysUserDTO sysUser) {
        baseService.updateById(BeanUtil.copyProperties(sysUser, new SysUser()));
    }
    // 方法参数LetaPage<SysUser>中的泛型仅做toPage()转换用
    @ApiOperation("分页查询")
    @GetMapping("/sysUser/query")
    public LetaPage<SysUserDTO> query(@RequestBody LetaPage<SysUser> letaPage) {
        return BeanUtil.toLetaPage(baseService.selectPage(letaPage.toPage()), SysUserDTO.class);
    }
}
