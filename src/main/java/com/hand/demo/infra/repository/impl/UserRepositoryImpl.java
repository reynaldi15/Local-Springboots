package com.hand.demo.infra.repository.impl;
import com.hand.demo.api.dto.UserRequest;
import com.hand.demo.domain.entity.User;
import com.hand.demo.domain.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.hzero.mybatis.base.impl.BaseRepositoryImpl;

import java.util.Collections;
import java.util.List;

/**
 * 用户资源库实现
 */
@Component
public class UserRepositoryImpl extends BaseRepositoryImpl<User> implements UserRepository {
    @Override
    public List<UserRequest> selectList(UserRequest userRequest) {
        return Collections.emptyList();
    }
}