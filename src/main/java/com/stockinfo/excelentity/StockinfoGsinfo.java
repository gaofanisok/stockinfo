package com.stockinfo.excelentity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("stockinfo_gsinfo")
public class StockinfoGsinfo {
    private String id;
    @Excel(name = "当地股票代码", orderNum = "1")
    private String ddgpdm;
    @Excel(name = "日期 (格式为2019-01-01)", orderNum = "2",width = 30)
    private String rq;
    @Excel(name = "营业收入", orderNum = "3")
    private String yysr;
    @Excel(name = "同比", orderNum = "4")
    private String tb1;
    @Excel(name = "每股收益", orderNum = "5")
    private String mgsy;
    @Excel(name = "同比2", orderNum = "6")
    private String tb2;
    private String type;
    private LocalDateTime creationtime;
}
