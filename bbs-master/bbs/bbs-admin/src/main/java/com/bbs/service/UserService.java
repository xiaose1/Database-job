package com.bbs.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bbs.common.entity.User;
import com.bbs.common.mapper.UserMapper;
import com.bbs.common.vo.PageVo;
import com.bbs.dto.UserListDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public PageVo<User> list(UserListDTO listDTO) {
        Page<User> queryPage = new Page<>(listDTO.getPage(), listDTO.getPageSize());
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(listDTO.getAccount())) {
            queryWrapper.lambda().like(User::getAccount, listDTO.getAccount());
        }
        IPage<User> list = userMapper.selectPage(queryPage, queryWrapper);
        return new PageVo<>(list);
    }
}
