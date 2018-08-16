package cn.leta.upms.controller;

import cn.leta.common.Errors;
import cn.leta.common.exception.BizException;
import cn.leta.common.utils.BeanUtil;
import cn.leta.common.vo.LetaPage;
import cn.leta.common.web.BaseController;
import cn.leta.upms.model.entity.SysResource;
import cn.leta.upms.model.dto.SysResourceDTO;
import cn.leta.upms.service.SysResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
/**
 * Template Created By Xie Gengcai
 * Auto Generate By Code-Generator
 */
@Api(tags = "系统资源操作接口")
@RestController
public class SysResourceController extends BaseController<SysResource, SysResourceService>{
	
	@ApiOperation("按ID查询系统资源")
    @GetMapping("/sysResource/{id}")
    public SysResourceDTO get(@PathVariable @ApiParam(value = "ID", required = true) int id) {
        SysResource sysResource = baseService.selectById(id);
        if (sysResource == null) {
        throw new BizException(Errors.GLOBAL.DATA_NOT_EXISTED.getCode(), String.format("不存在id=%s的%s记录", id, SysResource.class));
        }
        return BeanUtil.copyProperties(sysResource, new SysResourceDTO());
    }

    @ApiOperation("增加系统资源")
    @PostMapping("/sysResource")
    public void add(@RequestBody @Valid SysResourceDTO sysResource) {
        baseService.insert(BeanUtil.copyProperties(sysResource, new SysResource()));
    }

    @ApiOperation("按ID删除系统资源")
    @DeleteMapping("/sysResource/{id}")
    public void delete(@PathVariable @ApiParam(value = "ID", required = true) int id) {
        baseService.deleteById(id);
    }

    @ApiOperation("更新系统资源")
    @PutMapping("/sysResource")
    public void edit(@RequestBody  @Valid SysResourceDTO sysResource) {
        baseService.updateById(BeanUtil.copyProperties(sysResource, new SysResource()));
    }
    // 方法参数LetaPage<SysResource>中的泛型仅做toPage()转换用
    @ApiOperation("分页查询")
    @GetMapping("/sysResource/query")
    public LetaPage<SysResourceDTO> query(@RequestBody LetaPage<SysResource> letaPage) {
        return BeanUtil.toLetaPage(baseService.selectPage(letaPage.toPage()), SysResourceDTO.class);
    }
}
