package com.stockinfo.manager;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import cn.hutool.poi.excel.sax.Excel03SaxReader;
import cn.hutool.poi.excel.sax.Excel07SaxReader;
import cn.hutool.poi.excel.sax.handler.RowHandler;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stockinfo.dao.CommunalDao;
import com.stockinfo.dao.ItemMapper;
import com.stockinfo.excelentity.*;
import com.stockinfo.service.*;
import com.stockinfo.util.ExcelUtil;
import com.stockinfo.util.StringUtil;
import com.stockinfo.util.Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
    @Resource
    ITableService tableService;
    @Resource
    IIndustrytableService iIndustrytableService;
    @Resource
    IInstitutionstableService iInstitutionstableService;
    @Resource
    IItemService iItemService;

    List<StockInfoItemExport> stockInfoItemExports = new ArrayList<>();
    List<StockinfoTableExport> stockinfoTableExports = new ArrayList<>();
    List<StockinfoInstitutionsExport> stockinfoInstitutionsExports = new ArrayList<>();
    List<StockinfoIndustryExport> stockinfoIndustryExports = new ArrayList<>();
    List<Gsinfo> stockgsInfoExports = new ArrayList<>();

    @Transactional(rollbackFor = Exception.class)
    public String importExcel(MultipartFile file, String type) throws Exception {
        ImportParams importParams = new ImportParams();
        // 表头在第几行
        importParams.setTitleRows(1);
        InputStream in = file.getInputStream();
        BufferedInputStream buffInputStream = new BufferedInputStream(in);
        if (POIFSFileSystem.hasPOIFSHeader(buffInputStream)) {
            Excel03SaxReader reader = new Excel03SaxReader(createRowHandler2(type));
            reader.read(file.getInputStream(), 0);
        }
        if (POIXMLDocument.hasOOXMLHeader(buffInputStream)) {
            Excel07SaxReader reader = new Excel07SaxReader(createRowHandler2(type));
            reader.read(file.getInputStream(), 0);
        }
        try {
            if (StringUtil.isNotEmpty(type)) {
                switch (type) {
                    case "1":
                        StringBuilder sql = new StringBuilder("insert into stockinfo_table values");
                        List<String> gpId = new ArrayList<>();
                        if (stockinfoTableExports.size() <= 0) {
                            return "";
                        }
                        int i = 0;
                        List<StockinfoTableExport> stockinfoTableExportss = stockinfoTableExports.stream().distinct().filter(distinctByKey(StockinfoTableExport::getDdgpdm)).collect(Collectors.toList());
                        for (StockinfoTableExport demoExcel : stockinfoTableExportss) {
                            i++;
                            sql.append("('").append(demoExcel.getId()).append("','").append(demoExcel.getMc()).append("','").append(demoExcel.getGdzjpj()).append("','").append(demoExcel.getDrbh()).append("','").append(demoExcel.getWrbh()).append("','").append(demoExcel.getEsrbh()).append("','").append(demoExcel.getBzzf()).append("','").append(demoExcel.getLx()).append("','").append(Util.newdata()).append("','").append(demoExcel.getDdgpdm()).append("')");
                            if (i < stockinfoTableExportss.size()) {
                                sql.append(",");
                            }
                            gpId.add(demoExcel.getDdgpdm());
                        }
                        tableService.remove(new QueryWrapper<Table>().in("ddgpdm", gpId));
                        communalDao.execute(sql.toString());
                        stockinfoTableExports.clear();
                        break;
                    case "3":
                        StringBuilder sql2 = new StringBuilder("insert into stockinfo_industrytable values");
                        int i1 = 0;
                        List<String> gpId2 = new ArrayList<>();
                        if (stockinfoIndustryExports.size() <= 0) {
                            return "";
                        }
                        List<StockinfoIndustryExport> stockinfoIndustryExportLists = stockinfoIndustryExports.stream().distinct().filter(distinctByKey(StockinfoIndustryExport::getDdgpdm)).collect(Collectors.toList());
                        for (StockinfoIndustryExport demoExcel : stockinfoIndustryExportLists) {
                            i1++;
                            sql2.append("('").append(demoExcel.getId()).append("','").append(demoExcel.getMc()).append("','").append(demoExcel.getGn()).append("','").append(demoExcel.getPh()).append("','").append(demoExcel.getLtpj()).append("','").append(demoExcel.getLtsz()).append("','").append(demoExcel.getCcjgsl()).append("','").append(Util.newdata()).append("','").append(demoExcel.getLx()).append("','").append(demoExcel.getDdgpdm()).append("')");
                            if (i1 < stockinfoIndustryExportLists.size()) {
                                sql2.append(",");
                            }
                            gpId2.add(demoExcel.getDdgpdm());
                        }
                        iIndustrytableService.remove(new QueryWrapper<Industrytable>().in("ddgpdm", gpId2));
                        communalDao.execute(sql2.toString());
                        stockinfoIndustryExports.clear();
                        break;
                    case "2":
                        List<String> gpId3 = new ArrayList<>();
                        StringBuilder sql3 = new StringBuilder("insert into stockinfo_institutionstable values");
                        int i2 = 0;
                        if (stockinfoInstitutionsExports.size() <= 0) {
                            return "";
                        }
                        List<StockinfoInstitutionsExport> stockinfoInstitutionsExportLists = stockinfoInstitutionsExports.stream().distinct().filter(distinctByKey(StockinfoInstitutionsExport::getDdgpdm)).collect(Collectors.toList());
                        for (StockinfoInstitutionsExport demoExcel : stockinfoInstitutionsExportLists) {
                            i2++;
                            sql3.append("('").append(demoExcel.getId()).append("','").append(demoExcel.getMc()).append("','").append(demoExcel.getLtpj()).append("','").append(demoExcel.getBsz()).append("','").append(demoExcel.getQqjgcgsl()).append("','").append(demoExcel.getBsqbh()).append("','").append(demoExcel.getZzjds()).append("','").append(demoExcel.getLx()).append("','").append(Util.newdata()).append("','").append(demoExcel.getDdgpdm()).append("')");
                            if (i2 < stockinfoInstitutionsExportLists.size()) {
                                sql3.append(",");
                            }
                            gpId3.add(demoExcel.getDdgpdm());
                        }
                        iInstitutionstableService.remove(new QueryWrapper<Institutionstable>().in("ddgpdm", gpId3));
                        communalDao.execute(sql3.toString());
                        stockinfoInstitutionsExports.clear();
                        break;
                    default:
                        break;
                }
            }
        } catch (Exception e) {
            //  return "导入失败！请检查导入文档的格式是否正确";
            throw e;
        }
        return "导入成功";
    }

    /**
     * 去重判断
     *
     * @param keyExtractor
     * @return * @return java.util.function.Predicate<T>
     * @date 2020/5/21 0021 14:43
     */
    private <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }


    @Transactional(rollbackFor = Exception.class)
    public String importItemExcel(MultipartFile file, String type, String state) throws Exception, ParseException {
        if (!state.isEmpty()) {
            ImportParams importParams = new ImportParams();
            // 表头在第几行
            importParams.setTitleRows(1);
            if ("0".equals(state)) {
                List<String> gpId = new ArrayList<>();
                InputStream in = file.getInputStream();
                BufferedInputStream buffInputStream = new BufferedInputStream(in);
                StringBuilder sql = new StringBuilder("insert into stockinfo_item values");
                if (POIFSFileSystem.hasPOIFSHeader(buffInputStream)) {
                    Excel03SaxReader reader = new Excel03SaxReader(createRowHandler(type));
                    reader.read(file.getInputStream(), 0);
                }
                if (POIXMLDocument.hasOOXMLHeader(buffInputStream)) {
                    Excel07SaxReader reader = new Excel07SaxReader(createRowHandler(type));
                    reader.read(file.getInputStream(), 0);
                }
                //去重
                List<StockInfoItemExport> stockinfoTableExports = stockInfoItemExports.stream().distinct().filter(distinctByKey(StockInfoItemExport::getDdgpdm)).collect(Collectors.toList());
                for (int i = 0; i < stockinfoTableExports.size(); i++) {
                    StockInfoItemExport s = stockinfoTableExports.get(i);
                    sql.append("('").append(s.getId()).append("','").append(s.getDdgpdm()).append("','").append(type).append("','").append(s.getWz1()).append("','").append(s.getWz2()).append("','").append(s.getWz3()).append("','").append(s.getSshy()).append("','").append(s.getGdjc()).append("','").append(s.getGqzy()).append("',").append("'").append(s.getSy()).append("','").append(s.getZjyngxl()).append("','").append(s.getZhpf()).append("','").append(s.getKsnfmgyjyq()).append("','").append(s.getYqz1()).append("','").append(s.getYqbh1()).append("','").append(s.getJsnfmgyjyq()).append("','").append(s.getYqz2()).append("',").append("'").append(s.getYqbh2()).append("','").append(s.getZyywdqzzl()).append("','").append(s.getZyywdqwdx()).append("','").append(s.getZyywzqzzl()).append("','").append(s.getZyywcqwdx()).append("','").append(s.getYi()).append("','").append(s.getEr()).append("','").append(s.getSan()).append("',").append("'").append(s.getSi()).append("','").append(s.getWu()).append("','").append(s.getLiu()).append("','").append(s.getQi()).append("','").append(s.getBa()).append("','").append(s.getJiu()).append("','").append(s.getShi()).append("','").append(s.getShiyi()).append("','").append(s.getShier()).append("'").append(",'").append(s.getShisan()).append("','").append(s.getShisi()).append("','").append(s.getShiwu()).append("','").append(s.getShiliu()).append("','").append(s.getShiqi()).append("','").append(s.getShiba()).append("','").append(s.getShijiu()).append("'").append(",'").append(s.getErshi()).append("','").append(s.getErshiyi()).append("','").append(s.getErshier()).append("','").append(s.getErshisan()).append("','").append(s.getErshisi()).append("','").append(s.getErshiwu()).append("','").append(s.getErshiliu()).append("',").append("'").append(s.getErshiqi()).append("','").append(s.getErshiba()).append("','").append(s.getErshijiu()).append("','").append(s.getSanshi()).append("','").append(s.getSanshiyi()).append("','").append(s.getSanshier()).append("'").append(",'").append(s.getSanshisan()).append("','").append(s.getSanshisi()).append("','").append(s.getSanshiwu()).append("','").append(s.getSanshiliu()).append("','").append(s.getSanshiqi()).append("','").append(s.getSanshiba()).append("','").append(s.getSanshijiu()).append("',").append("'").append(s.getSishi()).append("','").append(s.getSjksrq()).append("','").append(s.getSjjsrq()).append("','").append(s.getDyt()).append("','").append(s.getDytz()).append("','").append(s.getDet()).append("'").append(",'").append(s.getDetz()).append("','").append(s.getDst()).append("','").append(s.getDstz()).append("','").append(s.getDsit()).append("','").append(s.getDsitz()).append("','").append(s.getDwt()).append("'").append(",'").append(s.getDwtz()).append("','").append(s.getHydyt()).append("','").append(s.getHydytz()).append("','").append(s.getHydet()).append("','").append(s.getHydetz()).append("','").append(s.getHydst()).append("','").append(s.getHydstz()).append("','").append(s.getHydsit()).append("'").append(",'").append(s.getHydsitz()).append("','").append(s.getHydwt()).append("','").append(s.getHydwtz()).append("','").append(s.getZyyw1()).append("','").append(s.getZb1()).append("','").append(s.getZyyw2()).append("','").append(s.getZb2()).append("','").append(s.getZyyw3()).append("','").append(s.getZb3()).append("'").append(",'").append(s.getDyjdqrqz()).append("','").append(s.getDyjdhrqz()).append("','").append(s.getDejdqrqz()).append("','").append(s.getDejdhrqz()).append("','").append(s.getDsjdqrqz()).append("','").append(s.getDsjdhrqz()).append("','").append(s.getDsijdqrqz()).append("'").append(",'").append(s.getDsijdhrqz()).append("','").append(s.getQrq()).append("','").append(s.getHrq()).append("','").append(LocalDateTime.now()).append("')");
                    if (i < stockinfoTableExports.size() - 1) {
                        sql.append(",");
                    }
                    gpId.add(s.getDdgpdm());
                }
                iItemService.remove(new QueryWrapper<StockInfoItemExport>().in("ddgpdm", gpId).eq("type", type));
                communalDao.execute(sql.toString());
                stockInfoItemExports.clear();
            } else if ("1".equals(state)) {
                List<String> gpId = new ArrayList<>();
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
                    gpId.add(stockinfoGsinfo.getDdgpdm());
                });
                iGsinfoService.remove(new QueryWrapper<StockinfoGsinfo>().in("ddgpdm", gpId).eq("type", type));
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
                    ExcelUtil.exportExcel(stockinfoGsinfoList, "公司详细数据", "公司详细数据", StockinfoGsinfo.class, "公司详细数据表" + ".xls", response);
                    break;
                default:
                    break;
            }
        }
    }


    private RowHandler createRowHandler(String type) {
        RowHandler r = new RowHandler() {
            @Override
            public void handle(int sheetIndex, int rowIndex, List<Object> rowList) {
                if (rowList.size() > 0) {
                    if (rowIndex != 0 && rowIndex != 1 && rowList.get(0) != null && !"".equals(rowList.get(0) + "")) {
                        StockInfoItemExport stockInfoItemExport = new StockInfoItemExport();
                        stockInfoItemExport.setId(UUID.randomUUID().toString());
                        stockInfoItemExport.setType(type);
                        stockInfoItemExport.setDdgpdm(String.valueOf(rowList.size()<1 ?"":rowList.get(0)));
                        stockInfoItemExport.setWz1(String.valueOf(rowList.size()<2 ?"":rowList.get(1)));
                        stockInfoItemExport.setWz2(String.valueOf(rowList.size()<3 ?"":rowList.get(2)));
                        stockInfoItemExport.setWz3(String.valueOf(rowList.size()<4 ?"":rowList.get(3)));
                        stockInfoItemExport.setSshy(String.valueOf(rowList.size()<5 ?"":rowList.get(4)));
                        stockInfoItemExport.setGdjc(String.valueOf(rowList.size()<6 ?"":rowList.get(5)));
                        stockInfoItemExport.setGqzy(String.valueOf(rowList.size()<7 ?"":rowList.get(6)));
                        stockInfoItemExport.setSy(String.valueOf(rowList.size()<8 ?"":rowList.get(7)));
                        stockInfoItemExport.setZjyngxl(String.valueOf(rowList.size()<9 ?"":rowList.get(8)));
                        stockInfoItemExport.setZhpf(String.valueOf(rowList.size()<10 ?"":rowList.get(9)));
                        stockInfoItemExport.setKsnfmgyjyq(String.valueOf(rowList.size()<11 ?"":rowList.get(10)));
                        stockInfoItemExport.setYqz1(String.valueOf(rowList.size()<12 ?"":rowList.get(11)));
                        stockInfoItemExport.setYqbh1(String.valueOf(rowList.size()<13 ?"":rowList.get(12)));
                        stockInfoItemExport.setJsnfmgyjyq(String.valueOf(rowList.size()<14 ?"":rowList.get(13)));
                        stockInfoItemExport.setYqz2(String.valueOf(rowList.size()<15 ?"":rowList.get(14)));
                        stockInfoItemExport.setYqbh2(String.valueOf(rowList.size()<16 ?"":rowList.get(15)));
                        stockInfoItemExport.setZyywdqzzl(String.valueOf(rowList.size()<17 ?"":rowList.get(16)));
                        stockInfoItemExport.setZyywdqwdx(String.valueOf(rowList.size()<18 ?"":rowList.get(17)));
                        stockInfoItemExport.setZyywzqzzl(String.valueOf(rowList.size()<19 ?"":rowList.get(18)));
                        stockInfoItemExport.setZyywcqwdx(String.valueOf(rowList.size()<20 ?"":rowList.get(19)));
                        stockInfoItemExport.setYi(String.valueOf(rowList.size()<21 ?"":rowList.get(20)));
                        stockInfoItemExport.setEr(String.valueOf(rowList.size()<22 ?"":rowList.get(21)));
                        stockInfoItemExport.setSan(String.valueOf(rowList.size()<23 ?"":rowList.get(22)));
                        stockInfoItemExport.setSi(String.valueOf(rowList.size()<24 ?"":rowList.get(23)));
                        stockInfoItemExport.setWu(String.valueOf(rowList.size()<25 ?"":rowList.get(24)));
                        stockInfoItemExport.setLiu(String.valueOf(rowList.size()<26 ?"":rowList.get(25)));
                        stockInfoItemExport.setQi(String.valueOf(rowList.size()<27 ?"":rowList.get(26)));
                        stockInfoItemExport.setBa(String.valueOf(rowList.size()<28 ?"":rowList.get(27)));
                        stockInfoItemExport.setJiu(String.valueOf(rowList.size()<29 ?"":rowList.get(28)));
                        stockInfoItemExport.setShi(String.valueOf(rowList.size()<30 ?"":rowList.get(29)));
                        stockInfoItemExport.setShiyi(String.valueOf(rowList.size()<31 ?"":rowList.get(30)));
                        stockInfoItemExport.setShier(String.valueOf(rowList.size()<32 ?"":rowList.get(31)));
                        stockInfoItemExport.setShisan(String.valueOf(rowList.size()<33 ?"":rowList.get(32)));
                        stockInfoItemExport.setShisi(String.valueOf(rowList.size()<34 ?"":rowList.get(33)));
                        stockInfoItemExport.setShiwu(String.valueOf(rowList.size()<35 ?"":rowList.get(34)));
                        stockInfoItemExport.setShiliu(String.valueOf(rowList.size()<36 ?"":rowList.get(35)));
                        stockInfoItemExport.setShiqi(String.valueOf(rowList.size()<37 ?"":rowList.get(36)));
                        stockInfoItemExport.setShiba(String.valueOf(rowList.size()<38 ?"":rowList.get(37)));
                        stockInfoItemExport.setShijiu(String.valueOf(rowList.size()<39 ?"":rowList.get(38)));
                        stockInfoItemExport.setErshi(String.valueOf(rowList.size()<40 ?"":rowList.get(39)));
                        stockInfoItemExport.setErshiyi(String.valueOf(rowList.size()<41 ?"":rowList.get(40)));
                        stockInfoItemExport.setErshier(String.valueOf(rowList.size()<42 ?"":rowList.get(41)));
                        stockInfoItemExport.setErshisan(String.valueOf(rowList.size()<43 ?"":rowList.get(42)));
                        stockInfoItemExport.setErshisi(String.valueOf(rowList.size()<44 ?"":rowList.get(43)));

                        stockInfoItemExport.setErshiwu(String.valueOf(rowList.size()<45 ?"":rowList.get(44)));
                        stockInfoItemExport.setErshiliu(String.valueOf(rowList.size()<46 ?"":rowList.get(45)));
                        stockInfoItemExport.setErshiqi(String.valueOf(rowList.size()<47 ?"":rowList.get(46)));
                        stockInfoItemExport.setErshiba(String.valueOf(rowList.size()<48 ?"":rowList.get(47)));
                        stockInfoItemExport.setErshijiu(String.valueOf(rowList.size()<49 ?"":rowList.get(48)));
                        stockInfoItemExport.setSanshi(String.valueOf(rowList.size()<50 ?"":rowList.get(49)));
                        stockInfoItemExport.setSanshiyi(String.valueOf(rowList.size()<51 ?"":rowList.get(50)));
                        stockInfoItemExport.setSanshier(String.valueOf(rowList.size()<52 ?"":rowList.get(51)));
                        stockInfoItemExport.setSanshisan(String.valueOf(rowList.size()<53 ?"":rowList.get(52)));
                        stockInfoItemExport.setSanshisi(String.valueOf(rowList.size()<54 ?"":rowList.get(53)));
                        stockInfoItemExport.setSanshiwu(String.valueOf(rowList.size()<55 ?"":rowList.get(54)));
                        stockInfoItemExport.setSanshiliu(String.valueOf(rowList.size()<56 ?"":rowList.get(55)));
                        stockInfoItemExport.setSanshiqi(String.valueOf(rowList.size()<57 ?"":rowList.get(56)));
                        stockInfoItemExport.setSanshiba(String.valueOf(rowList.size()<58 ?"":rowList.get(57)));
                        stockInfoItemExport.setSanshijiu(String.valueOf(rowList.size()<59 ?"":rowList.get(58)));
                        stockInfoItemExport.setSishi(String.valueOf(rowList.size()<60 ?"":rowList.get(59)));

                        stockInfoItemExport.setSjksrq(String.valueOf(rowList.size()<61 ?"":rowList.get(60)));
                        stockInfoItemExport.setSjjsrq(String.valueOf(rowList.size()<62 ?"":rowList.get(61)));

                        stockInfoItemExport.setDyt(String.valueOf(rowList.size()<63 ?"":rowList.get(62)));
                        stockInfoItemExport.setDytz(String.valueOf(rowList.size()<64 ?"":rowList.get(63)));
                        stockInfoItemExport.setDet(String.valueOf(rowList.size()<65 ?"":rowList.get(64)));
                        stockInfoItemExport.setDetz(String.valueOf(rowList.size()<66 ?"":rowList.get(65)));
                        stockInfoItemExport.setDst(String.valueOf(rowList.size()<67 ?"":rowList.get(66)));
                        stockInfoItemExport.setDstz(String.valueOf(rowList.size()<68 ?"":rowList.get(67)));
                        stockInfoItemExport.setDsit(String.valueOf(rowList.size()<69 ?"":rowList.get(68)));
                        stockInfoItemExport.setDsitz(String.valueOf(rowList.size()<70 ?"":rowList.get(69)));
                        stockInfoItemExport.setDwt(String.valueOf(rowList.size()<71 ?"":rowList.get(70)));
                        stockInfoItemExport.setDwtz(String.valueOf(rowList.size()<72 ?"":rowList.get(71)));

                        stockInfoItemExport.setHydyt(String.valueOf(rowList.size()<73 ?"":rowList.get(72)));
                        stockInfoItemExport.setHydytz(String.valueOf(rowList.size()<74 ?"":rowList.get(73)));
                        stockInfoItemExport.setHydet(String.valueOf(rowList.size()<75 ?"":rowList.get(74)));
                        stockInfoItemExport.setHydetz(String.valueOf(rowList.size()<76 ?"":rowList.get(75)));
                        stockInfoItemExport.setHydst(String.valueOf(rowList.size()<77 ?"":rowList.get(76)));
                        stockInfoItemExport.setHydstz(String.valueOf(rowList.size()<78 ?"":rowList.get(77)));
                        stockInfoItemExport.setHydsit(String.valueOf(rowList.size()<79 ?"":rowList.get(78)));
                        stockInfoItemExport.setHydsitz(String.valueOf(rowList.size()<80 ?"":rowList.get(79)));
                        stockInfoItemExport.setHydwt(String.valueOf(rowList.size()<81 ?"":rowList.get(80)));
                        stockInfoItemExport.setHydwtz(String.valueOf(rowList.size()<82 ?"":rowList.get(81)));

                        stockInfoItemExport.setZyyw1(String.valueOf(rowList.size()<83 ?"":rowList.get(82)));
                        stockInfoItemExport.setZb1(String.valueOf(rowList.size()<84 ?"":rowList.get(83)));
                        stockInfoItemExport.setZyyw2(String.valueOf(rowList.size()<85 ?"":rowList.get(84)));
                        stockInfoItemExport.setZb2(String.valueOf(rowList.size()<86 ?"":rowList.get(85)));
                        stockInfoItemExport.setZyyw3(String.valueOf(rowList.size()<87 ?"":rowList.get(86)));
                        stockInfoItemExport.setZb3(String.valueOf(rowList.size()<88 ?"":rowList.get(87)));

                        stockInfoItemExport.setDyjdqrqz(String.valueOf(rowList.size()<89 ?"":rowList.get(88)));
                        stockInfoItemExport.setDyjdhrqz(String.valueOf(rowList.size()<90 ?"":rowList.get(89)));
                        stockInfoItemExport.setDejdqrqz(String.valueOf(rowList.size()<91 ?"":rowList.get(90)));
                        stockInfoItemExport.setDejdhrqz(String.valueOf(rowList.size()<92 ?"":rowList.get(91)));
                        stockInfoItemExport.setDsjdqrqz(String.valueOf(rowList.size()<93 ?"":rowList.get(92)));
                        stockInfoItemExport.setDsjdhrqz(String.valueOf(rowList.size()<94 ?"":rowList.get(93)));
                        stockInfoItemExport.setDsijdqrqz(String.valueOf(rowList.size()<95 ?"":rowList.get(94)));
                        stockInfoItemExport.setDsijdhrqz(String.valueOf(rowList.size()<96 ?"":rowList.get(95)));
                        stockInfoItemExport.setQrq(String.valueOf(rowList.size()<97 ?"":rowList.get(96)));
                        stockInfoItemExport.setHrq(String.valueOf(rowList.size()<98 ?"":rowList.get(97)));
                        stockInfoItemExports.add(stockInfoItemExport);
                    }
                }
            }
        };
        return r;
    }

    private RowHandler createRowHandler2(String type) {
        if ("1".equals(type)) {
            RowHandler r = new RowHandler() {
                @Override
                public void handle(int sheetIndex, int rowIndex, List<Object> rowList) {
                    if (rowList.size() > 0) {
                        if (rowIndex != 0 && rowIndex != 1 && rowList.get(0) != null && !"".equals(rowList.get(0) + "")) {
                            StockinfoTableExport stockInfoItemExport = new StockinfoTableExport();
                            stockInfoItemExport.setId(UUID.randomUUID().toString());
                            stockInfoItemExport.setDdgpdm(rowList.get(0) + "");
                            stockInfoItemExport.setMc(rowList.get(1) + "");
                            stockInfoItemExport.setGdzjpj(rowList.get(2) + "");
                            stockInfoItemExport.setDrbh(rowList.get(3) + "");
                            stockInfoItemExport.setWrbh(rowList.get(4) + "");
                            stockInfoItemExport.setEsrbh(rowList.get(5) + "");
                            stockInfoItemExport.setBzzf(rowList.get(6) + "");
                            stockInfoItemExport.setLx(rowList.get(7) + "");
                            stockinfoTableExports.add(stockInfoItemExport);
                        }
                    }
                }
            };
            return r;
        }
        if ("2".equals(type)) {
            RowHandler r1 = new RowHandler() {
                @Override
                public void handle(int sheetIndex, int rowIndex, List<Object> rowList) {
                    if (rowList.size() > 0) {
                        if (rowIndex != 0 && rowIndex != 1 && rowList.get(0) != null && !"".equals(rowList.get(0) + "")) {
                            StockinfoInstitutionsExport stockInfoItemExport = new StockinfoInstitutionsExport();
                            stockInfoItemExport.setId(UUID.randomUUID().toString());
                            stockInfoItemExport.setDdgpdm(rowList.get(0) + "");
                            stockInfoItemExport.setMc(rowList.get(1) + "");
                            stockInfoItemExport.setLtpj(rowList.get(2) + "");
                            stockInfoItemExport.setBsz(rowList.get(3) + "");
                            stockInfoItemExport.setQqjgcgsl(rowList.get(4) + "");
                            stockInfoItemExport.setBsqbh(rowList.get(5) + "");
                            stockInfoItemExport.setZzjds(rowList.get(6) + "");
                            stockInfoItemExport.setLx(rowList.get(7) + "");
                            stockinfoInstitutionsExports.add(stockInfoItemExport);
                        }
                    }
                }
            };
            return r1;
        }
        if ("3".equals(type)) {
            RowHandler r2 = new RowHandler() {
                @Override
                public void handle(int sheetIndex, int rowIndex, List<Object> rowList) {
                    if (rowList.size() > 0) {
                        if (rowIndex != 0 && rowIndex != 1 && rowList.get(0) != null && !"".equals(rowList.get(0) + "")) {
                            StockinfoIndustryExport stockInfoItemExport = new StockinfoIndustryExport();
                            stockInfoItemExport.setId(UUID.randomUUID().toString());
                            stockInfoItemExport.setDdgpdm(rowList.get(0) + "");
                            stockInfoItemExport.setMc(rowList.get(1) + "");
                            stockInfoItemExport.setGn(rowList.get(2) + "");
                            stockInfoItemExport.setPh(rowList.get(3) + "");
                            stockInfoItemExport.setLtpj(rowList.get(4) + "");
                            stockInfoItemExport.setLtsz(rowList.get(5) + "");
                            stockInfoItemExport.setCcjgsl(rowList.get(6) + "");
                            stockInfoItemExport.setLx(rowList.get(7).toString().substring(0, rowList.get(7).toString().length() - 2));
                            stockinfoIndustryExports.add(stockInfoItemExport);
                        }
                    }
                }
            };
            return r2;
        }
        return null;
    }
}
