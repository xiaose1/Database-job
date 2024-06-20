package com.bbs.common.mapper;

import com.bbs.common.entity.ForumComment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bbs.common.vo.ForumCommentListVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface ForumCommentMapper extends BaseMapper<ForumComment> {

    List<ForumCommentListVo> list(@Param("forumId") Integer forumId);
}
