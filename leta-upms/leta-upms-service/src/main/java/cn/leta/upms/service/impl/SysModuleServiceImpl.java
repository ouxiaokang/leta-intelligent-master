package cn.leta.upms.service.impl;

import cn.leta.common.exception.BizException;
import cn.leta.common.utils.DateUtils;
import cn.leta.upms.model.entity.SysModule;
import cn.leta.upms.mapper.SysModuleMapper;
import cn.leta.upms.service.SysModuleService;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Template Created By Xie Gengcai
 * Auto Generate By Code-Generator
 */
@Service
public class SysModuleServiceImpl extends ServiceImpl<SysModuleMapper, SysModule> implements SysModuleService {
    @Transactional(rollbackFor = {BizException.class, Exception.class})
    @Override
    public boolean insert(SysModule entity) {
        // 设置创建时间
        entity.setCreateTime(DateUtils.nowWithTime());
        return super.insert(entity);
    }

    @Transactional(rollbackFor = {BizException.class, Exception.class})
    @Override
    public boolean insertOrUpdate(SysModule entity) {
        if (entity.getId() == null) {
            return this.insert(entity);
        }
        return this.updateById(entity);
    }
}
