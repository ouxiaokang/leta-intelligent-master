package cn.leta.upms.service.impl;

import cn.leta.common.exception.BizException;
import cn.leta.common.utils.DateUtils;
import cn.leta.upms.model.entity.SysDepartment;
import cn.leta.upms.mapper.SysDepartmentMapper;
import cn.leta.upms.service.SysDepartmentService;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Template Created By Xie Gengcai
 * Auto Generate By Code-Generator
 */
@Service
public class SysDepartmentServiceImpl extends ServiceImpl<SysDepartmentMapper, SysDepartment> implements SysDepartmentService {
    @Transactional(rollbackFor = {BizException.class, Exception.class})
    @Override
    public boolean insert(SysDepartment entity) {
        Date now = DateUtils.nowWithTime();
        // 设置创建时间
        entity.setCreateTime(now);
        // 设置更新时间
        entity.setUpdateTime(now);
        return super.insert(entity);
    }

    @Transactional(rollbackFor = {BizException.class, Exception.class})
    @Override
    public boolean insertOrUpdate(SysDepartment entity) {
        if (entity.getId() == null) {
            return this.insert(entity);
        }
        return this.updateById(entity);
    }

    @Transactional(rollbackFor = {BizException.class, Exception.class})
    @Override
    public boolean updateById(SysDepartment entity) {
        entity.setUpdateTime(DateUtils.nowWithTime());
        return super.updateById(entity);
    }
}
