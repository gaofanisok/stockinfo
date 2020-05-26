package com.stockinfo.controller;

import com.stockinfo.manager.UserManager;
import com.stockinfo.util.CommonUtil;
import com.stockinfo.util.RequestUtil;
import com.stockinfo.util.StringUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

/**
 * @Auther: gaofan
 * @Date: 2020/4/13 0013 16 40
 * @Description:用户层
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
            String name=RequestUtil.getString(request,"name");
            String user=userManager.seleuser(pageIndex,pageSize,name);
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
    /**
     *<pre>
     * Description  : 查询用户详细  <br/>
     * ChangeLog    : 1. 创建 (2020/4/20 0020 17:29 [gaofan]);
     * @author gaofan
     * @date 2020/4/20 0020 17:29
     *</pre>
     */
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

//    /**
//     *<pre>
//     * Description  : 保存小程序用户  <br/>
//     * ChangeLog    : 1. 创建 (2020/4/20 0020 17:29 [gaofan]);
//     * @author gaofan
//     * @date 2020/4/20 0020 17:29
//     *</pre>
//     */
//    @PostMapping(value = "saveUser")
//    public String saveUser(HttpServletRequest request){
//        try {
//            String datajson=RequestUtil.getString(request,"datajson");
//            String user=userManager.saveUser(datajson);
//            if (StringUtil.isNotEmpty(user)){
//                return CommonUtil.toReturnJsonMsg(0,"注册成功",user);
//            }else {
//                return CommonUtil.toReturnJsonMsg(1,"注册失败");
//            }
//        } catch (Exception e) {
//            logger.error(e);
//            e.printStackTrace();
//        }
//        return CommonUtil.toReturnJsonMsg(-1,"系统繁忙，请重试");
//
//    }
    @PostMapping(value = "getUnionId")
    public Map getUnionId(HttpServletRequest request) {
        String encryptedData=RequestUtil.getString(request,"encryptedData");
        String iv=RequestUtil.getString(request,"iv");
        String code=RequestUtil.getString(request,"code");
        return userManager.getSessionKeyOrOpenId(code, encryptedData, iv);
    }

    /**
     * 更新权限
     * @param request
     * @return
     * @throws ParseException
     */
    @PostMapping(value = "updateUser")
    public String updateUser(HttpServletRequest request) throws ParseException {
        String id=RequestUtil.getString(request,"id");
        String endTime=RequestUtil.getString(request,"endtime");
        String type=RequestUtil.getString(request,"type");
        String user=userManager.updateUser(endTime,id,type);
        try {
            if (StringUtil.isNotEmpty(user)) {
                return CommonUtil.toReturnJsonMsg(0, "更新成功");
            } else {
                return CommonUtil.toReturnJsonMsg(1, "更新失败");
            }
        } catch (Exception e) {
            logger.error(e);
            e.printStackTrace();
        }

        return CommonUtil.toReturnJsonMsg(-1,"系统繁忙，请重试");
    }





}
