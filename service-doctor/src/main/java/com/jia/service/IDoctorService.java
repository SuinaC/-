package com.jia.service;


import com.jia.base.IBaseService;
import com.jia.entity.TDoctor;

import java.util.List;

/**
 * @author wangjia
 */
public interface IDoctorService extends IBaseService<TDoctor> {

    List<TDoctor> selectByDepartment(String department);
}
