package com.bbs.web;

import com.bbs.common.vo.DataResult;
import com.bbs.dto.UserListDTO;
import com.bbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * UserController
 *
 * @author yangjiajia
 * @createdAt 2024/4/8 14:50
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("list")
    public DataResult list(@RequestBody UserListDTO listDTO) {
        return DataResult.success(userService.list(listDTO));
    }
}
