package com.stockinfo.controller;

import com.stockinfo.manager.ExcelManager;
import com.stockinfo.util.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: Administrator
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

        return excelManager.importExcel(file,type);
    }
    @GetMapping("/singles")
    public void export(HttpServletResponse response,HttpServletRequest request) {
        String type=RequestUtil.getString(request,"type");
        excelManager.export(response,type);
    }
}
