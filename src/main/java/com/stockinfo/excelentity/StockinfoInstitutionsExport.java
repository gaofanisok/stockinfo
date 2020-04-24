package com.stockinfo.excelentity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 * @Auther: gaofan
 * @Date: 2020/4/24 0024 17 16
 * @Description:导出类机构评级数据
 */
@Data
public class StockinfoInstitutionsExport {
    @Excel(name = "名称",orderNum="1")
    private String mc;

    @Excel(name = "路透评级",orderNum="2")
    private String ltpj;

    @Excel(name = "比上周",orderNum="3")
    private String bsz;

    @Excel(name = "全球机构持股数量",orderNum="4")
    private String qqjgcgsl;

    @Excel(name = "比上期变化",orderNum="5")
    private String bsqbh;

    @Excel(name = "增长季度数",orderNum="6")
    private String zzjds;
}
