package com.stockinfo.excelentity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;

/**
 * @Auther: gaofan
 * @Date: 2020/4/24 0024 17 09
 * @Description:导出类行业top
 */
@Data
@ExcelTarget("StockinfoIndustryExport")
public class StockinfoIndustryExport {
    @Excel(name = "名称",orderNum = "1")
    private String mc;

    @Excel(name = "概念",orderNum = "2")
    private String gn;

    @Excel(name = "排行",orderNum = "3")
    private String ph;

    @Excel(name = "路透评级",orderNum = "4")
    private String ltpj;

    @Excel(name = "流通市值",orderNum = "5")
    private String ltsz;

    @Excel(name = "持仓机构数量",orderNum = "6")
    private String ccjgsl;
}
