package ru.langchainspringboot;

import dev.langchain4j.mcp.McpToolProvider;
import dev.langchain4j.mcp.client.DefaultMcpClient;
import dev.langchain4j.mcp.client.McpClient;
import dev.langchain4j.mcp.client.transport.http.StreamableHttpMcpTransport;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.service.AiServices;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Configuration
public class ChatModelConfiguration {
    @Value("${langchain4j.chat-model.ollama.base-url}")
    private String baseUrl;

    @Value("${langchain4j.chat-model.ollama.model-name}")
    private String modelName;

    @Value("${langchain4j.mcp.url}")
    private String mcpUrl;

    @Value("${langchain4j.mcp.api-key}")
    private String mcpApiKey;

    @Bean
    ChatModel chatModel() {
        return OllamaChatModel.builder()
                .modelName(modelName)
                .baseUrl(baseUrl)
                .logResponses(true)
                .logRequests(true)
                .build();
    }

    @Bean
    Assistant assistant(ChatModel chatModel, McpToolProvider mcpToolProvider) {
        return AiServices.builder(Assistant.class)
                .chatModel(chatModel)
                .toolProvider(mcpToolProvider)
                .build();
    }

    @Bean
    McpToolProvider mcpToolProvider(McpClient mcpClient) {
        return McpToolProvider.builder()
                .mcpClients(mcpClient)
                .build();
    }

    @Bean
    McpClient mcpClient() {
        StreamableHttpMcpTransport transport = new StreamableHttpMcpTransport.Builder()
                .customHeaders(Map.of(AUTHORIZATION, mcpApiKey))
                .url(mcpUrl)
                .logRequests(true)
                .logResponses(true)
                .build();
        return DefaultMcpClient.builder()
                .transport(transport)
                .build();
    }
}
