package com.bbs.common.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * ForumCommentListVo
 *
 * @author yangjiajia
 * @createdAt 2024/4/5 17:40
 */
@Data
public class ForumCommentListVo {

    private Integer id;

    private Integer forumId;

    private Integer userId;
    private String userName;

    private String content;

    private LocalDateTime createTime;
}
