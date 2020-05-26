package com.stockinfo.controller;

import com.stockinfo.manager.ExcelManager;
import com.stockinfo.util.CommonUtil;
import com.stockinfo.util.RequestUtil;
import com.stockinfo.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: gaofan
 * @Date: 2020/4/24 0024 15 03
 * @Description:
 */
@RestController
@RequestMapping("/excel")
@Slf4j
public class ExcelController {
    @Autowired
    private ExcelManager excelManager;

    @PostMapping("/single")
    public String importExcel(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        String type=RequestUtil.getString(request,"type");
        String res=excelManager.importExcel(file,type);
        if (StringUtil.isNotEmpty(res)){
            return  CommonUtil.toReturnJsonMsg(0, "成功",res);
        }
        return  CommonUtil.toReturnJsonMsg(1, "失败",res);
    }
    @GetMapping("/singles")
    public void export(HttpServletResponse response,HttpServletRequest request) {
        String type=RequestUtil.getString(request,"type");
        excelManager.export(response,type);
    }

    @PostMapping("/importItemExcel")
    public String importItemExcel(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception {
        String type=RequestUtil.getString(request,"type");
        String state=RequestUtil.getString(request,"state");
        String res=excelManager.importItemExcel(file,type,state);
        if (StringUtil.isNotEmpty(res)){
            return  CommonUtil.toReturnJsonMsg(0, "成功",res);
        }
        return  CommonUtil.toReturnJsonMsg(1, "失败",res);
    }
}
