package com.bbs.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * registerDTO
 *
 * @author yangjiajia
 * @createdAt 2024/4/2 12:01
 */
@Data
public class RegisterDTO {

    /**
     * 账号
     */
    @NotBlank
    private String account;

    /**
     * 姓名
     */
    @NotBlank
    private String name;

    /**
     * 密码
     */
    @NotBlank
    private String password;
    @NotBlank
    private String rePassword;

    /**
     * 邮箱
     */
    @NotBlank
    private String mail;
}
