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

    public String getLtpj() {
        return ltpj;
    }

    public void setLtpj(String ltpj) {
        this.ltpj = ltpj;
    }

    public String getBsz() {
        return bsz;
    }

    public void setBsz(String bsz) {
        this.bsz = bsz;
    }

    public String getQqjgcgsl() {
        return qqjgcgsl;
    }

    public void setQqjgcgsl(String qqjgcgsl) {
        this.qqjgcgsl = qqjgcgsl;
    }

    public String getBsqbh() {
        return bsqbh;
    }

    public void setBsqbh(String bsqbh) {
        this.bsqbh = bsqbh;
    }

    public String getZzjds() {
        return zzjds;
    }

    public void setZzjds(String zzjds) {
        this.zzjds = zzjds;
    }
}
