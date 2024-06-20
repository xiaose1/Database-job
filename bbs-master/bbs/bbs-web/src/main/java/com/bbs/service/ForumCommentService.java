package com.bbs.service;

import com.bbs.common.entity.ForumComment;
import com.bbs.common.mapper.ForumCommentMapper;
import com.bbs.common.utils.SessionManager;
import com.bbs.common.vo.ForumCommentListVo;
import com.bbs.dto.ForumCommentSaveDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Log4j2
@Service
public class ForumCommentService {

    @Autowired
    private ForumCommentMapper forumCommentMapper;

    public void save(ForumCommentSaveDTO saveDTO) {
        ForumComment comment = new ForumComment();
        BeanUtils.copyProperties(saveDTO, comment);
        comment.setUserId(SessionManager.getUser().getId());
        comment.setCreateTime(LocalDateTime.now());
        forumCommentMapper.insert(comment);
    }

    public List<ForumCommentListVo> list(int forumId) {
        return forumCommentMapper.list(forumId);
    }
}
