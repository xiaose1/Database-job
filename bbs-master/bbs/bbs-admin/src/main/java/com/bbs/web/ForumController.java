package com.bbs.web;

import com.bbs.common.vo.DataResult;
import com.bbs.dto.ForumListDTO;
import com.bbs.service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ForumController
 *
 * @author yangjiajia
 * @createdAt 2024/4/8 15:06
 */
@RestController
@RequestMapping("forum")
public class ForumController {

    @Autowired
    private ForumService forumService;

    @PostMapping("list")
    public DataResult list(@RequestBody ForumListDTO listDTO) {
        return DataResult.success(forumService.list(listDTO));
    }

    @GetMapping("delete")
    public DataResult delete(@RequestParam("id") Integer id) {
        forumService.delete(id);
        return DataResult.success();
    }
}
