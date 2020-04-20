package com.stockinfo.util;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @Auther: gaofan
 * @Date: 2020/4/20 0020 10 14
 * @Description:分页工具类
 */
public class PageUtil {
    /**
     *<pre>
     * Description  : 分页插件  <br/>
     * ChangeLog    : 1. 创建 (2020/4/20 0020 10:38 [gaofan]);
     * @author gaofan
     * @date 2020/4/20 0020 10:38
     * @param pageIndex 页数
     * @param pageSize 条数
     * @param list  集合
     *</pre>
    */
    public static PageInfo<Map<String,Object>> PageQuery(int pageIndex, int pageSize, List<Map<String,Object>> list){
        PageHelper.startPage(pageIndex,pageSize);
        PageInfo<Map<String,Object>> pageList=new  PageInfo<Map<String,Object>>(list);
        return pageList;
    }
}
