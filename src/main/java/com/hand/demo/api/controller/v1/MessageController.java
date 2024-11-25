package com.hand.demo.api.controller.v1;

import com.hand.demo.api.dto.MessageRequest;
import com.hand.demo.app.service.impl.MessageServiceImpl;
import com.hand.demo.config.SwaggerTags;
import com.hand.demo.domain.entity.Task;
import io.choerodon.core.iam.ResourceLevel;
import io.choerodon.swagger.annotation.Permission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hzero.boot.message.entity.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Api(tags = SwaggerTags.MESSENGER)
@RestController("MessengerController.v1.48209")
@RequestMapping("/v1/ra/{organizationId}/messages")
public class MessageController {
    private final MessageServiceImpl messageService;

    public MessageController(MessageServiceImpl messageService) {
        this.messageService = messageService;
    }

    @Permission(level = ResourceLevel.SITE)
    @ApiOperation(value = "Messenger Sent")
    @PostMapping(
            path = "/email"
    )
    public ResponseEntity<?> sendEmail(
            @PathVariable("organizationId") Long tenantId,
            @RequestParam(name = "email_receiver")String emailReceiver
            ,@RequestBody String context) {
        messageService.sendEmail(context, emailReceiver, tenantId);
        Map<String, Object> mapResponse = new HashMap<>();
        mapResponse.put("tenatId ", tenantId);
        mapResponse.put("receiver", emailReceiver);
        mapResponse.put("messages", context);
        return ResponseEntity.status(HttpStatus.OK).body(
                mapResponse
        );
    }

    @Permission(level = ResourceLevel.SITE)
    @ApiOperation(value = "Messenger Sent")
    @PostMapping(
    )
    public ResponseEntity<?> sendNotifications(
            @PathVariable("organizationId") Long tenantId,
            @RequestParam(name = "receiver") Long receiver,
            @RequestBody List<String> messages) {
        List<Message> messages1 =    messageService.sendNotification(tenantId, receiver, messages);

        return ResponseEntity.status(HttpStatus.OK).body(
                messages1
        );
    }
}
