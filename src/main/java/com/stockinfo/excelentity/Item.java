package com.stockinfo.excelentity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author f4cklangzi@gmail.com
 * @since 2020-05-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("stockinfo_item")
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String ddgpdm;

    private String type;

    private String wz1;

    private String wz2;

    private String wz3;

    private String sshy;

    private String gdjc;

    private String gqzy;

    private String sy;

    private String zjyngxl;

    private String zhpf;

    private String ksnfmgyjyq;

    private String yqz1;

    private String yqbh1;

    private String jsnfmgyjyq;

    private String yqz2;

    private String yqbh2;

    private String zyywdqzzl;

    private String zyywdqwdx;

    private String zyywzqzzl;

    private String zyywcqwdx;

    private String yi;

    private String er;

    private String san;

    private String si;

    private String wu;

    private String liu;

    private String qi;

    private String ba;

    private String jiu;

    private String shi;

    private String shiyi;

    private String shier;

    private String shisan;

    private String shisi;

    private String shiwu;

    private String shiliu;

    private String shiqi;

    private String shiba;

    private String shijiu;

    private String ershi;

    private String ershiyi;

    private String ershier;

    private String ershisan;

    private String ershisi;

    private String ershiwu;

    private String ershiliu;

    private String ershiqi;

    private String ershiba;

    private String ershijiu;

    private String sanshi;

    private String sanshiyi;

    private String sanshier;

    private String sanshisan;

    private String sanshisi;

    private String sanshiwu;

    private String sanshiliu;

    private String sanshiqi;

    private String sanshiba;

    private String sanshijiu;

    private String sishi;

    private String sjksrq;

    private String sjjsrq;

    private String dyt;

    private String dytz;

    private String dytd;

    private String det;

    private String detz;

    private String detd;

    private String dst;

    private String dstz;

    private String dstd;

    private String dsit;

    private String dsitz;

    private String dsitd;

    private String dwt;

    private String dwtz;

    private String dwtd;

    private String hydyt;

    private String hydet;

    private String hydst;

    private String hydsit;

    private String hydwt;

    private String zyyw1;

    private String zb1;

    private String zyyw2;

    private String zb2;

    private String zyyw3;

    private String zb3;

    private String dyjddqrqz;

    private String dyjdhrqz;

    private String dejdqrqz;

    private String dejdhrqz;

    private String dsjdqrqz;

    private String dsjdhrqz;

    private String dsijdqrqz;

    private String dsijdhrqz;

    private String qrq;

    private String hrq;

    private LocalDateTime creationtime;


}
