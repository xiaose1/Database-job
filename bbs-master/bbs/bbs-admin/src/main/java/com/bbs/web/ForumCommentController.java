package com.bbs.web;

import com.bbs.common.vo.DataResult;
import com.bbs.service.ForumCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * ForumCommentController
 *
 * @author yangjiajia
 * @createdAt 2024/4/8 15:25
 */
@RestController
@RequestMapping("forumComment")
public class ForumCommentController {

    @Autowired
    private ForumCommentService forumCommentService;

    @GetMapping("list")
    public DataResult list(@RequestParam("forumId") Integer forumId) {
        return DataResult.success(forumCommentService.list(forumId));
    }
}
