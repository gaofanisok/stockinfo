package com.stockinfo.service.impl;

import com.stockinfo.excelentity.Item;
import com.stockinfo.dao.ItemMapper;
import com.stockinfo.excelentity.StockInfoItemExport;
import com.stockinfo.service.IItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author f4cklangzi@gmail.com
 * @since 2020-05-15
 */
@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper, StockInfoItemExport> implements IItemService {

}
