package com.bbs.common.mapper;

import com.bbs.common.entity.ForumComment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bbs.common.vo.ForumCommentListVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 评论 Mapper 接口
 * </p>
 *
 * @author yangjiajia
 * @since 2024-04-02
 */
@Mapper
public interface ForumCommentMapper extends BaseMapper<ForumComment> {

    List<ForumCommentListVo> list(@Param("forumId") Integer forumId);
}
