package com.bbs.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bbs.common.dto.LoginDTO;
import com.bbs.common.entity.User;
import com.bbs.common.exception.CommonException;
import com.bbs.common.mapper.UserMapper;
import com.bbs.common.service.VerificationCodeService;
import com.bbs.common.vo.LoginVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * LoginService
 *
 * @author yangjiajia
 * @createdAt 2024/4/2 11:49
 */
@Service
public class LoginService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private VerificationCodeService verificationCodeService;

    public LoginVo login(LoginDTO loginDTO) {

        if (!verificationCodeService.checkCode(loginDTO.getCodeId(), loginDTO.getCode())) {
            throw new CommonException("验证码不正确");
        }

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getAccount, loginDTO.getAccount());
        User user = userMapper.selectOne(queryWrapper);
        if (null == user) {
            throw new CommonException("账号不存在");
        }
        if (!user.getPassword().equals(loginDTO.getPassword())) {
            throw new CommonException("密码不正确");
        }
        if (user.getAuthLevel() != 1) {
            throw new CommonException("没有权限");
        }

        LoginVo loginVo = new LoginVo();
        BeanUtils.copyProperties(user, loginVo);
        return loginVo;
    }
}
