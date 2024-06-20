package com.bbs.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * ForumSaveDTO
 *
 * @author yangjiajia
 * @createdAt 2024/4/5 16:36
 */
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
