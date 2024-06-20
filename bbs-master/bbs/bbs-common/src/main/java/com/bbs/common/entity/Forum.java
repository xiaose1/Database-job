package com.bbs.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 帖子
 * </p>
 *
 * @author yangjiajia
 * @since 2024-04-02
 */
@Getter
@Setter
public class Forum implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
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

    /**
     * 正文内容
     */
    private String content;

    /**
     * 删除标志，0正常，1删除
     */
    private Integer deleteFlag;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;


}
