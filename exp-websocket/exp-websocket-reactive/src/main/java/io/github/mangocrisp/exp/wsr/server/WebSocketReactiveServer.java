package io.github.mangocrisp.exp.wsr.server;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import io.github.mangocrisp.spring.taybct.tool.core.annotation.ServerReactiveEndpoint;
import io.github.mangocrisp.spring.taybct.tool.core.websocket.endpoint.AbstractWebSocketReactiveServer;
import io.github.mangocrisp.spring.taybct.tool.core.websocket.support.WebsocketReactiveSession;
import org.springframework.boot.autoconfigure.AutoConfiguration;

import java.util.List;
import java.util.Map;

/**
 * <pre>
 * websocket 服务
 * </pre>
 *
 * @author XiJieYin
 * @since 2025/6/13 14:46
 */
@AutoConfiguration
@ServerReactiveEndpoint("/websocket/{deptId}/{userId}")
public class WebSocketReactiveServer extends AbstractWebSocketReactiveServer {

    @Override
    public void onMessage(WebsocketReactiveSession session, Long userId, String message) {
        Map<String, List<String>> requestParameter = getRequestParameterMap(session.session().getId());
        // 从 session 里面拿请求参数，知道需要发送给的用户，这些用户可能是群发
        List<String> toUserIdList = requestParameter.get("toUserId");
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
