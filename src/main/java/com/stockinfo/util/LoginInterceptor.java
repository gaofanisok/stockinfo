package com.stockinfo.util;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.servlet.ModelAndView;


/**
 * @Auther: 拦截器
 * @Date: 2020/4/15 0015 15 43
 * @Description: gaofan
 */
@Component//表示是个java beng
public class LoginInterceptor implements HandlerInterceptor {
    //    在请求处理之前调用,只有返回true才会执行要执行的请求
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        httpServletResponse.setCharacterEncoding("UTF-8");
        String token=httpServletRequest.getHeader("accessToken");
        if (token!=null){
            boolean result=Util.verify(token);
            if (result){
                return true;
            }
        }
        Map<String,Object> map=new HashMap<>();
        map.put("data","token is null");
        map.put("code","401");
        httpServletResponse.getWriter().write(JSONObject.toJSONString(map));
        return false;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
