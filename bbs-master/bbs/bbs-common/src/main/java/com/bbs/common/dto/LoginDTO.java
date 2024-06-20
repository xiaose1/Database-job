package com.bbs.common.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * LoginDTO
 *
 * @author yangjiajia
 * @createdAt 2024/4/2 11:50
 */
@Data
public class LoginDTO {

    @NotBlank(message = "账号不能为空")
    private String account;

    @NotBlank(message = "密码不能为空")
    private String password;

    @NotBlank(message = "验证码不能为空")
    private String codeId;

    @NotBlank(message = "验证码不能为空")
    private String code;
}
