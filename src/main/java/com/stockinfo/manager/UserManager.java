package com.stockinfo.manager;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.stockinfo.dao.CommunalDao;
import com.stockinfo.util.PageUtil;
import com.stockinfo.util.Util;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Auther: gaofan
 * @Date: 2020/4/17 0017 14 17
 * @Description:user逻辑层
 */
@Service
public class UserManager {
    @Resource
    CommunalDao communalDao;
    /**
     *<pre>
     * Description  : 查询  <br/>
     * ChangeLog    : 1. 创建 (2019/12/13 0013 10:38 [gaofan]);
     * @author gaofan
     * @date 2019/12/13 0013 10:38
     * @param pkid 用户ID
     * @return java.lang.String
     *</pre>
     */
    public String seleuser(int pageIndex,int pageSize) throws ParseException {
        PageHelper.startPage(pageIndex,pageSize);
        JSONObject js=new JSONObject();
        JSONArray ja=new JSONArray();
        String sql="Select * FROM stockinfo_userlog ";
        PageInfo<Map<String,Object>> pageList=PageUtil.PageQuery(pageIndex,pageSize,communalDao.queryPage(sql));
        if (pageList.getSize()>0){
            List<Map<String,Object>> list=pageList.getList();
            for (int i=0;i<list.size();i++){
                JSONObject js2=new JSONObject();
                Map<String,Object> maps=list.get(i);
                js2.put("id",maps.getOrDefault("ID_",""));
                js2.put("username",maps.getOrDefault("username",""));
                js2.put("phone",maps.getOrDefault("phone","")+"");
                js2.put("creationtime",Util.dateToString(maps.getOrDefault("creationtime","")+"","yyyy-MM-dd HH:mm:ss"));
                ja.add(js2);
            }

            js.put("data",ja);
            js.put("total",pageList.getTotal());
            return js.toString();
        }

        return js.toString();
    }
    /**
     *<pre>
     * Description  : 查询单个用户  <br/>
     * ChangeLog    : 1. 创建 (2020/4/20 0020 17:18 [gaofan]);
     * @author gaofan
     * @date 2020/4/20 0020 17:18
     *</pre>          
    */
    public String  getUserByid(String id){
        String sql="select * from stockinfo_userlog where ID_='"+id+"'";
        List<Map<String,Object>> userList=communalDao.query(sql);
        if (userList.size()>0){
            JSONObject jo=new JSONObject();
            Map<String,Object> map=userList.get(0);
            jo.put("ID_",map.getOrDefault("ID_",""));
            jo.put("username",map.getOrDefault("username",""));
            jo.put("phone",map.getOrDefault("phone",""));
            return jo.toString();
        }
        return "";
    }
}
