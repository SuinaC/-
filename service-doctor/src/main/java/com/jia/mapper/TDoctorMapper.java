package com.jia.mapper;


import com.jia.base.IBaseDao;
import com.jia.entity.TDoctor;

import java.util.List;

/**
 * @author wangjia
 */
public interface TDoctorMapper extends IBaseDao<TDoctor> {

    List<TDoctor> selectByDepartment(String department);

}