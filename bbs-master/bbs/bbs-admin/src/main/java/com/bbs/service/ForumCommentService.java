package com.bbs.service;

import com.bbs.common.mapper.ForumCommentMapper;
import com.bbs.common.vo.ForumCommentListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ForumCommentService
 *
 * @author yangjiajia
 * @createdAt 2024/4/8 15:24
 */
@Service
public class ForumCommentService {

    @Autowired
    private ForumCommentMapper forumCommentMapper;

    public List<ForumCommentListVo> list(int forumId) {
        return forumCommentMapper.list(forumId);
    }
}
