package com.bbs.common.vo;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class ForumCommentListVo {

    private Integer id;

    private Integer forumId;

    private Integer userId;
    private String userName;

    private String content;

    private LocalDateTime createTime;
}
