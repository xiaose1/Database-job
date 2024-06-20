package com.bbs.common.dto;

import lombok.Data;


@Data
public class ForumListDTO extends ListDTO{

    private String title;
    private Integer userId;
}
