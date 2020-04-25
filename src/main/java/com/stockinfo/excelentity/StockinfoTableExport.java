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

    @Excel(name = "类型{非会员填写 0\", \"会员填写1}",orderNum = "7",width = 30)
    private String lx;

    public String getLx() {
        return lx;
    }

    public void setLx(String lx) {
        this.lx = lx;
    }

    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc;
    }

    public String getGdzjpj() {
        return gdzjpj;
    }

    public void setGdzjpj(String gdzjpj) {
        this.gdzjpj = gdzjpj;
    }

    public String getDrbh() {
        return drbh;
    }

    public void setDrbh(String drbh) {
        this.drbh = drbh;
    }

    public String getWrbh() {
        return wrbh;
    }

    public void setWrbh(String wrbh) {
        this.wrbh = wrbh;
    }

    public String getEsrbh() {
        return esrbh;
    }

    public void setEsrbh(String esrbh) {
        this.esrbh = esrbh;
    }

    public String getBzzf() {
        return bzzf;
    }

    public void setBzzf(String bzzf) {
        this.bzzf = bzzf;
    }
}
