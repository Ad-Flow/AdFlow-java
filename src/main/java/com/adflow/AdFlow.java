package com.adflow;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.time.Duration;
import java.util.Random;

public class AdFlow {
    private final String apiKey;
    private final String baseUrl;
    private final HttpClient client;
    private final Random random;

    public AdFlow(String apiKey) {
        this(apiKey, "http://144.31.199.202:5000/api");
    }

    public AdFlow(String apiKey, String baseUrl) {
        this.apiKey = apiKey;
        this.baseUrl = baseUrl;
        this.client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();
        this.random = new Random();
    }

    // ===== ПОЛУЧИТЬ РЕКЛАМУ (JSON) =====
    public String getAd() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/ad"))
                .header("X-API-Key", apiKey)
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    // ===== ПОЛУЧИТЬ РЕКЛАМУ (HTML-КАРТОЧКА) =====
    public String getAdHtml() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/ad/html"))
                .header("X-API-Key", apiKey)
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    // ===== ОТСЛЕДИТЬ КЛИК =====
    public void click(int adId) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/click/" + adId))
                .header("X-API-Key", apiKey)
                .GET()
                .build();

        client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    // ===== СТАТИСТИКА =====
    public String getStats() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/stats"))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    // ===== СОЗДАТЬ РЕКЛАМУ =====
    public String createAd(String title, String text, String link, String icon) throws Exception {
        String json = String.format(
                "{\"title\":\"%s\",\"text\":\"%s\",\"link\":\"%s\",\"icon\":\"%s\"}",
                escapeJson(title), escapeJson(text), escapeJson(link), escapeJson(icon)
        );

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/ads/create"))
                .header("X-API-Key", apiKey)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    private String escapeJson(String s) {
        return s.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n");
    }
}
