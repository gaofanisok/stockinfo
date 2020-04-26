package com.stockinfo.manager;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.stockinfo.dao.CommunalDao;
import com.stockinfo.util.PageUtil;
import com.stockinfo.util.StringUtil;
import com.stockinfo.util.Util;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @Auther: gaofan
 * @Date: 2020/4/17 0017 14 17
 * @Description:user逻辑层
 */
@Service
public class UserManager {
    @Resource
    CommunalDao communalDao;

    PageUtil PageUtil=new PageUtil();
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
    public String seleuser(int pageIndex,int pageSize,String phone) throws ParseException {
        PageHelper.startPage(pageIndex,pageSize);
        JSONObject js=new JSONObject();
        JSONArray ja=new JSONArray();
        String sql="Select * FROM stockinfo_userprogram  ";
        if (StringUtil.isNotEmpty(phone)){
            sql+=" where user_phone='"+phone+"'";
        }
        sql+=" order by creationtime desc";
        PageInfo<Map<String,Object>> pageList=PageUtil.PageQuery(communalDao.queryPage(sql));
        if (pageList.getSize()>0){
            List<Map<String,Object>> list=pageList.getList();
            for (int i=0;i<list.size();i++){
                JSONObject js2=new JSONObject();
                Map<String,Object> maps=list.get(i);
                js2.put("user_id",maps.getOrDefault("user_id",""));
                js2.put("user_name",maps.getOrDefault("user_name",""));
                js2.put("user_phone",maps.getOrDefault("user_phone","")+"");
                js2.put("user_wxid",maps.getOrDefault("user_phone","")+"");
                js2.put("user_sex",maps.getOrDefault("user_sex","")+"");
                js2.put("starttime",Util.dateToString(maps.getOrDefault("starttime","")+"","yyyy-MM-dd"));
                js2.put("endtime",Util.dateToString(maps.getOrDefault("endtime","")+"","yyyy-MM-dd"));
                js2.put("user_type",maps.getOrDefault("user_type","")+"");
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
    /**
     *<pre>
     * Description  : 小程序用户添加  <br/>
     * ChangeLog    : 1. 创建 (2020/4/20 0020 17:18 [gaofan]);
     * @author gaofan
     * @date 2020/4/20 0020 17:18
     *</pre>
     */
    public String saveUser(String datajson){
        if (StringUtil.isNotEmpty(datajson)){
            String id= UUID.randomUUID().toString();
            Map<String,Object> datamap = com.alibaba.fastjson.JSONObject.parseObject(datajson);
            String sql="select * from stockinfo_userprogram where user_wxid='"+datamap.get("user_wxid")+"'";
            if (communalDao.query(sql).size()<=0){
                String sql2 = "insert into stockinfo_userprogram(user_id,user_name,user_phone,user_wxid,user_tx,user_sex,creationtime,user_type) value('" + id + "','" + datamap.getOrDefault("user_name", "") + "','" + datamap.getOrDefault("user_phone", "") + "','" + datamap.getOrDefault("user_wxid", "") + "','"+datamap.getOrDefault("user_tx","")+"','"+datamap.getOrDefault("user_sex","")+"','" + Util.newdata() + "','0')";
                communalDao.execute(sql2);
                return id;
            }

        }
        return "";
    }
}
