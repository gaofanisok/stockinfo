package com.stockinfo.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.stockinfo.excelentity.StockInfoItemExport;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface StockInfoItemExportDtoMapper extends IService<StockInfoItemExport> {
}
