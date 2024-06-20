package com.bbs.common.dto;

import lombok.Data;


@Data
public class ListDTO {

    private int page = 1;
    private int pageSize = 10;
}
