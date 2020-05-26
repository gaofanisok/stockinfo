package com.stockinfo.controller;

import com.stockinfo.manager.StockinfoManager;
import com.stockinfo.manager.UserManager;
import com.stockinfo.util.CommonUtil;
import com.stockinfo.util.RequestUtil;
import com.stockinfo.util.StringUtil;
import com.stockinfo.util.Util;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

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
     * <pre>
     * Description  : 赢富资列表 <br/>
     * ChangeLog    : 1. 创建 (2020/4/21 0021 14:50 [gaofan]);
     * @author gaofan
     * @date 2020/4/21 0021 14:50
     * </pre>
     */
    @RequestMapping(value = "stockinfo", method = RequestMethod.POST)
    public String stockinfo(HttpServletRequest request) {
        try {
            int pageIndex = RequestUtil.getInt(request, "pageIndex", 1);
            int pageSize = RequestUtil.getInt(request, "pageSize", 5);
            String mc = RequestUtil.getString(request, "mc");
            String stockinfo = stockinfoManager.stockinfo(pageIndex, pageSize, mc, "0");
            if (StringUtil.isNotEmpty(stockinfo)) {
                return CommonUtil.toReturnJsonMsg(0, "成功", stockinfo);
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
     * <pre>
     * Description  : 赢富资详细  <br/>
     * ChangeLog    : 1. 创建 (2020/4/21 0021 14:50 [gaofan]);
     * @author gaofan
     * @date 2020/4/21 0021 14:50
     * </pre>
     */
    @RequestMapping(value = "getStockById", method = RequestMethod.POST)
    public String getStockById(HttpServletRequest request) {
        try {
            String id = RequestUtil.getString(request, "id");
            String stockinfo = stockinfoManager.getStockById(id);
            if (StringUtil.isNotEmpty(stockinfo)) {
                return CommonUtil.toReturnJsonMsg(0, "成功", stockinfo);
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
     * <pre>
     * Description  : 机构评级列表  <br/>
     * ChangeLog    : 1. 创建 (2020/4/21 0021 14:31 [gaofan]);
     * @author gaofan
     * @return java.lang.String
     * </pre>
     */
    @RequestMapping(value = "institutionsInfo", method = RequestMethod.POST)
    public String institutionsInfo(HttpServletRequest request) {
        try {
            int pageIndex = RequestUtil.getInt(request, "pageIndex", 1);
            int pageSize = RequestUtil.getInt(request, "pageSize", 5);
            String mc = RequestUtil.getString(request, "mc");
            String stockinfo = stockinfoManager.institutionsInfo(pageIndex, pageSize, mc, "0");
            if (StringUtil.isNotEmpty(stockinfo)) {
                return CommonUtil.toReturnJsonMsg(0, "成功", stockinfo);
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
     * <pre>
     * Description  : 机构评级详细  <br/>
     * ChangeLog    : 1. 创建 (2020/4/21 0021 14:47 [gaofan]);
     * @author gaofan
     * @date 2020/4/21 0021 14:47
     * </pre>
     */
    @RequestMapping(value = "getInstitutionById", method = RequestMethod.POST)
    public String geInstitutionById(HttpServletRequest request) {
        try {
            String id = RequestUtil.getString(request, "id");
            String stockinfo = stockinfoManager.getInstitutionById(id);
            if (StringUtil.isNotEmpty(stockinfo)) {
                return CommonUtil.toReturnJsonMsg(0, "成功", stockinfo);
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
     * <pre>
     * Description  :  行业top列表 <br/>
     * ChangeLog    : 1. 创建 (2020/4/21 0021 14:31 [gaofan]);
     * @author gaofan
     * @return java.lang.String
     * </pre>
     */
    @RequestMapping(value = "industryinfo", method = RequestMethod.POST)
    public String industryinfo(HttpServletRequest request) {
        try {
            int pageIndex = RequestUtil.getInt(request, "pageIndex", 1);
            int pageSize = RequestUtil.getInt(request, "pageSize", 5);
            String mc = RequestUtil.getString(request, "mc");
            String gn = RequestUtil.getString(request, "gn");
            String stockinfo = stockinfoManager.industryinfo(pageIndex, pageSize, mc, gn);
            if (StringUtil.isNotEmpty(stockinfo)) {
                return CommonUtil.toReturnJsonMsg(0, "成功", stockinfo);
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
     * <pre>
     * Description  : 行业top详细 <br/>
     * ChangeLog    : 1. 创建 (2020/4/21 0021 14:47 [gaofan]);
     * @author gaofan
     * @date 2020/4/21 0021 14:47
     * </pre>
     */
    @RequestMapping(value = "getIndustrytaById", method = RequestMethod.POST)
    public String getIndustrytaById(HttpServletRequest request) {
        try {
            String id = RequestUtil.getString(request, "id");
            String stockinfo = stockinfoManager.getIndustrytaById(id);
            if (StringUtil.isNotEmpty(stockinfo)) {
                return CommonUtil.toReturnJsonMsg(0, "成功", stockinfo);
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
     * <pre>
     * Description  : 收藏数据  <br/>
     * ChangeLog    : 1. 创建 (2020/4/15 0015 15:29 [gaofan]);
     * @author gaofan
     * @date 2020/4/15 0015 15:29
     * </pre>
     */
    @RequestMapping(value = "/collect", method = RequestMethod.POST)
    public String collect(HttpServletRequest request, HttpServletResponse response) {
        String datajson = RequestUtil.getString(request, "datajson");
        String openId = request.getHeader("openId");
        String id = stockinfoManager.collect(datajson, openId);
        if (StringUtil.isNotEmpty(id)) {
            return CommonUtil.toReturnJsonMsg(0, "收藏成功", id);
        }
        return CommonUtil.toReturnJsonMsg(1, "收藏失败:已经收藏过了");
    }

    /**
     * <pre>
     * Description  :我的收藏列表 <br/>
     * ChangeLog    : 1. 创建 (2020/4/21 0021 14:47 [gaofan]);
     * </pre>
     */
    @RequestMapping(value = "myCollectList", method = RequestMethod.POST)
    public String myCollectList(HttpServletRequest request) {
        try {
            int pageIndex = RequestUtil.getInt(request, "pageIndex", 1);
            int pageSize = RequestUtil.getInt(request, "pageSize", 5);
            String mc = RequestUtil.getString(request, "mc");
            String type = RequestUtil.getString(request, "type");
            String column = RequestUtil.getString(request, "column");
            String orderBy = RequestUtil.getString(request, "orderBy");
            String openId = request.getHeader("openId");
            String stockinfo = stockinfoManager.myCollectList(openId, type, mc, pageIndex, pageSize, column, orderBy);
            if (StringUtil.isNotEmpty(stockinfo)) {
                return CommonUtil.toReturnJsonMsg(0, "成功", stockinfo);
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
     * <pre>
     * Description  :删除所有 <br/>
     * ChangeLog    : 1. 创建 (2020/4/21 0021 14:47 [gaofan]);
     * </pre>
     */
    @RequestMapping(value = "delAll", method = RequestMethod.POST)
    public String delAll(HttpServletRequest request) {
        try {
            String type = RequestUtil.getString(request, "type");
            String id = RequestUtil.getString(request, "id");
            String state = RequestUtil.getString(request, "state");
            String stockinfo = stockinfoManager.delAll(type, id, state);
            if (StringUtil.isNotEmpty(stockinfo)) {
                return CommonUtil.toReturnJsonMsg(0, "删除成功");
            } else {
                return CommonUtil.toReturnJsonMsg(1, "数据错误：删除失败！");
            }
        } catch (Exception e) {
            logger.error(e);
            e.printStackTrace();
        }
        return CommonUtil.toReturnJsonMsg(-1, "系统繁忙，请重试");
    }

    /**
     * <pre>
     * Description  :我的收藏列表 <br/>
     * ChangeLog    : 1. 创建 (2020/4/21 0021 14:47 [gaofan]);
     * </pre>
     */
    @RequestMapping(value = "appinfoList", method = RequestMethod.POST)
    public String appinfoList(HttpServletRequest request) {
        try {
            int pageIndex = RequestUtil.getInt(request, "pageIndex", 1);
            int pageSize = RequestUtil.getInt(request, "pageSize", 5);
            String column = RequestUtil.getString(request, "column");
            String orderBy = RequestUtil.getString(request, "orderBy");
            String cxcolumn = RequestUtil.getString(request, "cxcolumn");
            String cxlr = RequestUtil.getString(request, "cxlr");
            String type = RequestUtil.getString(request, "type");
            String openId = request.getHeader("openId");
            String stockinfo = stockinfoManager.appinfoList(type, openId, pageIndex, pageSize, column, orderBy, cxcolumn, cxlr);
            if (StringUtil.isNotEmpty(stockinfo)) {
                return CommonUtil.toReturnJsonMsg(0, "成功", stockinfo);
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
     * 留言
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "comments", method = RequestMethod.POST)
    public String comments(HttpServletRequest request) {
        try {
            String content = RequestUtil.getString(request, "content");
            String openId = request.getHeader("openId");
            String comments = stockinfoManager.comments(openId, content);
            if (StringUtil.isNotEmpty(comments)) {
                return CommonUtil.toReturnJsonMsg(0, "留言成功", comments);
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
     * 回复留言
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "replycomments", method = RequestMethod.POST)
    public String replycomments(HttpServletRequest request) {
        try {
            String content = RequestUtil.getString(request, "content");
            String commentId = RequestUtil.getString(request, "commentId");
            String token = request.getHeader("token");
            String userId = Util.getUserId(token);
            String userName = Util.getUserName(token);
            String comments = stockinfoManager.replycomments(userId, userName, content, commentId);
            if (StringUtil.isNotEmpty(comments)) {
                return CommonUtil.toReturnJsonMsg(0, "回复留言成功", comments);
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
     * 后台留言列表
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "commentList", method = RequestMethod.POST)
    public String commentList(HttpServletRequest request) {
        try {
            int pageIndex = RequestUtil.getInt(request, "pageIndex", 1);
            int pageSize = RequestUtil.getInt(request, "pageSize", 5);
            String comments = stockinfoManager.commentList(pageIndex, pageSize);
            if (StringUtil.isNotEmpty(comments)) {
                return CommonUtil.toReturnJsonMsg(0, "成功", comments);
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
     * 小程序用户留言
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "appCommentList", method = RequestMethod.POST)
    public String appCommentList(HttpServletRequest request) {
        try {
            int pageIndex = RequestUtil.getInt(request, "pageIndex", 1);
            int pageSize = RequestUtil.getInt(request, "pageSize", 5);
            String openId = request.getHeader("openId");
            String comments = stockinfoManager.appCommentList(pageIndex, pageSize, openId);
            if (StringUtil.isNotEmpty(comments)) {
                return CommonUtil.toReturnJsonMsg(0, "成功", comments);
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
     * 获取列名
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "columnName", method = RequestMethod.POST)
    public String columnName(HttpServletRequest request) {
        try {
            String type = RequestUtil.getString(request, "type");
            String comments = stockinfoManager.columnName(type);
            if (StringUtil.isNotEmpty(comments)) {
                return CommonUtil.toReturnJsonMsg(0, "成功", comments);
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
     * 删除留言
     * @param request
     * @return
     */
    @PostMapping("/delComment")
    public String delComment(HttpServletRequest request) {
        try {
            String id = RequestUtil.getString(request, "id");
            String del = stockinfoManager.delComment(id);
            if (StringUtil.isNotEmpty(del)) {
                return CommonUtil.toReturnJsonMsg(0, "删除成功", del);
            } else {
                return CommonUtil.toReturnJsonMsg(1, "数据错误：删除失败！");
            }
        } catch (Exception e) {
            logger.error(e);
            e.printStackTrace();
        }
        return CommonUtil.toReturnJsonMsg(-1, "系统繁忙，请重试");
    }

    /**
     * 点赞或取消点赞
     * @param request
     * @param openId
     * @return
     */
    @PostMapping("/praise")
    public String praise(HttpServletRequest request, @RequestHeader("openId") String openId) {
        try {
            String id = RequestUtil.getString(request, "id");
            String praise = stockinfoManager.praise(openId, id);
            if (StringUtil.isNotEmpty(praise)) {
                return CommonUtil.toReturnJsonMsg(0, "成功", praise);
            } else {
                return CommonUtil.toReturnJsonMsg(1, "数据错误：失败！");
            }
        } catch (Exception e) {
            logger.error(e);
            e.printStackTrace();
        }
        return CommonUtil.toReturnJsonMsg(-1, "系统繁忙，请重试");
    }

    /**
     * 数据详细
     * @param request
     * @param openId
     * @return
     */
    @PostMapping("/StockinfoDetailed")
    public String StockinfoDetailed(HttpServletRequest request, @RequestHeader("openId") String openId) {
        try {
            String type = RequestUtil.getString(request, "type");
            String gpdm = RequestUtil.getString(request, "gpdm");
            String praise = stockinfoManager.StockinfoDetailed(type, gpdm);
            if (StringUtil.isNotEmpty(praise)) {
                return CommonUtil.toReturnJsonMsg(0, "成功", praise);
            } else {
                return CommonUtil.toReturnJsonMsg(1, "数据错误：失败！");
            }
        } catch (Exception e) {
            logger.error(e);
            e.printStackTrace();
        }
        return CommonUtil.toReturnJsonMsg(-1, "系统繁忙，请重试");
    }
}
