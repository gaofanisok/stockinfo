package com.stockinfo.excelentity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("stockinfo_item")
public class StockInfoItemExport implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String type;
    @Excel(name = "当地股票代码", orderNum = "1")
    private String ddgpdm;
    @Excel(name = "未知1", orderNum = "2")
    private String wz1;
    @Excel(name = "未知2", orderNum = "3")
    private String wz2;
    @Excel(name = "未知3", orderNum = "4")
    private String wz3;
    @Excel(name = "所属行业", orderNum = "5")
    private String sshy;
    @Excel(name = "股东减持", orderNum = "6")
    private String gdjc;
    @Excel(name = "股权质押", orderNum = "7")
    private String gqzy;
    @Excel(name = "商誉", orderNum = "8")
    private String sy;
    @Excel(name = "阿尔法值", orderNum = "9")
    private String zjyngxl;
    @Excel(name = "年度综合评分", orderNum = "10")
    private String zhpf;

    @Excel(name = "开始年份每股业绩预期名称 (如2019年每股业绩预期)", orderNum = "11",width = 30)
    private String ksnfmgyjyq;
    @Excel(name = "预期值1", orderNum = "12")
    private String yqz1;
    @Excel(name = "预期变化1", orderNum = "13")
    private String yqbh1;
    @Excel(name = "结束年份每股业绩预期名称 (如2020年每股业绩预期)", orderNum = "14",width = 30)
    private String jsnfmgyjyq;
    @Excel(name = "预期值2", orderNum = "15")
    private String yqz2;
    @Excel(name = "预期变化2", orderNum = "16")
    private String yqbh2;
    @Excel(name = "主营业务短期增长率", orderNum = "17")
    private String zyywdqzzl;
    @Excel(name = "主营业务短期稳定性", orderNum = "18")
    private String zyywdqwdx;
    @Excel(name = "主营业务中期增长率", orderNum = "19")
    private String zyywzqzzl;
    @Excel(name = "主营业务长期稳定性", orderNum = "20")
    private String zyywcqwdx;

    @Excel(name = "第一日", orderNum = "21")
    private String yi;
    @Excel(name = "第二日", orderNum = "22")
    private String er;
    @Excel(name = "第三日", orderNum = "23")
    private String san;
    @Excel(name = "第四日", orderNum = "24")
    private String si;
    @Excel(name = "第五日", orderNum = "25")
    private String wu;
    @Excel(name = "第六日", orderNum = "26")
    private String liu;
    @Excel(name = "第七日", orderNum = "27")
    private String qi;
    @Excel(name = "第八日", orderNum = "28")
    private String ba;
    @Excel(name = "第九日", orderNum = "29")
    private String jiu;
    @Excel(name = "第十日", orderNum = "30")
    private String shi;
    @Excel(name = "第十一日", orderNum = "31")
    private String shiyi;
    @Excel(name = "第十二日", orderNum = "32")
    private String shier;
    @Excel(name = "第十三日", orderNum = "33")
    private String shisan;
    @Excel(name = "第十四日", orderNum = "34")
    private String shisi;
    @Excel(name = "第十五日", orderNum = "35")
    private String shiwu;
    @Excel(name = "第十六日", orderNum = "36")
    private String shiliu;
    @Excel(name = "第十七日", orderNum = "37")
    private String shiqi;
    @Excel(name = "第十八日", orderNum = "38")
    private String shiba;
    @Excel(name = "第十九日", orderNum = "39")
    private String shijiu;
    @Excel(name = "第二十日", orderNum = "40")
    private String ershi;
    @Excel(name = "第二十一日", orderNum = "41")
    private String ershiyi;
    @Excel(name = "第二十二日", orderNum = "42")
    private String ershier;
    @Excel(name = "第二十三日", orderNum = "43")
    private String ershisan;
    @Excel(name = "第二十四日", orderNum = "44")
    private String ershisi;
    @Excel(name = "第二十五日", orderNum = "45")
    private String ershiwu;
    @Excel(name = "第二十六日", orderNum = "46")
    private String ershiliu;
    @Excel(name = "第二十七日", orderNum = "47")
    private String ershiqi;
    @Excel(name = "第二十八日", orderNum = "48")
    private String ershiba;
    @Excel(name = "第二十九日", orderNum = "49")
    private String ershijiu;
    @Excel(name = "第三十日", orderNum = "50")
    private String sanshi;
    @Excel(name = "第三十一日", orderNum = "51")
    private String sanshiyi;
    @Excel(name = "第三十二日", orderNum = "52")
    private String sanshier;
    @Excel(name = "第三十三日", orderNum = "53")
    private String sanshisan;
    @Excel(name = "第三十四日", orderNum = "54")
    private String sanshisi;
    @Excel(name = "第三十五日", orderNum = "55")
    private String sanshiwu;
    @Excel(name = "第三十六日", orderNum = "56")
    private String sanshiliu;
    @Excel(name = "第三十七日", orderNum = "57")
    private String sanshiqi;
    @Excel(name = "第三十八日", orderNum = "58")
    private String sanshiba;
    @Excel(name = "第三十九日", orderNum = "59")
    private String sanshijiu;
    @Excel(name = "第四十日", orderNum = "60")
    private String sishi;

    @Excel(name = "数据开始日期（格式如2019-01-01）", orderNum = "61", width = 30)
    private String sjksrq;
    @Excel(name = "数据结束日期 （格式如2019-01-01）", orderNum = "62", width = 30)
    private String sjjsrq;
    @Excel(name = "第一日（路透评级）", orderNum = "63")
    private String dyt;
    @Excel(name = "第一日（值）", orderNum = "64")
    private String dytz;
    @Excel(name = "第二日（路透评级）", orderNum = "65")
    private String det;
    @Excel(name = "第二日（值）", orderNum = "66")
    private String detz;
    @Excel(name = "第三日（路透评级）", orderNum = "67")
    private String dst;
    @Excel(name = "第三日（值）", orderNum = "68")
    private String dstz;
    @Excel(name = "第四日（路透评级）", orderNum = "69")
    private String dsit;
    @Excel(name = "第四日（值）", orderNum = "70")
    private String dsitz;
    @Excel(name = "第五日（路透评级）", orderNum = "71")
    private String dwt;
    @Excel(name = "第五日（值）", orderNum = "72")
    private String dwtz;

    @Excel(name = "行业热点变化第一天日期 （格式如 04-13）", orderNum = "73", width = 30)
    private String hydyt;
    @Excel(name = "行业热点变化第一天值", orderNum = "74")
    private String hydytz;
    @Excel(name = "行业热点变化第二天日期 （格式如 04-13）", orderNum = "75", width = 30)
    private String hydet;
    @Excel(name = "行业热点变化第二天值", orderNum = "76")
    private String hydetz;
    @Excel(name = "行业热点变化第三天日期 （格式如 04-13）", orderNum = "77", width = 30)
    private String hydst;
    @Excel(name = "行业热点变化第三天值", orderNum = "78")
    private String hydstz;
    @Excel(name = "行业热点变化第四天日期 （格式如 04-13）", orderNum = "79", width = 30)
    private String hydsit;
    @Excel(name = "行业热点变化第四天值", orderNum = "80")
    private String hydsitz;
    @Excel(name = "行业热点变化第五天日期 （格式如 04-13）", orderNum = "81", width = 30)
    private String hydwt;
    @Excel(name = "行业热点变化第五天值", orderNum = "82")
    private String hydwtz;

    @Excel(name = "主营业务一", orderNum = "83")
    private String zyyw1;
    @Excel(name = "占比1", orderNum = "84")
    private String zb1;
    @Excel(name = "主营业务二", orderNum = "85")
    private String zyyw2;
    @Excel(name = "占比2", orderNum = "86")
    private String zb2;
    @Excel(name = "主营业务三", orderNum = "87")
    private String zyyw3;
    @Excel(name = "占比3", orderNum = "88")
    private String zb3;

    @Excel(name = "投资回报第一季度前日期值", orderNum = "89",width = 30)
    private String dyjdqrqz;
    @Excel(name = "投资回报第一季度后日期值", orderNum = "90",width = 30)
    private String dyjdhrqz;
    @Excel(name = "投资回报第二季度前日期值", orderNum = "91",width = 30)
    private String dejdqrqz;
    @Excel(name = "投资回报第二季度后日期值", orderNum = "92",width = 30)
    private String dejdhrqz;
    @Excel(name = "投资回报第三季度前日期值", orderNum = "93",width = 30)
    private String dsjdqrqz;
    @Excel(name = "投资回报第三季度后日期值", orderNum = "94",width = 30)
    private String dsjdhrqz;
    @Excel(name = "投资回报第四季度前日期值", orderNum = "95",width = 30)
    private String dsijdqrqz;
    @Excel(name = "投资回报第四季度后日期值", orderNum = "96",width = 30)
    private String dsijdhrqz;
    @Excel(name = "开始日期 （格式如2019）", orderNum = "97",width = 30)
    private String qrq;
    @Excel(name = "结束日期 （格式如2019）", orderNum = "98",width = 30)
    private String hrq;

    private LocalDateTime creationtime;


}
