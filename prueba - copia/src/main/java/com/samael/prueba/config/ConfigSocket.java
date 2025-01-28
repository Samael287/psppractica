package com.samael.prueba.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;


@Configuration
@EnableWebSocketMessageBroker
public class ConfigSocket implements WebSocketMessageBrokerConfigurer {


    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Nos indicará a dónde tenemos que conectarnos,
        // en este caso a http://localhost:4200/chat-websocket


        registry.addEndpoint("/chat-websocket")
                .setAllowedOrigins("http://localhost:4200") // Dominios permitidos
                .withSockJS();
    }


    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/chat/", "/queue/");
        // El broker se usará para enviar los mensajes a los clientes
        registry.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void configureWebSocketTransport(WebSocketTransportRegistration registry) {
        registry.setMessageSizeLimit(1048576);  // Aumentar el límite de tamaño del mensaje STOMP (por ejemplo, 1MB)
        registry.setSendBufferSizeLimit(1048576); // Aumentar el tamaño del buffer para enviar los mensajes
    }
}