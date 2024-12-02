package com.hand.demo.app.service.impl;
import com.hand.demo.api.dto.TaskRequest;
import com.hand.demo.api.dto.UserRequest;
import com.hand.demo.app.service.TaskService;
import com.hand.demo.app.service.UserService;
import com.hand.demo.domain.entity.Task;
import com.hand.demo.domain.entity.User;
import com.hand.demo.domain.repository.TaskRepository;
import com.hand.demo.domain.repository.UserRepository;
import io.choerodon.core.exception.CommonException;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 用户应用服务实现
 */
@Service
public class UserServiceImpl implements UserService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TaskService taskService;
    public UserServiceImpl(TaskRepository taskRepository, UserRepository userRepository, TaskService taskService) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.taskService = taskService;
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public User create(User user) {
        userRepository.insert(user);
        return user;
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long userId) {
        User exist = userRepository.selectByPrimaryKey(userId);
        if (exist == null) {
            throw new CommonException("htdo.warn.user.notFound");
        }
        // 删除用户
        userRepository.deleteByPrimaryKey(userId);
        // 删除与用户关联的任务
        List<Task> tasks = taskRepository.selectByEmployeeId(userId);
        if (CollectionUtils.isNotEmpty(tasks)) {
            taskRepository.batchDelete(tasks);
        }
    }

    @Override
    public List<UserRequest> exportData(UserRequest userRequest){
        List<UserRequest> userList = userRepository.selectList(userRequest);
        List<Long> userIdList = new ArrayList<>();
        userList.forEach(user -> userIdList.add(user.getId()));
        Map<Long,List<TaskRequest>> taskMap= taskService.selectList(new TaskRequest().setEmpIdList(userIdList))
                .stream()
                .collect(Collectors.groupingBy(TaskRequest::getEmployeeId));
        userList.forEach(user -> user.setTaskList(taskMap.get(user.getId())));
        return userList;
    }

}