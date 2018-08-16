package cn.leta.upms.service.impl;

import cn.leta.common.exception.BizException;
import cn.leta.upms.model.entity.SysUserRole;
import cn.leta.upms.mapper.SysUserRoleMapper;
import cn.leta.upms.service.SysUserRoleService;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Template Created By Xie Gengcai
 * Auto Generate By Code-Generator
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {
    @Transactional(rollbackFor = {BizException.class, Exception.class})
    @Override
    public boolean insertOrUpdate(SysUserRole entity) {
        if (entity.getId() == null) {
            return this.insert(entity);
        }
        return this.updateById(entity);
    }
}
