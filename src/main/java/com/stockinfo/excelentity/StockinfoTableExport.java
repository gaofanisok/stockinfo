package com.stockinfo.excelentity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;

/**
 * @Auther: gaofan
 * @Date: 2020/4/24 0024 15 40
 * @Description:导出类富赢资金
 */
@Data
@ExcelTarget("StockinfoTableExport")
public class StockinfoTableExport {
    @Excel(name = "名称",orderNum="1")
    private String mc;

    @Excel(name = "股东资金评级",orderNum="2")
    private String gdzjpj;

    @Excel(name = "当日变化",orderNum="3")
    private String drbh;

    @Excel(name = "五日变化",orderNum="4")
    private String wrbh;

    @Excel(name = "二十日变化",orderNum="5")
    private String esrbh;

    @Excel(name = "本周涨幅",orderNum="6")
    private String bzzf;

}
