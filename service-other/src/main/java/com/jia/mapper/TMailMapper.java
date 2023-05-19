package com.jia.mapper;

import com.jia.entity.TMail;
import com.jia.entity.TMailExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TMailMapper {
    int countByExample(TMailExample example);

    int deleteByExample(TMailExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TMail record);

    int insertSelective(TMail record);

    List<TMail> selectByExample(TMailExample example);

    TMail selectByPrimaryKey(Long id);

    TMail selectByConsumerId(Long id);

    int updateByExampleSelective(@Param("record") TMail record, @Param("example") TMailExample example);

    int updateByExample(@Param("record") TMail record, @Param("example") TMailExample example);

    int updateByPrimaryKeySelective(TMail record);

    int updateByPrimaryKey(TMail record);
}