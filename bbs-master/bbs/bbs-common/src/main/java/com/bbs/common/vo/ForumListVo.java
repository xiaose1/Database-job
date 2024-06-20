package com.bbs.common.vo;

import com.bbs.common.consts.CommonConstant;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class ForumListVo {

    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 封面图
     */
    private String coverImage;

    /**
     * 用户id
     */
    private Integer userId;
    private String userName;

    /**
     * 正文内容
     */
    private String content;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = CommonConstant.DATE_TIME_FORMAT)
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = CommonConstant.DATE_TIME_FORMAT)
    private LocalDateTime updateTime;
}
