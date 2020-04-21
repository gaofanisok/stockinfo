package com.stockinfo.manager;

import com.alibaba.fastjson.JSONObject;
import com.stockinfo.dao.CommunalDao;
import com.stockinfo.util.StringUtil;
import com.stockinfo.util.Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Auther: gaofan
 * @Date: 2020/4/20 0020 13 49
 * @Description:登陆逻辑层
 */
@Service
public class LoginManager {
    @Resource
    CommunalDao communalDao;
    /**
     *<pre>
     * Description  : 验证用户是否存在  <br/>
     * ChangeLog    : 1. 创建 (2020/4/20 0020 14:05 [gaofan]);
     * @author gaofan
     * @date 2020/4/20 0020 14:05
     * @param userName 用户名
     * @param passWord  密码
     * @return java.lang.Boolean
     *</pre>
     */
    public Map<String,Object>  UserIsExist(String userName,String passWord){
        String newPassWord=Util.getSHA256(passWord);
        String sql="select * from stockinfo_userlog where username='"+userName+"' and password='"+newPassWord+"'";
        Map<String,Object> mapReques=new HashMap<>();
        List<Map<String,Object>> list=communalDao.query(sql);
        if (list.size()>0){
            Map<String,Object> map=list.get(0);
            mapReques.put("msg",true);
            mapReques.put("userid",map.get("ID_"));
            return mapReques;
        }
        mapReques.put("msg",false);
        return mapReques;
    }
    /**
     *<pre>
     * Description  : 注册用户  <br/>
     * ChangeLog    : 1. 创建 (2020/4/20 0020 15:03 [gaofan]);
     * @author gaofan
     * @date 2020/4/20 0020 15:03
     *</pre>
     */
    public String CreateUser(String dataJson){
        if (StringUtil.isNotEmpty(dataJson)){
            String id=UUID.randomUUID().toString();
            Map<String,Object> datamap = JSONObject.parseObject(dataJson);
            String sql="select * from stockinfo_userlog where username='"+datamap.get("username")+"' ";
            if (communalDao.query(sql).size()<=0) {
                datamap.put("password", Util.getSHA256(datamap.get("password") + ""));
                String sql2 = "insert into stockinfo_userlog value('" + id + "','" + datamap.getOrDefault("username", "") + "','" + datamap.getOrDefault("password", "") + "','" + datamap.getOrDefault("phone", "") + "','0','" + Util.newdata() + "')";
                communalDao.execute(sql2);
                return id;
            }
        }
        return "";
    }
}
