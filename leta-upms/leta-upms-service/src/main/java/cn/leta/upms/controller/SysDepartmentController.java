package cn.leta.upms.controller;

import cn.leta.common.Errors;
import cn.leta.common.exception.BizException;
import cn.leta.common.utils.BeanUtil;
import cn.leta.common.vo.LetaPage;
import cn.leta.common.web.BaseController;
import cn.leta.upms.model.entity.SysDepartment;
import cn.leta.upms.model.dto.SysDepartmentDTO;
import cn.leta.upms.service.SysDepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
/**
 * Template Created By Xie Gengcai
 * Auto Generate By Code-Generator
 */
@Api(tags = "部门操作接口")
@RestController
public class SysDepartmentController extends BaseController<SysDepartment, SysDepartmentService>{
	
	@ApiOperation("按ID查询部门")
    @GetMapping("/sysDepartment/{id}")
    public SysDepartmentDTO get(@PathVariable @ApiParam(value = "ID", required = true) int id) {
        SysDepartment sysDepartment = baseService.selectById(id);
        if (sysDepartment == null) {
            throw new BizException(Errors.GLOBAL.DATA_NOT_EXISTED.getCode(), String.format("不存在id=%s的%s记录", id, SysDepartment.class));
        }
        return BeanUtil.copyProperties(sysDepartment, new SysDepartmentDTO());
    }

    @ApiOperation("增加部门")
    @PostMapping("/sysDepartment")
    public void add(@RequestBody @Valid SysDepartmentDTO sysDepartment) {
        baseService.insert(BeanUtil.copyProperties(sysDepartment, new SysDepartment()));
    }

    @ApiOperation("按ID删除部门")
    @DeleteMapping("/sysDepartment/{id}")
    public void delete(@PathVariable @ApiParam(value = "ID", required = true) int id) {
        baseService.deleteById(id);
    }

    @ApiOperation("更新部门")
    @PutMapping("/sysDepartment")
    public void edit(@RequestBody  @Valid SysDepartmentDTO sysDepartment) {
        baseService.updateById(BeanUtil.copyProperties(sysDepartment, new SysDepartment()));
    }
    // 方法参数LetaPage<SysDepartment>中的泛型仅做toPage()转换用
    @ApiOperation("分页查询")
    @GetMapping("/sysDepartment/query")
    public LetaPage<SysDepartmentDTO> query(@RequestBody LetaPage<SysDepartment> letaPage) {
        return BeanUtil.toLetaPage(baseService.selectPage(letaPage.toPage()), SysDepartmentDTO.class);
    }
}
