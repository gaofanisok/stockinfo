package com.stockinfo.manager;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import com.stockinfo.excelentity.StockinfoIndustryExport;
import com.stockinfo.excelentity.StockinfoInstitutionsExport;
import com.stockinfo.excelentity.StockinfoTableExport;
import com.stockinfo.util.ExcelUtil;
import com.stockinfo.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: Administrator
 * @Date: 2020/4/24 0024 14 53
 * @Description:
 */
@Service
@Slf4j
public class ExcelManager {

    public String importExcel(MultipartFile file, String type) {
        ImportParams importParams = new ImportParams();
        // 表头在第几行
        importParams.setTitleRows(1);
        try {
            ExcelImportResult<StockinfoTableExport> result = ExcelImportUtil.importExcelMore(file.getInputStream(), StockinfoTableExport.class, importParams);
            List<StockinfoTableExport> successList = result.getList();
            for (StockinfoTableExport demoExcel : successList) {
                System.out.println(demoExcel);
            }
        } catch (Exception e) {
            log.error("导入失败:{}", e);
            return "导入失败！请检查导入文档的格式是否正确";
        }
        return "导入成功";
    }

    public void export(HttpServletResponse response, String type) {
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
        //导出操作
    }
}
