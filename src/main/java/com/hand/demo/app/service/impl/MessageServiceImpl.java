package com.hand.demo.app.service.impl;

import com.hand.demo.api.dto.MessageRequest;
import org.hzero.boot.message.MessageClient;
import org.hzero.boot.message.entity.FlyBookMsgType;
import org.hzero.boot.message.entity.Message;
import org.hzero.boot.message.entity.Receiver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MessageServiceImpl {
    private static final Logger log = LoggerFactory.getLogger(MessageServiceImpl.class);
    private final MessageClient messageClient;
    private final String TEMPLATE_CODE_FEISHU = "DEMO-48029-FEISHU";
    private final String SERVER_CODE_FEISHU = "FEIYU";
    private final String TEMPLATE_CODE = "DEMO-48029";
    private final String LANGUAGE_CODE = "en_US";
    private final String SERVER_CODE = "DEMO-48209";
    private final String SUBJECT = "DEMO CODE";

    public MessageServiceImpl(MessageClient messageClient) {
        this.messageClient = messageClient;
    }

    public List<Message> sendNotification(Long tenantId, Long userId, List<String> message)
    {
        List<Message> responseMessages = new ArrayList<>();
        Receiver receiver = new Receiver();
        receiver.setUserId(userId);
        receiver.setTargetUserTenantId(tenantId);

        if(Objects.isNull(message))
        {

            Map<String, String> args = new HashMap<>();
            Message message1 = messageClient.sendWebMessage(tenantId, TEMPLATE_CODE, LANGUAGE_CODE,
                    Collections.singletonList(receiver),
                    args);
            responseMessages.add(message1);
        }else {
            for (String s : message) {
                Map<String, String> args = new HashMap<>();
                if(Objects.isNull(s))
                {
                    s = message.get(0);
                }
                args.put("msg", s);
                Message message1 = messageClient.sendWebMessage(tenantId, TEMPLATE_CODE, LANGUAGE_CODE,
                        Collections.singletonList(receiver),
                        args);
                responseMessages.add(message1);
            }
        }
        return responseMessages;
    }

    public void sendEmail(String context, String email, Long tenantId)
    {
        Receiver receiver = new Receiver();
        receiver.setEmail(email);
        receiver.setTargetUserTenantId(tenantId);

        messageClient.sendCustomEmail(
                tenantId,
                SERVER_CODE,
                SUBJECT,
                context,
                Collections.singletonList(receiver),
                null,
                null,
                null
        );
    }
    public Message sendFeishuMessage(long tenantId, String contextMessage, String email)
    {
        List<Map<String, String>> receivers = new ArrayList<>();
        Map<String, String> receiver = new HashMap<>();
        receiver.put("email", email);
        receivers.add(receiver);

        Map<String, Object> args = new HashMap<>();
        args.put("userName", "Reynaldi Silalahi");
        args.put("empNumber", 48209);
        args.put("email", "reynaldi.silalahi@hand-global.com");
        return messageClient.sendFlyBook(tenantId,
                SERVER_CODE_FEISHU,
                TEMPLATE_CODE_FEISHU,
                FlyBookMsgType.TEXT,
                LANGUAGE_CODE,
                receivers,
                args
        );
    }

}
