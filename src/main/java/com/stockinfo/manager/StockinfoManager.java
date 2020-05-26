package com.stockinfo.manager;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.stockinfo.dao.CommunalDao;
import com.stockinfo.excelentity.Dz;
import com.stockinfo.service.IDzService;
import com.stockinfo.util.PageUtil;
import com.stockinfo.util.StringUtil;
import com.stockinfo.util.Util;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.beans.Transient;
import java.text.ParseException;
import java.util.*;

/**
 * @Auther: gaofan
 * @Date: 2020/4/21 0021 14 15
 * @Description:后台信息页面
 */
@Service
public class StockinfoManager {
    @Autowired
    CommunalDao communalDao;
    @Resource
    UserManager userManager;
    @Resource
    IDzService dzService;

    /**
     * <pre>
     * Description  : 赢富资列表  <br/>
     * ChangeLog    : 1. 创建 (2020/4/21 0021 14:31 [gaofan]);
     * @author gaofan
     * @param mc 名称
     * @param lx 类型（0：全部，1：个人）
     * @return java.lang.String
     * </pre>
     */
    public String stockinfo(int pageIndex, int pageSize, String mc, String lx) {
        PageHelper.startPage(pageIndex, pageSize);
        String sql = "select * from stockinfo_table  where lx !='3' ";
        JSONObject jsRestu = new JSONObject();
        JSONArray ja = new JSONArray();
        if (StringUtil.isNotEmpty(mc)) {
            sql += " and mc like '%" + mc + "%' ";
        }
        sql += " order by creationtime desc";
        PageInfo<Map<String, Object>> pageList = PageUtil.PageQuery(communalDao.queryPage(sql));
        if (pageList.getSize() > 0) {
            List<Map<String, Object>> list = pageList.getList();
            for (Map map : list) {
                JSONObject jo = new JSONObject();
                jo.put("id", map.getOrDefault("id_", ""));
                jo.put("mc", map.getOrDefault("mc", ""));
                jo.put("gdzjpj", map.getOrDefault("gdzjpj", ""));
                jo.put("drbh", map.getOrDefault("drbh", ""));
                jo.put("wrbh", map.getOrDefault("wrbh", ""));
                jo.put("esrbh", map.getOrDefault("esrbh", ""));
                jo.put("bzzf", map.getOrDefault("bzzf", ""));
                ja.add(jo);
            }

        }
        jsRestu.put("data", ja);
        jsRestu.put("total", pageList.getTotal());
        return jsRestu.toString();
    }

    /**
     * <pre>
     * Description  : 赢富资详细  <br/>
     * ChangeLog    : 1. 创建 (2020/4/21 0021 14:47 [gaofan]);
     * @author gaofan
     * @date 2020/4/21 0021 14:47
     * </pre>
     */
    public String getStockById(String id) {
        String sql = "select * from stockinfo_table  where id_='" + id + "'";
        JSONObject jsRestu = new JSONObject();
        List<Map<String, Object>> stockList = communalDao.query(sql);
        if (stockList.size() > 0) {
            Map<String, Object> map = stockList.get(0);
            jsRestu.put("id", map.getOrDefault("id_", ""));
            jsRestu.put("mc", map.getOrDefault("mc", ""));
            jsRestu.put("gdzjpj", map.getOrDefault("gdzjpj", ""));
            jsRestu.put("drbh", map.getOrDefault("drbh", ""));
            jsRestu.put("wrbh", map.getOrDefault("wrbh", ""));
            jsRestu.put("esrbh", map.getOrDefault("esrbh", ""));
            jsRestu.put("bzzf", map.getOrDefault("bzzf", ""));
        }
        return jsRestu.toString();

    }

    /**
     * <pre>
     * Description  : 机构评级列表  <br/>
     * ChangeLog    : 1. 创建 (2020/4/21 0021 14:31 [gaofan]);
     * @author gaofan
     * @param mc 名称
     * @param lx 类型（0：全部，1：个人）
     * @return java.lang.String
     * </pre>
     */
    public String institutionsInfo(int pageIndex, int pageSize, String mc, String lx) {
        PageHelper.startPage(pageIndex, pageSize);
        String sql = "select * from stockinfo_institutionstable  where lx !='3'";
        JSONObject jsRestu = new JSONObject();
        JSONArray ja = new JSONArray();
        if (StringUtil.isNotEmpty(mc)) {
            sql += " and mc like '%" + mc + "%' ";
        }
        sql += " order by creationtime desc";
        PageInfo<Map<String, Object>> pageList = PageUtil.PageQuery(communalDao.queryPage(sql));
        if (pageList.getSize() > 0) {
            List<Map<String, Object>> list = pageList.getList();
            for (Map map : list) {
                JSONObject jo = new JSONObject();
                jo.put("id", map.getOrDefault("id_", ""));
                jo.put("mc", map.getOrDefault("mc", ""));
                jo.put("ltpj", map.getOrDefault("ltpj", ""));
                jo.put("bsz", map.getOrDefault("bsz", ""));
                jo.put("qqjgcgsl", map.getOrDefault("qqjgcgsl", ""));
                jo.put("bsqbh", map.getOrDefault("bsqbh", ""));
                jo.put("zzjds", map.getOrDefault("zzjds", ""));
                ja.add(jo);
            }
        }
        jsRestu.put("data", ja);
        jsRestu.put("total", pageList.getTotal());
        return jsRestu.toString();
    }

    /**
     * <pre>
     * Description  : 机构评级详细  <br/>
     * ChangeLog    : 1. 创建 (2020/4/21 0021 14:47 [gaofan]);
     * @author gaofan
     * @date 2020/4/21 0021 14:47
     * </pre>
     */
    public String getInstitutionById(String id) {
        String sql = "select * from stockinfo_institutionstable  where id_='" + id + "'";
        JSONObject jsRestu = new JSONObject();
        List<Map<String, Object>> stockList = communalDao.query(sql);
        if (stockList.size() > 0) {
            Map<String, Object> map = stockList.get(0);
            jsRestu.put("id", map.getOrDefault("id_", ""));
            jsRestu.put("mc", map.getOrDefault("mc", ""));
            jsRestu.put("ltpj", map.getOrDefault("ltpj", ""));
            jsRestu.put("bsz", map.getOrDefault("bsz", ""));
            jsRestu.put("qqjgcgsl", map.getOrDefault("qqjgcgsl", ""));
            jsRestu.put("bsqbh", map.getOrDefault("bsqbh", ""));
            jsRestu.put("zzjds", map.getOrDefault("zzjds", ""));
        }
        return jsRestu.toString();
    }

    /**
     * <pre>
     * Description  : 行业top列表  <br/>
     * ChangeLog    : 1. 创建 (2020/4/21 0021 14:31 [gaofan]);
     * @author gaofan
     * @param mc 名称
     * @param lx 类型（0：全部，1：个人）
     * @return java.lang.String
     * </pre>
     */
    public String industryinfo(int pageIndex, int pageSize, String mc, String gn) {
        PageHelper.startPage(pageIndex, pageSize);
        String sql = "select * from stockinfo_industrytable where lx !='3' ";
        JSONObject jsRestu = new JSONObject();
        JSONArray ja = new JSONArray();
        if (StringUtil.isNotEmpty(mc)) {
            sql += " and mc like '%" + mc + "%' ";
        }
        if (StringUtil.isNotEmpty(gn)) {
            sql += " and gn like '%" + gn + "%' ";
        }
        sql += " order by ph ";
        PageInfo<Map<String, Object>> pageList = PageUtil.PageQuery(communalDao.queryPage(sql));
        if (pageList.getSize() > 0) {
            List<Map<String, Object>> list = pageList.getList();
            for (Map map : list) {
                JSONObject jo = new JSONObject();
                jo.put("id", map.getOrDefault("id_", ""));
                jo.put("mc", map.getOrDefault("mc", ""));
                jo.put("gn", map.getOrDefault("gn", ""));
                jo.put("ph", map.getOrDefault("ph", ""));
                jo.put("ltpj", map.getOrDefault("ltpj", ""));
                jo.put("ltsz", map.getOrDefault("ltsz", ""));
                jo.put("ccjgsl", map.getOrDefault("ccjgsl", ""));
                ja.add(jo);
            }
        }
        jsRestu.put("data", ja);
        jsRestu.put("total", pageList.getTotal());
        return jsRestu.toString();
    }

    /**
     * <pre>
     * Description  : 行业top详细  <br/>
     * ChangeLog    : 1. 创建 (2020/4/21 0021 14:47 [gaofan]);
     * @author gaofan
     * @date 2020/4/21 0021 14:47
     * </pre>
     */
    public String getIndustrytaById(String id) {
        String sql = "select * from stockinfo_industrytable  where id_='" + id + "'";
        JSONObject jsRestu = new JSONObject();
        List<Map<String, Object>> stockList = communalDao.query(sql);
        if (stockList.size() > 0) {
            Map<String, Object> map = stockList.get(0);
            jsRestu.put("id", map.getOrDefault("id_", ""));
            jsRestu.put("mc", map.getOrDefault("mc", ""));
            jsRestu.put("gn", map.getOrDefault("gn", ""));
            jsRestu.put("ph", map.getOrDefault("ph", ""));
            jsRestu.put("ltpj", map.getOrDefault("ltpj", ""));
            jsRestu.put("ltsz", map.getOrDefault("ltsz", ""));
            jsRestu.put("ccjgsl", map.getOrDefault("ccjgsl", ""));
        }
        return jsRestu.toString();
    }

    /**
     * <pre>
     * Description  :收藏数据 <br/>
     * ChangeLog    : 1. 创建 (2020/4/21 0021 14:47 [gaofan]);
     * @author gaofan
     * @date 2020/4/21 0021 14:47
     * </pre>
     */
    public String collect(String datajson, String openId) {
        if (StringUtil.isNotEmpty(datajson)) {
            //用户信息
            Map map = userManager.getOpenIdUser(openId);
            if (map.size() <= 0) {
                return "";
            }
            String id = UUID.randomUUID().toString();
            Map<String, Object> datamap = com.alibaba.fastjson.JSONObject.parseObject(datajson);
            String sql = "select * from stockinfo_collect where lx='" + datamap.get("lx") + "' and scid='" + datamap.get("scid") + "' and userid='" + map.getOrDefault("user_id", "") + "' ";
            if (communalDao.query(sql).size() <= 0) {
                String sql2 = "insert into stockinfo_collect value('" + id + "','" + datamap.getOrDefault("lx", "") + "','" + datamap.getOrDefault("scid", "") + "','" + map.getOrDefault("user_id", "") + "','" + Util.newdata() + "')";
                communalDao.execute(sql2);
                return id;
            }
        }
        return "";
    }

    /**
     * <pre>
     * Description  :我的收藏列表 <br/>
     * ChangeLog    : 1. 创建 (2020/4/21 0021 14:47 [gaofan]);
     * @author gaofan
     * @date 2020/4/21 0021 14:47
     * </pre>
     */
    public String myCollectList(String openId, String type, String mc, int pageIndex, int pageSize, String column, String orderBy) {
        if (StringUtil.isNotEmpty(openId) && StringUtil.isNotEmpty(type)) {
            //用户信息
            Map map = userManager.getOpenIdUser(openId);
            if (map.size() <= 0) {
                return "";
            }
            PageHelper.startPage(pageIndex, pageSize);
            JSONObject jo = new JSONObject();
            String sql = "";
            //type:0:stockinfo_table,1:stockinfo_institutionstable
            if ("0".equals(type)) {
                sql = "select b.*,a.lx type from stockinfo_collect as a left join stockinfo_table as b on a.scid=b.id_ where a.userid='" + map.getOrDefault("user_id", "") + "' and a.lx='" + type + "'";
            }
            if ("1".equals(type)) {
                sql = "select b.*,a.lx type from stockinfo_collect as a left join stockinfo_institutionstable as b on a.scid=b.id_ where a.userid='" + map.getOrDefault("user_id", "") + "' and a.lx='" + type + "'";
            }
            if (StringUtil.isNotEmpty(mc)) {
                sql += " and ( b.mc like '%" + mc + "%' or b.ddgpdm like '%" + mc + "%')";
            }
            if (StringUtil.isNotEmpty(column) && StringUtil.isNotEmpty(orderBy)) {
                sql += ordrBy(column, orderBy);
            } else {
                sql += "order By creationtime desc ";
            }
            PageInfo<Map<String, Object>> pageList = PageUtil.PageQuery(communalDao.queryPage(sql));
            jo.put("data", pageList.getList());
            jo.put("total", pageList.getTotal());
            return jo.toString();
        }
        return "";
    }

    /**
     * <pre>
     * Description  :删除所有表 <br/>
     * ChangeLog    : 1. 创建 (2020/4/21 0021 14:47 [gaofan]);
     * @author gaofan
     * @param type 表类型 1：富赢资金，2：行业top,3:机构评级
     * @param state 删除类型 0：根据ID删除，1：删除当天导入
     * @date 2020/4/21 0021 14:47
     * </pre>
     */
    @Transactional(rollbackFor = Exception.class)
    public String delAll(String type, String id, String state) {
        if (StringUtil.isNotEmpty(type) && StringUtil.isNotEmpty(state)) {
            String sql = "";
            String desql = "delete from stockinfo_item ";
            String desql2 = "delete from stockinfo_gsinfo ";
            switch (type) {
                case "1":
                    switch (state) {
                        case "0":
                            if (StringUtil.isNotEmpty(id)) {
                                sql = delsql("stockinfo_table", "0", id);
                                desql += "where ddgpdm=(select ddgpdm from stockinfo_table where id_='" + id + "') and type='1'";
                                desql2 += "where ddgpdm=(select ddgpdm from stockinfo_table where id_='" + id + "') and type='1'";
                            }
                            break;
                        case "1":
                            sql = delsql("stockinfo_table", "1", "");
                            desql += "where ddgpdm in(select ddgpdm from stockinfo_table where  DATE_FORMAT(creationtime,'%Y-%m-%d')=DATE_FORMAT('" + Util.newdata() + "','%Y-%m-%d')') and type='1'";
                            desql2 += "where ddgpdm in(select ddgpdm from stockinfo_table where  DATE_FORMAT(creationtime,'%Y-%m-%d')=DATE_FORMAT('" + Util.newdata() + "','%Y-%m-%d')') and type='1'";
                            break;
                    }
                    break;
                case "2":
                    switch (state) {
                        case "0":
                            if (StringUtil.isNotEmpty(id)) {
                                sql = delsql("stockinfo_industrytable", "0", id);
                                desql += "where ddgpdm=(select ddgpdm from stockinfo_industrytable where id_='" + id + "') and type='3'";
                                desql2 += "where ddgpdm=(select ddgpdm from stockinfo_industrytable where id_='" + id + "') and type='3'";
                            }
                            break;
                        case "1":
                            sql = delsql("stockinfo_industrytable", "1", "");
                            desql += "where ddgpdm in(select ddgpdm from stockinfo_industrytable where  DATE_FORMAT(creationtime,'%Y-%m-%d')=DATE_FORMAT('" + Util.newdata() + "','%Y-%m-%d')') and type='3'";
                            desql2 += "where ddgpdm in(select ddgpdm from stockinfo_industrytable where  DATE_FORMAT(creationtime,'%Y-%m-%d')=DATE_FORMAT('" + Util.newdata() + "','%Y-%m-%d')') and type='3'";
                            break;
                    }
                    break;
                case "3":
                    switch (state) {
                        case "0":
                            if (StringUtil.isNotEmpty(id)) {
                                sql = delsql("stockinfo_institutionstable", "0", id);
                                desql += "where ddgpdm=(select ddgpdm from stockinfo_institutionstable where id_='" + id + "') and type='2'";
                                desql2 += "where ddgpdm=(select ddgpdm from stockinfo_institutionstable where id_='" + id + "') and type='2'";
                            }
                            break;
                        case "1":
                            sql = delsql("stockinfo_institutionstable", "1", "");
                            desql += "where ddgpdm in(select ddgpdm from stockinfo_institutionstable where  DATE_FORMAT(creationtime,'%Y-%m-%d')=DATE_FORMAT('" + Util.newdata() + "','%Y-%m-%d')') and type='2'";
                            desql2 += "where ddgpdm in(select ddgpdm from stockinfo_institutionstable where  DATE_FORMAT(creationtime,'%Y-%m-%d')=DATE_FORMAT('" + Util.newdata() + "','%Y-%m-%d')') and type='2'";
                            communalDao.execute(desql);
                            break;
                    }
                    break;

            }
            communalDao.execute(sql);
            communalDao.execute(desql);
            communalDao.execute(desql2);
            return "1";
        }
        return "";
    }

    /**
     * <pre>
     * Description  :删除sql <br/>
     * ChangeLog    : 1. 创建 (2020/4/21 0021 14:47 [gaofan]);
     * @author gaofan
     * @date 2020/4/21 0021 14:47
     * </pre>
     */
    public String delsql(String form, String type, String id) {
        String sql = "";
        switch (type) {
            case "0":
                sql = "delete from " + form + " where id_='" + id + "'";
                break;
            case "1":
                sql = "delete from " + form + " where DATE_FORMAT(creationtime,'%Y-%m-%d')=DATE_FORMAT('" + Util.newdata() + "','%Y-%m-%d')";
                break;
            default:
                break;
        }
        return sql;
    }

    /**
     * 小程序页面信息
     *
     * @param type   0：富赢资，1：机构评级，2：行业top
     * @param openId
     * @return
     * @throws ParseException
     */
    public String appinfoList(String type, String openId, int pageIndex, int pageSize, String column, String orderBy, String cxcolumn, String cxlr) throws ParseException {
        if (StringUtil.isNotEmpty(type) && StringUtil.isNotEmpty(openId)) {
            String lx = "";
            Map map = userManager.getOpenIdUser(openId);
            long newDate = Util.dateGetTime();
            long endDate = Util.stringToDate(map.getOrDefault("endtime", "2000-01-01") + "", "yyyy-MM-dd").getTime();
            if (endDate >= newDate) {
                lx = "1";
            } else {
                lx = "0";
            }
            switch (type) {
                case "0":
                    return infoList("stockinfo_table", lx, pageIndex, pageSize, column, orderBy, cxcolumn, cxlr);
                case "1":
                    return infoList("stockinfo_institutionstable", lx, pageIndex, pageSize, column, orderBy, cxcolumn, cxlr);
                case "2":
                    return infoList("stockinfo_industrytable", lx, pageIndex, pageSize, column, orderBy, cxcolumn, cxlr);
                default:
                    return "";
            }
        }
        return "";
    }


    /**
     * 查询表
     *
     * @param form 表明
     * @param type 是否是vip信息
     * @return
     */
    public String infoList(String form, String type, int pageIndex, int pageSize, String column, String orderBy, String cxcolumn, String cxlr) {
        PageHelper.startPage(pageIndex, pageSize);
        JSONObject jsonObject = new JSONObject();
        String sql = "select * from " + form + " where 1=1";
        if ("0".equals(type)) {
            sql += " and lx='0'";
        }
        if (!cxlr.isEmpty()) {
            sql += "and ( mc like '%" + cxlr + "%' or ddgpdm like '%" + cxlr + "%')";
        }
        if (!column.isEmpty() && !orderBy.isEmpty()) {
            sql += ordrBy(column, orderBy);
        } else {
            sql += " order by creationtime desc";
        }
        PageInfo<Map<String, Object>> pageList = PageUtil.PageQuery(communalDao.queryPage(sql));
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(pageList.getList());
        jsonObject.put("data", jsonArray);
        jsonObject.put("total", pageList.getTotal());
        return jsonObject.toString();
    }

    /**
     * 添加留言
     *
     * @param openId
     * @param datajson
     * @return
     */
    public String comments(String openId, String content) {
        if (StringUtil.isNotEmpty(openId)) {
            String id = UUID.randomUUID().toString();
            Map map = userManager.getOpenIdUser(openId);
            String sql = "insert into stockinfo_comments(id_,user_id,content,name,creationtime,dz,tx) value('" + id + "','" + map.getOrDefault("user_id", "") + "','" + content + "','" + map.getOrDefault("user_name", "") + "','" + Util.newdata() + "',0,'" + map.getOrDefault("user_tx", "") + "')";
            communalDao.execute(sql);
            return id;
        }
        return "";
    }

    /**
     * 回复评论
     *
     * @param userId
     * @param userName
     * @param content
     * @param commentId
     * @return
     */
    public String replycomments(String userId, String userName, String content, String commentId) {
        if (StringUtil.isNotEmpty(userId)) {
            String id = UUID.randomUUID().toString();
            String sql = "insert into stockinfo_comments value('" + id + "','" + commentId + "','" + userId + "','" + content + "','" + userName + "','" + Util.newdata() + "')";
            communalDao.execute(sql);
            return id;
        }
        return "";
    }


    /**
     * 后台收藏列表
     *
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public String commentList(int pageIndex, int pageSize) {
        PageHelper.startPage(pageIndex, pageSize);
        String sql = "select * from stockinfo_comments where ref_id_ is  null order by creationtime desc ";
        PageInfo<Map<String, Object>> pageList = PageUtil.PageQuery(communalDao.queryPage(sql));
        JSONObject jb = new JSONObject();
        JSONArray ja = new JSONArray();
        if (pageList.getSize() > 0) {
            List<Map<String, Object>> list = pageList.getList();
            for (int i = 0; i < list.size(); i++) {
                String sql2 = "select * from stockinfo_comments where ref_id_='" + list.get(i).getOrDefault("id_", "") + "' ";
                List<Map<String, Object>> list1 = communalDao.query(sql2);
                list.get(i).put("reply", list1);
            }
            jb.put("data", list);
            jb.put("total", pageList.getTotal());
        }
        return jb.toString();
    }

    /**
     * 小程序我的留言以及回复
     *
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public String appCommentList(int pageIndex, int pageSize, String openId) {
        PageHelper.startPage(pageIndex, pageSize);
        String sql = "select * from stockinfo_comments where ref_id_ is null order by creationtime desc ";
        PageInfo<Map<String, Object>> pageList = PageUtil.PageQuery(communalDao.queryPage(sql));
        JSONObject jb = new JSONObject();
        if (pageList.getSize() > 0) {
            List<Map<String, Object>> list = pageList.getList();
            for (int i = 0; i < list.size(); i++) {
                String sql2 = "select * from stockinfo_comments where ref_id_='" + list.get(i).getOrDefault("id_", "") + "' ";
                List<Map<String, Object>> list1 = communalDao.query(sql2);
                list.get(i).put("reply", list1);
            }
            jb.put("data", list);
            jb.put("total", pageList.getTotal());
        }
        return jb.toString();
    }

    /**
     * 获取排序
     *
     * @param column 列名称
     * @param ordrBy 排序规则 aes desc
     * @return
     */
    public String ordrBy(String column, String ordrBy) {
        return " order by " + column + "+0  " + ordrBy + "";
    }

    /**
     * 查询列名称
     *
     * @param type
     * @return
     */
    public String columnName(String type) {
        if (!type.isEmpty()) {
            JSONObject jsonObject = new JSONObject();
            String tableName = "";
            switch (type) {
                case "0":
                    tableName = "stockinfo_table";
                    break;
                case "1":
                    tableName = "stockinfo_institutionstable";
                    break;
                case "2":
                    tableName = "stockinfo_industrytable";
                    break;

                default:
                    tableName = "";
                    break;
            }
            if (!tableName.isEmpty()) {
                String sql = "select COLUMN_NAME from information_schema.`COLUMNS` WHERE TABLE_NAME='" + tableName + "'";

                jsonObject.put("data", communalDao.query(sql));
                return jsonObject.toString();
            }
        }
        return "";
    }

    /**
     * 删除收藏接口
     *
     * @param id
     * @return
     */
    public String delComment(String id) {
        if (StringUtil.isNotEmpty(id)) {
            String delSql = "delete from stockinfo_comments where id_='" + id + "' or ref_id_='" + id + "'  ";
            communalDao.execute(delSql);
            return "1";
        }
        return "";
    }

    @Transactional(rollbackFor = Exception.class)
    public String praise(String openId, String id) {
        if (StringUtil.isNotEmpty(openId)) {
            String updateSql = "update stockinfo_comments SET dz=dz+1 where id_='" + id + "'";
            String updateSql2 = "update stockinfo_comments SET dz=dz-1 where id_='" + id + "'";
            Dz dz = dzService.getOne(new QueryWrapper<Dz>().eq("plid", id).eq("dzid", openId));
            if (dz == null) {
                Dz dz1 = new Dz();
                String ids = UUID.randomUUID().toString();
                dz1.setId(ids);
                dz1.setDzid(openId);
                dz1.setPlid(id);
                //1已点赞，0：未点赞
                dz1.setSfdz("1");
                communalDao.execute(updateSql);
                dzService.save(dz1);
                return "点赞成功";
            } else {
                if ("0".equals(dz.getSfdz())) {
                    dz.setSfdz("1");
                    communalDao.execute(updateSql);
                    dzService.updateById(dz);
                    return "点赞成功";
                } else {
                    dz.setSfdz("0");
                    communalDao.execute(updateSql2);
                    dzService.updateById(dz);
                    return "取消点赞成功";
                }
            }
        }
        return "";
    }

    /**
     * 详细数据
     *
     * @param type
     * @param gpdm
     * @return
     */
    public String StockinfoDetailed(String type, String gpdm) {
        if (StringUtil.isNotEmpty(type) && StringUtil.isNotEmpty(gpdm)) {
            JSONObject jb = new JSONObject();
            JSONArray jbxx = new JSONArray();
            JSONArray qyzb = new JSONArray();
            JSONArray fyzy = new JSONArray();
            JSONArray ltpj = new JSONArray();
            JSONArray hyrd = new JSONArray();
            JSONArray zyyw = new JSONArray();
            JSONArray tzhb = new JSONArray();
            JSONArray gsxx = new JSONArray();
            List<Map<String, Object>> detailedList = communalDao.query("select * from stockinfo_item where ddgpdm='" + gpdm + "' and type='" + type + "'");
            List<Map<String, Object>> gsList = communalDao.query("select * from stockinfo_gsinfo where ddgpdm='" + gpdm + "' and type='" + type + "'");
            if (detailedList.size() > 0) {
                detailedList.forEach(any -> {
                    Map<String, Object> jbxxmap = new HashMap<>();
                    jbxxmap.put("wz1", any.getOrDefault("wz1", ""));
                    jbxxmap.put("wz2", any.getOrDefault("wz2", ""));
                    jbxxmap.put("wz3", any.getOrDefault("wz3", ""));
                    jbxxmap.put("sshy", any.getOrDefault("sshy", ""));
                    jbxxmap.put("gdjc", any.getOrDefault("gdjc", ""));
                    jbxxmap.put("gqzy", any.getOrDefault("gqzy", ""));
                    jbxxmap.put("sy", any.getOrDefault("sy", ""));
                    jbxxmap.put("zjyngxl", any.getOrDefault("zjyngxl", ""));
                    jbxxmap.put("zhpf", any.getOrDefault("zhpf", ""));
                    jbxx.add(jbxxmap);
                    Map<String, Object> qyzbmap = new HashMap<>();
                    qyzbmap.put("ksnfmgyjyq", any.getOrDefault("ksnfmgyjyq", ""));
                    qyzbmap.put("yqz1", any.getOrDefault("yqz1", ""));
                    qyzbmap.put("yqbh1", any.getOrDefault("yqbh1", ""));
                    qyzbmap.put("jsnfmgyjyq", any.getOrDefault("jsnfmgyjyq", ""));
                    qyzbmap.put("yqz2", any.getOrDefault("yqz2", ""));
                    qyzbmap.put("yqbh2", any.getOrDefault("yqbh2", ""));
                    qyzbmap.put("yqz2", any.getOrDefault("yqz2", ""));
                    qyzbmap.put("zyywdqzzl", any.getOrDefault("zyywdqzzl", ""));
                    qyzbmap.put("zyywdqwdx", any.getOrDefault("zyywdqwdx", ""));
                    qyzbmap.put("zyywzqzzl", any.getOrDefault("zyywzqzzl", ""));
                    qyzbmap.put("zyywcqwdx", any.getOrDefault("zyywcqwdx", ""));
                    qyzb.add(qyzbmap);
                    Map<String, Object> fyzymap = new HashMap<>();
                    fyzymap.put("yi", any.getOrDefault("yi", ""));
                    fyzymap.put("er", any.getOrDefault("er", ""));
                    fyzymap.put("san", any.getOrDefault("san", ""));
                    fyzymap.put("si", any.getOrDefault("si", ""));
                    fyzymap.put("wu", any.getOrDefault("wu", ""));
                    fyzymap.put("liu", any.getOrDefault("liu", ""));
                    fyzymap.put("qi", any.getOrDefault("qi", ""));
                    fyzymap.put("ba", any.getOrDefault("ba", ""));
                    fyzymap.put("jiu", any.getOrDefault("jiu", ""));
                    fyzymap.put("shi", any.getOrDefault("shi", ""));
                    fyzymap.put("shiyi", any.getOrDefault("shiyi", ""));
                    fyzymap.put("shier", any.getOrDefault("shier", ""));
                    fyzymap.put("shisan", any.getOrDefault("shisan", ""));
                    fyzymap.put("shisi", any.getOrDefault("shisi", ""));
                    fyzymap.put("shiwu", any.getOrDefault("shiwu", ""));
                    fyzymap.put("shiliu", any.getOrDefault("shiliu", ""));
                    fyzymap.put("shiqi", any.getOrDefault("shiqi", ""));
                    fyzymap.put("shiba", any.getOrDefault("shiba", ""));
                    fyzymap.put("shijiu", any.getOrDefault("shijiu", ""));
                    fyzymap.put("ershi", any.getOrDefault("ershi", ""));
                    fyzymap.put("ershiyi", any.getOrDefault("ershiyi", ""));
                    fyzymap.put("ershier", any.getOrDefault("ershier", ""));
                    fyzymap.put("ershisan", any.getOrDefault("ershisan", ""));
                    fyzymap.put("ershisi", any.getOrDefault("ershisi", ""));
                    fyzymap.put("ershiwu", any.getOrDefault("ershiwu", ""));
                    fyzymap.put("ershiliu", any.getOrDefault("ershiliu", ""));
                    fyzymap.put("ershiqi", any.getOrDefault("ershiqi", ""));
                    fyzymap.put("ershiba", any.getOrDefault("ershiba", ""));
                    fyzymap.put("ershijiu", any.getOrDefault("ershijiu", ""));
                    fyzymap.put("sanshi", any.getOrDefault("sanshi", ""));
                    fyzymap.put("sanshiyi", any.getOrDefault("sanshiyi", ""));
                    fyzymap.put("sanshier", any.getOrDefault("sanshier", ""));
                    fyzymap.put("sanshisan", any.getOrDefault("sanshisan", ""));
                    fyzymap.put("sanshisi", any.getOrDefault("sanshisi", ""));
                    fyzymap.put("sanshiwu", any.getOrDefault("sanshiwu", ""));
                    fyzymap.put("sanshiliu", any.getOrDefault("sanshiliu", ""));
                    fyzymap.put("sanshiqi", any.getOrDefault("sanshiqi", ""));
                    fyzymap.put("sanshiba", any.getOrDefault("sanshiba", ""));
                    fyzymap.put("sanshijiu", any.getOrDefault("sanshijiu", ""));
                    fyzymap.put("sishi", any.getOrDefault("sishi", ""));
                    fyzy.add(fyzymap);
                    Map<String, Object> ltpjmap2 = new LinkedHashMap<>();
                    ltpjmap2.put("sjksrq", any.getOrDefault("sjksrq", ""));
                    ltpjmap2.put("sjjsrq", any.getOrDefault("sjjsrq", ""));
                    ltpjmap2.put("dyt", any.getOrDefault("dyt", ""));
                    ltpjmap2.put("dytz", any.getOrDefault("dytz", ""));
                    ltpjmap2.put("det", any.getOrDefault("det", ""));
                    ltpjmap2.put("detz", any.getOrDefault("detz", ""));
                    ltpjmap2.put("dst", any.getOrDefault("dst", ""));
                    ltpjmap2.put("dstz", any.getOrDefault("dstz", ""));
                    ltpjmap2.put("dsit", any.getOrDefault("dsit", ""));
                    ltpjmap2.put("dsitz", any.getOrDefault("dsitz", ""));
                    ltpjmap2.put("dwt", any.getOrDefault("dwt", ""));
                    ltpjmap2.put("dwtz", any.getOrDefault("dwtz", ""));
                    ltpj.add(ltpjmap2);
                    Map<String, Object> hyrdmap = new HashMap<>();
                    hyrdmap.put("hydyt", any.getOrDefault("hydyt", ""));
                    hyrdmap.put("hydytz", any.getOrDefault("hydytz", ""));
                    hyrdmap.put("hydet", any.getOrDefault("hydet", ""));
                    hyrdmap.put("hydetz", any.getOrDefault("hydetz", ""));
                    hyrdmap.put("hydst", any.getOrDefault("hydst", ""));
                    hyrdmap.put("hydstz", any.getOrDefault("hydstz", ""));
                    hyrdmap.put("hydsit", any.getOrDefault("hydsit", ""));
                    hyrdmap.put("hydsitz", any.getOrDefault("hydsitz", ""));
                    hyrdmap.put("hydwt", any.getOrDefault("hydwt", ""));
                    hyrdmap.put("hydwtz", any.getOrDefault("hydwtz", ""));
                    hyrd.add(hyrdmap);
                    Map<String, Object> zyywmap = new HashMap<>();
                    zyywmap.put("zyyw1", any.getOrDefault("zyyw1", ""));
                    zyywmap.put("zb1", any.getOrDefault("zb1", ""));
                    zyywmap.put("zyyw2", any.getOrDefault("zyyw2", ""));
                    zyywmap.put("zb2", any.getOrDefault("zb2", ""));
                    zyywmap.put("zyyw3", any.getOrDefault("zyyw3", ""));
                    zyywmap.put("zb3", any.getOrDefault("zb3", ""));
                    zyyw.add(zyywmap);
                    Map<String, Object> tzhbmap = new HashMap<>();
                    tzhbmap.put("dyjdqrqz", any.getOrDefault("dyjdqrqz", ""));
                    tzhbmap.put("dyjdhrqz", any.getOrDefault("dyjdhrqz", ""));
                    tzhbmap.put("dejdqrqz", any.getOrDefault("dejdqrqz", ""));
                    tzhbmap.put("dejdhrqz", any.getOrDefault("dejdhrqz", ""));
                    tzhbmap.put("dsjdqrqz", any.getOrDefault("dsjdqrqz", ""));
                    tzhbmap.put("dsjdhrqz", any.getOrDefault("dsjdhrqz", ""));
                    tzhbmap.put("dsijdqrqz", any.getOrDefault("dsijdqrqz", ""));
                    tzhbmap.put("dsijdhrqz", any.getOrDefault("dsijdhrqz", ""));
                    tzhbmap.put("qrq", any.getOrDefault("qrq", ""));
                    tzhbmap.put("hrq", any.getOrDefault("hrq", ""));
                    tzhb.add(tzhbmap);
                });
            }
            if (gsList.size() > 0) {
                gsList.forEach(any -> {
                    Map<String, Object> gsxxmap = new HashMap<>();
                    gsxxmap.put("rq", any.getOrDefault("rq", ""));
                    gsxxmap.put("yysr", any.getOrDefault("yysr", ""));
                    gsxxmap.put("tb1", any.getOrDefault("tb1", ""));
                    gsxxmap.put("mgsy", any.getOrDefault("mgsy", ""));
                    gsxxmap.put("tb2", any.getOrDefault("tb2", ""));
                    gsxx.add(gsxxmap);
                });
            }
            jb.put("jbxx", jbxx);
            jb.put("qyzb", qyzb);
            jb.put("fyzy", fyzy);
            jb.put("ltpj", ltpj);
            jb.put("hyrd", hyrd);
            jb.put("zyyw", zyyw);
            jb.put("tzhb", tzhb);
            jb.put("gsxx", gsxx);
            return jb.toString();
        }
        return "";
    }


}
