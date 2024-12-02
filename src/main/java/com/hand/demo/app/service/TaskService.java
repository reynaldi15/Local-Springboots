package com.hand.demo.app.service;
import com.hand.demo.api.dto.TaskRequest;
import com.hand.demo.domain.entity.Task;

import java.util.Collection;
import java.util.List;

/**
 * 任务应用服务
 */
public interface TaskService {
    /**
     * 创建任务
     *
     * @param task 任务
     * @return Task
     */
    Task create(Task task);
    /**
     * 更新任务
     *
     * @param task 任务
     * @return Task
     */
    Task update(Task task);
    /**
     * 根据任务编号删除
     *
     * @param taskNumber 任务编号
     */
    void deleteByTaskNumber(String taskNumber);

    List<TaskRequest> selectList(TaskRequest task);

    Task save(String description, Long tenantId);

//    Task save(String description, Long tenantId);
//    <task> Task save(task);
}