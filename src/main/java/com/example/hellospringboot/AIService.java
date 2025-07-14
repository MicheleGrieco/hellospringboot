package com.example.hellospringboot;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AIService {
    
    /** The chat client used to interact with the AI service. */
    private final ChatClient chatClient;
    
    /**
     * Constructor for AIService.
     * 
     * @param builder the ChatClient.Builder used to create the chat client
     */
    public AIService(ChatClient.Builder builder) {
        chatClient = builder.build();
    }

    /**
     * Sends a prompt to the AI service and retrieves the response.
     * 
     * @param prompt the prompt to send to the AI service
     * @return the response from the AI service
     */
    public String chat(String prompt) {
        return chatClient.prompt(prompt).call().content();
    }
}
