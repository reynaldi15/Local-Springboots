package com.hand.demo.api.dto;

import com.hand.demo.domain.entity.Task;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hzero.export.annotation.ExcelSheet;

import java.util.List;

@ExcelSheet(zh = "Task Info",en = "Task Info")
@Accessors(chain = true)
@Getter
@Setter
public class TaskRequest extends Task {
    private List<Long> empIdList;
}
