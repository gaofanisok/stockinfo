package com.stockinfo.controller;

import com.stockinfo.manager.UserManager;
import com.stockinfo.util.CommonUtil;
import com.stockinfo.util.RequestUtil;
import com.stockinfo.util.StringUtil;
import com.stockinfo.util.Util;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: Administrator
 * @Date: 2020/4/13 0013 16 40
 * @Description:
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private static Log logger = LogFactory.getLog(UserController.class);
    @Resource
    UserManager userManager;



    /**
     *<pre>
     * Description  : 查询所有后台用户  <br/>
     * ChangeLog    : 1. 创建 (2020/4/20 0020 17:29 [gaofan]);
     * @author gaofan
     * @date 2020/4/20 0020 17:29
     *</pre>          
    */
    @RequestMapping(value = "showUser", method = RequestMethod.POST)
    public String selectUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int pageIndex=RequestUtil.getInt(request,"pageIndex",1);
            int pageSize=RequestUtil.getInt(request,"pageSize",5);
            String user=userManager.seleuser(pageIndex,pageSize);
            if (StringUtil.isNotEmpty(user)) {
                return  CommonUtil.toReturnJsonMsg(0, "成功",user);
            } else {
                return CommonUtil.toReturnJsonMsg(1, "数据错误：获取数据失败！");
            }
        } catch (Exception e) {
            logger.error(e);
            e.printStackTrace();
        }
        return CommonUtil.toReturnJsonMsg(-1, "系统繁忙，请重试");

    }
    @RequestMapping(value = "getUserByid", method = RequestMethod.POST)
    public String getUserByid(HttpServletRequest request){
        try {
             String id=RequestUtil.getString(request,"id");
             String user=userManager.getUserByid(id);
             if (StringUtil.isNotEmpty(user)){
                   return CommonUtil.toReturnJsonMsg(0,"查询成功",user);
              }else {
                  return CommonUtil.toReturnJsonMsg(1,"查询失败");
             }
        } catch (Exception e) {
            logger.error(e);
            e.printStackTrace();
        }
        return CommonUtil.toReturnJsonMsg(-1,"系统繁忙，请重试");

    }



}