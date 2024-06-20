package com.bbs.common.dto;

import lombok.Data;

/**
 * ForumListDTO
 *
 * @author yangjiajia
 * @createdAt 2024/4/5 17:49
 */
@Data
public class ForumListDTO extends ListDTO{

    private String title;
    private Integer userId;
}
