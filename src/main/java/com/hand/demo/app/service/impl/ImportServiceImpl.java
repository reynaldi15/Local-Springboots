package com.hand.demo.app.service.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.hand.demo.domain.entity.Task;
import com.hand.demo.domain.entity.User;
import com.hand.demo.domain.repository.TaskRepository;
import com.hand.demo.domain.repository.UserRepository;
import org.hzero.boot.imported.app.service.BatchImportHandler;
import org.hzero.boot.imported.app.service.ImportHandler;
import org.hzero.boot.imported.infra.validator.annotation.ImportService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;


@ImportService(templateCode = "DEMO-CLIENT-48209")
public class ImportServiceImpl extends ImportHandler {
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    TaskRepository taskRepository;
    private static final String TASK_NUMBER_REGEX = "^[A-Za-z]+$";
    @Override
    public Boolean doImport(String data) {
        Task task;
        try {
            task = objectMapper.readValue(data, Task.class);
        } catch (IOException e) {
            getContext().addErrorMsg("Data Import Error, Check yoiur file!!");
            return Boolean.FALSE;

        }
        if (!isValidTaskNumber(task.getTaskNumber())) {
            getContext().addErrorMsg("Invalid Task Number: Must contain English letters only.");
            return Boolean.FALSE;
        }
//        if (){
//
//        }
//        repository.insertSelective(user);
//        // 成功
//        return true;
        taskRepository.insertSelective(task);
        getContext().addBackInfo(String.valueOf(task.getId()));
        return Boolean.TRUE;
    }

    private boolean isValidTaskNumber(String taskNumber) {
        return taskNumber != null && taskNumber.matches(TASK_NUMBER_REGEX);
    }

}
