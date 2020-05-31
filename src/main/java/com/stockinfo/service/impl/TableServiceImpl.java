package com.stockinfo.service.impl;

import com.stockinfo.excelentity.Table;
import com.stockinfo.dao.TableMapper;
import com.stockinfo.service.ITableService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author f4cklangzi@gmail.com
 * @since 2020-05-27
 */
@Service
public class TableServiceImpl extends ServiceImpl<TableMapper, Table> implements ITableService {

}
