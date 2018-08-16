package cn.leta.upms.service.impl;

import cn.leta.common.exception.BizException;
import cn.leta.common.utils.DateUtils;
import cn.leta.upms.model.entity.SysRole;
import cn.leta.upms.mapper.SysRoleMapper;
import cn.leta.upms.service.SysRoleService;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Template Created By Xie Gengcai
 * Auto Generate By Code-Generator
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
    @Transactional(rollbackFor = {BizException.class, Exception.class})
    @Override
    public boolean insert(SysRole entity) {
        // 设置创建时间
        entity.setCreateTime(DateUtils.nowWithTime());
        return super.insert(entity);
    }

    @Transactional(rollbackFor = {BizException.class, Exception.class})
    @Override
    public boolean insertOrUpdate(SysRole entity) {
        if (entity.getId() == null) {
            return this.insert(entity);
        }
        return this.updateById(entity);
    }
}
