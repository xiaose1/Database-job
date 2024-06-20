package com.bbs.web;

import com.bbs.common.dto.ForumListDTO;
import com.bbs.common.vo.DataResult;
import com.bbs.dto.ForumSaveDTO;
import com.bbs.service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * ForumController
 *
 * @author yangjiajia
 * @createdAt 2024/4/5 17:00
 */
@RestController
@RequestMapping("forum")
public class ForumController {

    @Autowired
    private ForumService forumService;

    @PostMapping("save")
    public DataResult save(@Valid @RequestBody ForumSaveDTO saveDTO) {
        forumService.save(saveDTO);
        return DataResult.success();
    }

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
