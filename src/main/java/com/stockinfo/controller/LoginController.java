package com.stockinfo.controller;


import com.stockinfo.manager.LoginManager;
import com.stockinfo.util.CommonUtil;
import com.stockinfo.util.RequestUtil;
import com.stockinfo.util.StringUtil;
import com.stockinfo.util.Util;
import com.sun.corba.se.spi.ior.ObjectKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * @Auther: gaofan
 * @Date: 2020/4/15 0015 15 29
 * @Description:登陆控制层
 */
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    LoginManager loginManager;
    /**
     *<pre>
     * Description  : 登陆  <br/>
     * ChangeLog    : 1. 创建 (2020/4/15 0015 15:29 [gaofan]);
     * @author gaofan
     * @date 2020/4/15 0015 15:29
     *</pre>
    */
    @RequestMapping(value = "/userLogin",method = RequestMethod.POST)
    public String login(HttpServletRequest request, HttpServletResponse response){
        String username=RequestUtil.getString(request,"userName");
        String password=RequestUtil.getString(request,"password");

        Map<String,Object> isSuccess=loginManager.UserIsExist(username,password);
        if (Boolean.valueOf(isSuccess.get("msg")+"")){
            try {
                String token=Util.sign(username,isSuccess.get("userid")+"");
                if (token!=null){
                    return  CommonUtil.toReturnJsonMsg(0, "成功",token);
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return  CommonUtil.toReturnJsonMsg(1, "登陆失败");
    }
    @RequestMapping(value = "/createUser",method = RequestMethod.POST)
    public String createUser(HttpServletRequest request, HttpServletResponse response){
        String datajson=RequestUtil.getString(request,"datajson");

        String id =loginManager.CreateUser(datajson);
        if (StringUtil.isNotEmpty(id)){

         return  CommonUtil.toReturnJsonMsg(0, "注册成功",id);


        }
        return  CommonUtil.toReturnJsonMsg(1, "注册失败");
    }
}
