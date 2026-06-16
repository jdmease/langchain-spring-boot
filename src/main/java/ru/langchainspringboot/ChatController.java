package ru.langchainspringboot;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChatController {

    private final Assistant assistant;

    @PostMapping("/call")
    ResponseEntity<String> doCall(
            @RequestBody ChatDto chatDto
    ) {
        return ResponseEntity.ok(assistant.doChat(chatDto.userRequest()));
    }
}
