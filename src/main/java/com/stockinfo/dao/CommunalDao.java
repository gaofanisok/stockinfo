package com.stockinfo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Auther: gaofan
 * @Date: 2020/4/17 0017:15:11
 * @Description:通用dao
 */
@Mapper
public interface CommunalDao {
    //查询通用
    List<Map<String,Object>> query(@Param(value = "sql") String sql);
    //查询通用
    List<Map<String,Object>> queryPage(@Param(value = "sql") String sql);
    //添加删除通用
    void execute(@Param(value = "sql") String sql);


}
