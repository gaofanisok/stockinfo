package com.stockinfo.manager;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.stockinfo.dao.CommunalDao;
import com.stockinfo.util.PageUtil;
import com.stockinfo.util.StringUtil;
import com.stockinfo.util.Util;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @Auther: gaofan
 * @Date: 2020/4/21 0021 14 15
 * @Description:后台信息页面
 */
@Service
public class StockinfoManager {
    @Autowired
    CommunalDao communalDao;
    /**
     *<pre>
     * Description  : 赢富资列表  <br/>
     * ChangeLog    : 1. 创建 (2020/4/21 0021 14:31 [gaofan]);
     * @author gaofan
     * @param mc 名称
     * @param lx 类型（0：全部，1：个人）
     * @return java.lang.String
     *</pre>
     */
    public String stockinfo(int pageIndex,int pageSize,String mc,String lx){
        PageHelper.startPage(pageIndex,pageSize);
        String sql="select * from stockinfo_table  where lx='"+lx+"'";
        JSONObject jsRestu=new JSONObject();
        JSONArray ja=new JSONArray();
        if (StringUtil.isNotEmpty(mc)){
            sql+=" and mc like '%"+mc+"%' ";
        }
        sql+=" order by creationtime desc";
        PageInfo<Map<String,Object>> pageList=PageUtil.PageQuery(communalDao.queryPage(sql));
        if (pageList.getSize()>0){
            List<Map<String,Object>> list=pageList.getList();
            for (Map map:list){
                JSONObject jo=new JSONObject();
                jo.put("id",map.getOrDefault("id_",""));
                jo.put("mc",map.getOrDefault("mc",""));
                jo.put("gdzjpj",map.getOrDefault("gdzjpj",""));
                jo.put("drbh",map.getOrDefault("drbh",""));
                jo.put("wrbh",map.getOrDefault("wrbh",""));
                jo.put("esrbh",map.getOrDefault("esrbh",""));
                jo.put("bzzf",map.getOrDefault("bzzf",""));
                ja.add(jo);
            }
            jsRestu.put("data",ja);
        }
        jsRestu.put("total",pageList.getTotal());
        return jsRestu.toString();
    }
    /**
     *<pre>
     * Description  : 赢富资详细  <br/>
     * ChangeLog    : 1. 创建 (2020/4/21 0021 14:47 [gaofan]);
     * @author gaofan
     * @date 2020/4/21 0021 14:47
     *</pre>
     */
    public String getStockById(String id){
        String sql="select * from stockinfo_table  where id_='"+id+"'";
        JSONObject jsRestu=new JSONObject();
        List<Map<String,Object>> stockList=communalDao.query(sql);
        if (stockList.size()>0){
            Map<String,Object> map=stockList.get(0);
            jsRestu.put("id",map.getOrDefault("id_",""));
            jsRestu.put("mc",map.getOrDefault("mc",""));
            jsRestu.put("gdzjpj",map.getOrDefault("gdzjpj",""));
            jsRestu.put("drbh",map.getOrDefault("drbh",""));
            jsRestu.put("wrbh",map.getOrDefault("wrbh",""));
            jsRestu.put("esrbh",map.getOrDefault("esrbh",""));
            jsRestu.put("bzzf",map.getOrDefault("bzzf",""));
        }
        return jsRestu.toString();

    }

    /**
     *<pre>
     * Description  : 机构评级列表  <br/>
     * ChangeLog    : 1. 创建 (2020/4/21 0021 14:31 [gaofan]);
     * @author gaofan
     * @param mc 名称
     * @param lx 类型（0：全部，1：个人）
     * @return java.lang.String
     *</pre>
     */
    public String institutionsInfo(int pageIndex,int pageSize,String mc,String lx){
        PageHelper.startPage(pageIndex,pageSize);
        String sql="select * from stockinfo_institutionstable  where lx='"+lx+"'";
        JSONObject jsRestu=new JSONObject();
        JSONArray ja=new JSONArray();
        if (StringUtil.isNotEmpty(mc)){
            sql+=" and mc like '%"+mc+"%' ";
        }
        sql+=" order by creationtime desc";
        PageInfo<Map<String,Object>> pageList=PageUtil.PageQuery(communalDao.queryPage(sql));
        if (pageList.getSize()>0){
            List<Map<String,Object>> list=pageList.getList();
            for (Map map:list){
                JSONObject jo=new JSONObject();
                jo.put("id",map.getOrDefault("id_",""));
                jo.put("mc",map.getOrDefault("mc",""));
                jo.put("ltpj",map.getOrDefault("ltpj",""));
                jo.put("bsz",map.getOrDefault("bsz",""));
                jo.put("qqjgcgsl",map.getOrDefault("qqjgcgsl",""));
                jo.put("bsqbh",map.getOrDefault("bsqbh",""));
                jo.put("zzjds",map.getOrDefault("zzjds",""));
                ja.add(jo);
            }
            jsRestu.put("data",ja);
        }
        jsRestu.put("total",pageList.getTotal());
        return jsRestu.toString();
    }
    /**
     *<pre>
     * Description  : 机构评级详细  <br/>
     * ChangeLog    : 1. 创建 (2020/4/21 0021 14:47 [gaofan]);
     * @author gaofan
     * @date 2020/4/21 0021 14:47
     *</pre>
     */
    public String getInstitutionById(String id){
        String sql="select * from stockinfo_institutionstable  where id_='"+id+"'";
        JSONObject jsRestu=new JSONObject();
        List<Map<String,Object>> stockList=communalDao.query(sql);
        if (stockList.size()>0){
            Map<String,Object> map=stockList.get(0);
            jsRestu.put("id",map.getOrDefault("id_",""));
            jsRestu.put("mc",map.getOrDefault("mc",""));
            jsRestu.put("ltpj",map.getOrDefault("ltpj",""));
            jsRestu.put("bsz",map.getOrDefault("bsz",""));
            jsRestu.put("qqjgcgsl",map.getOrDefault("qqjgcgsl",""));
            jsRestu.put("bsqbh",map.getOrDefault("bsqbh",""));
            jsRestu.put("zzjds",map.getOrDefault("zzjds",""));
        }
        return jsRestu.toString();
    }

    /**
     *<pre>
     * Description  : 行业top列表  <br/>
     * ChangeLog    : 1. 创建 (2020/4/21 0021 14:31 [gaofan]);
     * @author gaofan
     * @param mc 名称
     * @param lx 类型（0：全部，1：个人）
     * @return java.lang.String
     *</pre>
     */
    public String industryinfo(int pageIndex,int pageSize,String mc,String gn){
        PageHelper.startPage(pageIndex,pageSize);
        String sql="select * from stockinfo_industrytable where lx='0'";
        JSONObject jsRestu=new JSONObject();
        JSONArray ja=new JSONArray();
        if (StringUtil.isNotEmpty(mc)){
            sql+=" and mc like '%"+mc+"%' ";
        }
        if (StringUtil.isNotEmpty(gn)){
            sql+=" and gn like '%"+gn+"%' ";
        }
        sql+=" order by ph ";
        PageInfo<Map<String,Object>> pageList=PageUtil.PageQuery(communalDao.queryPage(sql));
        if (pageList.getSize()>0){
            List<Map<String,Object>> list=pageList.getList();
            for (Map map:list){
                JSONObject jo=new JSONObject();
                jo.put("id",map.getOrDefault("id_",""));
                jo.put("mc",map.getOrDefault("mc",""));
                jo.put("gn",map.getOrDefault("gn",""));
                jo.put("ph",map.getOrDefault("ph",""));
                jo.put("ltpj",map.getOrDefault("ltpj",""));
                jo.put("ltsz",map.getOrDefault("ltsz",""));
                jo.put("ccjgsl",map.getOrDefault("ccjgsl",""));
                ja.add(jo);
            }
            jsRestu.put("data",ja);
        }
        jsRestu.put("total",pageList.getTotal());
        return jsRestu.toString();
    }
    /**
     *<pre>
     * Description  : 行业top详细  <br/>
     * ChangeLog    : 1. 创建 (2020/4/21 0021 14:47 [gaofan]);
     * @author gaofan
     * @date 2020/4/21 0021 14:47
     *</pre>
     */
    public String getIndustrytaById(String id){
        String sql="select * from stockinfo_industrytable  where id_='"+id+"'";
        JSONObject jsRestu=new JSONObject();
        List<Map<String,Object>> stockList=communalDao.query(sql);
        if (stockList.size()>0){
            Map<String,Object> map=stockList.get(0);
            jsRestu.put("id",map.getOrDefault("id_",""));
            jsRestu.put("mc",map.getOrDefault("mc",""));
            jsRestu.put("gn",map.getOrDefault("gn",""));
            jsRestu.put("ph",map.getOrDefault("ph",""));
            jsRestu.put("ltpj",map.getOrDefault("ltpj",""));
            jsRestu.put("ltsz",map.getOrDefault("ltsz",""));
            jsRestu.put("ccjgsl",map.getOrDefault("ccjgsl",""));
        }
        return jsRestu.toString();
    }

    /**
     *<pre>
     * Description  :收藏数据 <br/>
     * ChangeLog    : 1. 创建 (2020/4/21 0021 14:47 [gaofan]);
     * @author gaofan
     * @date 2020/4/21 0021 14:47
     *</pre>
     */
    public String collect(String datajson,String userid){
        if (StringUtil.isNotEmpty(datajson)){
            String id= UUID.randomUUID().toString();
            Map<String,Object> datamap = com.alibaba.fastjson.JSONObject.parseObject(datajson);
            String sql="select * from stockinfo_collect where lx='"+datamap.get("lx")+"' and scid='"+datamap.get("scid")+"' and userid='"+userid+"' ";
            if (communalDao.query(sql).size()<=0) {
                String sql2 = "insert into stockinfo_collect value('" + id + "','" + datamap.getOrDefault("lx", "") + "','" + datamap.getOrDefault("scid", "") + "','" +userid+ "','" + Util.newdata() + "')";
                communalDao.execute(sql2);
                return id;
            }
        }
        return "";
    }
}
