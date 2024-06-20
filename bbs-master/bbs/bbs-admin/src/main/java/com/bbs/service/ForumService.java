package com.bbs.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bbs.common.entity.Forum;
import com.bbs.common.exception.CommonException;
import com.bbs.common.mapper.ForumMapper;
import com.bbs.common.vo.PageVo;
import com.bbs.dto.ForumListDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * ForumService
 *
 * @author yangjiajia
 * @createdAt 2024/4/8 15:02
 */
@Service
public class ForumService {

    @Autowired
    private ForumMapper forumMapper;

    public PageVo<Forum> list(ForumListDTO listDTO) {
        Page<Forum> queryPage = new Page<>(listDTO.getPage(), listDTO.getPageSize());
        QueryWrapper<Forum> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(listDTO.getTitle())) {
            queryWrapper.lambda().like(Forum::getTitle, listDTO.getTitle());//模糊查询
        }
        queryWrapper.lambda().eq(Forum::getDeleteFlag, 0).orderByDesc(Forum::getCreateTime);
        IPage<Forum> list = forumMapper.selectPage(queryPage, queryWrapper);
        return new PageVo<>(list);
    }

    public void delete(int id) {
        Forum forum = forumMapper.selectById(id);
        if (null == forum) {
            throw new CommonException("帖子不存在");
        }
        forum.setDeleteFlag(1);
        forum.setUpdateTime(LocalDateTime.now());
        int count = forumMapper.updateById(forum);
        if (count != 1) {
            throw new CommonException("删除失败");
        }
    }
}
