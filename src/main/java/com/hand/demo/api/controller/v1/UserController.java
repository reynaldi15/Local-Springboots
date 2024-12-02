package com.hand.demo.api.controller.v1;
import com.hand.demo.api.dto.UserRequest;
import com.hand.demo.app.service.UserService;
import com.hand.demo.config.SwaggerTags;
import io.choerodon.core.domain.Page;
import io.choerodon.core.iam.ResourceLevel;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import io.choerodon.swagger.annotation.Permission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hzero.boot.platform.lov.annotation.ProcessLovValue;
import org.hzero.core.base.BaseConstants;
import org.hzero.core.base.BaseController;
import org.hzero.core.util.Results;
import org.hzero.export.annotation.ExcelExport;
import org.hzero.export.vo.ExportParam;
import org.hzero.mybatis.helper.SecurityTokenHelper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.hand.demo.domain.entity.User;
import com.hand.demo.domain.repository.UserRepository;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;
import java.util.List;

/**
 * 用户接口
 */
@Api(tags = SwaggerTags.USER)
@RestController("userController.v1")
//@RequestMapping("/v1/users")
@RequestMapping("/v1/{organizationId}/users")
public class UserController extends BaseController {
    private final UserService userService;
    private final UserRepository userRepository;
    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }
    @Permission(level = ResourceLevel.SITE)
    @ApiOperation(value = "分页查询用户")
    @GetMapping
    public ResponseEntity<Page<User>> list(User user, PageRequest pageRequest) {
        return Results.success(userRepository.pageAndSort(pageRequest, user));
    }
    @Permission(level = ResourceLevel.SITE)
    @ApiOperation(value = "创建 todo 用户")
    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        // 简单数据校验
        this.validObject(user);
        // 创建用户
        return Results.success(userService.create(user));
    }
    @Permission(level = ResourceLevel.SITE)
    @ApiOperation(value = "删除 todo 用户")
    @DeleteMapping
    public ResponseEntity<User> delete(@RequestBody User user) {
        // 数据防篡改校验
        SecurityTokenHelper.validToken(user);
        // 删除用户
        userService.delete(user.getId());
        return Results.success();
    }
    @Permission(level = ResourceLevel.ORGANIZATION)
    @ApiOperation(value = "export")
    @GetMapping("/export")
    @ExcelExport(value= UserRequest.class)
    @ProcessLovValue(targetField = BaseConstants.FIELD_BODY)
    public ResponseEntity<List<UserRequest>> export(UserRequest userRequest,
                                                    ExportParam exportParam,
                                                    HttpServletResponse response,
//                                                    @PathVariable String organizationId
                                                    @PathVariable Long organizationId) {
        return Results.success(userService.exportData(userRequest));
    }
}