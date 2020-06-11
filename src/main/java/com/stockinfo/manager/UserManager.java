package com.stockinfo.manager;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.stockinfo.dao.CommunalDao;
import com.stockinfo.dao.DzMapper;
import com.stockinfo.util.*;
import net.sf.json.JSONArray;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.HashMap;
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
    private static Log log = LogFactory.getLog(UserManager.class);

    @Resource
    CommunalDao communalDao;
    @Resource
    DzMapper dzMapper;
    private final String appId = "wxb7137856ad4e3fa5";
    private final static String secret = "79d04d17658d4e1ab720b374dd371ecc";
    private final static String requestUrl = "https://api.weixin.qq.com/sns/jscode2session";
    private final static String grant_type = "authorization_code";

    PageUtil PageUtil = new PageUtil();

    /**
     * <pre>
     * Description  : 查询  <br/>
     * ChangeLog    : 1. 创建 (2019/12/13 0013 10:38 [gaofan]);
     * @author gaofan
     * @date 2019/12/13 0013 10:38
     * @param pkid 用户ID
     * @return java.lang.String
     * </pre>
     */
    public String seleuser(int pageIndex, int pageSize, String name) throws ParseException {
        PageHelper.startPage(pageIndex, pageSize);
        JSONObject js = new JSONObject();
        JSONArray ja = new JSONArray();
        String sql = "Select * FROM stockinfo_userprogram  ";
        if (StringUtil.isNotEmpty(name)) {
            sql += " where user_name like '%" + name + "%'";
        }
        sql += " order by creationtime desc";
        PageInfo<Map<String, Object>> pageList = PageUtil.PageQuery(communalDao.queryPage(sql));
        if (pageList.getSize() > 0) {
            List<Map<String, Object>> list = pageList.getList();
            for (int i = 0; i < list.size(); i++) {
                JSONObject js2 = new JSONObject();
                Map<String, Object> maps = list.get(i);
                js2.put("user_id", maps.getOrDefault("user_id", ""));
                js2.put("user_name", maps.getOrDefault("user_name", ""));
                js2.put("user_openid", maps.getOrDefault("user_openid", "") + "");
                js2.put("user_sex", "1".equals(maps.getOrDefault("user_sex", "") + "") ? "男" : "女");
                js2.put("starttime", Util.dateToString(maps.getOrDefault("starttime", "") + "", "yyyy-MM-dd"));
                js2.put("endtime", Util.dateToString(maps.getOrDefault("endtime", "") + "", "yyyy-MM-dd"));
                js2.put("user_type", maps.getOrDefault("user_type", "") + "");
                js2.put("creationtime", Util.dateToString(maps.getOrDefault("creationtime", "") + "", "yyyy-MM-dd HH:mm:ss"));
                ja.add(js2);
            }
        }
        js.put("data", ja);
        js.put("total", pageList.getTotal());
        return js.toString();
    }

    /**
     * <pre>
     * Description  : 查询单个用户  <br/>
     * ChangeLog    : 1. 创建 (2020/4/20 0020 17:18 [gaofan]);
     * @author gaofan
     * @date 2020/4/20 0020 17:18
     * </pre>
     */
    public String getUserByid(String openId) throws ParseException {
        String sql = "select * from stockinfo_userprogram where user_openid='" + openId + "'";
        List<Map<String, Object>> userList = communalDao.query(sql);
        if (userList.size() > 0) {
            String lx = "";
            JSONObject jo = new JSONObject();
            Map<String, Object> map = userList.get(0);
            jo.put("user_id", map.getOrDefault("user_id", ""));
            jo.put("user_name", map.getOrDefault("user_name", ""));
            jo.put("user_openid", map.getOrDefault("user_openid", "") + "");
            jo.put("user_tx", map.getOrDefault("user_tx", ""));
            jo.put("user_sex", "1".equals(map.getOrDefault("user_sex", "") + "") ? "男" : "女");
            jo.put("starttime", Util.dateToString(map.getOrDefault("starttime", "") + "", "yyyy-MM-dd"));
            jo.put("endtime", Util.dateToString(map.getOrDefault("endtime", "") + "", "yyyy-MM-dd"));
            long newDate = Util.dateGetTime();
            long endDate = Util.stringToDate(map.getOrDefault("endtime", "2000-01-01") + "", "yyyy-MM-dd").getTime();
            if (endDate >= newDate) {
                lx = "1";
            } else {
                lx = "0";
            }
            jo.put("user_type", lx);
            jo.put("creationtime", Util.dateToString(map.getOrDefault("creationtime", "") + "", "yyyy-MM-dd HH:mm:ss"));
            return jo.toString();
        }
        return "";
    }

    /**
     * <pre>
     * Description  : 小程序用户添加  <br/>
     * ChangeLog    : 1. 创建 (2020/4/20 0020 17:18 [gaofan]);
     * @author gaofan
     * @date 2020/4/20 0020 17:18
     * </pre>
     */
    public String saveUser(Map datajson) {
        if (datajson.size() > 0) {
            String id = UUID.randomUUID().toString();
            String sql = "select * from stockinfo_userprogram where user_openid='" + datajson.get("openId") + "'";
            if (communalDao.query(sql).size() <= 0) {
                String sql2 = "insert into stockinfo_userprogram(user_id,user_name,user_openid,user_tx,user_sex,creationtime,user_type) value('" + id + "','" + datajson.getOrDefault("nickName", "") + "','" + datajson.getOrDefault("openId", "") + "','" + datajson.getOrDefault("user_tx", "") + "','" + datajson.getOrDefault("gender", "") + "','" + Util.newdata() + "','0')";
                communalDao.execute(sql2);
                return id;
            }

        }
        return "";
    }

    /**
     * 修改用户头像
     *
     * @param tx
     * @param openid
     * @return
     */
    public String upUserTx(String tx, String openid) {
        if (StringUtil.isNotEmpty(tx)) {
            communalDao.execute("update stockinfo_userprogram set user_tx='" + tx + "' where user_openid='" + openid + "' ");
            return "1";
        }
        return "";
    }

    /**
     * 获取微信小程序 session_key 和 openid
     *
     * @param code 调用微信登陆返回的Code
     * @return
     * @author YeFei
     */
    public Map getSessionKeyOrOpenId(String code, String encryptedData, String iv) {
        Map<String, String> requestUrlParam = new HashMap<>();
        Map map = new HashMap();
        requestUrlParam.put("appid", appId);    //开发者设置中的appId
        requestUrlParam.put("secret", secret);    //开发者设置中的appSecret
        requestUrlParam.put("js_code", code);    //小程序调用wx.login返回的code
        //登录凭证不能为空
        if (code == null || code.length() == 0) {
            map.put("status", 0);
            map.put("msg", "code 不能为空");
            return map;
        }
        //请求参数
        String params = "appid=" + appId + "&secret=" + secret + "&js_code=" + code + "&grant_type=" + grant_type;
        //发送post请求读取调用微信 https://api.weixin.qq.com/sns/jscode2session 接口获取openid用户唯一标识
        //发送请求
        String sr = HttpRequest.sendGet("https://api.weixin.qq.com/sns/jscode2session", params);
        //解析相应内容（转换成json对象）
        JSONObject json = JSONObject.parseObject(sr);
        //获取会话密钥（session_key）
        String session_key = json.get("session_key").toString();
        //用户的唯一标识（openid）
        String openid = (String) json.get("openid");
        //////////////// 2、对encryptedData加密数据进行AES解密 ////////////////
        try {
            String result = AesUtil.decrypt(encryptedData, session_key, iv, "UTF-8");
            if (null != result && result.length() > 0) {
                map.put("status", 1);
                map.put("msg", "解密成功");
                JSONObject userInfoJSON = JSONObject.parseObject(result);
                Map userInfo = new HashMap();
                userInfo.put("openId", userInfoJSON.get("openId"));
                userInfo.put("nickName", userInfoJSON.get("nickName"));
                userInfo.put("gender", userInfoJSON.get("gender"));
                userInfo.put("city", userInfoJSON.get("city"));
                userInfo.put("province", userInfoJSON.get("province"));
                userInfo.put("country", userInfoJSON.get("country"));
                userInfo.put("avatarUrl", userInfoJSON.get("avatarUrl"));
                userInfo.put("unionId", userInfoJSON.get("unionId"));
                map.put("userInfo", userInfo);
                saveUser(userInfo);
                return map;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("status", 0);
        map.put("msg", "解密失败");
        return map;
    }

    /**
     * 增加用户权限
     *
     * @param endtime 结束时间
     */
    public String updateUser(String endtime, String id, String type) throws ParseException {
        if (StringUtil.isNotEmpty(endtime)) {
            String upsql = "UPDATE stockinfo_userprogram SET endtime = '" + endtime + "', user_type='" + type + "' where user_id='" + id + "'";
            communalDao.execute(upsql);
            return "1";
        }
        return "";
    }

    /**
     * 获取小程序用户信息
     *
     * @param openId 标识
     * @return map
     */
    public Map getOpenIdUser(String openId) {
        List<Map<String, Object>> userList = communalDao.query("select * from stockinfo_userprogram where user_openid='" + openId + "'");
        if (userList.size() <= 0) {
            throw new IllegalArgumentException("用户未授权或授权过期请重新授权");
        }
        Map map = userList.get(0);
        return map;
    }
}
