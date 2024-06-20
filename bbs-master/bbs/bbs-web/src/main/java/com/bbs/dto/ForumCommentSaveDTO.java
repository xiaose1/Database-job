package com.bbs.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
public class ForumCommentSaveDTO {

    @NotNull
    private Integer forumId;

    @NotBlank
    private String content;
}
