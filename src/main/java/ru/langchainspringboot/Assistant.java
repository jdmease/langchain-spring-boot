package ru.langchainspringboot;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.spring.AiService;

@AiService
public interface Assistant {

    @SystemMessage("Ты опытный программист специализирующийся на проведении код ревью.")
    String doChat(String userMessage);
}
