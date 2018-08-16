package cn.leta.upms.controller;

import cn.leta.common.Errors;
import cn.leta.common.exception.BizException;
import cn.leta.common.utils.BeanUtil;
import cn.leta.common.vo.LetaPage;
import cn.leta.common.web.BaseController;
import cn.leta.upms.model.entity.SysModule;
import cn.leta.upms.model.dto.SysModuleDTO;
import cn.leta.upms.service.SysModuleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
/**
 * Template Created By Xie Gengcai
 * Auto Generate By Code-Generator
 */
@Api(tags = "系统模块操作接口")
@RestController
public class SysModuleController extends BaseController<SysModule, SysModuleService>{
	
	@ApiOperation("按ID查询系统模块")
    @GetMapping("/sysModule/{id}")
    public SysModuleDTO get(@PathVariable @ApiParam(value = "ID", required = true) int id) {
        SysModule sysModule = baseService.selectById(id);
        if (sysModule == null) {
            throw new BizException(Errors.GLOBAL.DATA_NOT_EXISTED.getCode(), String.format("不存在id=%s的%s记录", id, SysModule.class));
        }
        return BeanUtil.copyProperties(sysModule, new SysModuleDTO());
    }

    @ApiOperation("增加系统模块")
    @PostMapping("/sysModule")
    public void add(@RequestBody @Valid SysModuleDTO sysModule) {
        baseService.insert(BeanUtil.copyProperties(sysModule, new SysModule()));
    }

    @ApiOperation("按ID删除系统模块")
    @DeleteMapping("/sysModule/{id}")
    public void delete(@PathVariable @ApiParam(value = "ID", required = true) int id) {
        baseService.deleteById(id);
    }

    @ApiOperation("更新系统模块")
    @PutMapping("/sysModule")
    public void edit(@RequestBody  @Valid SysModuleDTO sysModule) {
        baseService.updateById(BeanUtil.copyProperties(sysModule, new SysModule()));
    }
    // 方法参数LetaPage<SysModule>中的泛型仅做toPage()转换用
    @ApiOperation("分页查询")
    @GetMapping("/sysModule/query")
    public LetaPage<SysModuleDTO> query(@RequestBody LetaPage<SysModule> letaPage) {
        return BeanUtil.toLetaPage(baseService.selectPage(letaPage.toPage()), SysModuleDTO.class);
    }
}
