package org.sid.aichatbot.openai;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class OpenAIClient {
    private final WebClient.Builder webClientBuilder;

    @Value("${openai.api.key:}")
    private String apiKey;

    @Value("${openai.model:gpt-4o-mini}")
    private String model;

    public Mono<String> chat(String systemPrompt, String userMessage) {
        WebClient client = webClientBuilder.baseUrl("https://api.openai.com").build();
        Map<String, Object> payload = Map.of(
                "model", model,
                "messages", List.of(
                        Map.of("role", "system", "content", systemPrompt),
                        Map.of("role", "user", "content", userMessage)
                )
        );
        return client.post()
                .uri("/v1/chat/completions")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(payload))
                .retrieve()
                .bodyToMono(Map.class)
                .map(resp -> {
                    try {
                        List<Map<String, Object>> choices = (List<Map<String, Object>>) resp.get("choices");
                        Map<String, Object> choice0 = choices != null && !choices.isEmpty() ? choices.get(0) : Map.of();
                        Map<String, Object> msg = (Map<String, Object>) choice0.get("message");
                        Object content = msg != null ? msg.get("content") : "";
                        return content != null ? content.toString() : "";
                    } catch (Exception e) {
                        return "";
                    }
                });
    }
}
