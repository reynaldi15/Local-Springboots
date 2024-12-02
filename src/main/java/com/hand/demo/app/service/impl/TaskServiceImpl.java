package com.hand.demo.app.service.impl;
import com.hand.demo.api.dto.TaskRequest;
import com.hand.demo.app.service.TaskService;
import com.hand.demo.domain.entity.Task;
import com.hand.demo.domain.repository.TaskRepository;
import io.choerodon.core.exception.CommonException;
import io.choerodon.core.oauth.DetailsHelper;
import org.hzero.boot.platform.code.builder.CodeRuleBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 任务应用服务实现
 */
@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private final CodeRuleBuilder codeRuleBuilder;
    @Autowired
    private TaskRepository taskRepository;

    public TaskServiceImpl(CodeRuleBuilder codeRuleBuilder) {
        this.codeRuleBuilder = codeRuleBuilder;
    }
//    TASK STATE
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Task create(Task task) {
        // 生成任务编号
        task.generateTaskNumber();
        // 插入数据
        taskRepository.insert(task);
        return task;
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Task update(Task task) {
        Task exist = taskRepository.selectByPrimaryKey(task);
        if (exist == null) {
            throw new CommonException("htdo.warn.task.notFound");
        }
        // 更新指定字段
        taskRepository.updateOptional(task,
                Task.FIELD_STATE,
                Task.FIELD_TASK_DESCRIPTION
        );
        return task;
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByTaskNumber(String taskNumber) {
        Task task = new Task();
        task.setTaskNumber(taskNumber);
        taskRepository.delete(task);
    }

    @Override
    public List<TaskRequest> selectList(TaskRequest task) {
        return Collections.emptyList();
    }

    @Override
    public Task save(String description, Long tenantId) {
        String realName = DetailsHelper.getUserDetails().getRealName();
        Map<String, String> customSegmentMap = new HashMap<>();
        customSegmentMap.put("customSegment", "Reynaldi-");
        if (realName == null) {
            realName = "Real Name not Found";
        }
        String taskCode = codeRuleBuilder.generateCode("DEMO-48209", customSegmentMap);
        Task task = new Task();
        task.setEmployeeId(48209L);
        task.setState("PENDING");
        task.setTaskNumber(taskCode);
        task.setTaskDescription(description);
        task.setTenantId(tenantId);
        taskRepository.insert(task);
        return task;
    }
}