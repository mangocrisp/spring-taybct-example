package io.github.mangocrisp.exp.wsm.controller;

import io.github.mangocrisp.spring.taybct.tool.core.websocket.endpoint.IWebSocketServer;
import io.github.mangocrisp.spring.taybct.tool.core.websocket.enums.MessageUserType;
import io.github.mangocrisp.spring.taybct.tool.core.websocket.support.MessageUser;
import jakarta.websocket.Session;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <pre>
 *
 * </pre>
 *
 * @author XiJieYin
 * @since 2025/6/11 15:22
 */
@RestController
@RequestMapping("/msg")
@RequiredArgsConstructor
public class MsgController {

    final IWebSocketServer<Session> webSocketServer;

    @RequestMapping("/send")
    public String sendMessage(@RequestParam Long toUserId
            , @RequestParam(required = false) String toUserSessionId // 如果不传 sessionId 就会默认发送给这个用户的所有的会话
            , @RequestParam String data) {
        webSocketServer.sendSimpleMessage(data, new MessageUser(MessageUserType.USER, toUserId, toUserSessionId));
        return String.format("Message '%s' sent to connection: %s.", data, toUserId);
    }

}
