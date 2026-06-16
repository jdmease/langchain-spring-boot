package ru.langchainspringboot;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatModelConfiguration {
    @Value("${langchain4j.chat-model.ollama.base-url}")
    private String baseUrl;

    @Value("${langchain4j.chat-model.ollama.model-name}")
    private String modelName;

    @Bean
    ChatModel chatModel() {
        return OllamaChatModel.builder()
                .modelName(modelName)
                .baseUrl(baseUrl)
                .logResponses(true)
                .logRequests(true)
                .build();
    }
}
