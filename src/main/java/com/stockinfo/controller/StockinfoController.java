package com.stockinfo.controller;

import com.stockinfo.manager.StockinfoManager;
import com.stockinfo.util.CommonUtil;
import com.stockinfo.util.RequestUtil;
import com.stockinfo.util.StringUtil;
import com.stockinfo.util.Util;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: gaofan
 * @Date: 2020/4/21 0021 14 33
 * @Description: 信息页面交互
 */
@RestController
@RequestMapping("/stock")
public class StockinfoController {
    @Resource
    StockinfoManager stockinfoManager;
    private static Log logger = LogFactory.getLog(UserController.class);
    /**
     *<pre>
     * Description  : 赢富资列表 <br/>
     * ChangeLog    : 1. 创建 (2020/4/21 0021 14:50 [gaofan]);
     * @author gaofan
     * @date 2020/4/21 0021 14:50
     *</pre>
     */
    @RequestMapping(value = "stockinfo", method = RequestMethod.POST)
    public String stockinfo(HttpServletRequest request){
        try {
            int pageIndex=RequestUtil.getInt(request,"pageIndex",1);
            int pageSize=RequestUtil.getInt(request,"pageSize",5);
            String mc=RequestUtil.getString(request,"mc");
            String stockinfo=stockinfoManager.stockinfo(pageIndex,pageSize,mc,"0");
            if (StringUtil.isNotEmpty(stockinfo)) {
                return  CommonUtil.toReturnJsonMsg(0, "成功",stockinfo);
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
     * Description  : 赢富资详细  <br/>
     * ChangeLog    : 1. 创建 (2020/4/21 0021 14:50 [gaofan]);
     * @author gaofan
     * @date 2020/4/21 0021 14:50
     *</pre>
     */
    @RequestMapping(value = "getStockById", method = RequestMethod.POST)
    public String getStockById(HttpServletRequest request){
        try {
            String id=RequestUtil.getString(request,"id");
            String stockinfo=stockinfoManager.getStockById(id);
            if (StringUtil.isNotEmpty(stockinfo)) {
                return  CommonUtil.toReturnJsonMsg(0, "成功",stockinfo);
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
     * Description  : 机构评级列表  <br/>
     * ChangeLog    : 1. 创建 (2020/4/21 0021 14:31 [gaofan]);
     * @author gaofan
     * @return java.lang.String
     *</pre>
     */
    @RequestMapping(value = "institutionsInfo", method = RequestMethod.POST)
    public String institutionsInfo(HttpServletRequest request){
        try {
            int pageIndex=RequestUtil.getInt(request,"pageIndex",1);
            int pageSize=RequestUtil.getInt(request,"pageSize",5);
            String mc=RequestUtil.getString(request,"mc");
            String stockinfo=stockinfoManager.institutionsInfo(pageIndex,pageSize,mc,"0");
            if (StringUtil.isNotEmpty(stockinfo)) {
                return  CommonUtil.toReturnJsonMsg(0, "成功",stockinfo);
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
     * Description  : 机构评级详细  <br/>
     * ChangeLog    : 1. 创建 (2020/4/21 0021 14:47 [gaofan]);
     * @author gaofan
     * @date 2020/4/21 0021 14:47
     *</pre>
     */
    @RequestMapping(value = "getInstitutionById", method = RequestMethod.POST)
    public String geInstitutionById(HttpServletRequest request){
        try {
            String id=RequestUtil.getString(request,"id");
            String stockinfo=stockinfoManager.getInstitutionById(id);
            if (StringUtil.isNotEmpty(stockinfo)) {
                return  CommonUtil.toReturnJsonMsg(0, "成功",stockinfo);
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
     * Description  :  行业top列表 <br/>
     * ChangeLog    : 1. 创建 (2020/4/21 0021 14:31 [gaofan]);
     * @author gaofan
     * @return java.lang.String
     *</pre>
     */
    @RequestMapping(value = "industryinfo", method = RequestMethod.POST)
    public String industryinfo(HttpServletRequest request){
        try {
            int pageIndex=RequestUtil.getInt(request,"pageIndex",1);
            int pageSize=RequestUtil.getInt(request,"pageSize",5);
            String mc=RequestUtil.getString(request,"mc");
            String gn=RequestUtil.getString(request,"gn");
            String stockinfo=stockinfoManager.industryinfo(pageIndex,pageSize,mc,gn);
            if (StringUtil.isNotEmpty(stockinfo)) {
                return  CommonUtil.toReturnJsonMsg(0, "成功",stockinfo);
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
     * Description  : 行业top详细 <br/>
     * ChangeLog    : 1. 创建 (2020/4/21 0021 14:47 [gaofan]);
     * @author gaofan
     * @date 2020/4/21 0021 14:47
     *</pre>
     */
    @RequestMapping(value = "getIndustrytaById", method = RequestMethod.POST)
    public String getIndustrytaById(HttpServletRequest request){
        try {
            String id=RequestUtil.getString(request,"id");
            String stockinfo=stockinfoManager.getIndustrytaById(id);
            if (StringUtil.isNotEmpty(stockinfo)) {
                return  CommonUtil.toReturnJsonMsg(0, "成功",stockinfo);
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
     * Description  : 收藏数据  <br/>
     * ChangeLog    : 1. 创建 (2020/4/15 0015 15:29 [gaofan]);
     * @author gaofan
     * @date 2020/4/15 0015 15:29
     *</pre>
     */
    @RequestMapping(value = "/collect",method = RequestMethod.POST)
    public String collect(HttpServletRequest request, HttpServletResponse response){
        String datajson=RequestUtil.getString(request,"datajson");
        String token=request.getHeader("accessToken");
        String userid=Util.getUserId(token);
        String id =stockinfoManager.collect(datajson,userid);
        if (StringUtil.isNotEmpty(id)){
            return  CommonUtil.toReturnJsonMsg(0, "收藏成功",id);
        }
        return  CommonUtil.toReturnJsonMsg(1, "收藏失败:已经收藏过了");
    }
    /**
     *<pre>
     * Description  :我的收藏列表 <br/>
     * ChangeLog    : 1. 创建 (2020/4/21 0021 14:47 [gaofan]);
     *</pre>
     */
    @RequestMapping(value = "myCollectList", method = RequestMethod.POST)
    public String myCollectList(HttpServletRequest request){
        try {
            int pageIndex=RequestUtil.getInt(request,"pageIndex",1);
            int pageSize=RequestUtil.getInt(request,"pageSize",5);
            String mc=RequestUtil.getString(request,"mc");
            String type=RequestUtil.getString(request,"type");
            String token=request.getHeader("accessToken");
            String userid=Util.getUserId(token);
            String stockinfo=stockinfoManager.myCollectList(userid,type,mc,pageIndex,pageSize);
            if (StringUtil.isNotEmpty(stockinfo)) {
                return  CommonUtil.toReturnJsonMsg(0, "成功",stockinfo);
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
     * Description  :删除所有 <br/>
     * ChangeLog    : 1. 创建 (2020/4/21 0021 14:47 [gaofan]);
     *</pre>
     */
    @RequestMapping(value = "delAll", method = RequestMethod.POST)
    public String delAll(HttpServletRequest request){
        try {
            String type=RequestUtil.getString(request,"type");
            String id=RequestUtil.getString(request,"id");
            String state=RequestUtil.getString(request,"state");
            String stockinfo=stockinfoManager.delAll(type,id,state);
            if (StringUtil.isNotEmpty(stockinfo)) {
                return  CommonUtil.toReturnJsonMsg(0, "删除成功");
            } else {
                return CommonUtil.toReturnJsonMsg(1, "数据错误：删除失败！");
            }
        } catch (Exception e) {
            logger.error(e);
            e.printStackTrace();
        }
        return CommonUtil.toReturnJsonMsg(-1, "系统繁忙，请重试");
    }

}
