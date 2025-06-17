package io.github.mangocrisp.exp.wsm.server;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import io.github.mangocrisp.spring.taybct.tool.core.websocket.endpoint.AbstractWebSocketServer;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.boot.autoconfigure.AutoConfiguration;

import java.util.List;

/**
 * <pre>
 * websocket 服务
 * </pre>
 *
 * @author XiJieYin
 * @since 2025/6/13 14:46
 */
@AutoConfiguration
@ServerEndpoint("/websocket/{deptId}/{userId}")
public class WebSocketServer extends AbstractWebSocketServer {

    @Override
    public void onMessage(Session session, Long userId, String message) {
        // 从 session 里面拿请求参数，知道需要发送给的用户，这些用户可能是群发
        List<String> toUserIdList = session.getRequestParameterMap().get("toUserId");
        if (CollectionUtil.isNotEmpty(toUserIdList)) {
            toUserIdList.forEach(toUserId -> {
                // 如果转换失败，就发给自己
                sendSimpleMessage(userId, message, Convert.toLong(toUserId, userId));
                // 发送成功之后发送给自己知道已经发送成功
                sendSimpleMessage("发送成功！", userId);
            });
        }
    }

}
