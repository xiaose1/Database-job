package com.bbs.service;

import cn.hutool.core.collection.ListUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bbs.common.dto.ForumListDTO;
import com.bbs.common.entity.Forum;
import com.bbs.common.exception.CommonException;
import com.bbs.common.mapper.ForumMapper;
import com.bbs.common.utils.SessionManager;
import com.bbs.common.vo.ForumListVo;
import com.bbs.dto.ForumSaveDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ForumService
 *
 * @author yangjiajia
 * @createdAt 2024/4/5 16:35
 */
@Service
@Log4j2
public class ForumService {

    @Autowired
    private ForumMapper forumMapper;

    public void save(ForumSaveDTO saveDTO) {
        int userId = SessionManager.getUser().getId();
        log.info("帖子发布,param:{}", JSONObject.toJSONString(saveDTO));
        Forum forum = new Forum();
        BeanUtils.copyProperties(saveDTO, forum);
        forum.setUserId(userId);
        forum.setDeleteFlag(0);
        forum.setCreateTime(LocalDateTime.now());
        forum.setUpdateTime(LocalDateTime.now());
        forumMapper.insert(forum);
    }

    public List<List<ForumListVo>> list(ForumListDTO listDTO) {

        List<ForumListVo> list = forumMapper.list(listDTO);
        return ListUtil.partition(list, 4);
    }

    public void delete(int id) {

        Forum forum = forumMapper.selectById(id);
        if (null == forum) {
            throw new CommonException("信息未找到");
        }
        forum.setDeleteFlag(1);

        forum.setUpdateTime(LocalDateTime.now());
        forumMapper.updateById(forum);
    }
}
