package com.jia.mapper;

import com.jia.entity.TDoctorCollection;
import com.jia.entity.TDoctorCollectionExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TDoctorCollectionMapper {
    int countByExample(TDoctorCollectionExample example);

    int deleteByExample(TDoctorCollectionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TDoctorCollection record);

    int insertSelective(TDoctorCollection record);

    List<TDoctorCollection> selectByExample(TDoctorCollectionExample example);

    TDoctorCollection selectByPrimaryKey(Long id);

    List<TDoctorCollection> selectByConsumerId(Long id);

    TDoctorCollection selectByConsumerIdAndDoctorId(@Param("doctorId") Long doctorId,@Param("consumerId") Long consumerId);

    int updateByExampleSelective(@Param("record") TDoctorCollection record, @Param("example") TDoctorCollectionExample example);

    int updateByExample(@Param("record") TDoctorCollection record, @Param("example") TDoctorCollectionExample example);

    int updateByPrimaryKeySelective(TDoctorCollection record);

    int updateByPrimaryKey(TDoctorCollection record);
}