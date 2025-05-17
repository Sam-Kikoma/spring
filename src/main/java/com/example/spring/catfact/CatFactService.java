package com.example.spring.catfact;

import com.example.spring.Query;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class CatFactService implements Query<Integer,CatFactDto> {

    private final RestTemplate restTemplate;

    private final String url = "https://catfact.ninja/fact";
    private final String MAX_LENGTH="max_length";

    public CatFactService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseEntity<CatFactDto> execute(Integer input) {
        URI uri = UriComponentsBuilder
                .fromHttpUrl(url)
                .queryParam(MAX_LENGTH,input)
                .build()
                .toUri();
        //headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<CatFactResponse> response = restTemplate.exchange(uri, HttpMethod.GET,entity, CatFactResponse.class);
;
        CatFactDto catFactDto = new CatFactDto(response.getBody().getFact());
        return ResponseEntity.ok(catFactDto);
    }
}
