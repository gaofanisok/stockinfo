package com.stockinfo.dao;

import org.apache.ibatis.annotations.Mapper;


import java.util.List;
import java.util.Map;

@Mapper
public interface UserDao {
    //查询通用
    List<Map<String,Object>> query(Map map);
    //添加删除通用
    void execute(Map map);
}
