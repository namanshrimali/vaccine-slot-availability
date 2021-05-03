package com.cowin.vaccineNotification.client;

import com.cowin.vaccineNotification.pojo.CowinResponsePojo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class CowinClientImpl {
    @Value("${date}")
    String date;
    @Value("${pin}")
    String pin;
    public Mono<CowinResponsePojo> getCowinVaccineAvailabilityData() {
        WebClient webClient = WebClient.builder()
                .baseUrl(
                        String.format("https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByPin?pincode=%s&date=%s", pin, date)
                )
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        return webClient
                .get()
                .retrieve()
                .bodyToMono(CowinResponsePojo.class)
                .onErrorResume(throwable -> null);
    }
}
