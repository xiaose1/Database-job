package com.bbs.common.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.bbs.common.consts.CommonConstant;
import com.bbs.common.vo.DataResult;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();
        if (session.getAttribute(CommonConstant.SESSION_USER) == null) {
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JSONObject.toJSONString(DataResult.fail(999, "用户未登录")));
            return false;
        }
        return true;
    }
}
