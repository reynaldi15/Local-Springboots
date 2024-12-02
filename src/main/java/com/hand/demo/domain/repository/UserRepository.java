package com.hand.demo.domain.repository;
import com.hand.demo.api.dto.UserRequest;
import org.hzero.mybatis.base.BaseRepository;
import com.hand.demo.domain.entity.User;

import java.util.List;

/**
 * 用户表(User)资源库
 *
 * @author shaoqin.zhou@hand-china.com
 * @since 2024-10-15 14:51:30
 */
public interface UserRepository extends BaseRepository<User> {
    List<UserRequest> selectList(UserRequest userRequest);
}