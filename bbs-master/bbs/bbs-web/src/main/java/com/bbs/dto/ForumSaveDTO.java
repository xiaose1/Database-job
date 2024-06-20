package com.bbs.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
public class ForumSaveDTO {

    /**
     * 标题
     */
    @NotBlank
    private String title;

    /**
     * 封面图
     */
    @NotBlank
    private String coverImage;

    /**
     * 正文内容
     */
    @NotBlank
    private String content;
}
