package cn.leta.common.web;

import cn.leta.common.vo.StaffVO;
import com.baomidou.mybatisplus.service.IService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by xiegengcai on 2018/7/3.
 *
 * @author Xie Gengcai
 */
public abstract class BaseController<T, S extends IService<T>> {

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected S baseService;

    protected StaffVO getStaffVO(){
        return new StaffVO(getHeader("groupCode"), Long.valueOf(getHeader("staffId"))
                , getHeader("staffName"), getHeader("phone"));
    }

    protected String getHeader(String name) {
        return request.getHeader(name);
    }
}
