package ru.langchainspringboot;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;

public interface Assistant {

    @SystemMessage("Ты опытный программист специализирующийся на проведении код ревью.")
    String doChat(@UserMessage String userMessage);
}
