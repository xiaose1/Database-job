package com.bbs.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * ForumCommentSaveDTO
 *
 * @author yangjiajia
 * @createdAt 2024/4/5 17:37
 */
@Data
public class ForumCommentSaveDTO {

    @NotNull
    private Integer forumId;

    @NotBlank
    private String content;
}
