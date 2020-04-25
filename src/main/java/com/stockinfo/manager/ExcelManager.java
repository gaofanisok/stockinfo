package com.stockinfo.manager;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import com.stockinfo.dao.CommunalDao;
import com.stockinfo.excelentity.StockinfoIndustryExport;
import com.stockinfo.excelentity.StockinfoInstitutionsExport;
import com.stockinfo.excelentity.StockinfoTableExport;
import com.stockinfo.util.ExcelUtil;
import com.stockinfo.util.StringUtil;
import com.stockinfo.util.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @Auther: Administrator
 * @Date: 2020/4/24 0024 14 53
 * @Description:
 */
@Service
@Slf4j
public class ExcelManager {
    @Autowired
    CommunalDao communalDao;

    public String importExcel(MultipartFile file, String type) {
        ImportParams importParams = new ImportParams();
        // 表头在第几行
        importParams.setTitleRows(1);
        try {
            if (StringUtil.isNotEmpty(type)) {
                switch (type) {
                    case "1":
                        String sql="insert into stockinfo_table values";
                        ExcelImportResult<StockinfoTableExport> result = ExcelImportUtil.importExcelMore(file.getInputStream(), StockinfoTableExport.class, importParams);
                        List<StockinfoTableExport> stockinfoTableExports = result.getList();
                        if (stockinfoTableExports.size()<0){
                            return "";
                        }
                        int i=0;
                        for (StockinfoTableExport demoExcel : stockinfoTableExports) {
                            i++;
                            sql+="('"+ UUID.randomUUID().toString() +"','"+demoExcel.getMc()+"','"+demoExcel.getGdzjpj()+"','"+demoExcel.getDrbh()+"','"+demoExcel.getWrbh()+"','"+demoExcel.getEsrbh()+"','"+demoExcel.getBzzf()+"','"+demoExcel.getLx()+"','"+ Util.newdata()+"')";
                            if (i<stockinfoTableExports.size()){
                                sql+=",";
                            }
                        }
                        communalDao.execute(sql);
                        break;
                    case "2":
                        String sql2="insert into stockinfo_industrytable values";
                        ExcelImportResult<StockinfoIndustryExport> result2 = ExcelImportUtil.importExcelMore(file.getInputStream(), StockinfoIndustryExport.class, importParams);
                        List<StockinfoIndustryExport> stockinfoIndustryExportList = result2.getList();
                        int i1=0;
                        if (stockinfoIndustryExportList.size()<0){
                            return "";
                        }
                        for (StockinfoIndustryExport demoExcel : stockinfoIndustryExportList) {
                            i1++;
                            sql2+="('"+ UUID.randomUUID().toString() +"','"+demoExcel.getMc()+"','"+demoExcel.getGn()+"','"+demoExcel.getPh()+"','"+demoExcel.getLtpj()+"','"+demoExcel.getLtsz()+"','"+demoExcel.getCcjgsl()+"','"+demoExcel.getLx()+"','"+ Util.newdata()+"')";
                            if (i1<stockinfoIndustryExportList.size()){
                                sql2+=",";
                            }
                        }
                        communalDao.execute(sql2);
                        break;
                    case "3":
                        String sql3="insert into stockinfo_institutionstable values";
                        ExcelImportResult<StockinfoInstitutionsExport> result3 = ExcelImportUtil.importExcelMore(file.getInputStream(), StockinfoIndustryExport.class, importParams);
                        List<StockinfoInstitutionsExport> stockinfoInstitutionsExportList = result3.getList();
                        int i2=0;
                        if (stockinfoInstitutionsExportList.size()<0){
                            return "";
                        }
                        for (StockinfoInstitutionsExport demoExcel : stockinfoInstitutionsExportList) {
                            i2++;
                            sql3+="('"+ UUID.randomUUID().toString() +"','"+demoExcel.getMc()+"','"+demoExcel.getLtpj()+"','"+demoExcel.getBsz()+"','"+demoExcel.getQqjgcgsl()+"','"+demoExcel.getBsqbh()+"','"+demoExcel.getZzjds()+"','"+demoExcel.getLx()+"','"+ Util.newdata()+"')";
                            if (i2<stockinfoInstitutionsExportList.size()){
                                sql3+=",";
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
    /**
     *<pre>
     * Description  : 导出模板  <br/>
     * ChangeLog    : 1. 创建 (2020/4/20 0020 15:03 [gaofan]);
     * @author gaofan
     * @date 2020/4/20 0020 15:03
     *</pre>
     */
    public void export(HttpServletResponse response, String type) {
        //导出操作
        if (StringUtil.isNotEmpty(type)) {
            switch (type) {
                case "1":
                    List<StockinfoTableExport> stockinfoTableExportList = new ArrayList<>();
                    ExcelUtil.exportExcel(stockinfoTableExportList, "富赢资金", "富赢资金", StockinfoTableExport.class, "富赢资金信息表"  + ".xls", response);
                    break;
                case "2":
                    List<StockinfoIndustryExport> stockinfoIndustryExportList = new ArrayList<>();
                    ExcelUtil.exportExcel(stockinfoIndustryExportList, "行业top", "行业top", StockinfoIndustryExport.class, "行业top信息表"  + ".xls", response);
                    break;
                case "3":
                    List<StockinfoInstitutionsExport> stockinfoInstitutionsExportList = new ArrayList<>();
                    ExcelUtil.exportExcel(stockinfoInstitutionsExportList, "机构评级", "机构评级", StockinfoInstitutionsExport.class, "机构评级信息表"  + ".xls", response);
                    break;
                default:
                    break;
            }
        }
    }
}
