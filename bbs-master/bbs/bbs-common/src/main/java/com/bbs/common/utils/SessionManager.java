package com.bbs.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.bbs.common.consts.CommonConstant;
import com.bbs.common.exception.CommonException;
import com.bbs.common.vo.LoginVo;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Objects;

/**
 * SessionManager
 *
 * @author yangjiajia
 * @createdAt 2024/4/5 16:39
 */
public class SessionManager {

    public static LoginVo getUser() {
        Object attribute = Objects.requireNonNull(RequestContextHolder.getRequestAttributes()).getAttribute(CommonConstant.SESSION_USER, RequestAttributes.SCOPE_SESSION);
        if (null == attribute) {
            throw new CommonException("未找到用户");
        }
        return JSONObject.parseObject(attribute.toString(), LoginVo.class);
    }
}
