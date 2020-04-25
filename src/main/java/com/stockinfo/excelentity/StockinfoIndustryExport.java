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

    @Excel(name = "类型{非会员填写 0\", \"会员填写1}",orderNum = "7",width = 30)
    private String lx;

    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc;
    }

    public String getGn() {
        return gn;
    }

    public void setGn(String gn) {
        this.gn = gn;
    }

    public String getPh() {
        return ph;
    }

    public void setPh(String ph) {
        this.ph = ph;
    }

    public String getLtpj() {
        return ltpj;
    }

    public void setLtpj(String ltpj) {
        this.ltpj = ltpj;
    }

    public String getLtsz() {
        return ltsz;
    }

    public void setLtsz(String ltsz) {
        this.ltsz = ltsz;
    }

    public String getCcjgsl() {
        return ccjgsl;
    }

    public void setCcjgsl(String ccjgsl) {
        this.ccjgsl = ccjgsl;
    }

    public String getLx() {
        return lx;
    }

    public void setLx(String lx) {
        this.lx = lx;
    }
}
