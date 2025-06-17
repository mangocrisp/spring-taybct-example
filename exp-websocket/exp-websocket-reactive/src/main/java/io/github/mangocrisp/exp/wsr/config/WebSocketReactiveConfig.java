package io.github.mangocrisp.exp.wsr.config;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;

/**
 * <pre>
 * WebSocket
 * </pre>
 *
 * @author XiJieYin
 * @since 2025/3/14 15:30
 */
@AutoConfiguration
@ConditionalOnClass(RouterFunction.class)
public class WebSocketReactiveConfig {

    @Bean
    public WebSocketHandlerAdapter handlerAdapter() {
        return new WebSocketHandlerAdapter();
    }

}
