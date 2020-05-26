package com.stockinfo.manager;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stockinfo.dao.CommunalDao;
import com.stockinfo.dao.ItemMapper;
import com.stockinfo.excelentity.*;
import com.stockinfo.service.IGsinfoService;
import com.stockinfo.service.IItemService;
import com.stockinfo.util.ExcelUtil;
import com.stockinfo.util.StringUtil;
import com.stockinfo.util.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Auther: Administrator
 * @Date: 2020/4/24 0024 14 53
 * @Description:
 */
@Service
@Slf4j
public class ExcelManager extends ServiceImpl<ItemMapper, StockInfoItemExport> implements IItemService {
    @Autowired
    CommunalDao communalDao;
    @Autowired
    IGsinfoService iGsinfoService;

    public String importExcel(MultipartFile file, String type) {
        ImportParams importParams = new ImportParams();
        // 表头在第几行
        importParams.setTitleRows(1);
        try {
            if (StringUtil.isNotEmpty(type)) {
                switch (type) {
                    case "1":
                        String sql = "insert into stockinfo_table values";
                        ExcelImportResult<StockinfoTableExport> result = ExcelImportUtil.importExcelMore(file.getInputStream(), StockinfoTableExport.class, importParams);
                        List<StockinfoTableExport> stockinfoTableExports = result.getList();
                        if (stockinfoTableExports.size() < 0) {
                            return "";
                        }
                        int i = 0;
                        for (StockinfoTableExport demoExcel : stockinfoTableExports) {
                            i++;
                            sql += "('" + UUID.randomUUID().toString() + "','" + demoExcel.getMc() + "','" + demoExcel.getGdzjpj() + "','" + demoExcel.getDrbh() + "','" + demoExcel.getWrbh() + "','" + demoExcel.getEsrbh() + "','" + demoExcel.getBzzf() + "','" + demoExcel.getLx() + "','" + Util.newdata() + "','" + demoExcel.getDdgpdm() + "')";
                            if (i < stockinfoTableExports.size()) {
                                sql += ",";
                            }
                        }
                        communalDao.execute(sql);
                        break;
                    case "3":
                        String sql2 = "insert into stockinfo_industrytable values";
                        ExcelImportResult<StockinfoIndustryExport> result2 = ExcelImportUtil.importExcelMore(file.getInputStream(), StockinfoIndustryExport.class, importParams);
                        List<StockinfoIndustryExport> stockinfoIndustryExportList = result2.getList();
                        int i1 = 0;
                        if (stockinfoIndustryExportList.size() < 0) {
                            return "";
                        }
                        for (StockinfoIndustryExport demoExcel : stockinfoIndustryExportList) {
                            i1++;
                            sql2 += "('" + UUID.randomUUID().toString() + "','" + demoExcel.getMc() + "','" + demoExcel.getGn() + "','" + demoExcel.getPh() + "','" + demoExcel.getLtpj() + "','" + demoExcel.getLtsz() + "','" + demoExcel.getCcjgsl() + "','" + demoExcel.getLx() + "','" + Util.newdata() + "','" + demoExcel.getDdgpdm() + "')";
                            if (i1 < stockinfoIndustryExportList.size()) {
                                sql2 += ",";
                            }
                        }
                        communalDao.execute(sql2);
                        break;
                    case "2":
                        String sql3 = "insert into stockinfo_institutionstable values";
                        ExcelImportResult<StockinfoInstitutionsExport> result3 = ExcelImportUtil.importExcelMore(file.getInputStream(), StockinfoIndustryExport.class, importParams);
                        List<StockinfoInstitutionsExport> stockinfoInstitutionsExportList = result3.getList();
                        int i2 = 0;
                        if (stockinfoInstitutionsExportList.size() < 0) {
                            return "";
                        }
                        for (StockinfoInstitutionsExport demoExcel : stockinfoInstitutionsExportList) {
                            i2++;
                            sql3 += "('" + UUID.randomUUID().toString() + "','" + demoExcel.getMc() + "','" + demoExcel.getLtpj() + "','" + demoExcel.getBsz() + "','" + demoExcel.getQqjgcgsl() + "','" + demoExcel.getBsqbh() + "','" + demoExcel.getZzjds() + "','" + demoExcel.getLx() + "','" + Util.newdata() + "','" + demoExcel.getDdgpdm() + "')";
                            if (i2 < stockinfoInstitutionsExportList.size()) {
                                sql3 += ",";
                            }
                        }
                        communalDao.execute(sql3);
                        break;
                    default:
                        break;
                }
            }
        } catch (Exception e) {
            return "导入失败！请检查导入文档的格式是否正确";
        }
        return "导入成功";
    }

    public String importItemExcel(MultipartFile file, String type, String state) throws Exception, ParseException {
        if (!state.isEmpty()) {
            ImportParams importParams = new ImportParams();
            // 表头在第几行
            importParams.setTitleRows(1);
            if ("0".equals(state)) {
                String sql = "insert into stockinfo_item values";
                ExcelImportResult<StockInfoItemExport> result = ExcelImportUtil.importExcelMore(file.getInputStream(), StockInfoItemExport.class, importParams);
                List<StockInfoItemExport> stockinfoTableExports = result.getList();
                for (int i = 0; i < stockinfoTableExports.size(); i++) {
                    StockInfoItemExport s = stockinfoTableExports.get(i);
                    sql += "('" + UUID.randomUUID().toString() + "','" + s.getDdgpdm() + "','" + type + "','" + s.getWz1() + "','" + s.getWz2() + "','" + s.getWz3() + "','" + s.getSshy() + "','" + s.getGdjc() + "','" + s.getGqzy() + "'," +
                            "'" + s.getSy() + "','" + s.getZjyngxl() + "','" + s.getZhpf() + "','" + s.getKsnfmgyjyq() + "','" + s.getYqz1() + "','" + s.getYqbh1() + "','" + s.getJsnfmgyjyq() + "','" + s.getYqz2() + "'," +
                            "'" + s.getYqbh2() + "','" + s.getZyywdqzzl() + "','" + s.getZyywdqwdx() + "','" + s.getZyywzqzzl() + "','" + s.getZyywcqwdx() + "','" + s.getYi() + "','" + s.getEr() + "','" + s.getSan() + "'," +
                            "'" + s.getSi() + "','" + s.getWu() + "','" + s.getLiu() + "','" + s.getQi() + "','" + s.getBa() + "','" + s.getJiu() + "','" + s.getShi() + "','" + s.getShiyi() + "','" + s.getShier() + "'" +
                            ",'" + s.getShisan() + "','" + s.getShisi() + "','" + s.getShiwu() + "','" + s.getShiliu() + "','" + s.getShiqi() + "','" + s.getShiba() + "','" + s.getShijiu() + "'" +
                            ",'" + s.getErshi() + "','" + s.getErshiyi() + "','" + s.getErshier() + "','" + s.getErshisan() + "','" + s.getErshisi() + "','" + s.getErshiwu() + "','" + s.getErshiliu() + "'," +
                            "'" + s.getErshiqi() + "','" + s.getErshiba() + "','" + s.getErshijiu() + "','" + s.getSanshi() + "','" + s.getSanshiyi() + "','" + s.getSanshier() + "'" +
                            ",'" + s.getSanshisan() + "','" + s.getSanshisi() + "','" + s.getSanshiwu() + "','" + s.getSanshiliu() + "','" + s.getSanshiqi() + "','" + s.getSanshiba() + "','" + s.getSanshijiu() + "'," +
                            "'" + s.getSishi() + "','" + s.getSjksrq() + "','" + s.getSjjsrq() + "','" + s.getDyt() + "','" + s.getDytz() + "','" + s.getDet() + "'" +
                            ",'" + s.getDetz() + "','" + s.getDst() + "','" + s.getDstz() + "','" + s.getDsit() + "','" + s.getDsitz() + "','" + s.getDwt() + "'" +
                            ",'" + s.getDwtz() + "','" + s.getHydyt() + "','" + s.getHydytz() + "','" + s.getHydet() + "','" + s.getHydetz() + "','" + s.getHydst() + "','" + s.getHydstz() + "','" + s.getHydsit() + "'" +
                            ",'" + s.getHydsitz() + "','" + s.getHydwt() + "','" + s.getHydwtz() + "','" + s.getZyyw1() + "','" + s.getZb1() + "','" + s.getZyyw2() + "','" + s.getZb2() + "','" + s.getZyyw3() + "','" + s.getZb3() + "'" +
                            ",'" + s.getDyjdqrqz() + "','" + s.getDyjdhrqz() + "','" + s.getDejdqrqz() + "','" + s.getDejdhrqz() + "','" + s.getDsjdqrqz() + "','" + s.getDsjdhrqz() + "','" + s.getDsijdqrqz() + "'" +
                            ",'" + s.getDsijdhrqz() + "','" + s.getQrq() + "','" + s.getHrq() + "','" + LocalDateTime.now() + "')";
                    if (i < stockinfoTableExports.size() - 1) {
                        sql += ",";
                    }
                }
                communalDao.execute(sql);
            } else if ("1".equals(state)) {

                ExcelImportResult<StockinfoGsinfo> result = ExcelImportUtil.importExcelMore(file.getInputStream(), StockinfoGsinfo.class, importParams);
                List<StockinfoGsinfo> stockinfoGsinfos = result.getList();
                stockinfoGsinfos.forEach(stockinfoGsinfo -> {
                    try {
                        stockinfoGsinfo.setRq(Util.dateToStrings(stockinfoGsinfo.getRq()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    stockinfoGsinfo.setId(UUID.randomUUID().toString());
                    stockinfoGsinfo.setType(type);
                    stockinfoGsinfo.setCreationtime(LocalDateTime.now());
                });
                iGsinfoService.saveBatch(stockinfoGsinfos);
            }
            return "1";
        }
        return "";
    }


    /**
     * <pre>
     * Description  : 导出模板  <br/>
     * ChangeLog    : 1. 创建 (2020/4/20 0020 15:03 [gaofan]);
     * @author gaofan
     * @date 2020/4/20 0020 15:03
     * </pre>
     */
    public void export(HttpServletResponse response, String type) {
        //导出操作
        if (StringUtil.isNotEmpty(type)) {
            switch (type) {
                case "1":
                    List<StockinfoTableExport> stockinfoTableExportList = new ArrayList<>();
                    ExcelUtil.exportExcel(stockinfoTableExportList, "富赢资金", "富赢资金", StockinfoTableExport.class, "富赢资金信息表" + ".xls", response);
                    break;
                case "3":
                    List<StockinfoIndustryExport> stockinfoIndustryExportList = new ArrayList<>();
                    ExcelUtil.exportExcel(stockinfoIndustryExportList, "行业top", "行业top", StockinfoIndustryExport.class, "行业top信息表" + ".xls", response);
                    break;
                case "2":
                    List<StockinfoInstitutionsExport> stockinfoInstitutionsExportList = new ArrayList<>();
                    ExcelUtil.exportExcel(stockinfoInstitutionsExportList, "机构评级", "机构评级", StockinfoInstitutionsExport.class, "机构评级信息表" + ".xls", response);
                    break;
                case "4":
                    List<StockInfoItemExport> stockInfoItemExportList = new ArrayList<>();
                    ExcelUtil.exportExcel(stockInfoItemExportList, "详细数据", "详细数据", StockInfoItemExport.class, "详细数据表" + ".xls", response);
                case "5":
                    List<StockinfoGsinfo> stockinfoGsinfoList = new ArrayList<>();
                    ExcelUtil.exportExcel(stockinfoGsinfoList, "公司详细数据", "公司详细数据", StockinfoGsinfo.class, "详细数据表" + ".xls", response);
                default:
                    break;
            }
        }
    }
}
