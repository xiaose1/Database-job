package com.bbs.web;

import com.bbs.common.vo.DataResult;
import com.bbs.dto.ForumCommentSaveDTO;
import com.bbs.service.ForumCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * ForumCommentController
 *
 * @author yangjiajia
 * @createdAt 2024/4/5 17:44
 */
@RestController
@RequestMapping("forumComment")
public class ForumCommentController {

    @Autowired
    private ForumCommentService forumCommentService;

    @PostMapping("save")
    public DataResult save(@Valid @RequestBody ForumCommentSaveDTO saveDTO) {
        forumCommentService.save(saveDTO);
        return DataResult.success();
    }

    @GetMapping("list")
    public DataResult list(@RequestParam("forumId") Integer forumId) {
        return DataResult.success(forumCommentService.list(forumId));
    }
}
