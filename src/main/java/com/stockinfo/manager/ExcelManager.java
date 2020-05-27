package com.stockinfo.manager;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import cn.hutool.core.lang.Console;
import cn.hutool.poi.excel.sax.Excel03SaxReader;
import cn.hutool.poi.excel.sax.Excel07SaxReader;
import cn.hutool.poi.excel.sax.handler.RowHandler;
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
import java.io.File;
import java.io.InputStream;
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
                        StringBuilder sql = new StringBuilder("insert into stockinfo_table values");
                        ExcelImportResult<StockinfoTableExport> result = ExcelImportUtil.importExcelMore(file.getInputStream(), StockinfoTableExport.class, importParams);
                        List<StockinfoTableExport> stockinfoTableExports = result.getList();
                        if (stockinfoTableExports.size() <= 0) {
                            return "";
                        }
                        int i = 0;
                        for (StockinfoTableExport demoExcel : stockinfoTableExports) {
                            i++;
                            sql.append("('").append(UUID.randomUUID().toString()).append("','").append(demoExcel.getMc()).append("','").append(demoExcel.getGdzjpj()).append("','").append(demoExcel.getDrbh()).append("','").append(demoExcel.getWrbh()).append("','").append(demoExcel.getEsrbh()).append("','").append(demoExcel.getBzzf()).append("','").append(demoExcel.getLx()).append("','").append(Util.newdata()).append("','").append(demoExcel.getDdgpdm()).append("')");
                            if (i < stockinfoTableExports.size()) {
                                sql.append(",");
                            }
                        }
                        communalDao.execute(sql.toString());
                        break;
                    case "3":
                        StringBuilder sql2 = new StringBuilder("insert into stockinfo_industrytable values");
                        ExcelImportResult<StockinfoIndustryExport> result2 = ExcelImportUtil.importExcelMore(file.getInputStream(), StockinfoIndustryExport.class, importParams);
                        List<StockinfoIndustryExport> stockinfoIndustryExportList = result2.getList();
                        int i1 = 0;
                        if (stockinfoIndustryExportList.size() <= 0) {
                            return "";
                        }
                        for (StockinfoIndustryExport demoExcel : stockinfoIndustryExportList) {
                            i1++;
                            sql2.append("('").append(UUID.randomUUID().toString()).append("','").append(demoExcel.getMc()).append("','").append(demoExcel.getGn()).append("','").append(demoExcel.getPh()).append("','").append(demoExcel.getLtpj()).append("','").append(demoExcel.getLtsz()).append("','").append(demoExcel.getCcjgsl()).append("','").append(demoExcel.getLx()).append("','").append(Util.newdata()).append("','").append(demoExcel.getDdgpdm()).append("')");
                            if (i1 < stockinfoIndustryExportList.size()) {
                                sql2.append(",");
                            }
                        }
                        communalDao.execute(sql2.toString());
                        break;
                    case "2":
                        StringBuilder sql3 = new StringBuilder("insert into stockinfo_institutionstable values");
                        ExcelImportResult<StockinfoInstitutionsExport> result3 = ExcelImportUtil.importExcelMore(file.getInputStream(), StockinfoIndustryExport.class, importParams);
                        List<StockinfoInstitutionsExport> stockinfoInstitutionsExportList = result3.getList();
                        int i2 = 0;
                        if (stockinfoInstitutionsExportList.size() <= 0) {
                            return "";
                        }
                        for (StockinfoInstitutionsExport demoExcel : stockinfoInstitutionsExportList) {
                            i2++;
                            sql3.append("('").append(UUID.randomUUID().toString()).append("','").append(demoExcel.getMc()).append("','").append(demoExcel.getLtpj()).append("','").append(demoExcel.getBsz()).append("','").append(demoExcel.getQqjgcgsl()).append("','").append(demoExcel.getBsqbh()).append("','").append(demoExcel.getZzjds()).append("','").append(demoExcel.getLx()).append("','").append(Util.newdata()).append("','").append(demoExcel.getDdgpdm()).append("')");
                            if (i2 < stockinfoInstitutionsExportList.size()) {
                                sql3.append(",");
                            }
                        }
                        communalDao.execute(sql3.toString());
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
                List<Object> list=new ArrayList<>();
                StringBuilder sql = new StringBuilder("insert into stockinfo_item values");
                Excel03SaxReader reader = new Excel03SaxReader (createRowHandler(type));
                reader.read(file.getInputStream(), 0);
//                             ExcelImportResult<StockInfoItemExport> result = ExcelImportUtil.importExcelMore(file.getInputStream(), StockInfoItemExport.class, importParams);
                //             List<StockInfoItemExport> stockinfoTableExports = result.getList();
//                for (int i = 0; i < stockinfoTableExports.size(); i++) {
//                    StockInfoItemExport s = stockinfoTableExports.get(i);
//                    sql.append("('").append(UUID.randomUUID().toString()).append("','").append(s.getDdgpdm()).append("','").append(type).append("','").append(s.getWz1()).append("','").append(s.getWz2()).append("','").append(s.getWz3()).append("','").append(s.getSshy()).append("','").append(s.getGdjc()).append("','").append(s.getGqzy()).append("',").append("'").append(s.getSy()).append("','").append(s.getZjyngxl()).append("','").append(s.getZhpf()).append("','").append(s.getKsnfmgyjyq()).append("','").append(s.getYqz1()).append("','").append(s.getYqbh1()).append("','").append(s.getJsnfmgyjyq()).append("','").append(s.getYqz2()).append("',").append("'").append(s.getYqbh2()).append("','").append(s.getZyywdqzzl()).append("','").append(s.getZyywdqwdx()).append("','").append(s.getZyywzqzzl()).append("','").append(s.getZyywcqwdx()).append("','").append(s.getYi()).append("','").append(s.getEr()).append("','").append(s.getSan()).append("',").append("'").append(s.getSi()).append("','").append(s.getWu()).append("','").append(s.getLiu()).append("','").append(s.getQi()).append("','").append(s.getBa()).append("','").append(s.getJiu()).append("','").append(s.getShi()).append("','").append(s.getShiyi()).append("','").append(s.getShier()).append("'").append(",'").append(s.getShisan()).append("','").append(s.getShisi()).append("','").append(s.getShiwu()).append("','").append(s.getShiliu()).append("','").append(s.getShiqi()).append("','").append(s.getShiba()).append("','").append(s.getShijiu()).append("'").append(",'").append(s.getErshi()).append("','").append(s.getErshiyi()).append("','").append(s.getErshier()).append("','").append(s.getErshisan()).append("','").append(s.getErshisi()).append("','").append(s.getErshiwu()).append("','").append(s.getErshiliu()).append("',").append("'").append(s.getErshiqi()).append("','").append(s.getErshiba()).append("','").append(s.getErshijiu()).append("','").append(s.getSanshi()).append("','").append(s.getSanshiyi()).append("','").append(s.getSanshier()).append("'").append(",'").append(s.getSanshisan()).append("','").append(s.getSanshisi()).append("','").append(s.getSanshiwu()).append("','").append(s.getSanshiliu()).append("','").append(s.getSanshiqi()).append("','").append(s.getSanshiba()).append("','").append(s.getSanshijiu()).append("',").append("'").append(s.getSishi()).append("','").append(s.getSjksrq()).append("','").append(s.getSjjsrq()).append("','").append(s.getDyt()).append("','").append(s.getDytz()).append("','").append(s.getDet()).append("'").append(",'").append(s.getDetz()).append("','").append(s.getDst()).append("','").append(s.getDstz()).append("','").append(s.getDsit()).append("','").append(s.getDsitz()).append("','").append(s.getDwt()).append("'").append(",'").append(s.getDwtz()).append("','").append(s.getHydyt()).append("','").append(s.getHydytz()).append("','").append(s.getHydet()).append("','").append(s.getHydetz()).append("','").append(s.getHydst()).append("','").append(s.getHydstz()).append("','").append(s.getHydsit()).append("'").append(",'").append(s.getHydsitz()).append("','").append(s.getHydwt()).append("','").append(s.getHydwtz()).append("','").append(s.getZyyw1()).append("','").append(s.getZb1()).append("','").append(s.getZyyw2()).append("','").append(s.getZb2()).append("','").append(s.getZyyw3()).append("','").append(s.getZb3()).append("'").append(",'").append(s.getDyjdqrqz()).append("','").append(s.getDyjdhrqz()).append("','").append(s.getDejdqrqz()).append("','").append(s.getDejdhrqz()).append("','").append(s.getDsjdqrqz()).append("','").append(s.getDsjdhrqz()).append("','").append(s.getDsijdqrqz()).append("'").append(",'").append(s.getDsijdhrqz()).append("','").append(s.getQrq()).append("','").append(s.getHrq()).append("','").append(LocalDateTime.now()).append("')");
//                    if (i < stockinfoTableExports.size() - 1) {
//                        sql.append(",");
//                    }
//                }
//                communalDao.execute(sql.toString());
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
                    break;
                case "5":
                    List<StockinfoGsinfo> stockinfoGsinfoList = new ArrayList<>();
                    ExcelUtil.exportExcel(stockinfoGsinfoList, "公司详细数据", "公司详细数据", StockinfoGsinfo.class, "详细数据表" + ".xls", response);
                    break;
                default:
                    break;
            }
        }
    }


    private RowHandler createRowHandler(String type) {
        return  new RowHandler() {
            List<StockInfoItemExport> stockInfoItemExports=new ArrayList<>();
            @Override
            public void handle(int sheetIndex, int rowIndex, List<Object> rowList) {
                StockInfoItemExport stockInfoItemExport=new StockInfoItemExport();
                if (rowIndex!=0 && rowIndex !=1){
                    stockInfoItemExport.setId(UUID.randomUUID().toString());
                    stockInfoItemExport.setType(type);
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setWz1(String.valueOf(rowList.get(1)));
                    stockInfoItemExport.setWz2(String.valueOf(rowList.get(2)));
                    stockInfoItemExport.setWz3(String.valueOf(rowList.get(3)));
                    stockInfoItemExport.setSshy(String.valueOf(rowList.get(4)));
                    stockInfoItemExport.setGdjc(String.valueOf(rowList.get(5)));
                    stockInfoItemExport.setGqzy(String.valueOf(rowList.get(6)));
                    stockInfoItemExport.setSy(String.valueOf(rowList.get(7)));
                    stockInfoItemExport.setZjyngxl(String.valueOf(rowList.get(8)));
                    stockInfoItemExport.setZhpf(String.valueOf(rowList.get(9)));
                    stockInfoItemExport.setKsnfmgyjyq(String.valueOf(rowList.get(10)));
                    stockInfoItemExport.setYqz1(String.valueOf(rowList.get(11)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));
                    stockInfoItemExport.setDdgpdm(String.valueOf(rowList.get(0)));


                }
                Console.log("[{}] [{}] {}", sheetIndex, rowIndex, rowList);
                Console.log(rowIndex);
            }
        };
    }
}
