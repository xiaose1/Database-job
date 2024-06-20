package com.bbs.common.dto;

import lombok.Data;

/**
 * ListDTO
 *
 * @author yangjiajia
 * @createdAt 2024/4/1 17:36
 */
@Data
public class ListDTO {

    private int page = 1;
    private int pageSize = 10;
}
