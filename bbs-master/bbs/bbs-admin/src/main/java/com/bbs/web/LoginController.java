package com.bbs.web;

import com.alibaba.fastjson.JSONObject;
import com.bbs.common.consts.CommonConstant;
import com.bbs.common.dto.LoginDTO;
import com.bbs.common.vo.DataResult;
import com.bbs.common.vo.LoginVo;
import com.bbs.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * LoginController
 *
 * @author yangjiajia
 * @createdAt 2024/4/2 11:58
 */
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("login")
    public DataResult login(@Valid @RequestBody LoginDTO loginDTO, HttpSession session) {
        LoginVo login = loginService.login(loginDTO);
        session.setAttribute(CommonConstant.SESSION_USER, JSONObject.toJSONString(login));
        return DataResult.success(login);
    }
}
