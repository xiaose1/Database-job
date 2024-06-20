package com.bbs.common.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;

import java.util.List;

/**
 * description
 *
 * @author yangjiajia
 * @createdAt 2023/12/18 21:20
 */
@Data
public class PageVo<T> {

    private int page;

    private int pageSize;

    private int total;

    private List<T> list;

    public PageVo() {}

    public PageVo(long page, long pageSize, long total, List<T> list) {
        this.page = (int) page;
        this.pageSize = (int) pageSize;
        this.total = (int) total;
        this.list = list;
    }

    public PageVo(IPage<T> page) {
        this.page = (int) page.getCurrent();
        this.pageSize = (int) page.getSize();
        this.total = (int) page.getTotal();
        this.list = page.getRecords();
    }
}
