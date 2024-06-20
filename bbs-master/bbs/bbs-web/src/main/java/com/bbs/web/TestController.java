package com.bbs.web;

import com.bbs.common.vo.DataResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TestController
 *
 * @author yangjiajia
 * @createdAt 2024/4/1 17:21
 */
@RestController
public class TestController {

    @GetMapping("test")
    public DataResult test() {
        return DataResult.success();
    }
}
