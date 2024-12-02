package com.hand.demo.api.dto;

import com.hand.demo.domain.entity.User;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hzero.export.annotation.ExcelColumn;
import org.hzero.export.annotation.ExcelSheet;


import java.util.List;

@ExcelSheet(zh = "User Info",en = "User Info")
@Accessors(chain = true)
@Getter
@Setter
public class UserRequest extends User {
    @ExcelColumn(promptCode="children",promptKey="children",child=true)
//    private List<TaskRequest> taskRequestList
    private List<TaskRequest> taskList;
}
