package com.bbs.common.mapper;

import com.bbs.common.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户 Mapper 接口
 * </p>
 *
 * @author yangjiajia
 * @since 2024-04-02
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
